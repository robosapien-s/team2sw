package org.firstinspires.ftc.teamcode.wrappers;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtensionWrapperDD {
    HardwareMap hardwareMap;
    Telemetry telemetry;
    //DcMotor bottomMotor;
    //DcMotor topMotor;
    public Servo leftServo; //1
    public Servo rightServo; //0
    DcMotorEx slideMotorRight; //0
    DcMotorEx slideMotorLeft; //1
    double leftSlideFactor = 0.98;
    public int slidePos = 0;
    int Ratio = 28;
    final int slideEncoderFactor = 10;

    boolean limit = true;


    double servoPos = 0.5;
    double currentRotation;
    double MotorTicks = ((((1+(46/17))) * (1+(46/11))) * 28);


    boolean open = true;

    public ExtensionWrapperDD(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        telemetry = inTelemetry;

        //bottomMotor  = hardwareMap.get(tDcMotor.class, "bottomMotor");
        //topMotor  = hardwareMap.get(DcMotor.class, "topMotor");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        slideMotorLeft  = hardwareMap.get(DcMotorEx.class, "slideMotorLeft");
        slideMotorRight  = hardwareMap.get(DcMotorEx.class, "slideMotorRight");
        slideMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotorLeft.setTargetPosition(0);
        slideMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotorRight.setTargetPosition(0);
    }
    double N = 10;
    int slowRange = 300;
    int slideOldPos = 0;
    public void PPArmMove(JoystickWrapper joystickWrapper) {

        int deltaSlide = slideMotorRight.getTargetPosition() + (int)((joystickWrapper.gamepad1GetRightTrigger()-joystickWrapper.gamepad1GetLeftTrigger())*slideEncoderFactor) - slidePos;
        slideOldPos = slideMotorRight.getTargetPosition() + (int)((joystickWrapper.gamepad1GetRightTrigger()-joystickWrapper.gamepad1GetLeftTrigger())*slideEncoderFactor);
        if (deltaSlide != 0) {
            for (int count = 0; count < N; count++) {
                slidePos = (int) (slideOldPos + ((count/N) * deltaSlide));
            }
        } else {
            slidePos=slideOldPos;
        }
        if (joystickWrapper.gamepad1GetRightBumperDown()){
            if(limit){
                limit=false;
            }else {
                limit=true;
            }
        }

       /* if (joystickWrapper.gamepad2GetDDown()) {
            clawBase.setPower(-.5);
            servoPos = clawBase.getPosition() - .01;
        }
        if(joystickWrapper.gamepad2GetDUp()) {
            servoPos = clawBase.getPosition();

        }*/

        //clawBase.setPower(joystickWrapper.gamepad1GetLeftStickY());

        if (joystickWrapper.gamepad1GetA()) {
            slidePos = 70;
        }else if (joystickWrapper.gamepad1GetX()) {
            slidePos = 220;
        }
        else if (joystickWrapper.gamepad1GetY()) {
            slidePos = 390;
        }else if (joystickWrapper.gamepad1GetB()) {
            slidePos = 550;
        }
        if (joystickWrapper.gamepad1GetDDown()) {
            slidePos = 5;
        }else if (joystickWrapper.gamepad1GetDLeft()) {
            slidePos = 1670;
        }
        else if (joystickWrapper.gamepad1GetDUp()) {
            slidePos = 2800;
        }else if (joystickWrapper.gamepad1GetDRight()) {
            slidePos = 4000;
        }


        if (slidePos<5 && limit) {
            slidePos = 5;
        }
        if (slidePos>4000 && limit) {
            slidePos = 4000;
        }
        telemetry.addData("CurrentPosition:slide", slideMotorRight.getCurrentPosition());
        telemetry.addData("TargetPosition:slide", slideMotorRight.getTargetPosition());
        telemetry.addData("CurrentPosition:rightServo", rightServo.getPosition());
        telemetry.addData("CurrentPosition:leftServo", leftServo.getPosition());
        telemetry.addData("TargetPosition", slidePos);
        telemetry.addData("Limit?", limit);
        telemetry.update();

        if (slideMotorRight.getTargetPosition() == 4000) {
            if (slideMotorRight.getCurrentPosition()> 4000-slowRange) {
                slideMotorRight.setPower(.25);
                slideMotorLeft.setPower(.25);
            } else {
                slideMotorRight.setPower(1);
                slideMotorLeft.setPower(1);
            }
        } else if (slideMotorRight.getTargetPosition() == 2900) {
            if (slideMotorRight.getCurrentPosition()>2900-slowRange && slideMotorRight.getCurrentPosition() < 2900+slowRange) {
                slideMotorRight.setPower(.25);
                slideMotorLeft.setPower(.25);
            } else {
                slideMotorRight.setPower(1);
                slideMotorLeft.setPower(1);
            }
        } else if (slideMotorRight.getTargetPosition() == 1670) {
            if (slideMotorRight.getCurrentPosition()> 1670-slowRange && slideMotorRight.getCurrentPosition() < 1670+slowRange) {
                slideMotorRight.setPower(.25);
                slideMotorLeft.setPower(.25);
            } else {
                slideMotorRight.setPower(1);
                slideMotorLeft.setPower(1);
            }
        } else {
            slideMotorRight.setPower(1);
            slideMotorLeft.setPower(1);
        }
        //slideMotorRight.setPower(1); //COMMENT THIS OUT IF WE ADD THE LOOPS PRIOR
        //slideMotorLeft.setPower(1);
        slideMotorRight.setTargetPosition(slidePos);
        slideMotorLeft.setTargetPosition((int) ((slidePos)*leftSlideFactor));
        slideMotorRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotorLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        //topMotor.setPower(-joystickWrapper.gamepad2GetLeftStickY());

        if(joystickWrapper.gamepad1GetLeftBumperDown()) {
            if (open) {
                rightServo.setPosition(.80);
                leftServo.setPosition(.20);
                open = false;
            } else {
                rightServo.setPosition(.9);
                leftServo.setPosition(0.1);
                open = true;
            }
        }
    }

    public void UpdatePos() {
        slideMotorRight.setPower(1);
        slideMotorLeft.setPower(1);
        slideMotorRight.setTargetPosition(slidePos);
        slideMotorLeft.setTargetPosition(slidePos);

        slideMotorRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        slideMotorLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    /*public double GetCurrentRotation(){
        double n = (360*(bottomMotor.getCurrentPosition()/(MotorTicks*Ratio)))%360;
        if(n>180){
            return n-360;
        }else return n;
    }*/

    /*public void RotateArm(double angle){
        if(0>angle - GetCurrentRotation()){
            bottomMotor.setPower(-1 * 0.25);
            telemetry.addData("Input", "Negative");
        }else if (0<angle - GetCurrentRotation()){
            bottomMotor.setPower(1 * 0.25);
            telemetry.addData("Input", "Positive");
        }
        telemetry.update();
    }*/

}





/*
init 192.168.43.1:5555 at ip ADB
push REABD library -- =: 17
POP REABD library -- =: 17(grade.strip().split(1::2))
 */