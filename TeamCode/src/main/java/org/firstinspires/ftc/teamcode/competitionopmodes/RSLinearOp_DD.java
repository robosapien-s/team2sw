package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperClassBased;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperPara;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperDD;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp(group = "TeleOp")
public class
RSLinearOp_DD extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionWrapperDD extensionArmWrapper;
    DrivingWrapperClassBased drivingWrapper;

    double speed = .75;
    double rotspeed = .75;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionWrapperDD(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapperClassBased(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            if(joystickWrapper.gamepad1GetLeftStick()){
                speed = 1;
            }else {
                speed = .75;
            }
            if(joystickWrapper.gamepad1GetRightStick()){
                rotspeed = 1;
            }else {
                rotspeed = .75;
            }
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
