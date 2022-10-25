package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection2;

@TeleOp(name = "Testin")
public class Teleop extends OpMode {

    OpenCvDetection2 opencv;
    @Override
    public void init() {
        opencv = new OpenCvDetection2(telemetry, hardwareMap);
        opencv.init(false);
    }

    @Override
    public void loop() {

    }

    @Override
    public void init_loop(){

    }
}
