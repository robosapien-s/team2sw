package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.opencv.core.Mat;

public class SharedShipping implements IRunnableTeleOp{
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;

    Pose2d start = new Pose2d(62,39,90);

    public SharedShipping(SampleMecanumDrive inDrive, ArmWrapper inArmWrapper){
        drive = inDrive;
        armWrapper = inArmWrapper;
    }
    @Override
    public void Run(){
        drive.setPoseEstimate(start);
        armWrapper.SetLevel(1);
        Trajectory trajectory1 = drive.trajectoryBuilder(start,12,10)
                .lineToLinearHeading(new Pose2d(62,-12,90))
                .build();
        Trajectory trajectory2 = drive.trajectoryBuilder(trajectory1.end(),12,10)
                .lineToLinearHeading(new Pose2d(62,39,90))
                .build();


        drive.followTrajectory(trajectory1);
        drive.turn(Math.toRadians(135));
        armWrapper.Intake(.45);
        drive.turn(Math.toRadians(90));
        drive.followTrajectory(trajectory2);
    }
}
