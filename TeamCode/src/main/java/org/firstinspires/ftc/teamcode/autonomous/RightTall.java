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
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;

public class RightTall implements IAutonomousRunner {

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

    Pose2d startPose = new Pose2d(37, -60, Math.toRadians(90));
    Pose2d parkStartPose = new Pose2d(23.5,-15,Math.toRadians(168));


    public RightTall(SampleMecanumDrive inDrive, ExtensionWrapperDD inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry, HardwareMap inHardwareMap) {
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
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 4000;armWrapper.UpdatePos(); })
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(.05); })
                .setConstraints(new TrajectoryVelocityConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 50;
                    }
                }, new TrajectoryAccelerationConstraint() {
                    @Override
                    public double get(double v, @NonNull Pose2d pose2d, @NonNull Pose2d pose2d1, @NonNull Pose2d pose2d2) {
                        return 45;
                    }
                })
                .lineToLinearHeading(new Pose2d(37, -1, Math.toRadians(80))) //first drive
                .lineToLinearHeading(new Pose2d(38,-4,Math.toRadians(80)))

                .lineToLinearHeading(new Pose2d(27,-8,Math.toRadians(125))) //position of first drop
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); })
                //drop 1 open

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> { armWrapper.slidePos = 600;armWrapper.UpdatePos();  }) //slide down for first pickup
                .UNSTABLE_addTemporalMarkerOffset(.6,()->{armWrapper.guideServo.setPosition(0.05);}) //guide servo up for first pickup
                .lineToLinearHeading(new Pose2d(40,-14,Math.toRadians(0))) //backup to align to first pickup



                //.waitSeconds(.35) //do we need this? testing
                .lineToLinearHeading(new Pose2d(67,-16,Math.toRadians(2))) //first pickup location
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); }) //closing of servo at first pickup

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 4000;armWrapper.UpdatePos(); }) //arm up for second drop
                .UNSTABLE_addTemporalMarkerOffset(.35, () -> { armWrapper.guideServo.setPosition(.7); })//guide down for second drop


                .lineToLinearHeading(new Pose2d(26,-5,Math.toRadians(135))) //location for second drop
                .waitSeconds(.4)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); }) //servo open for second drop

                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> { armWrapper.slidePos = 450;armWrapper.UpdatePos(); }) //arm going down for second pickup
                .UNSTABLE_addTemporalMarkerOffset(.6, () -> { armWrapper.guideServo.setPosition(0); }) //guide going up for second pickup
                .lineToLinearHeading(new Pose2d(40,-12,Math.toRadians(10))) //aligning for second pickup


                .lineToLinearHeading(new Pose2d(65,-15,Math.toRadians(3))) //position of second pickup
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); })//closing for second pickup

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 4000;armWrapper.UpdatePos(); })//raising arm for third drop
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(.7); })//lowering guide for third drop

                .waitSeconds(.35)

                .lineToLinearHeading(new Pose2d(26,-5,Math.toRadians(135))) //location of third drop

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); }) //third drop open servo

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> { armWrapper.slidePos = 305;armWrapper.UpdatePos();}) //arm down for third pickup
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {armWrapper.guideServo.setPosition(0);}) //guide up for third pickup
                .lineToLinearHeading(new Pose2d(40,-12,Math.toRadians(12))) //alligning for third pickup
                .lineToLinearHeading(new Pose2d(65,-16,Math.toRadians(0))) //third pickup location
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); }) //close servo for third pickup

                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 4000;armWrapper.UpdatePos(); }) //raise arm for fourth drop
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(.7); })//drop guide for fourth drop

                .waitSeconds(.35)

                .lineToLinearHeading(new Pose2d(26,-5,Math.toRadians(135)))//position of fourth drop
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); })
                .waitSeconds(.35)

                .UNSTABLE_addTemporalMarkerOffset(0.3, () -> { armWrapper.slidePos = 305;armWrapper.UpdatePos();}) //arm down for fourth pickup
                .UNSTABLE_addTemporalMarkerOffset(0.6, () -> {armWrapper.guideServo.setPosition(0);}) //guide up for fourth pickup
                .lineToLinearHeading(new Pose2d(40,-13,Math.toRadians(12)))//alignment for fourth pickup
                .lineToLinearHeading(new Pose2d(65,-18,Math.toRadians(0))) //fourth pickup location
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.9); armWrapper.leftServo.setPosition(.1); }) //close servo for fourth pickup

                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 4000;armWrapper.UpdatePos(); }) //raise arm for fifth drop
                .UNSTABLE_addTemporalMarkerOffset(.4, () -> { armWrapper.guideServo.setPosition(.7); })//drop guide for fifth drop
                .waitSeconds(.35)

                .lineToLinearHeading(new Pose2d(26,-7,Math.toRadians(135)))//position of fifth drop
                .waitSeconds(.35)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.rightServo.setPosition(.8); armWrapper.leftServo.setPosition(.2); })

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
                .resetConstraints()
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
                        return 45;
                    }
                })
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos(); armWrapper.guideServo.setPosition(0); })
                .lineToLinearHeading(new Pose2d(61.5,-9.5,Math.toRadians(168)))
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
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos(); armWrapper.guideServo.setPosition(0); })
                .lineToLinearHeading(new Pose2d(39.5,-11,Math.toRadians(168)))
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
                .UNSTABLE_addTemporalMarkerOffset(0, () -> { armWrapper.slidePos = 0;armWrapper.UpdatePos(); armWrapper.guideServo.setPosition(0); })
                .lineToLinearHeading(new Pose2d(17.5,-15,Math.toRadians(168)))
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