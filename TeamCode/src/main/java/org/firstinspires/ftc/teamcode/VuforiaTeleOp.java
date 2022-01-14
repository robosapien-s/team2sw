package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

//import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

import java.util.ArrayList;


@TeleOp(name="VuforiaTeleOp")
public class VuforiaTeleOp extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotorEx armMotor;

    // what is the Color sensor for?
    ColorSensor color;

    VuforiaLocalizerWrapper vuforiaWrapper;
    @Override
    public void runOpMode() {
        vuforiaWrapper = new VuforiaLocalizerWrapper();
        vuforiaWrapper.init(hardwareMap, telemetry);

        waitForStart();

        while (!isStopRequested()){
            Pose2d pose2d = vuforiaWrapper.getLocation();
            if(pose2d!=null){
                telemetry.addData("Location",pose2d.toString());
            }else{
                telemetry.addData("Location","null");
            }
            telemetry.update();
            sleep(100);
        }
     }
}
