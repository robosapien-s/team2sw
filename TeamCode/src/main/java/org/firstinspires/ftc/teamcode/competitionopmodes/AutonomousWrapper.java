package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autonomous.BlueCarouselRunnerV2;
import org.firstinspires.ftc.teamcode.autonomous.BlueCorner;
import org.firstinspires.ftc.teamcode.autonomous.BlueHomeRunner;
import org.firstinspires.ftc.teamcode.autonomous.BlueMiddle;
import org.firstinspires.ftc.teamcode.autonomous.IAutonomousRunner;
import org.firstinspires.ftc.teamcode.autonomous.RedCarouselRunnerV2;
import org.firstinspires.ftc.teamcode.autonomous.RedCorner;
import org.firstinspires.ftc.teamcode.autonomous.RedHomeRunnerV1;
import org.firstinspires.ftc.teamcode.autonomous.RedMiddle;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.testopmodes.AprilTagAutonomousInitDetectionExample;

import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;


public class AutonomousWrapper {



    SampleMecanumDrive roadRunner;
    DrivingWrapper driver;
    ArmWrapper arm;
    double speed = .25; //Unused
    double rotSpeed = .25; //Unused
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public DcMotor crMotor;

    public OpenCvDetection OpenCVWrapper;
    public AprilTagAutonomousInitDetectionExample initDetection;

    SampleMecanumDrive drive;

    IAutonomousRunner runner = null;

    int signalInt;



    public AutonomousWrapper(HardwareMap map, Telemetry inTelemetry, VuforiaWebcamLocalization.ELocation location,LinearOpMode opMode){
        hardwareMap = map;
        telemetry = inTelemetry;
        roadRunner = new SampleMecanumDrive(hardwareMap);
//        crMotor  = hardwareMap.get(DcMotor.class, "carouselMotor");
        init(location,opMode);
    }

    public void init(VuforiaWebcamLocalization.ELocation location,LinearOpMode opMode){



        driver = new DrivingWrapper(hardwareMap,telemetry);
//        arm = new ArmWrapper(hardwareMap, telemetry);


        initDetection = new AprilTagAutonomousInitDetectionExample(telemetry,hardwareMap,this,opMode);
        OpenCVWrapper = new OpenCvDetection(telemetry, hardwareMap);


//        OpenCVWrapper.init(true);
        initDetection.run(true);

        runner = null;

        drive = new SampleMecanumDrive(hardwareMap);

        telemetry.addData("barcode", OpenCVWrapper.barcodeInt);
        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
            runner = new BlueCarouselRunnerV2(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            runner = new BlueHomeRunner(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUECORNER) {
            runner = new BlueCorner(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEMIDDLE) {
            runner = new BlueMiddle(drive, arm, opMode, this, telemetry);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            runner = new RedCarouselRunnerV2(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            runner = new RedHomeRunnerV1(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCORNER) {
            runner = new RedCorner(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDMIDDLE) {
            runner = new RedMiddle(drive, arm, opMode, this);
        }
    }
    public void RunAutonomous(VuforiaWebcamLocalization.ELocation location, LinearOpMode opMode) {


//        telemetry.addData("Location" ,location.toString());
//        telemetry.addData("Barcode", OpenCVWrapper.barcodeInt);


        telemetry.update();


        runner.run();
    }
}
