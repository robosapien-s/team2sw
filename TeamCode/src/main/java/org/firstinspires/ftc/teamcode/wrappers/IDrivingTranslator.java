package org.firstinspires.ftc.teamcode.wrappers;


import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IDrivingTranslator {
    public Pose2d update(Pose2d pose2d, Telemetry telemetry);
}
