package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivingWrapperClassBased {
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

    IDrivingTranslator driveTranslator = new PassThroughDrivingTranslator();


    public DrivingWrapperClassBased(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes
        telemetry = inTelemetry;// making a reference to Telemetry in opModes

        //Motor 0
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft"); //setting up the motors with hardware maps
        //Motor 2
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        //Motor 1
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        //Motor 3
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");


        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE); //setting the right side motors to reverse so they go the right direction
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

    public void Drive(JoystickWrapper joystickWrapper, double speed, double rotSpeed) {
        double y = -joystickWrapper.gamepad1GetLeftStickY(); // Remember, this is reversed! | Defining the y variable
        double x = joystickWrapper.gamepad1GetLeftStickX() * 1.1; // Counteract imperfect strafing | Defining the x variable
        double rx = joystickWrapper.gamepad1GetRightStickX() * rotSpeed; // Defining the rx (right x) variable

        if (joystickWrapper.gamepad1GetRightBumperRaw()){
            telemetry.addData("Move", "Back Right");
            y=-1;
            x=.3;
        }
        if (joystickWrapper.gamepad1GetLeftBumperRaw()){
            telemetry.addData("Move", "Back Left");
            y=-1;
            x=-.3;
        }
        if (joystickWrapper.gamepad1GetRightTriggerPressed()){
            telemetry.addData("Move", "Forward Right");
            y=1;
            x=.3;
        }
        if (joystickWrapper.gamepad1GetLeftTriggerPressed()){
            telemetry.addData("Move", "Forward Left");
            y=1;
            x=-.3;
        }


        Pose2d in = driveTranslator.update(new Pose2d(x,y,rx));


        double denominator = Math.max(Math.abs(in.getY()) + Math.abs(in.getX()) + Math.abs(in.getHeading()), 1); // Defining the denominator variable

        motorFrontLeft.setPower(FrontLeftPower(denominator, in.getY(), in.getX(), in.getHeading()/speed) * speed); //setting the power for the motors
        motorBackLeft.setPower(BackLeftPower(denominator, in.getY(), in.getX(), in.getHeading()/speed) * speed);
        motorFrontRight.setPower(FrontRightPower(denominator, in.getY(), in.getX(), in.getHeading()/speed) * speed);
        motorBackRight.setPower(BackRightPower(denominator, in.getY(), in.getX(), in.getHeading()/speed) * speed);
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

