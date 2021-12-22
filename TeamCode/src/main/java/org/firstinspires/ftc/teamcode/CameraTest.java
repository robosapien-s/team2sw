package org.firstinspires.ftc.teamcode;

import android.media.Image;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Camera Test ")
public class CameraTest extends OpMode {

    ImageClassifier imageClassifier;

    ColorSensor color;
    @Override
    public void init() {
        imageClassifier = new ImageClassifier();
        imageClassifier.Initialize(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        telemetry.addData(imageClassifier.GetCurrentRecognition(), "ay");
        telemetry.update();
    }
}
