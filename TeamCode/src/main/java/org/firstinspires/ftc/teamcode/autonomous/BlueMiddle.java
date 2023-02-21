package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.competitionopmodes.AutonomousWrapper;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;

public class BlueMiddle implements IAutonomousRunner {

    TrajectorySequence trajectory1;
    TrajectorySequence trajectory2;
    TrajectorySequence trajectory3;

    SampleMecanumDrive drive;
    ExtensionArmWrapper armWrapper;
    LinearOpMode linearOpMode;
    AutonomousWrapper wrapper;
    Telemetry telemetry;
    int signalInt;


    public BlueMiddle(SampleMecanumDrive inDrive, ExtensionArmWrapper inArm, LinearOpMode inLinearOpMode, AutonomousWrapper inWrapper, Telemetry inTelemetry) {
        drive = inDrive;
        armWrapper = inArm;
        linearOpMode = inLinearOpMode;
        wrapper = inWrapper;
        telemetry = inTelemetry;
        telemetry.addData("Angle at zero = ", fudjeFactor(0));
        telemetry.update();
    }

    @Override
    public void run() {



        Pose2d startPose = new Pose2d(36, 63, Math.toRadians(-90));

        drive.setPoseEstimate(startPose);

        signalInt = wrapper.initDetection.signalInt;

        telemetry.addData("Int = ", signalInt);
        telemetry.addData("Angle at zero = ", fudjeFactor(0));
        telemetry.update();

        Pose2d finalPosition;
        if(signalInt==0){
            finalPosition = new Pose2d(18,13,Math.toRadians(21));
        }else if(signalInt==1){
            finalPosition = new Pose2d(41,22,Math.toRadians(21));
        }else{
            finalPosition = new Pose2d(65,31,Math.toRadians(21));
        }

        TrajectorySequence trajectoryBase = drive.trajectorySequenceBuilder(new Pose2d(36, 62, Math.toRadians(-90)))

                // Initial Grab
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.5);
                })
                .waitSeconds(.8)//.waitSeconds(1)
                //Slide Up
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 4000;
                    armWrapper.UpdatePos();
                })
                .waitSeconds(.6)//.waitSeconds(.8)
                //Move to position first drop
                .lineToLinearHeading(new Pose2d(19,61,Math.toRadians(-90)))//-6.42857142857139)))
                .lineToLinearHeading(new Pose2d(14,28,Math.toRadians(-156.428571)))//fudjeFactor(140))))
                .waitSeconds(.5)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.3);
                })

                .waitSeconds(.4)//.waitSeconds(.6)
                . lineToLinearHeading(new Pose2d(13,10,Math.toRadians(100)))
                .lineToLinearHeading(new Pose2d(43,21,Math.toRadians(20)))



                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 550;
                    armWrapper.UpdatePos();
                })

                .waitSeconds(.6)//.waitSeconds(.8)

                //move forward to first pick up
                .lineToLinearHeading(new Pose2d(67,29,Math.toRadians(21)))
                //Grab
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.clawServo.setPosition(.5);
                })
                .waitSeconds(.4)//.waitSeconds(.6)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 4000;
                    armWrapper.UpdatePos();
                })
                .waitSeconds(.4)//.waitSeconds(.6)

                //2nd  Drop
                .lineToLinearHeading(new Pose2d(35,13,Math.toRadians(-73)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {
                    armWrapper.slidePos = 4000;
                    armWrapper.UpdatePos();
                })
                .waitSeconds(.2)//.waitSeconds(.4)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.clawServo.setPosition(.3);})
                .waitSeconds(.4)//.waitSeconds(.6)
                .UNSTABLE_addTemporalMarkerOffset(1, () -> {armWrapper.slidePos = 390;armWrapper.UpdatePos();})
                .waitSeconds(.6)//.waitSeconds(.8)
                .lineToLinearHeading(new Pose2d(49,27,Math.toRadians(-5)))


                .waitSeconds(0)//.waitSeconds(.2)


                //second pickup
                .lineToLinearHeading(new Pose2d(68,29,Math.toRadians(21)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.clawServo.setPosition(.5);})
                .waitSeconds(.4)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 3000;armWrapper.UpdatePos();})

                //third drop
                .lineToLinearHeading(new Pose2d(35,24,Math.toRadians(125)))
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.clawServo.setPosition(.3);})
                .waitSeconds(.4)
                .lineToLinearHeading(new Pose2d(48,27,Math.toRadians(-6)))
                .waitSeconds(.4)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 220;armWrapper.UpdatePos();})
                .lineToLinearHeading(finalPosition)
                .UNSTABLE_addTemporalMarkerOffset(0, () -> {armWrapper.slidePos = 0;armWrapper.UpdatePos();})

                .build();
        drive.followTrajectorySequence(trajectoryBase);


    }

    double fudjeFactor(double angle){
        double multiplier = (90.0000000000000/84.0000000000000000000);
        double newAngle = (angle+90.000000);
        telemetry.addData("Multiplier: ", multiplier);
        telemetry.addData("new angle", newAngle);
        return (newAngle*-multiplier)+90.0000;
    }
    double fudjeFactor2(double angle){
        double multiplier = (90.0000000000000/99.0000000000000000000);
        double newAngle = (angle+90.000000);
        telemetry.addData("Multiplier: ", multiplier);
        telemetry.addData("new angle", newAngle);
        return (newAngle*multiplier)-90.0000;
    }
    double fudjeFactor3(double angle){
        double multiplier = (90.0000000000000/99.0000000000000000000);
        double newAngle = (angle+90.000000);
        telemetry.addData("Multiplier: ", multiplier);
        telemetry.addData("new angle", newAngle);
        return (newAngle*multiplier)-90.0000;
    }



}