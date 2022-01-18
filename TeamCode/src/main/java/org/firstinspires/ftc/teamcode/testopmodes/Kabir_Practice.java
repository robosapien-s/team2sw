package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// This is a comment LOL ¯\_(ツ)_/¯

/* this is also a comment


and also this ¯\_(ツ)_/¯
 */

@TeleOp
public class Kabir_Practice extends OpMode{
    /**
     * This is called when the driver presses INIT
     */
    @Override
    public void init() {
        // This sends to the driver station
        int teamNumber;
        double motorSpeed;
        boolean touchSensorPressed;

        teamNumber = 16072;
        motorSpeed = 0.5;
        touchSensorPressed = true;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Touch Sensor", touchSensorPressed);
    }

    /**
     * This is called repeatedly while OpMode is playing
     */

    @Override
    public void loop() {
        // intentionally left blank
    }
}