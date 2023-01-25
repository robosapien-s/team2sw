package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
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
    AutonomousWrapper wrapper;

    public RedCarouselRunnerV2(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
    }

    @Override
    public void run() {

        Pose2d startPose = new Pose2d(-32, -60, Math.toRadians(90));

        drive.setPoseEstimate(startPose);



        levelInt = wrapper.OpenCVWrapper.barcodeInt;

        if (wrapper.OpenCVWrapper.barcodeInt==0){
            wrapper.OpenCVWrapper.barcodeInt=3;
        }

        if (levelInt==1){
            trajectory1 = drive.trajectoryBuilder(startPose)
                    .splineTo(new Vector2d(-35,-40),Math.toRadians(45))
                    .build();
        }else {
            trajectory1 = drive.trajectoryBuilder(startPose)
                    .splineTo(new Vector2d(-26,-36),Math.toRadians(45))
                    .build();
        }

        trajectory2 = drive.trajectoryBuilder(trajectory1.end())
                .lineToLinearHeading(new Pose2d(-64,-40,Math.toRadians(90)))
                .build();

        trajectory3 = drive.trajectoryBuilder(trajectory2.end(), 6, 5)
                .back(10)
                .build();

        trajectory4 = drive.trajectoryBuilder(trajectory3.end(), 6, 5)
                .forward(18)
                .build();


        armWrapper.SetLevel(levelInt);
        linearOpMode.sleep(1000);
        drive.followTrajectory(trajectory1);
        armWrapper.Intake(.2);
        linearOpMode.sleep(1000);
        armWrapper.StopIntake();

        drive.followTrajectory(trajectory2);

        drive.followTrajectory(trajectory3);

//        wrapper.crMotor.setPower(-.5);
        linearOpMode.sleep(4000);
//        wrapper.crMotor.setPower(0);

        drive.followTrajectory(trajectory4);
        armWrapper.ResetArm();
        linearOpMode.sleep(5000000);


    }

}