package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
@Autonomous(group = "drive", name = "spline")
public class SplineTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        SampleMecanumDrive roadRunner = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-36,-72, Math.toRadians(-90));

        roadRunner.setPoseEstimate(startPose);

        Trajectory splineTest = roadRunner.trajectoryBuilder(startPose)
                .build();



        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            roadRunner.followTrajectory(splineTest);
        }
    }
}