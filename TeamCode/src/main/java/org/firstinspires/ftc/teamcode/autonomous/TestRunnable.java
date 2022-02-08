package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;

public class TestRunnable implements IRunnableTeleOp{
    SampleMecanumDrive drive;
    ArmWrapper armWrapper;

    Pose2d start = new Pose2d(62,39,90);

    public TestRunnable(SampleMecanumDrive inDrive, ArmWrapper inArmWrapper){
        drive = inDrive;
        armWrapper = inArmWrapper;
    }
    @Override
    public void Run(){
        drive.setPoseEstimate(start);
        armWrapper.SetLevel(1);
        Trajectory trajectory1 = drive.trajectoryBuilder(start,12,10)
                .back(51)
                .build();
        Trajectory trajectory2 = drive.trajectoryBuilder(trajectory1.end(),12,10)
                .forward(51)
                .build();


        drive.followTrajectory(trajectory1);
        drive.turn(Math.toRadians(20));
        armWrapper.Intake(.45);
        drive.turn(Math.toRadians(-20));
        drive.followTrajectory(trajectory2);
    }
}
