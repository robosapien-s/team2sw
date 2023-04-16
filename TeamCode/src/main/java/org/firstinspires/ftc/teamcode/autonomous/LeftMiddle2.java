package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;

public class LeftMiddle2 implements IAutonomousRunner {

    TrajectorySequence trajectoryBase;
    TrajectorySequence trajectory1;
    TrajectorySequence trajectory2;
    TrajectorySequence trajectory3;
    Trajectory trajectory4;
    Trajectory trajectory5;
    SampleMecanumDrive drive;
    ExtensionWrapperDD armWrapper;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;
    Telemetry telemetry;
    int signalInt;
    HardwareMap hardwareMap;
    DrivingWrapperCompetition drivingWrapper;

    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;

    float robotHalfLength = 7;
    float halfTile = 12;

    Pose2d startPose = new Pose2d(-36, -60, Math.toRadians(90));
    Pose2d parkStartPose = new Pose2d(-23.5,-15,Math.toRadians(168));


    public LeftMiddle2(SampleMecanumDrive inDrive, ExtensionWrapperDD inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry, HardwareMap inHardwareMap) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
        telemetry = inTelemetry;
        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes

        //Motor 0
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft"); //setting up the motors with hardwaremaps
        //Motor 2
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        //Motor 1
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        //Motor 3
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");

    }

    @Override
    public void run() {
        drive.setPoseEstimate(startPose);


        signalInt = wrapper.initDetection.signalInt;

        telemetry.addData("Int = ", signalInt);
        telemetry.update();


        trajectoryBase = drive.trajectorySequenceBuilder(startPose)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.rightServo.setPosition(.9);
                    armWrapper.leftServo.setPosition(.1);
                })

                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 2800;armWrapper.UpdatePos();
                })
                .splineTo(new Vector2d(-36,-40),Math.toRadians(90))
                .splineTo(new Vector2d(-48,33),Math.toRadians(180))

                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.rightServo.setPosition(.8);
                    armWrapper.leftServo.setPosition(.2);
                })


//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.9);
//                    armWrapper.leftServo.setPosition(.1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 2800;armWrapper.UpdatePos();
//                })
//
//
//                .lineToLinearHeading(new Pose2d(-36, 0,Math.toRadians(90)), new TrajectoryVelocityConstraint() {
//                    @Override
//                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
//                        return 50;
//                    }
//                }, new TrajectoryAccelerationConstraint() {
//                    @Override
//                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
//                        return 40;
//                    }
//                })
//
//
//                .lineToLinearHeading(new Pose2d(-27,-22,Math.toRadians(-45)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.8);
//                    armWrapper.leftServo.setPosition(.2);
//                })
//
//                .waitSeconds(.4)
//
//                .lineToLinearHeading(new Pose2d(-40,-14,Math.toRadians(180)))
//
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 600;armWrapper.UpdatePos();
//                    armWrapper.guideServo.setPosition(0);
//                })
//
//                .waitSeconds(.4)
//                .lineToLinearHeading(new Pose2d(-65.5,-14.5,Math.toRadians(178)))
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.9);
//                    armWrapper.leftServo.setPosition(.1);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 2800;armWrapper.UpdatePos();
//                })
//
//                .UNSTABLE_addTemporalMarkerOffset(.4, () -> {
//                    armWrapper.guideServo.setPosition(.65);
//                })
//
//                .lineToLinearHeading(new Pose2d(-23,-21,Math.toRadians(-90)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.8);
//                    armWrapper.leftServo.setPosition(.2);
//                })
//
//                .waitSeconds(.4)
//
//                .lineToLinearHeading(new Pose2d(-30,-14,Math.toRadians(170)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 500;armWrapper.UpdatePos();
//                    armWrapper.guideServo.setPosition(0);
//                })
//
//                .lineToLinearHeading(new Pose2d(-64,-11.5,Math.toRadians(177)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.9);
//                    armWrapper.leftServo.setPosition(.1);
//                })
//                .waitSeconds(.4)
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 2800;armWrapper.UpdatePos();
//                })
//
//                .UNSTABLE_addTemporalMarkerOffset(.4, () -> {
//                    armWrapper.guideServo.setPosition(.65);
//                })
//                .waitSeconds(.4)
//                .lineToLinearHeading(new Pose2d(-24,-22,Math.toRadians(-94)))
//
//                .waitSeconds(.4)
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.8);
//                    armWrapper.leftServo.setPosition(.2);
//                })
//
//                .waitSeconds(.4)
//
//                .lineToLinearHeading(new Pose2d(-30,-14,Math.toRadians(168)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 305;armWrapper.UpdatePos();
//                    armWrapper.guideServo.setPosition(0);
//                })
//
//                .lineToLinearHeading(new Pose2d(-64,-9.5,Math.toRadians(168)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.9);
//                    armWrapper.leftServo.setPosition(.1);
//                })
//                .waitSeconds(.4)
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.slidePos = 2800;armWrapper.UpdatePos();
//                })
//
//                .UNSTABLE_addTemporalMarkerOffset(.4, () -> {
//                    armWrapper.guideServo.setPosition(.65);
//                })
//                .waitSeconds(.4)
//                .lineToLinearHeading(new Pose2d(-23.5,-21,Math.toRadians(-94)))
//
//                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
//                    armWrapper.rightServo.setPosition(.8);
//                    armWrapper.leftServo.setPosition(.2);
//                })
//
//                .waitSeconds(.4)
//
//                .lineToLinearHeading(new Pose2d(-23.5,-15,Math.toRadians(168)))
//
//                //drop 1
//
////                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
////                // open and go to height
////                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
////                //grab then adjust height
////                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
////
////                //drop 2
////                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
////                // open and go to height
////                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
////                //grab then adjust height
////                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
////
////                //drop 3
////                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
////                // open and go to height
////                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
////                //grab then adjust height
////                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
////
////                //drop 4
////                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
////                // open and go to height
////                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
////                //grab then adjust height
////                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
////
////                //drop 5
////                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
////                // open and go to height
////                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
////                //grab then adjust height
////                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
                .build();

        trajectory1 = drive.trajectorySequenceBuilder(parkStartPose)
                .setConstraints(new TrajectoryVelocityConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                }, new TrajectoryAccelerationConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                })
                .lineToLinearHeading(new Pose2d(-61.5,-9.5,Math.toRadians(168)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 0;armWrapper.UpdatePos();
                    armWrapper.guideServo.setPosition(0);
                })
                .resetConstraints()
                .build();

        trajectory2 = drive.trajectorySequenceBuilder(parkStartPose)
                .setConstraints(new TrajectoryVelocityConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                }, new TrajectoryAccelerationConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                })
                .lineToLinearHeading(new Pose2d(-39.5,-11,Math.toRadians(168)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 0;armWrapper.UpdatePos();
                    armWrapper.guideServo.setPosition(0);
                })
                .resetConstraints()
                .build();

        trajectory3 = drive.trajectorySequenceBuilder(parkStartPose)
                .setConstraints(new TrajectoryVelocityConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                }, new TrajectoryAccelerationConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                })
                .lineToLinearHeading(new Pose2d(-17.5,-15,Math.toRadians(168)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 0;armWrapper.UpdatePos();
                    armWrapper.guideServo.setPosition(0);
                })
                .resetConstraints()
                .build();


        drive.followTrajectorySequence(trajectoryBase);


//        if(signalInt==0){
//            drive.followTrajectorySequence(trajectory3);
//        }else if(signalInt==1){
//            drive.followTrajectorySequence(trajectory2);
//        }else{
//            drive.followTrajectorySequence(trajectory1);
//        }
        linearOpMode.sleep(10000);


    }

    void goForward(double speed){
        motorFrontLeft.setPower(speed);
        motorBackLeft.setPower(speed);
        motorFrontRight.setPower(speed);
        motorBackRight.setPower(speed);
    }

    void goRight(double speed){
        motorFrontLeft.setPower(speed);
        motorBackLeft.setPower(-speed);
        motorFrontRight.setPower(-speed);
        motorBackRight.setPower(speed);
    }





}