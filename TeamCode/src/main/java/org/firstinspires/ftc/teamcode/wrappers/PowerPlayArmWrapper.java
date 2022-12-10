package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

public class PowerPlayArmWrapper {
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DcMotor bottomMotor;
    DcMotor topMotor;
    Servo clawServo;
    Servo clawBase;
    DcMotorEx slideMotor;

    int slidePos = 0;
    final int slideEncoderFactor = 200;

    double servoPos = 0.5;

    boolean open = true;

    public PowerPlayArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        telemetry = inTelemetry;
        bottomMotor  = hardwareMap.get(DcMotor.class, "bottomMotor");
        topMotor  = hardwareMap.get(DcMotor.class, "topMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        clawBase = hardwareMap.get(Servo.class, "clawBase");
        slideMotor  = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void PPArmMove(JoystickWrapper joystickWrapper) {

        bottomMotor.setPower(joystickWrapper.gamepad2GetLeftStickX() * 0.25);
        slidePos = slideMotor.getCurrentPosition() + (int)(joystickWrapper.gamepad2GetRightStickY()*slideEncoderFactor);

        if (joystickWrapper.gamepad2GetDDownRaw()) {
            servoPos = clawBase.getPosition() - .01;
        }
        else if (joystickWrapper.gamepad2GetDUpRaw()) {
            servoPos = clawBase.getPosition() + .01;
        }

        clawBase.setPosition(servoPos);



        if (slidePos>-5) {
            slidePos = -5;
        }
        telemetry.addData("CurrentPosition:slide", slideMotor.getCurrentPosition());
        telemetry.addData("CurrentPosition:servo", clawServo.getPosition());
        telemetry.addData("TargetPosition", slidePos);
        telemetry.update();
        slideMotor.setPower(1);
        slideMotor.setTargetPosition(slidePos);

        slideMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        topMotor.setPower(-joystickWrapper.gamepad2GetLeftStickY());





        if(joystickWrapper.gamepad2GetA()) {
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


