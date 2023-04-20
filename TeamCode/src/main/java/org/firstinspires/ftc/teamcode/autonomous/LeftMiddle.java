package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;

public class LeftMiddle implements IAutonomousRunner {

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


    public LeftMiddle(SampleMecanumDrive inDrive, ExtensionWrapperDD inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry, HardwareMap inHardwareMap) {
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

        drive.setPoseEstimate(startPose);





        trajectoryBase = drive.trajectorySequenceBuilder(startPose)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 2850;armWrapper.UpdatePos(); })
                .setConstraints(new TrajectoryVelocityConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                }, new TrajectoryAccelerationConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 40;
                    }
                }) //setting fast speed
                .lineToLinearHeading(new Pose2d(-36, 0, Math.toRadians(90)))//first drive at increased speed

                .lineToLinearHeading(new Pose2d(-27,-22,Math.toRadians(-45))) //moving to the first drop

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); }) //opening servo for first drop

                .waitSeconds(.35)

                .lineToLinearHeading(new Pose2d(-40,-14,Math.toRadians(180))) //aligning for first pickup

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 600;armWrapper.UpdatePos(); armWrapper.guideServo.setPosition(0.05);}) //moving arm down and guide up for first pickup

                .waitSeconds(.35)
                .lineToLinearHeading(new Pose2d(-66,-14.5,Math.toRadians(182))) //location for pickup
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); }) //closing claw for pickup

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 2850;armWrapper.UpdatePos(); }) //moving arm up for second drop
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(0); })// moving guide out for second drop
                .lineToLinearHeading(new Pose2d(-25,-22,Math.toRadians(-90))) //location of second drop
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); }) //opening claw for second drop

                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0.2, () -> { armWrapper.slidePos = 450;armWrapper.UpdatePos();}) //moving arm back down on a delay
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {armWrapper.guideServo.setPosition(0.05);})
                .lineToLinearHeading(new Pose2d(-30,-17,Math.toRadians(190))) //aligning for second pickup
                .lineToLinearHeading(new Pose2d(-66,-10.5,Math.toRadians(183)))// location for second pickup
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); }) //closing servo for second pickup
                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 2850;armWrapper.UpdatePos(); }) //arm going up for third drop
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(0); }) //guide going down for third drop


                .lineToLinearHeading(new Pose2d(-24,-22,Math.toRadians(-90))) //location of third drop

                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); })//claw opening for third drop

                .waitSeconds(.35)
                .lineToLinearHeading(new Pose2d(-28, -19, Math.toRadians(178)))
                .waitSeconds(.35)



                //drop 1

//                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
//                // open and go to height
//                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
//                //grab then adjust height
//                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
//
//                //drop 2
//                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
//                // open and go to height
//                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
//                //grab then adjust height
//                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
//
//                //drop 3
//                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
//                // open and go to height
//                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
//                //grab then adjust height
//                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
//
//                //drop 4
//                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
//                // open and go to height
//                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
//                //grab then adjust height
//                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
//
//                //drop 5
//                .lineToLinearHeading(new Pose2d(-36,-12,Math.toRadians(180)))
//                // open and go to height
//                .lineToLinearHeading(new Pose2d(-60,-12,Math.toRadians(180)))
//                //grab then adjust height
//                .lineToLinearHeading(new Pose2d(-24,-12,Math.toRadians(90)))
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
                //.UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 3000; armWrapper.UpdatePos();}) //moving arm up to avoid hitting pole on turn
                //may need above line for this trajectory, but it is not tested
                .UNSTABLE_addTemporalMarkerOffset(0.2, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos();})
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {armWrapper.guideServo.setPosition(0.05);})
                .lineToLinearHeading(new Pose2d(-63,-11,Math.toRadians(168)))
                .resetConstraints()

                .waitSeconds(2)

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
                //.UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 3000; armWrapper.UpdatePos();}) //moving arm up to avoid hitting pole on turn
                //may need above line for this trajectory, but it is not tested
                .UNSTABLE_addTemporalMarkerOffset(0.2, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos();})
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {armWrapper.guideServo.setPosition(0.05);})
                .lineToLinearHeading(new Pose2d(-39.5,-15,Math.toRadians(168)))
                .resetConstraints()

                .waitSeconds(2)

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
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 3000; armWrapper.UpdatePos();}) //moving arm up to avoid hitting pole on turn
                .UNSTABLE_addTemporalMarkerOffset(0.2, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos();})
                .UNSTABLE_addTemporalMarkerOffset(0.4, () -> {armWrapper.guideServo.setPosition(0.05);})
                .lineToLinearHeading(new Pose2d(-15,-15,Math.toRadians(168)))
                .resetConstraints()

                .waitSeconds(2)

                .build();
    }

    @Override
    public void run() {
        signalInt = wrapper.initDetection.signalInt;
        telemetry.addData("Int = ", signalInt);
        telemetry.update();
        drive.followTrajectorySequence(trajectoryBase);

        if(signalInt==0){
            drive.followTrajectorySequence(trajectory3);
        }else if(signalInt==1){
            drive.followTrajectorySequence(trajectory2);
        }else{
            drive.followTrajectorySequence(trajectory1);
        }

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