package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * Op mode for preliminary tuning of the follower PID coefficients (located in the drive base
 * classes). The robot drives back and forth in a straight line indefinitely. Utilization of the
 * dashboard is recommended for this tuning routine. To access the dashboard, connect your computer
 * to the RC's WiFi network. In your browser, navigate to https://192.168.49.1:8080/dash if you're
 * using the RC phone or https://192.168.43.1:8080/dash if you are using the Control Hub. Once
 * you've successfully connected, start the program, and your robot will begin moving forward and
 * backward. You should observe the target position (green) and your pose estimate (blue) and adjust
 * your follower PID coefficients such that you follow the target position as accurately as possible.
 * If you are using SampleMecanumDrive, you should be tuning TRANSLATIONAL_PID and HEADING_PID.
 * If you are using SampleTankDrive, you should be tuning AXIAL_PID, CROSS_TRACK_PID, and HEADING_PID.
 * These coefficients can be tuned live in dashboard.
 *
 * This opmode is designed as a convenient, coarse tuning for the follower PID coefficients. It
 * is recommended that you use the FollowerPIDTuner opmode for further fine tuning.
 */
@Config
@Autonomous(group = "drive", name = "square")
public class RRTest extends LinearOpMode {

    public static double DISTANCE = 10;

    boolean usingLocalizer;

    VuforiaWebcamLocalizer localizer;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive roadRunner = new SampleMecanumDrive(hardwareMap);

        usingLocalizer = true;

        if (usingLocalizer){
            localizer = new VuforiaWebcamLocalizer();

            localizer.init(hardwareMap, telemetry);

            roadRunner.setLocalizer(localizer);

            while (localizer.getCurrentLocation()==null);
            telemetry.addData("locationU",localizer.getCurrentLocation().toString());
            telemetry.update();
            sleep(1000);


        }


        Trajectory trajectoryForward = roadRunner.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();

        Trajectory trajectoryRight = roadRunner.trajectoryBuilder(trajectoryForward.end())
                .strafeRight(DISTANCE*2)
                .build();
        Trajectory trajectoryBackward = roadRunner.trajectoryBuilder(trajectoryRight.end())
                .back(DISTANCE)
                .build();

        Trajectory trajectoryLeft = roadRunner.trajectoryBuilder(trajectoryBackward.end())
                .strafeLeft(DISTANCE*2)
                .build();
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            roadRunner.followTrajectory(trajectoryForward);
            telemetry.addData("location", localizer.getCurrentLocation());
            telemetry.update();
            sleep(1000);
            roadRunner.followTrajectory(trajectoryRight);
            telemetry.addData("location", localizer.getCurrentLocation());
            telemetry.update();
            sleep(1000);
            roadRunner.followTrajectory(trajectoryBackward);
            telemetry.addData("location", localizer.getCurrentLocation());
            telemetry.update();
            sleep(1000);
            roadRunner.followTrajectory(trajectoryLeft);
            telemetry.addData("location", localizer.getCurrentLocation());
            telemetry.update();
            sleep(1000);
        }
    }
}