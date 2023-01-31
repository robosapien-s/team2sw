package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedCorner implements IAutonomousRunner {


    TrajectorySequence trajectory1;
    Trajectory trajectory2;
    Trajectory trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;
    Telemetry telemetry;
    int signalInt;


    public RedCorner(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(10, -60, Math.toRadians(90));

        drive.setPoseEstimate(startPose);


        signalInt = wrapper.initDetection.signalInt;

        telemetry.addData("Signal: ", signalInt);


        trajectory1 = drive.trajectorySequenceBuilder(new Pose2d(36, -60, Math.toRadians(90)))
                .turn(Math.toRadians(45))
                .lineToLinearHeading(new Pose2d(60,-60,Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(60,-12,Math.toRadians(90)))
                .turn(Math.toRadians(-90))
                .lineToLinearHeading(new Pose2d(48,-12,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,-12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,-12,Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(60,-12,Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(24,-12,Math.toRadians(90)))

                /*.turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))
                .forward(30)
                .turn(Math.toRadians(90))*/
                .build();

        trajectory1.start();

    }


}