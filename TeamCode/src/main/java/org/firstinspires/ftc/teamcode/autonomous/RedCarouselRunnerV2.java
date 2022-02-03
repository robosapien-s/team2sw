package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedCarouselRunnerV2 implements IAutonomousRunner {


    Trajectory trajectory1;
    Trajectory trajectory2;
    Trajectory trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    int levelInt = 3;
    LinearOpMode linearOpMode;


    public RedCarouselRunnerV2(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(32, 60, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        armWrapper.init(true);
        trajectory1 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(-18,34),Math.toRadians(-135))
                .build();

        trajectory2 = drive.trajectoryBuilder(trajectory1.end()).lineToLinearHeading(new Pose2d(-64,40,0)).build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end(), 6, 5).strafeLeft(5).build();

        trajectory4 = drive.trajectoryBuilder(trajectory3.end(), 6, 5).strafeRight(5).build();


        armWrapper.SetLevel(3);
        drive.followTrajectory(trajectory1);
        armWrapper.Intake(1);
        linearOpMode.sleep(1000);
        armWrapper.StopIntake();
        drive.followTrajectory(trajectory2);

        armWrapper.IntakeReverse(1);
        armWrapper.SetLevel(0);
        drive.followTrajectory(trajectory3);
        drive.followTrajectory(trajectory4);


    }

}