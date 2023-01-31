package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;

@TeleOp
public class RSLinearOp_PP extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionWrapperCompetition extensionArmWrapper;
    DrivingWrapperCompetition drivingWrapper;

    double speed = 0.5;
    double rotspeed = 0.5;;

    boolean open = true;
    @Override
    public void runOpMode() {
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);
        extensionArmWrapper = new ExtensionWrapperCompetition(hardwareMap, telemetry);
        drivingWrapper = new DrivingWrapperCompetition(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested()){
            extensionArmWrapper.PPArmMove(joystickWrapper);
            drivingWrapper.Drive(joystickWrapper, speed, rotspeed);
        }
    }
}
