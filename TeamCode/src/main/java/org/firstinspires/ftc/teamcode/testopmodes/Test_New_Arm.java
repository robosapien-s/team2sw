package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
//lol
@Disabled
@TeleOp
public class Test_New_Arm extends LinearOpMode{

    JoystickWrapper joystickWrapper;
    ExtensionArmWrapper extensionArmWrapper;
    DrivingWrapper drivingWrapper;

    double speed = 0.5;
    double rotSpeed = 0.5;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        extensionArmWrapper = new ExtensionArmWrapper(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapper(hardwareMap, telemetry); //see DrivingWrapper
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            drivingWrapper.Drive(joystickWrapper, speed, rotSpeed); //Driving code. See DrivingWrapper
        }
    }
}
