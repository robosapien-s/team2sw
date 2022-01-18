package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Servo_Test_AS")
public class Servo_Test extends OpMode {

    private CRServo servo1;
    //private boolean servoIsRunning = false;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void init() {
        servo1 = hardwareMap.get(CRServo.class, "servo1");
    }

    @Override
    public void loop() {
        /* Put initialization blocks here.
        servo1.setDirection(DcMotorSimple.Direction.REVERSE);
        servo2.setDirection(DcMotorSimple.Direction.FORWARD);
        // Put run blocks here.
        servo1.setPower(gamepad1.right_trigger);
        servo2.setPower(gamepad1.right_trigger);
        servo1.setPower(-1 * gamepad1.left_trigger);
        servo2.setPower(-1 * gamepad1.left_trigger);


         */
        if (gamepad1.left_trigger > 0) {
            servo1.setPower(-1);
            //servoIsRunning = true;
        }
        else if (gamepad1.right_trigger > 0) {
            servo1.setPower(1);
        }

        else {
            servo1.setPower(0);
        }

    }
}

