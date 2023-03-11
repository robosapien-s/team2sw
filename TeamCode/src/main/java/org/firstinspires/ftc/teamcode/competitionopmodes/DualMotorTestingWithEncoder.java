package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class DualMotorTestingWithEncoder extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;

    @Override
    public void runOpMode() {
        while (!isStopRequested()){
            //Motor 0
            motor1 = hardwareMap.dcMotor.get("motorBackRight"); //setting up the motors with hardwaremaps
            //Motor 2
            motor2 = hardwareMap.dcMotor.get("motorBackLeft");

            //slideMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        }

    }
}
