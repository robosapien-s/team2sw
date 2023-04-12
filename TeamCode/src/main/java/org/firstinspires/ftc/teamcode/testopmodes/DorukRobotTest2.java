package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper2;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
@Disabled
@TeleOp
public class DorukRobotTest2 extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionArmWrapper extensionArmWrapper;
    DrivingWrapper2 drivingWrapper;

    double speed = 0.5;
    double rotspeed = 0.5;;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionArmWrapper(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapper2(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
