package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class NewServo extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    Servo leftServo;
    Servo rightServo;

    @Override
    public void runOpMode() throws InterruptedException {
        joystickWrapper = new JoystickWrapper(gamepad1,gamepad2);
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        while (!isStopRequested()) {
            if (joystickWrapper.gamepad1GetLeftBumperDown()) {
                leftServo.setPosition(leftServo.getPosition()+0.1);
            } else if (joystickWrapper.gamepad1GetLeftTriggerDown()) {
                leftServo.setPosition(leftServo.getPosition()-0.1);
            } if (joystickWrapper.gamepad1GetRightBumperDown()) {
                rightServo.setPosition(rightServo.getPosition()+0.1);
            } else if (joystickWrapper.gamepad1GetRightTriggerDown()) {
                rightServo.setPosition(rightServo.getPosition()-0.1);
            }

            telemetry.addData("leftServo",leftServo.getPosition());
            telemetry.addData("rightServo",rightServo.getPosition());
            telemetry.update();
        }
    }
}
