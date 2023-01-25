package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Vector2d;

public interface IRobot {
    public void ChassisRotate(double rot);
    public void ChassisMove(Vector2d move);
    public void ChassisSpeed(double speed);
    public void ChassisPreset(int preset);

    public void ArmMove(double move);
    public void ArmSpeed(double speed);
    public void ChassisRotSpeed(double rotSpeed);
    public void ArmPreset(int preset);

    public void GenericVector2d(Vector2d vec2);
    public void GenericFloat(float flt);
    public void GenericInt(int Int);

    public void Update();

}
