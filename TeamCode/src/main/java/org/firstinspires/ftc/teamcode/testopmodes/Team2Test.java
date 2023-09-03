package org.firstinspires.ftc.teamcode.testopmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.wrappers.JoystickWrapper;
import org.firstinspires.ftc.teamcode.wrappers.ExtensionArmWrapper;
@TeleOp
public class Team2Test extends LinearOpMode {
    DcMotor fLM; //Front Left Motor
    DcMotor fRM; //Front Right Motor
    DcMotor bLM; //Back Left Motor
    DcMotor bRM; //Back Right Motor
    //Motor Mapping Definitions↑
    @Override
    public void runOpMode(){
        fLM = hardwareMap.get(DcMotor.class, "frontLeft");
        fRM = hardwareMap.get(DcMotor.class, "frontRight");
        bLM = hardwareMap.get(DcMotor.class, "backLeft");
        bRM = hardwareMap.get(DcMotor.class, "backRight");
        waitForStart();
        while (!isStopRequested()) {

        }
    }
    public void forward(float speed){ //Function defining going forward with a speed parameter
        fLM.setPower(-speed);
        fRM.setPower(-speed);
        bLM.setPower(speed);
        bRM.setPower(speed);
    }
    public void backward(float speed){ //Function defining going backward with a speed parameter
        fLM.setPower(speed);
        fRM.setPower(speed);
        bLM.setPower(-speed);
        bRM.setPower(-speed);
    }
    public void strafeRight(float speed){ //Function defining strafing right with a speed parameter
        fLM.setPower(speed);
        fRM.setPower(-speed);
        bLM.setPower(speed);
        bRM.setPower(-speed);
    }
    public void strafeLeft(float speed){ //Function defining strafing left with a speed parameter
        fLM.setPower(-speed);
        fRM.setPower(speed);
        bLM.setPower(-speed);
        bRM.setPower(speed);
    }
    public void right(float speed){ //Function defining turning right with a speed parameter
        fLM.setPower(-speed);
        fRM.setPower(-speed);
        bLM.setPower(-speed);
        bRM.setPower(-speed);
    }
    public void left(float speed) { //Function defining turning left with a speed parameter
        fLM.setPower(speed);
        fRM.setPower(speed);
        bLM.setPower(speed);
        bRM.setPower(speed);
    }
}