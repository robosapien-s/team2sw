package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
@Disabled
@TeleOp
public class DualMotorTestingWithEncoder extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    JoystickWrapper joystickWrapper;
    Boolean open;
    Servo clawServo;

    @Override
    public void runOpMode() {

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        motor1 = hardwareMap.dcMotor.get("motorBackRight"); //setting up the motors with hardwaremaps


        joystickWrapper = new JoystickWrapper(gamepad1,gamepad2);

        while (!isStopRequested()){
            motor1.setPower(gamepad1.left_stick_y*.1);
            if(joystickWrapper.gamepad1GetLeftBumperDown()) {

                if (open) {
                    clawServo.setPosition(.5);
                    open = false;
                } else {
                    clawServo.setPosition(.3);
                    open = true;
                }
            }



        }

    }
}
