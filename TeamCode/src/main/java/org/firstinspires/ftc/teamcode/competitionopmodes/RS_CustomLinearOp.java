package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.InputClasses.BasicInput;
import org.firstinspires.ftc.teamcode.InputClasses.BasicRobot;
import org.firstinspires.ftc.teamcode.autonomous.IRunnableTeleOp;
import org.firstinspires.ftc.teamcode.autonomous.TestRunnable;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.wrappers.RobotInputWrapper;

@Disabled
@TeleOp(name="RS_CustomLinearOp")
public class RS_CustomLinearOp extends LinearOpMode {



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
    public void runOpMode() {
        JoystickWrapper JoystickWrapper = new JoystickWrapper(gamepad1,gamepad1);

        BasicInput basicInput = new BasicInput(JoystickWrapper);
        BasicRobot basicRobot = new BasicRobot(hardwareMap);

        RobotInputWrapper inputWrapper = new RobotInputWrapper(basicInput,basicRobot);

        while (!isStopRequested()){
            inputWrapper.Update();
        }
    }
}
