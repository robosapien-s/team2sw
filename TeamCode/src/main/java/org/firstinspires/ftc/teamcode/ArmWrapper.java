package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class ArmWrapper {
    HardwareMap hardwareMap;
    DcMotorEx armMotor;
    Servo armServo;
    CRServo servo1;
    CRServo servo2;
    CRServo carouselServo;
    Telemetry telemetry;

    public ArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");// defining the servos' hardware maps

        carouselServo = hardwareMap.get(CRServo.class, "carouselServo");

        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armServo = hardwareMap.get(Servo.class, "armServo");

        telemetry = inTelemetry;
    }
    boolean started = false;
    public enum Level {
        START,
        ONE,
        TWO,
        THREE
    }
    Level level = Level.START;
    int level2int(Level inLevel) {
        if (inLevel == Level.START) {
            return 0;
        } else if (inLevel == Level.ONE) {
            return 1;
        } else if (inLevel == Level.TWO) {
            return 2;
        } else {
            return 3;
        }
    }
    int getNewPosition(Level previousLevel, Level newLevel) {
        int result = levelPositions[level2int(newLevel)] - levelPositions[level2int(previousLevel)];
        return result;
    }
    double getIntakePosition(Level previousIntakeLevel, Level newIntakeLevel) {
        //double intakeResult = servoPositions[level2int(newIntakeLevel)] - servoPositions[level2int(previousIntakeLevel)];
        return servoPositions[level2int(newIntakeLevel)];
    }
    Double encoderTicks = 537.7;
    int[] levelPositions = {1000, 1500, 2200, 3350};
    double[] servoPositions = {.26, .26, .16, 0.05};

    public boolean init(boolean started) {
        if (!started) {

            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            armMotor.setPower(.5);

            armMotor.setTargetPosition(-1000);


            armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            armServo.setDirection(Servo.Direction.REVERSE);
/*
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

 */

            armServo.setPosition(servoPositions[0]);

            telemetry.addData("position:",armServo.getPosition());

            started = true;
        }
        return started;
    }

    public void ResetArm(){

    }

    public void ArmMove(JoystickWrapper joystickWrapper) {
        started = init(started);
        armServo.setDirection(Servo.Direction.REVERSE);
        double armPower = 1;
        Level previousLevel = level;
        if (joystickWrapper.gamepad2GetX()) {
            System.out.println("x");
            level = Level.START;
            //telemetry.addData("KeyPressed","X");
        }
        if (joystickWrapper.gamepad2GetY()) {
            level = Level.ONE;
            System.out.println("y");
            //telemetry.addData("KeyPressed","Y");
        }
        if (joystickWrapper.gamepad2GetA()) {
            level = Level.TWO;
            System.out.println("a");
            //telemetry.addData("KeyPressed","A");
        }
        if (joystickWrapper.gamepad2GetB()) {
            level = Level.THREE;
            System.out.println("b");
            //telemetry.addData("KeyPressed","B");
        }
        if (joystickWrapper.gamepad2GetDDown()) {
            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            armMotor.setPower(.25);
            armMotor.setTargetPosition(50);
            armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }

        if (joystickWrapper.gamepad2GetDUp()) {
            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            armMotor.setPower(.25);
            armMotor.setTargetPosition(-50);
            armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        }

        if(previousLevel != level) {
            double newServoPosition = getIntakePosition(previousLevel, level);

            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            int newPosition = getNewPosition(previousLevel, level);
            //telemetry.addData("Target Pos:", armMotor.getTargetPosition());
            //telemetry.addData("LEVEL:", level);
            //telemetry.addData("P_LEVEL:", previousLevel);

            //telemetry.addData("Arm Position:", newPosition);
            //telemetry.addData("current position", armMotor.getCurrentPosition());
            armMotor.setPower(armPower);
            armMotor.setTargetPosition(-newPosition);
            armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            telemetry.addData("Arm Pos", armMotor.getPower());

            armServo.setPosition(newServoPosition);
            System.out.println(newServoPosition);

            telemetry.addData("Intake Position:" ,armServo.getPosition());
            telemetry.update();
        }
    }
    public void SetLevel(int lev) {
        started = init(started);
        double armPower = 1;
        Level previousLevel = level;
        if(lev == 0) {
            level = Level.START;
        }else if(lev == 1) {
            level = Level.ONE;
        }else if(lev == 2) {
            level = Level.TWO;
        }else if(lev == 3) {
            level = Level.THREE;
        }else{}
        if(previousLevel != level) {
            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            double newServoPosition = getIntakePosition(previousLevel, level);
            int newPosition = getNewPosition(previousLevel, level);
            telemetry.addData("Target Pos:", armMotor.getTargetPosition());
            //telemetry.addData("LEVEL:", level);
            //telemetry.addData("P_LEVEL:", previousLevel);
            telemetry.addData("New Intake Position:" ,newServoPosition);
            //telemetry.addData("Arm Position:", newPosition);
            //telemetry.addData("current position", armMotor.getCurrentPosition());
            armServo.setPosition(newServoPosition);
            System.out.println(newServoPosition);
            armMotor.setPower(armPower);
            armMotor.setTargetPosition(-newPosition);
            armMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            telemetry.addData("Actual Intake Position", armServo.getPosition());
            telemetry.update();
        }
    }
    public void AutonomousIntake(double time) {
        servo1.setPower(-1);
        servo2.setPower(1);
        try {
            Thread.sleep((long) (1000*time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        servo1.setPower(0);
        servo2.setPower(0);
    }
    public void AutonomousOutput(double time) {
        servo1.setPower(1);
        servo2.setPower(-1);
        try {
            Thread.sleep((long) (1000*time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        servo1.setPower(0);
        servo2.setPower(0);
    }
    public void Intake(double inputSpeed) {
        servo1.setPower(inputSpeed);
        servo2.setPower(-inputSpeed);

    }
    public void IntakeReverse(double inputSpeed) {

        servo1.setPower(-inputSpeed);
        servo2.setPower(inputSpeed);

    }

    public void StartCarousel(double time, double power, boolean wait) {
        carouselServo.setPower(power);
    }
    public void StopCarousel(double time, double power, boolean wait) {
        carouselServo.setPower(0);
    }
    public void Carousel(double time, double power, boolean wait) {
        carouselServo.setPower(power);
    }
}
