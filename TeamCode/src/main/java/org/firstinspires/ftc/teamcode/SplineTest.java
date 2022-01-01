package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Config
@Autonomous(group = "drive", name = "spline")
public class SplineTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        SampleMecanumDrive roadRunner = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(24,-48, Math.toRadians(90));

        roadRunner.setPoseEstimate(startPose);

        Trajectory strafeLeft = roadRunner.trajectoryBuilder(startPose)
                .strafeLeft(24)
                .build();

        Trajectory lineTo = roadRunner.trajectoryBuilder(strafeLeft.end())
                .lineTo(new Vector2d(0,0))
                .build();


        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            roadRunner.followTrajectory(strafeLeft);
            roadRunner.followTrajectory(lineTo);
        }
    }
}