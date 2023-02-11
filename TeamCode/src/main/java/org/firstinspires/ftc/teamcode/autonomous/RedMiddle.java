package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedMiddle implements IAutonomousRunner {


    TrajectorySequence trajectory1;
    TrajectorySequence trajectory2;
    TrajectorySequence trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;
    Telemetry telemetry;
    int signalInt;

    float robotHalfLength = 7;
    float halfTile = 12;


    public RedMiddle(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper) {
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
        trajectory1 = drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                .forward((halfTile*3) - robotHalfLength)
                .strafeLeft(halfTile*2)
                .build();
        trajectory2 = drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                .forward((halfTile*3) - robotHalfLength)
                .build();
        trajectory3 = drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                .forward((halfTile*3) - robotHalfLength)
                .strafeLeft(-halfTile*2)
                .build();

        if(signalInt == 1 ){
            trajectory1.start();
        }else if(signalInt == 2){
            trajectory2.start();
        }else {
            trajectory3.start();
        }

    }


}