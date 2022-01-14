package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.A;

import java.util.List;


@TeleOp(name="RS_TeleOp")
public class RS_TeleOp extends OpMode {



    JoystickWrapper joystickWrapper;
    DrivingWrapper drivingWrapper;
    ArmWrapper armWrapper;

    double speed = 1;

    DcMotor crMotor;
    /**
     * See all: JoystickWrapper, DrivingWrapper, and ArmWrapper
     */


    ElapsedTime stateTimer = new ElapsedTime();
    //Used to keep the time during a TeleOp. We don't use this currently


    @Override
    public void init() {

        stateTimer.reset(); //resets the timer everytime the code is initialized
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        drivingWrapper = new DrivingWrapper(hardwareMap, telemetry); //see DrivingWrapper
        armWrapper = new ArmWrapper(hardwareMap, telemetry); //see ArmWrapper
        crMotor  = hardwareMap.get(DcMotor.class, "carouselMotor");
//        armWrapper.started = armWrapper.init(armWrapper.started);


    }
    @Override
    public void loop() {

        drivingWrapper.Drive(joystickWrapper, speed); //Driving code. See DrivingWrapper
        armWrapper.ArmMove(joystickWrapper);
        //Everything below is what happens when every key is pressed, besides the joysticks because they are used to drive (above).


        if (joystickWrapper.gamepad1GetA()){
            speed = .25;
        }
        if (joystickWrapper.gamepad1GetB()){
            speed = .5;
        }
        if (joystickWrapper.gamepad1GetX()){
            speed = .75;
        }
        if (joystickWrapper.gamepad1GetY()){
            speed = 1;
        }

        telemetry.addData("speed", speed);

        if (joystickWrapper.gamepad2GetDUp()) {
            telemetry.addData("KeyPressed:", "DUp");
        }

        if (joystickWrapper.gamepad2GetDDown()) {
            telemetry.addData("KeyPressed:", "DDown");
        }

        if (joystickWrapper.gamepad2GetDRight()) {
            telemetry.addData("KeyPressed:", "DRight");
        }

        if (joystickWrapper.gamepad2GetDLeft()) {
            telemetry.addData("KeyPressed:", "DLeft");
        }
        if (joystickWrapper.gamepad2GetLeftBumper()) {
            telemetry.addData("KeyPressed:", "Left Bumper");
        }
        if (joystickWrapper.gamepad2GetRightBumper()) {
            telemetry.addData("KeyPressed:", "Right Bumper");
        }
        if (joystickWrapper.gamepad2GetLeftTrigger() >= .5) {
            armWrapper.Intake(.75);

        }else if (joystickWrapper.gamepad2GetRightTrigger() >= .5) {
            armWrapper.IntakeReverse(1);
        }else{
            armWrapper.StopIntake();
        }

        if (joystickWrapper.gamepad2GetRightBumperRaw()){
            crMotor.setPower(-1);
        }else {
            if (joystickWrapper.gamepad2GetLeftBumperRaw()){
                crMotor.setPower(1);
            }else {
                crMotor.setPower(0);
            }
        }


        telemetry.update();
    }
}
