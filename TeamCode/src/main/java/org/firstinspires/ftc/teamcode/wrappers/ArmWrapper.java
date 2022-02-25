package org.firstinspires.ftc.teamcode.wrappers;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

public class ArmWrapper {
    HardwareMap hardwareMap;
    DcMotorEx armMotor;
    Servo armServo;
    CRServo servo1;
    CRServo servo2;
    Telemetry telemetry;

    public ArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        //Servo 0
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        //Servo 1
        servo2 = hardwareMap.get(CRServo.class, "servo2");// defining the servos' hardware maps


        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        //Servo 2
        armServo = hardwareMap.get(Servo.class, "armServo");

        telemetry = inTelemetry;
    }
    boolean started = false;
    public enum Level {
        START,
        ONE,
        TWO,
        THREE,
        BACK,
        TWODOWN
    }
    Level level = Level.START;
    int level2int(Level inLevel) {
        if (inLevel == Level.START) {
            return 0;
        } else if (inLevel == Level.ONE) {
            return 1;
        } else if (inLevel == Level.TWO) {
            return 2;
        } else if(inLevel == Level.THREE){
            return 3;
        }else if (inLevel == Level.BACK){
            return 4;
        }else {
        return 5;
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
    int[] levelPositions = {1000, 1500, 2500, 3500, 7000,2500};
    double[] servoPositions = {.38, .38, .31, 0.23, .41,0};

    public boolean init(boolean started) {
        if (!started) {
            armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            armMotor.setPower(.5);

            armMotor.setTargetPosition(-620);


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
        armServo.setPosition(servoPositions[0]);

        SetLevel(0);

    }

    public void ArmMove(JoystickWrapper joystickWrapper) {
//        started = init(started);
        armServo.setDirection(Servo.Direction.REVERSE);
        double armPower = 1;
        Level previousLevel = level;
        if (joystickWrapper.gamepad2GetX()) {
            telemetry.addData("input","x");
            level = Level.START;
            //telemetry.addData("KeyPressed","X");
        }
        if (joystickWrapper.gamepad2GetY()) {
            level = Level.ONE;
            telemetry.addData("input","y");
            //telemetry.addData("KeyPressed","Y");
        }
        if (joystickWrapper.gamepad2GetA()) {
            level = Level.TWO;
            telemetry.addData("input","a");
            //telemetry.addData("KeyPressed","A");
        }
        if (joystickWrapper.gamepad2GetB()) {
            level = Level.THREE;
            telemetry.addData("input","b");
            //telemetry.addData("KeyPressed","B");
        }
        if (joystickWrapper.gamepad2GetRightStickDown()){
            level = Level.BACK;
            telemetry.addData("input","RD");
            //telemetry.addData("KeyPressed","B");
        }
        if (joystickWrapper.gamepad2GetLeftStickDown()){
            level = Level.TWODOWN;
            telemetry.addData("input","RD");
            //telemetry.addData("KeyPressed","B");
        }
        telemetry.update();
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
        if (joystickWrapper.gamepad2GetDRight()){
            armServo.setPosition(armServo.getPosition()-.05);
        }
        if (joystickWrapper.gamepad2GetDLeft()){
            armServo.setPosition(armServo.getPosition()+.05);
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
        }else if(lev == 4){
            level = Level.BACK;
        }
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
    public void Intake(double inputSpeed) {
        if (servo1.getPower()!=inputSpeed){
            telemetry.addData("IntakeReverse",inputSpeed);
            servo1.setPower(inputSpeed);
            servo2.setPower(-inputSpeed);
        }
        telemetry.update();
    }
    public void IntakeReverse(double inputSpeed) {
        if (servo2.getPower()!=inputSpeed){
            telemetry.addData("IntakeReverse",inputSpeed);
            servo1.setPower(-inputSpeed);
            servo2.setPower(inputSpeed);
        }
        telemetry.update();
    }
    public void StopIntake(){
        servo1.setPower(0);
        servo2.setPower(0);
        telemetry.addData("Intake","Stop");
    }

}
