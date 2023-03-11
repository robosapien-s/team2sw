package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class ServoTest extends LinearOpMode {

    Servo servo;
    JoystickWrapper joystickWrapper;
    double angle;

    @Override
    public void runOpMode() {
        joystickWrapper=new JoystickWrapper(gamepad1,gamepad2);
        servo = hardwareMap.get(Servo.class, "servo");
        servo.setDirection(Servo.Direction.FORWARD);
        while (!isStopRequested()){
            if (joystickWrapper.gamepad1GetLeftStickUsed(.3)){
                angle = joystickWrapper.gamepad1GetLeftStickAngle();
            }
            //Motor 0
            servo.setPosition(angle/180);
            telemetry.addData("Angle: ", angle);
            telemetry.addData("Divided: ", angle/180);
            telemetry.addData("Servo: ",servo.getPosition());
            telemetry.update();

        }

    }
}
