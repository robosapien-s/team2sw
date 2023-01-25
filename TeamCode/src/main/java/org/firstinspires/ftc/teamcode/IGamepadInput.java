package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public interface IGamepadInput {
    public double ChassisRotate();
    public Vector2d ChassisMove();
    public double ChassisSpeed();
    public double ChassisRotSpeed();

    public int ChassisPreset();

    public double ArmMove();
    public double ArmSpeed();
    public int ArmPreset();

    public Vector2d GenericVector2d();
    public float GenericFloat();
    public int GenericInt();
}
