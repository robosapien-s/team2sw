package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionWrapperCompetition;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
@Disabled
@TeleOp
public class DualMotorTesting extends LinearOpMode {


    @Override
    public void runOpMode() {
        while (!isStopRequested()){
            //Motor 0
            hardwareMap.dcMotor.get("motorBackRight").setPower(gamepad1.left_stick_y); //setting up the motors with hardwaremaps
            //Motor 2
            hardwareMap.dcMotor.get("motorBackLeft").setPower(gamepad1.right_stick_y);


        }

    }
}
