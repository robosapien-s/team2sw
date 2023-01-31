package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper69;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper69;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class DorukRobotTestNew extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionArmWrapper69 extensionArmWrapper;
    DrivingWrapper69 drivingWrapper;

    double speed = 0.5;
    double rotspeed = 0.5;;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionArmWrapper69(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapper69(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            if(joystickWrapper.gamepad1GetLeftStick()){
                speed = .75;
            }else {
                speed = .45;
            }
            if(joystickWrapper.gamepad1GetRightStick()){
                rotspeed = .75;
            }else {
                rotspeed = .45;
            }
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
