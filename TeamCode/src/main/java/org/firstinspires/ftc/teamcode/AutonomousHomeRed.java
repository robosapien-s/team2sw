package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Home Red", group="Iterative Opmode")
public class AutonomousHomeRed extends LinearOpMode {
    @Override
    public void runOpMode() {

        waitForStart();

        DrivingWrapper driver = new DrivingWrapper(hardwareMap);
        ArmWrapper arm = new ArmWrapper(hardwareMap, telemetry);

        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry);

        if(opModeIsActive()) {
            autonomousWrapper.RunAutonomous(false, false, this);
        }


        telemetry.addData("status: ", "done");
    }
}