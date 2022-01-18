package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Motor_test extends LinearOpMode {
    DcMotor Motor1;

    @Override
    public void runOpMode() throws InterruptedException {
        Motor1 = hardwareMap.get(DcMotor.class,"Motor1");
        Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        Motor1.setPower(1);
        Motor1.setTargetPosition(500);
        Motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(500);
        Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor1.setPower(1);
        Motor1.setTargetPosition(500);
        Motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(500);
        Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Motor1.setPower(1);
        Motor1.setTargetPosition(-500);
        Motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(500);
        Motor1.setPower(0);
        Motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
