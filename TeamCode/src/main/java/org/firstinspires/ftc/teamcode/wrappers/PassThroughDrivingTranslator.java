package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PassThroughDrivingTranslator implements IDrivingTranslator{
    @Override
    public Pose2d update(Pose2d pose2d, Telemetry telemetry) {
        return pose2d;
    }
}
