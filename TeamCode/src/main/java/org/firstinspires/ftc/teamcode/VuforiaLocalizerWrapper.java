package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XZY;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Vuforia;

import org.checkerframework.checker.i18n.qual.LocalizableKey;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VuforiaLocalizerWrapper implements Localizer {

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;
    Telemetry telemetry;

    HardwareMap hardwareMap;

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;

    ArrayList<VuforiaTrackable> allTrackables;

    public OpenGLMatrix lastLocation = OpenGLMatrix.identityMatrix();

    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = 6 * mmPerInch;          // the height of the center of the target image above the floor
    private static final float halfField        = 72 * mmPerInch;
    private static final float halfTile         = 12 * mmPerInch;
    private static final float oneAndHalfTile   = 36 * mmPerInch;

    private final String VUFORIA_KEY = "AUMliK3/////AAABmagADgA89U2Xsq+lLQdjtTZBktfma3T+X+DW3XLkPLxA8jT0n/1w4SzLeSi9k1B+He06svc/yzd6LkUPG7r1EJTRzjI9EsR7Xke6VDo+zDlBZc7ZFPoKs7Gr2t0kZxVa9m2oYy0LavCObTihphRpCmnTsKqmlW78PGVv3YNWznbpX2Q9ociNgqXuidC3pSuCRXoffEgI7m7B5WaWJNpAFFWovr1hEPEophwiQnup4xmWm76MvDO93PTaHaztapNZen7qoyj9l1SLvyYUXDFTDm5l3C2mrjzKsbrEfIEIT/RW7frXZwKjCKrM2QXUecvOrPgLIPLYaYa7fLcv0oOXMMC6yzE80Hgja2RW1NaGTDjv";


    public boolean targetVisible;

    boolean isWebcam = false;

    OpenGLMatrix locationInches;

    Pose2d roadRunnerLocation;

    public void init(HardwareMap inHardwareMap, Telemetry inTelemetry){

        hardwareMap = inHardwareMap;

        telemetry = inTelemetry;

        Vuforia.init();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        parameters =  new org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        if(isWebcam){
            parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        }else {
            parameters.cameraDirection   = CAMERA_CHOICE;
        }
        parameters.useExtendedTracking = true;


        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters);
        if(Vuforia.isInitialized()) {
            visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("FreightFrenzy");

            allTrackables = new ArrayList<VuforiaTrackable>();
            allTrackables.addAll(visionTargets);

            visionTargets.activate();



            identifyTarget(0, "Blue Storage",       -halfField,  oneAndHalfTile, mmTargetHeight, 90, 0, 90);
            identifyTarget(1, "Blue Alliance Wall",  halfTile,   halfField,      mmTargetHeight, 90, 0, 0);
            identifyTarget(2, "Red Storage",        -halfField, -oneAndHalfTile, mmTargetHeight, 90, 0, 90);
            identifyTarget(3, "Red Alliance Wall",   halfTile,  -halfField,      mmTargetHeight, 90, 0, 180);
        }

    }




    void    identifyTarget(int targetIndex, String targetName, float dx, float dy, float dz, float rx, float ry, float rz) {
        VuforiaTrackable aTarget = visionTargets.get(targetIndex);
        aTarget.setName(targetName);
        aTarget.setLocation(OpenGLMatrix.translation(dx, dy, dz).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, rx, ry, rz)));
    }

    public OpenGLMatrix getLocation(){
        targetVisible = false;
        if(allTrackables !=null ) {
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    // getUpdatedRobotLocation() will return null if no new information is available since
                    // the last time that call was made, or if the trackable is not currently visible.
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }
            if (targetVisible) {
                // express position (translation) of robot in inches.
                VectorF translation = lastLocation.getTranslation();
                telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

                // express the rotation of the robot in degrees.
                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
                locationInches = createMatrix(translation.get(0), translation.get(1), translation.get(2), rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
            } else {
                telemetry.addData("Visible Target", "none");
            }
            telemetry.update();

        }
        return locationInches;
    }
    public OpenGLMatrix getLocationRaw(){
        targetVisible = false;
        if(allTrackables !=null ) {
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    // getUpdatedRobotLocation() will return null if no new information is available since
                    // the last time that call was made, or if the trackable is not currently visible.
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }
            if (targetVisible) {
                // express position (translation) of robot in inches.
                VectorF translation = lastLocation.getTranslation();
                telemetry.addData("Pos (inches)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

                // express the rotation of the robot in degrees.
                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
                locationInches = createMatrix(translation.get(0), translation.get(1), translation.get(2), rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
            } else {
                telemetry.addData("Visible Target", "none");
            }
            telemetry.update();

        }
        return lastLocation;
    }

    public OpenGLMatrix createMatrix(float x, float y, float z, float u, float v,float w){
        return  OpenGLMatrix.translation(x,y,z).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, u, v, w));
    }

    @NonNull
    @Override
    public Pose2d getPoseEstimate() {
        return roadRunnerLocation;
    }

    @Override
    public void setPoseEstimate(@NonNull Pose2d pose2d) {

    }


    @Override
    public void update() {
        OpenGLMatrix location = getLocationRaw();
        VectorF translation = location.getTranslation();
        roadRunnerLocation = new Pose2d(translation.get(0),translation.get(1),location.getRow(1).get(3));
    }

    @Nullable
    @Override
    public Pose2d getPoseVelocity() {
        return null;
    }
}
