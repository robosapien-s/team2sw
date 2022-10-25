package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;
import org.firstinspires.ftc.teamcode.autonomous.IRunnableTeleOp;
import org.firstinspires.ftc.teamcode.autonomous.SharedShipping;
import org.firstinspires.ftc.teamcode.autonomous.TestRunnable;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
@TeleOp
public class Test_New_Arm extends LinearOpMode{

    JoystickWrapper joystickWrapper;
    DcMotor bottomMotor;
    DcMotor topMotor;


    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        bottomMotor  = hardwareMap.get(DcMotor.class, "bottomMotor");
        topMotor  = hardwareMap.get(DcMotor.class, "topMotor");
        waitForStart();

        while (!isStopRequested()){
            bottomMotor.setPower(joystickWrapper.gamepad1GetLeftStickX());
            topMotor.setPower(joystickWrapper.gamepad1GetRightStickY());
        }
    }
}
