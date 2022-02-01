package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.autonomous.IAutonomousRunner;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class BlueHomeRunner implements IAutonomousRunner {


    Trajectory trajectory1;
    Trajectory trajectory2;
    Trajectory trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    int levelInt = 3;


    public BlueHomeRunner(SampleMecanumDrive inDrive, ArmWrapper inArm) {
        drive = inDrive;
        armWrapper = inArm;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(10, 60, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        armWrapper.init(true);
        trajectory1 = drive.trajectoryBuilder(new Pose2d(10,60, Math.toRadians(-90)))
                .splineTo(new Vector2d(0,40),Math.toRadians(-135))
                .build();

        trajectory2 = drive.trajectoryBuilder(trajectory1.end()).lineToLinearHeading(new Pose2d(10,58,0)).build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end()).lineTo(new Vector2d(55,58)).build();

        trajectory4 = drive.trajectoryBuilder(trajectory3.end()).lineTo(new Vector2d(10,58)).build();

        trajectory5 = drive.trajectoryBuilder(trajectory4.end()).lineToLinearHeading(new Pose2d(0,40,Math.toRadians(-135))).build();



        //armWrapper.SetLevel(levelInt);
        drive.followTrajectory(trajectory1);
        armWrapper.IntakeReverse(1);
        drive.followTrajectory(trajectory2);
        armWrapper.Intake(1);
        armWrapper.SetLevel(1);
        drive.followTrajectory(trajectory3);
        armWrapper.StopIntake();

        drive.followTrajectory(trajectory4);

        for (int i = 0; i < 3; i++) {
            PickupDrop();
        }

    }

    public void PickupDrop(){
        drive.followTrajectory(trajectory5);
        armWrapper.IntakeReverse(1);
        drive.followTrajectory(trajectory2);
        drive.followTrajectory(trajectory3);
        armWrapper.Intake(1);
        armWrapper.SetLevel(1);
        drive.followTrajectory(trajectory4);
        armWrapper.SetLevel(levelInt);
    }
}