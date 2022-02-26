package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class RedHomeRunnerV3 implements IAutonomousRunner {


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

    double Rotation = -60;

    final static int IndexStart = 0;
    final static int IndexDrop1 = 1;
    final static int IndexOutsideWH = 2;
    final static int IndexInsideWH = 3;
    final static int IndexOutsideWH2 = 4;
    final static int IndexDrop2 = 5;

    Pose2d[] poseLocationsRed = new Pose2d[] {
        new Pose2d(10, -60, Math.toRadians(90)),
        new Pose2d(5, -38, Math.toRadians(130)),
        new Pose2d(10, -60, 0),
        new Pose2d(45, -60, 0),
        new Pose2d(20, -60, 0),
        new Pose2d(-6, -39, Math.toRadians(-250))

    };



    public RedHomeRunnerV3(SampleMecanumDrive inDrive, ArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;

        Pose2d startPose = new Pose2d(10, -60, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        armWrapper.init(true);

        levelInt = wrapper.OpenCVWrapper.barcodeInt - 1;
    }

    @Override
    public void run() {


        TrajectorySequence trajectorySequence = drive.trajectorySequenceBuilder(poseLocationsRed[IndexStart])
            .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                armWrapper.SetLevel(levelInt);
            })
            .splineTo(new Vector2d(poseLocationsRed[IndexDrop1].getX(), poseLocationsRed[IndexDrop1].getY()), poseLocationsRed[IndexDrop1].getHeading()) //1
            .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                armWrapper.Intake(.25);
            })
            .waitSeconds(1)
            .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                armWrapper.StopIntake() ;
            })
            .lineToLinearHeading(poseLocationsRed[IndexOutsideWH]) //2
            .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                armWrapper.SetLevel(0);
                armWrapper.IntakeReverse(1);
            })
//            .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//            .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                armWrapper.StopIntake();
//            })
//            .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY())) //4
//            .UNSTABLE_addTemporalMarkerOffset(-1.5, () -> {
//                armWrapper.SetLevel(4);
//            })
//            .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(), poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
//            .UNSTABLE_addTemporalMarkerOffset(-.2, () -> {
//                armWrapper.Intake(.25);
//            })
//            .waitSeconds(.1)
//            .UNSTABLE_addTemporalMarkerOffset(0, () ->{
//                armWrapper.StopIntake();
//            })
//            .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY()), poseLocationsRed[IndexOutsideWH2].getHeading()) //4
//            .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//            .UNSTABLE_addTemporalMarkerOffset(-1,()->{
//                armWrapper.SetLevel(0);
//            })
//            .UNSTABLE_addTemporalMarkerOffset(-.5,() -> {
//                armWrapper.IntakeReverse(1);
//            })
//
//
//            .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY())) //4
//            .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(), poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
//                .UNSTABLE_addTemporalMarkerOffset(-.2, () -> {
//                    armWrapper.Intake(.25);
//                })
//                .waitSeconds(.1)
//                .UNSTABLE_addTemporalMarkerOffset(0, () ->{
//                    armWrapper.StopIntake();
//                })
//            .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY()), poseLocationsRed[IndexOutsideWH2].getHeading()) //4
//                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//                .UNSTABLE_addTemporalMarkerOffset(-1.5,()->{
//                    armWrapper.SetLevel(0);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-.5,() -> {
//                    armWrapper.IntakeReverse(1);
//                })
//
//
//            .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY())) //4
//            .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(), poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
//            .UNSTABLE_addTemporalMarkerOffset(-.2, () -> {
//                armWrapper.Intake(.25);
//            })
//            .waitSeconds(.1)
//            .UNSTABLE_addTemporalMarkerOffset(0, () ->{
//                armWrapper.StopIntake();
//            })
//            .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY()), poseLocationsRed[IndexOutsideWH2].getHeading()) //4
//            .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//            .UNSTABLE_addTemporalMarkerOffset(-.5,() -> {
//                armWrapper.IntakeReverse(1);
//            })
//

//            .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY())) //4
//            .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(), poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
//            .UNSTABLE_addTemporalMarkerOffset(-.2, () -> {
//                armWrapper.Intake(.25);
//            })
//            .waitSeconds(.1)
//            .UNSTABLE_addTemporalMarkerOffset(0, () ->{
//                armWrapper.StopIntake();
//            })
//            .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY()), poseLocationsRed[IndexOutsideWH2].getHeading()) //4
//            .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//            .UNSTABLE_addTemporalMarkerOffset(-.5,() -> {
//                armWrapper.IntakeReverse(1);
//            })
//
//
//            .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY())) //4
//            .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(), poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
//            .UNSTABLE_addTemporalMarkerOffset(-.2, () -> {
//                armWrapper.Intake(.25);
//            })
//            .waitSeconds(.1)
//            .UNSTABLE_addTemporalMarkerOffset(0, () ->{
//                armWrapper.StopIntake();
//            })
//            .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(), poseLocationsRed[IndexOutsideWH2].getY()), poseLocationsRed[IndexOutsideWH2].getHeading()) //4
//            .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(), poseLocationsRed[IndexInsideWH].getY())) //3
//            .UNSTABLE_addTemporalMarkerOffset(-.5,() -> {
//                armWrapper.IntakeReverse(1);
//            })

            .build();

        drive.followTrajectorySequence(trajectorySequence);




    }

    public void PickupDrop(boolean stay) {
        drive.followTrajectory(trajectory5);
        armWrapper.Intake(.25);
        linearOpMode.sleep(1000);
        drive.followTrajectory(trajectory2);
        armWrapper.SetLevel(0);
        armWrapper.IntakeReverse(1);
        drive.followTrajectory(trajectory3);
        if (!stay) {
            drive.followTrajectory(trajectory4);
            armWrapper.StopIntake();
            armWrapper.SetLevel(4);
        } else {
            armWrapper.StopIntake();
        }
    }
}