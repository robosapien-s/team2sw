package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.autonomous.IRunnableTeleOp;
import org.firstinspires.ftc.teamcode.autonomous.TestRunnable;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.Encoder;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@Disabled
@TeleOp(name="Teleop")
public class Teleop extends OpMode {

    private Encoder leftEncoder, rightEncoder, frontEncoder;

    private int leftEncoderTicks, rightEncoderTicks, frontEncoderTicks;

    public void init() {
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "motorFrontRight"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "motorFrontLeft"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "motorBackLeft"));
        leftEncoderTicks = leftEncoder.getCurrentPosition();
        rightEncoderTicks = rightEncoder.getCurrentPosition();
        frontEncoderTicks = frontEncoder.getCurrentPosition();
    }

    @Override
    public void loop() {
        telemetry.addData("Left:",leftEncoder.getCurrentPosition()-leftEncoderTicks);
        telemetry.addData("Right: ", rightEncoder.getCurrentPosition() - rightEncoderTicks);
        telemetry.addData("Front: ",frontEncoder.getCurrentPosition() - frontEncoderTicks);
        telemetry.update();
    }
//    @Override
//    public void loop() {
//
//        drivingWrapper.Drive(joystickWrapper, speed); //Driving code. See DrivingWrapper
//        armWrapper.ArmMove(joystickWrapper);
//        //Everything below is what happens when every key is pressed, besides the joysticks because they are used to drive (above).
//
//
//        if (joystickWrapper.gamepad1GetA()){
//            speed = .25;
//        }
//        if (joystickWrapper.gamepad1GetB()){
//            speed = .5;
//        }
//        if (joystickWrapper.gamepad1GetX()){
//            speed = .75;
//        }
//        if (joystickWrapper.gamepad1GetY()){
//            speed = 1;
//        }
//        if (joystickWrapper.gamepad1GetLeftStick()){
//            runnable.Run();
//        }
//
//        telemetry.addData("speed", speed);
//
//        if (joystickWrapper.gamepad2GetDUp()) {
//            telemetry.addData("KeyPressed:", "DUp");
//        }
//
//        if (joystickWrapper.gamepad2GetDDown()) {
//            telemetry.addData("KeyPressed:", "DDown");
//        }
//
//        if (joystickWrapper.gamepad2GetDRight()) {
//            telemetry.addData("KeyPressed:", "DRight");
//        }
//
//        if (joystickWrapper.gamepad2GetDLeft()) {
//            telemetry.addData("KeyPressed:", "DLeft");
//        }
//        if (joystickWrapper.gamepad2GetLeftBumper()) {
//            telemetry.addData("KeyPressed:", "Left Bumper");
//        }
//        if (joystickWrapper.gamepad2GetRightBumper()) {
//            telemetry.addData("KeyPressed:", "Right Bumper");
//        }
//        if (joystickWrapper.gamepad2GetLeftTrigger() >= .5) {
//            armWrapper.IntakeReverse(1);
//
//        }else if (joystickWrapper.gamepad2GetRightTrigger() >= .5) {
//            armWrapper.Intake(.75);
//        }else{
//            armWrapper.StopIntake();
//        }
//
//        if (joystickWrapper.gamepad2GetRightBumperRaw()){
//            crMotor.setPower(-.5);
//        }else {
//            if (joystickWrapper.gamepad2GetLeftBumperRaw()){
//                crMotor.setPower(.5);
//            }else {
//                crMotor.setPower(0);
//            }
//        }
//
//
//        telemetry.update();
//    }
}
