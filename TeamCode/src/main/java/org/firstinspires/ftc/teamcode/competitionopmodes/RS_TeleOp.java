package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.autonomous.IRunnableTeleOp;
import org.firstinspires.ftc.teamcode.autonomous.SharedShipping;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

import java.util.Objects;

@Disabled
@TeleOp(name="RS_TeleOp",group = "TeleOp")
public class RS_TeleOp extends OpMode {



    JoystickWrapper joystickWrapper;
    DrivingWrapper drivingWrapper;
    ArmWrapper armWrapper;
    IRunnableTeleOp runnable;

    double speed = 1;
    double rotSpeed = 1;

    DcMotor crMotor;
    /**
     * See all: JoystickWrapper, DrivingWrapper, and ArmWrapper
     */


    ElapsedTime stateTimer = new ElapsedTime();
    //Used to keep the time during a TeleOp. We don't use this currently


    @Override
    public void init() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        stateTimer.reset(); //resets the timer everytime the code is initialized
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        drivingWrapper = new DrivingWrapper(hardwareMap, telemetry); //see DrivingWrapper
        armWrapper = new ArmWrapper(hardwareMap, telemetry); //see ArmWrapper
        crMotor  = hardwareMap.get(DcMotor.class, "carouselMotor");
        runnable = new SharedShipping(drive, armWrapper);
//        armWrapper.started = armWrapper.init(armWrapper.started);


    }
    @Override
    public void loop() {

        drivingWrapper.Drive(joystickWrapper, speed, rotSpeed); //Driving code. See DrivingWrapper
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
        if (joystickWrapper.gamepad1GetLeftStick()){
            runnable.Run();
        }

        telemetry.addData("speed", speed);

        if (joystickWrapper.gamepad1GetDUp()) {
            rotSpeed = 1;
        }

        if (joystickWrapper.gamepad1GetDDown()) {
            rotSpeed = .25;
        }

        if (joystickWrapper.gamepad1GetDRight()) {
            rotSpeed = .75;
        }

        if (joystickWrapper.gamepad1GetDLeft()) {
            rotSpeed = .5;
        }
        if (joystickWrapper.gamepad2GetLeftBumperDown()) {
            telemetry.addData("KeyPressed:", "Left Bumper");
        }
        if (joystickWrapper.gamepad2GetRightBumperDown()) {
            telemetry.addData("KeyPressed:", "Right Bumper");
        }
        if (joystickWrapper.gamepad2GetLeftTrigger() >= .5) {
            armWrapper.IntakeReverse(1);

        }else if (joystickWrapper.gamepad2GetRightTrigger() >= .5) {
            armWrapper.Intake(.25);
        }else{
            armWrapper.StopIntake();
        }

        if (joystickWrapper.gamepad2GetRightBumperRaw()){
            crMotor.setPower(-.5);
        }else {
            if (joystickWrapper.gamepad2GetLeftBumperRaw()){
                crMotor.setPower(.5);
            }else {
                crMotor.setPower(0);
            }
        }


        telemetry.update();
    }
}
