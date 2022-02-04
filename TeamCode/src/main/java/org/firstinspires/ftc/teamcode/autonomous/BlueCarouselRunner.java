package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class BlueCarouselRunner implements IAutonomousRunner {


    Trajectory trajectory1;
    Trajectory trajectory2;
    Trajectory trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;
    int levelInt = 3;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;


    public BlueCarouselRunner(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(32, 60, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        if (wrapper.OpenCVWrapper.barcodeInt==0){
            wrapper.OpenCVWrapper.barcodeInt=3;
        }
        armWrapper.init(true);
        trajectory1 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(2,38),Math.toRadians(-135))
                .build();

        trajectory2 = drive.trajectoryBuilder(trajectory1.end()).splineTo(new Vector2d(-50,50),-45).build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end(), 12, 10).splineTo(new Vector2d(-60,36),180).build();



        armWrapper.SetLevel(3);
        drive.followTrajectory(trajectory1);
        armWrapper.Intake(1);
        linearOpMode.sleep(1000);
        armWrapper.StopIntake();
        drive.followTrajectory(trajectory2);

        armWrapper.IntakeReverse(1);
        armWrapper.SetLevel(0);
        drive.followTrajectory(trajectory3);


    }

}