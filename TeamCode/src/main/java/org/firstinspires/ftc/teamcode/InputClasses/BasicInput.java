package org.firstinspires.ftc.teamcode.InputClasses;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.IGamepadInput;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

public class BasicInput implements IGamepadInput {

    JoystickWrapper joystickWrapper;

    double speed=.5;
    double rotSpeed=.5;

    public BasicInput(JoystickWrapper inJoystickWrapper){
        joystickWrapper = inJoystickWrapper;
    }
    @Override
    public double ChassisRotate() {
        return joystickWrapper.gamepad1GetLeftStickX();
    }

    @Override
    public Vector2d ChassisMove() {
        return new Vector2d(joystickWrapper.gamepad1GetLeftStickX(), joystickWrapper.gamepad1GetLeftStickY());
    }

    @Override
    public double ChassisSpeed() {
        if (joystickWrapper.gamepad1GetA()){
            speed = .25;
        }
        if (joystickWrapper.gamepad1GetB()){
            speed = .5;
        }
        if (joystickWrapper.gamepad1GetX()){
            speed = .75;
        }
        if (joystickWrapper.gamepad1GetY()){
            speed = 1;
        }
        return speed;
    }

    @Override
    public double ChassisRotSpeed() {
        if (joystickWrapper.gamepad1GetDUp()) {
            rotSpeed = 1;
        }

        if (joystickWrapper.gamepad1GetDDown()) {
            rotSpeed = .25;
        }

        if (joystickWrapper.gamepad1GetDRight()) {
            rotSpeed = .75;
        }

        if (joystickWrapper.gamepad1GetDLeft()) {
            rotSpeed = .5;
        }
        return rotSpeed;
    }
    @Override
    public int ChassisPreset() {
        return 0;
    }

    @Override
    public double ArmMove() {
        return 0;
    }

    @Override
    public double ArmSpeed() {
        return 0;
    }

    @Override
    public int ArmPreset() {
        return 0;
    }

    @Override
    public Vector2d GenericVector2d() {
        return null;
    }

    @Override
    public float GenericFloat() {
        return 0;
    }

    @Override
    public int GenericInt() {
        return 0;
    }
}
