package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.teamcode.autonomous.IAutonomousRunner;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedHomeRunner implements IAutonomousRunner {


    Trajectory trajectory1;
    Trajectory trajectory2;
    Trajectory trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    int levelInt = 3;
    LinearOpMode linearOpMode;


    public RedHomeRunner(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(10, -60, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        armWrapper.init(true);
        trajectory1 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(2,-38),Math.toRadians(-135))
                .build();

        trajectory2 = drive.trajectoryBuilder(trajectory1.end())
                .lineToLinearHeading(new Pose2d(10,-62.5,0))
                .build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end(), 12, 10)
                .lineTo(new Vector2d(50,-62.5))
                .build();

        trajectory4 = drive.trajectoryBuilder(trajectory3.end())
                .lineTo(new Vector2d(10,-62.5))
                .build();

        trajectory5 = drive.trajectoryBuilder(trajectory4.end())
                .lineToLinearHeading(new Pose2d(0,-35,Math.toRadians(-135)))
                .build();



        armWrapper.SetLevel(3);
        drive.followTrajectory(trajectory1);
        armWrapper.Intake(1);
        linearOpMode.sleep(1000);
        armWrapper.StopIntake();
        drive.followTrajectory(trajectory2);

        armWrapper.IntakeReverse(1);
        armWrapper.SetLevel(0);
        drive.followTrajectory(trajectory3);
        armWrapper.StopIntake();

        drive.followTrajectory(trajectory4);

        for (int i = 0; i < 3; i++) {
            PickupDrop();
        }

    }

    public void PickupDrop(){
        drive.followTrajectory(trajectory5);
        armWrapper.Intake(1);
        drive.followTrajectory(trajectory2);
        drive.followTrajectory(trajectory3);

        armWrapper.IntakeReverse(1);
        armWrapper.SetLevel(0);
        drive.followTrajectory(trajectory4);
        armWrapper.SetLevel(3);
    }
}