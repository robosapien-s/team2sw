package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;

public class RightMiddle implements IAutonomousRunner {


    TrajectorySequence trajectory1;
    TrajectorySequence trajectory2;
    TrajectorySequence trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ExtensionWrapperDD armWrapper;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;
    Telemetry telemetry;
    int signalInt;
    HardwareMap hardwareMap;
    DrivingWrapperCompetition drivingWrapper;

    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;

    float robotHalfLength = 7;
    float halfTile = 12;


    public RightMiddle(SampleMecanumDrive inDrive, ExtensionWrapperDD inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry, HardwareMap inHardwareMap) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
        telemetry = inTelemetry;
        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes

        //Motor 0
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft"); //setting up the motors with hardwaremaps
        //Motor 2
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        //Motor 1
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        //Motor 3
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");

    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(36, 62, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);


        signalInt = wrapper.initDetection.signalInt;

        telemetry.addData("Int = ", signalInt);
        telemetry.update();


        trajectory1 = drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.rightServo.setPosition(.80);
                    armWrapper.leftServo.setPosition(.20);
                })

                .lineToLinearHeading(new Pose2d(36,48,Math.toRadians(180)))

                .lineToLinearHeading(new Pose2d(36,0,Math.toRadians(180)))

                //drop 1

                .lineToLinearHeading(new Pose2d(36,12,Math.toRadians(0)))
                // open and go to height
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                //grab then adjust height
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))

                //drop 2
                .lineToLinearHeading(new Pose2d(36,12,Math.toRadians(0)))
                // open and go to height
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                //grab then adjust height
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))

                //drop 3
                .lineToLinearHeading(new Pose2d(36,12,Math.toRadians(0)))
                // open and go to height
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                //grab then adjust height
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))

                //drop 4
                .lineToLinearHeading(new Pose2d(36,12,Math.toRadians(0)))
                // open and go to height
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                //grab then adjust height
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))

                //drop 5
                .lineToLinearHeading(new Pose2d(36,12,Math.toRadians(0)))
                // open and go to height
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                //grab then adjust height
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))
                .build();


        if(signalInt==0){
            goForward(.4);
            linearOpMode.sleep(1500);
            goForward(0);
            linearOpMode.sleep(100);
            goRight(.4);
            linearOpMode.sleep(1900);
            goForward(0);


            //drive.followTrajectorySequence(trajectory3);
        }else if(signalInt==1){
            goForward(.4);
            linearOpMode.sleep(1500);
            goForward(0);
            //drive.followTrajectorySequence(trajectory2);
        }else{
            goForward(.4);
            linearOpMode.sleep(1500);
            goForward(0);
            linearOpMode.sleep(100);
            goRight(-.4);
            linearOpMode.sleep(1900);
            goForward(0);
            //drive.followTrajectorySequence(trajectory1);
        }
        linearOpMode.sleep(10000);


    }

    void goForward(double speed){
        motorFrontLeft.setPower(speed);
        motorBackLeft.setPower(speed);
        motorFrontRight.setPower(speed);
        motorBackRight.setPower(speed);
    }

    void goRight(double speed){
        motorFrontLeft.setPower(speed);
        motorBackLeft.setPower(-speed);
        motorFrontRight.setPower(-speed);
        motorBackRight.setPower(speed);
    }




}