package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ADD extends LinearOpMode {

    DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
    DcMotor motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
    DcMotor motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
    DcMotor motorBackRight = hardwareMap.dcMotor.get("motorBackRight");

    @Override
    public void runOpMode() throws InterruptedException {
        // Read joystick input
// Get the X and Y axis values from the left joystick
        float x = gamepad1.left_stick_x;
        float y = -gamepad1.left_stick_y; // Invert the Y-axis value since the FTC SDK reads up as negative and down as positive.

// Scale input
// Apply exponential scaling to the joystick input for smoother control
        float scaledX = x * Math.abs(x);
        float scaledY = y * Math.abs(y);

// Apply a deadzone
// Implement a deadzone to ignore small movements in the joystick
        float deadzone = 0.1f;

        if (Math.abs(scaledX) < deadzone) {
            scaledX = 0;
        }

        if (Math.abs(scaledY) < deadzone) {
            scaledY = 0;
        }

// Calculate motor powers
// Calculate the motor powers based on the scaled input values
// For a tank-drive system
        float leftPower = scaledY + scaledX;
        float rightPower = scaledY - scaledX;

// For a mecanum-drive system
        float rotation = gamepad1.right_stick_x;
        float frontLeftPower = scaledY + scaledX - rotation;
        float frontRightPower = scaledY - scaledX + rotation;
        float backLeftPower = scaledY - scaledX - rotation;
        float backRightPower = scaledY + scaledX + rotation;

// Implement a power ramping function
// Gradually change motor powers over time to prevent sudden movements
        float rampRate = 0.05f;
        float[] previousPowers = {0, 0, 0, 0}; // Store previous motor powers

// For each motor, calculate the new power based on the ramp rate
        float[] motorPowers = new float[0];
        for (int i = 0; i < motorPowers.length; i++) {
            float newPower = motorPowers[i];
            float previousPower = previousPowers[i];
            float deltaPower = newPower - previousPower;

            if (Math.abs(deltaPower) > rampRate) {
                newPower = previousPower + Math.signum(deltaPower) * rampRate;
            }

            previousPowers[i] = newPower;
            motorPowers[i] = newPower;
        }

// Set motor powers
// Assign the calculated motor powers to the drive motors
// For a tank-drive system
// leftMotor.setPower(leftPower);
// rightMotor.setPower(rightPower);

// For a mecanum-drive system
        motorFrontLeft.setPower(frontLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackLeft.setPower(backLeftPower);
        motorBackRight.setPower(backRightPower);
    }
}
