package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.IGamepadInput;
import org.firstinspires.ftc.teamcode.IRobot;

public class RobotInputWrapper {
    IGamepadInput gamepadInput;
    IRobot robot;

    public RobotInputWrapper(IGamepadInput iGamepadInput, IRobot iRobot){
        gamepadInput=iGamepadInput;
        robot=iRobot;
    }

    public void Update(){
        robot.ChassisRotate(gamepadInput.ChassisRotate());
        robot.ChassisMove(gamepadInput.ChassisMove());
        robot.ChassisSpeed(gamepadInput.ChassisSpeed());
        robot.ChassisRotSpeed(gamepadInput.ChassisRotSpeed());
        robot.ChassisPreset(gamepadInput.ChassisPreset());


        robot.ArmMove(gamepadInput.ArmMove());
        robot.ArmSpeed(gamepadInput.ArmSpeed());
        robot.ArmPreset(gamepadInput.ArmPreset());

        robot.GenericVector2d(gamepadInput.GenericVector2d());
        robot.GenericFloat(gamepadInput.GenericFloat());
        robot.GenericInt(gamepadInput.GenericInt());

        robot.Update();
    }
}
