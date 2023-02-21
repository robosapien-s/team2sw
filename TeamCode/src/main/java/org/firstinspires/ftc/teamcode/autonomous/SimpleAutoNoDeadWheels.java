package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;

public class SimpleAutoNoDeadWheels implements IAutonomousRunner {


    TrajectorySequence trajectory1;
    TrajectorySequence trajectory2;
    TrajectorySequence trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ExtensionArmWrapper armWrapper;
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


    public RedMiddle(SampleMecanumDrive inDrive, ExtensionArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry, HardwareMap inHardwareMap) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
        telemetry = inTelemetry;
        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes
        drivingWrapper = new DrivingWrapperCompetition(hardwareMap, telemetry);

    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(36, 62, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);


        signalInt = wrapper.initDetection.signalInt;

        telemetry.addData("Int = ", signalInt);
        telemetry.update();

        trajectory1 = drive.trajectorySequenceBuilder(new Pose2d(36, 62, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.5);
                })
                .waitSeconds(1)
                /*.turn(Math.toRadians(45))
                .lineToLinearHeading(new Pose2d(60,60,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(-90)))
                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(-90)))

                .turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))*/
                .lineToLinearHeading(new Pose2d(36,36,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,36,Math.toRadians(-90)))
                .build();
        trajectory2 = drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.5);
                })
                .waitSeconds(1)
                /*.turn(Math.toRadians(45))
                .lineToLinearHeading(new Pose2d(60,60,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(-90)))
                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(-90)))

                /*.turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))*/
                .lineToLinearHeading(new Pose2d(36,36,Math.toRadians(-90)))
                .build();
        trajectory3 = drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(-90)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.5);
                })
                .waitSeconds(1)
                /*.turn(Math.toRadians(45))
                .lineToLinearHeading(new Pose2d(60,60,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(-90)))
                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(48,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(-90)))

                /*.turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))*/
                .lineToLinearHeading(new Pose2d(36,36,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(12,36,Math.toRadians(-90)))
                .build();

        if(signalInt==0){
            AutonomousDrive(DrivingWrapperCompetition.Direction.FORWARD,1,.2);
            //drive.followTrajectorySequence(trajectory3);
        }else if(signalInt==1){
            drive.followTrajectorySequence(trajectory2);
        }else{
            drive.followTrajectorySequence(trajectory1);
        }


    }




}