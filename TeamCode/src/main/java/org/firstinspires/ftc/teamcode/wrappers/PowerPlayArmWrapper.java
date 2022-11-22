package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
    DcMotorEx slideMotor;

    boolean open = true;
    public PowerPlayArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        telemetry = inTelemetry;
        bottomMotor  = hardwareMap.get(DcMotor.class, "bottomMotor");
        topMotor  = hardwareMap.get(DcMotor.class, "topMotor");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        slideMotor  = hardwareMap.get(DcMotorEx.class, "slideMotor");



    }
    public void PPArmMove(JoystickWrapper joystickWrapper) {
        bottomMotor.setPower(-joystickWrapper.gamepad2GetLeftStickX() * 0.25);
        if (slideMotor.getTargetPosition() < 0) {
            slideMotor.setPower(-.5);
            slideMotor.setTargetPosition(1);
            slideMotor.setPower(0);
        }
        else {
            slideMotor.setPower(-joystickWrapper.gamepad2GetRightStickY()*0.75);
        }
        topMotor.setPower(joystickWrapper.gamepad2GetLeftStickY());
        if(joystickWrapper.gamepad2GetA()) {
            if (open) {
                clawServo.setPosition(10);
                open = false;
            } else {
                clawServo.setPosition(0);
                open = true;
            }
        }
    }
}


