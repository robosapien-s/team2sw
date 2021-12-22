package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class MyFirstOpMode extends OpMode {
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor leftBack;

    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        //rightFront.setDirection(DcMotor.Direction.REVERSE);
        //rightBack.setDirection(DcMotor.Direction.REVERSE);
        if (gamepad1.start) {
            start();
        }
    }

    @Override
    public void loop() {
        rightFront.setPower(-gamepad1.right_stick_y);
        rightBack.setPower(-gamepad1.right_stick_y);
        leftFront.setPower(gamepad1.right_stick_y);
        leftBack.setPower(gamepad1.right_stick_y);

        rightFront.setPower(gamepad1.right_stick_x);
        rightBack.setPower(-gamepad1.right_stick_x);
        leftFront.setPower(gamepad1.right_stick_x);
        leftBack.setPower(-gamepad1.right_stick_x);

        rightFront.setPower(gamepad1.left_stick_x);
        rightBack.setPower(gamepad1.left_stick_x);
        leftFront.setPower(gamepad1.left_stick_x);
        leftBack.setPower(gamepad1.left_stick_x);
    }
}
