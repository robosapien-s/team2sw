package org.firstinspires.ftc.teamcode.wrappers;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XZY;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Vuforia;

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

public class VuforiaWrapper {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTargets;

    ArrayList<VuforiaTrackable> visionTargetList = new ArrayList<VuforiaTrackable>();

    ArrayList<String> names = new ArrayList<String>();

    VuforiaTrackableDefaultListener listener;

    ArrayList<OpenGLMatrix> lastKnownLocations = new ArrayList<OpenGLMatrix>();
    OpenGLMatrix cameraLocation;

    Telemetry telemetry;

    HardwareMap hardwareMap;


    private static final String VUFORIA_KEY = "AUMliK3/////AAABmagADgA89U2Xsq+lLQdjtTZBktfma3T+X+DW3XLkPLxA8jT0n/1w4SzLeSi9k1B+He06svc/yzd6LkUPG7r1EJTRzjI9EsR7Xke6VDo+zDlBZc7ZFPoKs7Gr2t0kZxVa9m2oYy0LavCObTihphRpCmnTsKqmlW78PGVv3YNWznbpX2Q9ociNgqXuidC3pSuCRXoffEgI7m7B5WaWJNpAFFWovr1hEPEophwiQnup4xmWm76MvDO93PTaHaztapNZen7qoyj9l1SLvyYUXDFTDm5l3C2mrjzKsbrEfIEIT/RW7frXZwKjCKrM2QXUecvOrPgLIPLYaYa7fLcv0oOXMMC6yzE80Hgja2RW1NaGTDjv";


    public OpenGLMatrix createMatrix(float x, float y, float z, float u, float v,float w){
        return  OpenGLMatrix.translation(x,y,z).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, u, v, w));
    }

    public boolean init(HardwareMap inHardwareMap, Telemetry inTelemetry){


        hardwareMap = inHardwareMap;

        telemetry = inTelemetry;

        Vuforia.init();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        parameters =  new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        parameters.useExtendedTracking = true;

        vuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters);

        return checkVuforia();

    }



    public String formatMatrix(OpenGLMatrix matrix){
        return matrix.formatAsTransform();
    }

    public boolean checkVuforia(){
        if(Vuforia.isInitialized())
        {
            telemetry.addData("Initialized", "true");
            visionTargets = vuforiaLocalizer.loadTrackablesFromAsset("FreightFrenzy");
            visionTargets.activate();

            names.add("Blue Storage");
            names.add("Blue Alliance Wall");
            names.add("Red Storage");
            names.add("Red Alliance Wall");



            for (int i=0; i< names.size(); i++){

                lastKnownLocations.add(createMatrix(0,0,0,0,0,0));

                VuforiaTrackable target = visionTargets.get(i);

                if (target != null){
                    visionTargetList.add(target);

                    visionTargetList.get(i).setName(names.get(i));

                    telemetry.addData(visionTargetList.get(i).getName(), Integer.toString(i));

                    visionTargetList.get(i).setLocation(createMatrix(0,500,0,90,0,90));

                    listener = (VuforiaTrackableDefaultListener) visionTargetList.get(i).getListener();

                    telemetry.addData("visiontarget", visionTargetList.get(i).getName());
                }else {
                    telemetry.addData("visiontarget", "null");
                }
                telemetry.update();


            }


            cameraLocation = createMatrix(0,225,0,90,0,0);

            listener.setPhoneInformation(cameraLocation,parameters.cameraDirection);

        }
        else {
            telemetry.addData("Initialized", "false");
        }

        telemetry.update();

        return Vuforia.isInitialized();
    }

    public ArrayList<OpenGLMatrix> getMetrics(){

        for (int i=0; i< visionTargetList.size(); i++) {

            VuforiaTrackable trackable = visionTargetList.get(i);
            VuforiaTrackableDefaultListener trackableListener = (VuforiaTrackableDefaultListener)trackable.getListener();

            OpenGLMatrix pose = trackableListener.getFtcCameraFromTarget();


            if(pose != lastKnownLocations.get(i) && pose != null){

                lastKnownLocations.set(i, pose);

            }
        }


        return lastKnownLocations;
    }


//    public OpenGLMatrix getPlayerLocation(OpenGLMatrix cameraLocation, OpenGLMatrix targetLocation, VuforiaTrackable target){
//
//    }

    public OpenGLMatrix getBlueStorage(){
        return OpenGLMatrix.translation(-1828.8f, 914.4f, 152.4f).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90));
    }
    public OpenGLMatrix getBlueAllianceWall(){
        return OpenGLMatrix.translation(304.8f, 1828.8f, 152.4f).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0));
    }
    public OpenGLMatrix getRedStorage(){
        return OpenGLMatrix.translation(-1828.8f, -914.4f, 152.4f).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90));
    }
    public OpenGLMatrix getRedAllianceWall(){
        return OpenGLMatrix.translation(304.8f, -1828.8f, 152.4f).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0));
    }


}
