package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedHomeRunnerV2 implements IAutonomousRunner {


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
    Telemetry telemetry;

    double Rotation = -240;



    public RedHomeRunnerV2(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, double InRot) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
        Rotation = InRot;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(10, -60, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        armWrapper.init(true);

        levelInt = wrapper.OpenCVWrapper.barcodeInt - 1;




        if (levelInt<=0){
            levelInt=3;
        }
        trajectory1 = drive.trajectoryBuilder(startPose)
                .splineTo(new Vector2d(5,-38),Math.toRadians(120))
                .build();

        trajectory2 = drive.trajectoryBuilder(trajectory1.end(),20, 18)
                .lineToLinearHeading(new Pose2d(10,-60,0))
                .build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end())
                .lineTo(new Vector2d(45,-60))
                .build();

        trajectory4 = drive.trajectoryBuilder(trajectory3.end())
                .lineTo(new Vector2d(20,-60))
                .build();

        trajectory5 = drive.trajectoryBuilder(trajectory4.end())
                .splineTo(new Vector2d(0,-37.5), Math.toRadians(Rotation))
                .build();



        armWrapper.SetLevel(levelInt);
        drive.followTrajectory(trajectory1);
        armWrapper.Intake(.25);
        linearOpMode.sleep(1000);
        armWrapper.StopIntake();
        drive.followTrajectory(trajectory2);

        armWrapper.SetLevel(0);
        armWrapper.IntakeReverse(1);

//        armWrapper.SetLevel(0);
//        armWrapper.IntakeReverse(1);
//        linearOpMode.sleep(2000);
//        armWrapper.Intake(.2);
//        linearOpMode.sleep(500);
//        armWrapper.StopIntake();

        drive.followTrajectory(trajectory3);

        armWrapper.StopIntake();

        drive.followTrajectory(trajectory4);
        armWrapper.SetLevel(2);

//        Testing
        drive.followTrajectory(trajectory5);
//        Testing

        for (int i = 0; i < 2; i++) {
//            PickupDrop(i>=1);
        }
        armWrapper.ResetArm();
    }

    public void PickupDrop(boolean stay){
        drive.followTrajectory(trajectory5);
        armWrapper.Intake(.25);
        linearOpMode.sleep(1000);
        drive.followTrajectory(trajectory2);
        armWrapper.SetLevel(0);
        armWrapper.IntakeReverse(1);
        drive.followTrajectory(trajectory3);
        if(!stay){
            drive.followTrajectory(trajectory4);
            armWrapper.StopIntake();
            armWrapper.SetLevel(4);
        }else {
            armWrapper.StopIntake();
        }
    }
}