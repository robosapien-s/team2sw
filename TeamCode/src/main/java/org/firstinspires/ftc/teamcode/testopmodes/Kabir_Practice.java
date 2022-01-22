package org.firstinspires.ftc.teamcode.testopmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection.*;
import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


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
