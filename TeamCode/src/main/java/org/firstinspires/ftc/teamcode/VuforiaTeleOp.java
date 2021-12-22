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

    ColorSensor color;

    VuforiaWrapper vuforiaWrapper;
    @Override
    public void init() {
        vuforiaWrapper = new VuforiaWrapper();
        vuforiaWrapper.init(hardwareMap, telemetry);

    }

    @Override
    public void loop() {
        ArrayList<OpenGLMatrix> matrices = vuforiaWrapper.getMetrics();
        for (OpenGLMatrix matrix: matrices){
            telemetry.addData("value", vuforiaWrapper.formatMatrix(matrix));
        }
        telemetry.update();
    }
}
