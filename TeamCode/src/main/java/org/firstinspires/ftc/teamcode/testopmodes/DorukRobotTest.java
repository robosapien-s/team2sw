package org.firstinspires.ftc.teamcode.testopmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
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
public class DorukRobotTest extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    PowerPlayArmWrapper powerPlayArmWrapper;
    DrivingWrapper drivingWrapper;

    double speed = 0.5;
    double rotspeed = 0.5;;

    boolean open = true;
    @Override

    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        powerPlayArmWrapper = new PowerPlayArmWrapper(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapper(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            powerPlayArmWrapper.PPArmMove(joystickWrapper);
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
