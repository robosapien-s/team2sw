package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;
import org.firstinspires.ftc.teamcode.autonomous.IRunnableTeleOp;
import org.firstinspires.ftc.teamcode.autonomous.SharedShipping;
import org.firstinspires.ftc.teamcode.autonomous.TestRunnable;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.wrappers.PowerPlayArmWrapper;
@TeleOp
public class BriansRobotTest extends LinearOpMode {
    JoystickWrapper joystickWrapper;

    DcMotor bottomMotor;
    // hiii

    @Override
    public void runOpMode(){
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        //powerPlayArmWrapper = new PowerPlayArmWrapper(hardwareMap, telemetry);
        //drivingWrapper = new DrivingWrapper(hardwareMap, telemetry); //see DrivingWrapper
        bottomMotor = hardwareMap.get(DcMotor.class, "bottomMotor");
        waitForStart();
        while (!isStopRequested()) {
            telemetry.addData("JoystickLeftStickValue",String.valueOf(joystickWrapper.gamepad2GetRightStickX()));
            bottomMotor.setPower(joystickWrapper.gamepad2GetRightStickX());
        }
    }
}
