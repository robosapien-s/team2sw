package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtensionArmWrapper {
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DcMotor bottomMotor;
    DcMotor topMotor;
    Servo clawServo;
    Servo clawBase;
    DcMotorEx slideMotor;

    int slidePos = 0;
    int Ratio = 28;
    final int slideEncoderFactor = 200;


    double servoPos = 0.5;
    double currentRotation;
    double MotorTicks = ((((1+(46/17))) * (1+(46/11))) * 28);


    boolean open = true;

    public ExtensionArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
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

    public double GetCurrentRotation(){
        double n = (360*(bottomMotor.getCurrentPosition()/(MotorTicks*Ratio)))%360;
        if(n>180){
            return n-360;
        }else return n;
    }

    public void RotateArm(double angle){
        if(0>angle - GetCurrentRotation()){
            bottomMotor.setPower(-1 * 0.25);
            telemetry.addData("Input", "Negative");
        }else if (0<angle - GetCurrentRotation()){
            bottomMotor.setPower(1 * 0.25);
            telemetry.addData("Input", "Positive");
        }
        telemetry.update();
    }

}


