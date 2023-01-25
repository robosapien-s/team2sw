package org.firstinspires.ftc.teamcode.InputClasses;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.IRobot;

public class BasicRobot implements IRobot
{
    HardwareMap hardwareMap;

    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;

    double x;
    double y;
    double rx;

    double speed;

    public static double FrontLeftPower(double denominator, double y, double x, double rx) {
        double frontLeftPower = (y + x + rx) / denominator;
        return frontLeftPower;
    }

    public static double BackLeftPower(double denominator, double y, double x, double rx) {
        double backLeftPower = (y - x + rx) / denominator;
        return backLeftPower;
    }

    public static double FrontRightPower(double denominator, double y, double x, double rx) {
        double frontRightPower = (y - x - rx) / denominator;
        return frontRightPower;
    }

    public static double BackRightPower(double denominator, double y, double x, double rx) {
        double backRightPower = (y + x - rx) / denominator;
        return backRightPower;
    }

    public BasicRobot(HardwareMap inHardwareMap){
        hardwareMap=inHardwareMap;
    }

    @Override
    public void ChassisRotate(double rot) {
        rx = rot;
    }

    @Override
    public void ChassisMove(Vector2d move) {
        y = -move.getY();
        x = move.getX();
    }

    @Override
    public void ChassisSpeed(double inSpeed) {
        speed = inSpeed;
    }

    @Override
    public void ChassisPreset(int preset) {

    }

    @Override
    public void ArmMove(double move) {

    }

    @Override
    public void ArmSpeed(double speed) {

    }

    @Override
    public void ChassisRotSpeed(double rotSpeed) {

    }

    @Override
    public void ArmPreset(int preset) {

    }

    @Override
    public void GenericVector2d(Vector2d vec2) {

    }

    @Override
    public void GenericFloat(float flt) {

    }

    @Override
    public void GenericInt(int Int) {

    }

    @Override
    public void Update() {

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); // Defining the denominator variable
        motorFrontLeft.setPower(FrontLeftPower(denominator, y, x, rx/speed) * speed); //setting the power for the motors
        motorBackLeft.setPower(BackLeftPower(denominator, y, x, rx/speed) * speed);
        motorFrontRight.setPower(FrontRightPower(denominator, y, x, rx/speed) * speed);
        motorBackRight.setPower(BackRightPower(denominator, y, x, rx/speed) * speed);
    }

}
