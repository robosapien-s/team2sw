package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.util.QuikMaths;
import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivingWrapper2 {
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

    StandardTrackingWheelLocalizer myLocalizer;

    public DrivingWrapper2(HardwareMap inHardwareMap, Telemetry inTelemetry) {

        hardwareMap = inHardwareMap;// making a reference to HardwareMap in opModes
        telemetry = inTelemetry;// making a reference to Telemetry in opModes

        myLocalizer = new StandardTrackingWheelLocalizer(hardwareMap);


        myLocalizer.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(0)));


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

    public void Drive(JoystickWrapper joystickWrapper, double speed, double rotSpeed) {
        myLocalizer.update();
        Pose2d myPose = myLocalizer.getPoseEstimate();
        double inputAngle=0;

        if (joystickWrapper.gamepad1GetLeftStickUsed(.4)){
            inputAngle = joystickWrapper.gamepad1GetLeftStickAngle();
        }else{
            inputAngle=0;
        }

        Vector2d input = QuikMaths.a2s(inputAngle+(Math.toDegrees(myPose.getHeading() + 180) % 360 - 180));
        double y = input.getY();// Remember, this is reversed! | Defining the y variable
        double x = input.getX(); // Counteract imperfect strafing | Defining the x variable
        double rx = -joystickWrapper.gamepad1GetRightStickX() * rotSpeed; // Defining the rx (right x) variable

        telemetry.addData("Input Angle: ", joystickWrapper.gamepad1GetLeftStickAngle()+(Math.toDegrees(myPose.getHeading() + 180) % 360 - 180));
        telemetry.addData("Joystick: ", joystickWrapper.gamepad1GetLeftStickAngle());
        telemetry.addData("Rotation: ", (Math.toDegrees(myPose.getHeading() + 180) % 360 - 180))
        ;
        telemetry.addData("Y-Input: ",y);
        telemetry.addData("X-Input: ",x);
        telemetry.addData("Input: ", input);

        telemetry.update();
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

