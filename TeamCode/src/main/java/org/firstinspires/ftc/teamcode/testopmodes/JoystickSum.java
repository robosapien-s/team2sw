package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class JoystickSum extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    @Override
    public void runOpMode() throws InterruptedException {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        waitForStart();
        while (!isStopRequested()){
            double x = joystickWrapper.gamepad1GetLeftStickX();
            double y = joystickWrapper.gamepad1GetLeftStickY();
            double sum = Math.abs(x) + Math.abs(y);
            telemetry.addData("Sum", sum);
            telemetry.addData("x",x);
            telemetry.addData("y",y);
            telemetry.update();
        }
    }
}
