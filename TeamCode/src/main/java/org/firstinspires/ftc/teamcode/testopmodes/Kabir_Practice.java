package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;


// This is a comment LOL ¯\_(ツ)_/¯

/* this is also a comment


and also this ¯\_(ツ)_/¯
 */

@TeleOp
public class Kabir_Practice extends OpMode{

    OpenCvDetection OpenCVWrapper;



    @Override
    public void init(){

        OpenCVWrapper = new OpenCvDetection(telemetry, hardwareMap);

        OpenCVWrapper.init(false);

        telemetry.addData("barcode", OpenCVWrapper.barcodeInt);
    }

    @Override
    public void loop() {
        telemetry.addData("Running: ", OpenCVWrapper.barcodeInt);
        telemetry.update();
    }
}
