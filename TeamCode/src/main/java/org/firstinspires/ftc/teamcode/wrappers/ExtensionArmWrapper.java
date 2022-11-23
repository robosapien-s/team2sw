package org.firstinspires.ftc.teamcode.wrappers;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.util.QuikMaths;

public class ExtensionArmWrapper {

    HardwareMap hardwareMap;
    DcMotorEx armMotor;
    Servo armServo;
    Telemetry telemetry;

    public ExtensionArmWrapper(HardwareMap inHardwareMap, Telemetry inTelemetry) {
        hardwareMap = inHardwareMap;
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        armServo = hardwareMap.get(Servo.class, "armServo");
        telemetry = inTelemetry;
    }


}
