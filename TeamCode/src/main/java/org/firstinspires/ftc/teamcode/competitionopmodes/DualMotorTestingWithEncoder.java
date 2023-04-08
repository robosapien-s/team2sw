package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class DualMotorTestingWithEncoder extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    JoystickWrapper joystickWrapper;
    Boolean open = false;
    Servo clawServo;

    public int slidePos = 0;
    final int slideEncoderFactor = 10;

    @Override
    public void runOpMode() {

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        motor1 = hardwareMap.dcMotor.get("motorBackRight"); //setting up the motors with hardwaremaps
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        joystickWrapper = new JoystickWrapper(gamepad1,gamepad2);

        while (!isStopRequested()){
            slidePos = motor1.getTargetPosition() + (int)((joystickWrapper.gamepad1GetRightTrigger()-joystickWrapper.gamepad1GetLeftTrigger())*slideEncoderFactor);

            if(joystickWrapper.gamepad1GetLeftBumperDown()) {

                if (open) {
                    clawServo.setPosition(.5);
                    open = false;
                } else {
                    clawServo.setPosition(.3);
                    open = true;
                }
            }
            motor1.setTargetPosition(slidePos);

            motor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        }

    }
}
