package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoadRunnerWrapper {

    SampleMecanumDrive drive;

    Pose2d startPose = new Pose2d(-13,30,3.141592653589793);

    public RoadRunnerWrapper(SampleMecanumDrive inDrive, Localizer localizer){
        drive = inDrive;
    }


    public void init(){


        Pose2d startPose = new Pose2d();
        Trajectory trajectory = drive.trajectoryBuilder(startPose).splineTo(new Vector2d(-50,45.0),3.9269908169872414).build();



        drive.followTrajectory(trajectory);

    }


}
