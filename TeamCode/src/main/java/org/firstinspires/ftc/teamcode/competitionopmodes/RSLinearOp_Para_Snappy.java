package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperPara;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperPara_Snappy;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@Disabled
@TeleOp(group = "TeleOp")
public class RSLinearOp_Para_Snappy extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionWrapperCompetition extensionArmWrapper;
    DrivingWrapperPara_Snappy drivingWrapper;

    double speed = .65;
    double rotspeed = .45;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionWrapperCompetition(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapperPara_Snappy(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            if(joystickWrapper.gamepad1GetLeftStick()){
                speed = 1;
            }else {
                speed = .65;
            }
            if(joystickWrapper.gamepad1GetRightStick()){
                rotspeed = .75;
            }else {
                rotspeed = .45;
            }
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
