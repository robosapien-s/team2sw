package org.firstinspires.ftc.teamcode.testopmodes;
//dfjskla;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
@TeleOp
@Disabled
public class BriansRobotTest extends LinearOpMode {
    JoystickWrapper joystickWrapper;
    ExtensionArmWrapper extensionArmWrapper;
    DcMotor slideMotor;
    //DrivingWrapper drivingWrapper;
    @Override
    public void runOpMode(){
        joystickWrapper = new JoystickWrapper(gamepad1, gamepad2);  //see JoystickWrapper
        extensionArmWrapper = new ExtensionArmWrapper(hardwareMap, telemetry);
        slideMotor = hardwareMap.get(DcMotor.class, "slideMotor");
        //drivingWrapper = new DrivingWrapper(hardwareMap, telemetry); //see DrivingWrappee
        waitForStart();
        while (!isStopRequested()) {
            slideMotor.setPower(joystickWrapper.gamepad2GetRightStickY()*0.25);
        }
    }
}
