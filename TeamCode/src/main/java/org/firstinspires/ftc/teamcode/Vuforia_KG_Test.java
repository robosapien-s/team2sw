package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous (name = "Vuforia_KG_Test")
public class Vuforia_KG_Test extends LinearOpMode {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;
    VuforiaTrackable target;
    VuforiaTrackableDefaultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGLMatrix phoneLocation;


    private final String VUFORIA_KEY = "AUMliK3/////AAABmagADgA89U2Xsq+lLQdjtTZBktfma3T+X+DW3XLkPLxA8jT0n/1w4SzLeSi9k1B+He06svc/yzd6LkUPG7r1EJTRzjI9EsR7Xke6VDo+zDlBZc7ZFPoKs7Gr2t0kZxVa9m2oYy0LavCObTihphRpCmnTsKqmlW78PGVv3YNWznbpX2Q9ociNgqXuidC3pSuCRXoffEgI7m7B5WaWJNpAFFWovr1hEPEophwiQnup4xmWm76MvDO93PTaHaztapNZen7qoyj9l1SLvyYUXDFTDm5l3C2mrjzKsbrEfIEIT/RW7frXZwKjCKrM2QXUecvOrPgLIPLYaYa7fLcv0oOXMMC6yzE80Hgja2RW1NaGTDjv";

    @Override
    public void runOpMode() throws InterruptedException {

        setupVuforia();

        lastKnownLocation = createMatrix(0, 0, 0, 0, 0, 0);

        waitForStart();

        visionTargets.activate();

        while(opModeIsActive()) {
            OpenGLMatrix latestLocation = listener.getUpdatedRobotLocation();

            if(latestLocation != null)
                lastKnownLocation = latestLocation;

            telemetry.addData("Tracking " + target.getName(), listener.isVisible());
            telemetry.addData("Last Known Location", formatMatrix(lastKnownLocation));
            telemetry.update();
            idle();
        }
    }

    public void setupVuforia() {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);

        visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("FTC_2016-17");


        target = visionTargets.get(0);
        target.setName("Wheels Target");
        target.setLocation(createMatrix(0, 0, 0, 0, 0, 0));

        phoneLocation = createMatrix(0, 0, 0, 0, 0, 0);

        listener = (VuforiaTrackableDefaultListener) target.getListener();
        listener.setPhoneInformation(phoneLocation, parameters.cameraDirection);
    }

    public OpenGLMatrix createMatrix(float x, float y, float z, float u, float v, float w) {
        return OpenGLMatrix.translation(x, y, z).
                multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, u, v, w));
    }

    public  String formatMatrix(OpenGLMatrix matrix){
        return matrix.formatAsTransform();
    }
}
