package org.firstinspires.ftc.teamcode.wrappers;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivingWrapperPara {

    public enum Direction {
        LEFT,
        RIGHT,
        FORWARD,
        BACKWARD,
        FORWARDRIGHT,
        FORWARDLEFT,
        BACKWARDLEFT,
        BACKWARDRIGHT,
        SPINRIGHT,
        SPINLEFT
    }
    HardwareMap hardwareMap;// Making a variable hardwareMap of type HardwareMap
    Telemetry telemetry;

    //Creating the four motors
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;

    public DrivingWrapperPara(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes
        telemetry = inTelemetry;// making a reference to Telemetry in opModes

        //Motor 0
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft"); //setting up the motors with hardwaremaps
        //Motor 2
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        //Motor 1
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        //Motor 3
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");


        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE); //setting the right side motors to reverse so they go the right directiond
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

// static classes to return the amount of power for each motor
    public static double FrontLeftPower(double denominator, double y, double x, double rx) {
        double frontLeftPower = (y + x + rx) / denominator;
        return frontLeftPower;
    }

    public static double BackLeftPower(double denominator, double y, double x, double rx) {
        double backLeftPower = (y - x + rx) / denominator;
        return backLeftPower;
    }

    public static double FrontRightPower(double denominator, double y, double x, double rx) {
        double frontRightPower = (y - x - rx) / denominator;
        return frontRightPower;
    }

    public static double BackRightPower(double denominator, double y, double x, double rx) {
        double backRightPower = (y + x - rx) / denominator;
        return backRightPower;
    }

    double N = 4;
    double joyOldY = 0;
    double joyOldX = 0;
    double joyOldRX = 0;

    public void Drive(@NonNull JoystickWrapper joystickWrapper, double speed, double rotSpeed) {
        double deltaY = -joystickWrapper.gamepad1GetLeftStickY() - joyOldY;

        joyOldY = -joystickWrapper.gamepad1GetLeftStickY();

        double deltaX = (joystickWrapper.gamepad1GetLeftStickX()*1.1) - joyOldX;
        joyOldX = joystickWrapper.gamepad1GetLeftStickX()*1.1;

        double deltaRX = (joystickWrapper.gamepad1GetRightStickX() * rotSpeed) - joyOldRX;
        joyOldRX= joystickWrapper.gamepad1GetRightStickX() * rotSpeed;

        double y = 0;
        double x = 0;
        double rx = 0;

//        if (deltaY != 0) {
//            for (int count = 0; count < N; count++) {
//                y = joyOldY + ((count/N) * deltaY);
//
//            }
//        } else {
//            y = joyOldY;
//        }
//
//        if (deltaX != 0) {
//            for (int count = 0; count < N; count++) {
//                x = joyOldX + ((count/N) * deltaX);
//            }
//        } else {
//            x = joyOldX;
//        }
//
//        if (deltaRX != 0) {
//            for (int count = 0; count < N; count++) {
//                rx = joyOldRX + ((count/N) * deltaRX);
//            }
//        } else {
//            rx = joyOldRX;
//        }

        for (int count = 0; count < N; count++) {
            if (deltaY != 0) {
                y = joyOldY - ((count/N) * deltaY);
            } else {
                y = joyOldY;
            }

            if (deltaX != 0) {
                x = joyOldX - ((count/N) * deltaX);
            } else {
                x = joyOldX;
            }

            if (deltaRX != 0) {
                rx = joyOldRX - ((count/N) * deltaRX);
            } else {
                rx = joyOldRX;
            }
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); // Defining the denominator variable

            motorFrontLeft.setPower(FrontLeftPower(denominator, y, x, rx/speed) * speed); //setting the power for the motors
            motorBackLeft.setPower(BackLeftPower(denominator, y, x, rx/speed) * speed);
            motorFrontRight.setPower(FrontRightPower(denominator, y, x, rx/speed) * speed);
            motorBackRight.setPower(BackRightPower(denominator, y, x, rx/speed) * speed);

        }

        if (deltaX != 0) {
            for (int count = 0; count < N; count++) {
                x = joyOldX + ((count/N) * deltaX);
            }
        } else {
            x = joyOldX;
        }

        if (deltaRX != 0) {
            for (int count = 0; count < N; count++) {
                rx = joyOldRX + ((count/N) * deltaRX);
            }
        } else {
            rx = joyOldRX;
        }

        telemetry.addData("joystickY", joystickWrapper.gamepad1GetLeftStickY());
        telemetry.addData("y",y);
        telemetry.addData("joystickX", joystickWrapper.gamepad1GetLeftStickX());
        telemetry.addData("x",x);
        telemetry.addData("joystickRX", joystickWrapper.gamepad1GetRightStickX());
        telemetry.addData("rx",rx);
        telemetry.update();
        //double y = -joystickWrapper.gamepad1GetLeftStickY(); // Remember, this is reversed! | Defining the y variable
        //double x = joystickWrapper.gamepad1GetLeftStickX() * 1.1; // Counteract imperfect strafing | Defining the x variable
        //double rx = joystickWrapper.gamepad1GetRightStickX() * rotSpeed; // Defining the rx (right x) variable



        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1); // Defining the denominator variable

        motorFrontLeft.setPower(FrontLeftPower(denominator, y, x, rx/speed) * speed); //setting the power for the motors
        motorBackLeft.setPower(BackLeftPower(denominator, y, x, rx/speed) * speed);
        motorFrontRight.setPower(FrontRightPower(denominator, y, x, rx/speed) * speed);
        motorBackRight.setPower(BackRightPower(denominator, y, x, rx/speed) * speed);
    }
    public double calculateDenominator(double x, double y, double rx) {
        return Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    }

    public void AutonomousDriveStop() {
        motorFrontLeft.setPower(0); //setting the power for the motors
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
    }
    public void AutonomousDrive(Direction direction, double time, double speed) {
        double x = 0;
        double y = 0;
        double rx = 0;

        if(direction == Direction.RIGHT) { //x [-1, 0), y=0
            x = -1 * 1.1;
            y = 0;
            rx = 0;
        }else if(direction == Direction.LEFT) { // x (0, 1], y=0
            x = 1 * 1.1;
            y = 0;
            rx = 0;
        }else if(direction == Direction.FORWARD) { // x=0, y (0, 1]
            x = 0;
            y = 1;
            rx = 0;
        }else if(direction == Direction.BACKWARD) { // x=0, y [-1, 0)
            x = 0;
            y = -1;
            rx = 0;
        }else if(direction == Direction.SPINRIGHT){
            x = 0;
            y = 0;
            rx = 1;
        }else if(direction == Direction.SPINLEFT){
            x = 0;
            y = 0;
            rx = -1;
        }else if (direction == Direction.FORWARDRIGHT){
            x = .1;
            y = 1;
            rx = 0;
        }else if (direction == Direction.FORWARDLEFT){
            x = .1;
            y = -1;
            rx = 0;
        }else if (direction == Direction.BACKWARDLEFT){
            x = -1;
            y = -.1;
            rx = 0;
        }else if (direction == Direction.BACKWARDRIGHT){
            x = 1;
            y = -.1;
            rx = 0;
        }

        double denominator = calculateDenominator(x, y, rx);
        motorFrontLeft.setPower(FrontLeftPower(denominator, y, x, rx)*speed); //setting the power for the motors
        motorBackLeft.setPower(BackLeftPower(denominator, y, x, rx)*speed);
        motorFrontRight.setPower(FrontRightPower(denominator, y, x, rx)*speed);
        motorBackRight.setPower(BackRightPower(denominator, y, x, rx)*speed);
//
//            Thread.sleep((long) (1000*time));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}

