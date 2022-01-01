package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Home Blue", group="Iterative Opmode")
public class AutonomousHomeBlue extends LinearOpMode {

    @Override
    public void runOpMode() {

        waitForStart();

        DrivingWrapper driver = new DrivingWrapper(hardwareMap,telemetry);
        ArmWrapper arm = new ArmWrapper(hardwareMap, telemetry);

        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry);


        if(opModeIsActive()) {
            autonomousWrapper.RunAutonomous(true, false, this);
        }


        telemetry.addData("status: ", "done");
    }
}