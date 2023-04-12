package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
@Disabled
@TeleOp
public class DorukRobotTest1_5 extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionArmWrapper extensionArmWrapper;
    DrivingWrapper drivingWrapper;

    double speed = 0.5;
    double rotSpeed = 0.5;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionArmWrapper(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapper(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
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

            telemetry.addData("speed", speed);

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
            telemetry.addData("rotSpeed: ", rotSpeed);
            telemetry.update();
            extensionArmWrapper.PPArmMove(joystickWrapper);
            drivingWrapper.Drive(joystickWrapper, speed, rotSpeed);
        }
    }
}
