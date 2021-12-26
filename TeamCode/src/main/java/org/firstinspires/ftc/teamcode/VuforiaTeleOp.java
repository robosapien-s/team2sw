package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

import java.util.ArrayList;


@TeleOp(name="VuforiaTeleOp")
public class VuforiaTeleOp extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotorEx armMotor;

    // what is the Color sensor for?
    ColorSensor color;

    VuforiaLocalizerWrapper vuforiaWrapper;
    @Override
    public void init() {
        vuforiaWrapper = new VuforiaLocalizerWrapper();
        vuforiaWrapper.init(hardwareMap, telemetry);

     }

    @Override
    public void loop() {
        OpenGLMatrix matrix = vuforiaWrapper.getLocation();
        if(matrix!=null){
            telemetry.addData("Location",vuforiaWrapper.getLocation().toString());
        }
        telemetry.update();
    }
}
