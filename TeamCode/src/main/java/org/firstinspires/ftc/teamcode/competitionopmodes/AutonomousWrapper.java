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
import org.firstinspires.ftc.teamcode.autonomous.LeftMiddle;
import org.firstinspires.ftc.teamcode.autonomous.LeftTall;
import org.firstinspires.ftc.teamcode.autonomous.RedCarouselRunnerV2;
import org.firstinspires.ftc.teamcode.autonomous.RedCorner;
import org.firstinspires.ftc.teamcode.autonomous.RedHomeRunnerV1;
import org.firstinspires.ftc.teamcode.autonomous.RedMiddle;
import org.firstinspires.ftc.teamcode.autonomous.RightMiddle;
import org.firstinspires.ftc.teamcode.autonomous.RightTall;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.testopmodes.AprilTagAutonomousInitDetectionExample;

import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;


public class AutonomousWrapper {



    SampleMecanumDrive roadRunner;
    DrivingWrapper driver;
    ExtensionWrapperDD armA;
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
        armA = new ExtensionWrapperDD(hardwareMap, telemetry);

        armA.rightServo.setPosition(.9);
        armA.leftServo.setPosition(0.1);


        ArmWrapper arm = null;

        initDetection = new AprilTagAutonomousInitDetectionExample(telemetry,hardwareMap,this,opMode);
        OpenCVWrapper = new OpenCvDetection(telemetry, hardwareMap);


//        OpenCVWrapper.init(true);
        initDetection.run(true);

        runner = null;

        drive = new SampleMecanumDrive(hardwareMap);

        telemetry.addData("barcode", OpenCVWrapper.barcodeInt);
        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
            runner = new LeftMiddle(drive, armA, opMode, this, telemetry,hardwareMap);
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            runner = new RightTall(drive, armA, opMode, this, telemetry,hardwareMap);
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUECORNER) {
            runner = new BlueCorner(drive, armA, opMode, this,telemetry,hardwareMap);//Actually just parking Autonomous (Non deadwheel)
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEMIDDLE) {
            runner = new BlueMiddle(drive, armA, opMode, this, telemetry);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            runner = new LeftTall(drive, armA, opMode, this, telemetry,hardwareMap);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            runner = new RedHomeRunnerV1(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCORNER) {
            runner = new RedCorner(drive, arm, opMode, this);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDMIDDLE) {
            runner = new RightMiddle(drive, armA, opMode, this, telemetry,hardwareMap);
        }
    }
    public void RunAutonomous() {


//        telemetry.addData("Location" ,location.toString());
//        telemetry.addData("Barcode", OpenCVWrapper.barcodeInt);


        telemetry.update();


        runner.run();
    }
}
