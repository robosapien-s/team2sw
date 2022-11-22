package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;


// This is a comment LOL ¯\_(ツ)_/¯

/* this is also a comment


and also this ¯\_(ツ)_/¯
 */

@TeleOp
public class Kabir_Practice extends OpMode{

    JoystickWrapper joystickWrapper;



    @Override
    public void init(){
        joystickWrapper = new JoystickWrapper(gamepad1,gamepad2);
    }

    @Override
    public void loop() {
        telemetry.addData("Angle: ", joystickWrapper.gamepad1GetLeftStickAngle());
        telemetry.addData("Angle: ", joystickWrapper.gamepad1GetRightStickAngle());
        telemetry.addData("Angle: ", joystickWrapper.gamepad2GetLeftStickAngle());
        telemetry.addData("Angle: ", joystickWrapper.gamepad2GetRightStickAngle());
        telemetry.update();
    }
}
