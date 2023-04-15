package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;

@Autonomous(name="Left Middle 2", group="Autonomous")
public class AutonomousMiddleBlue extends LinearOpMode {

    @Override
    public void runOpMode() {

        DrivingWrapper driver = new DrivingWrapper(hardwareMap,telemetry);
//        ArmWrapper arm = new ArmWrapper(hardwareMap, telemetry);


        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry,VuforiaWebcamLocalization.ELocation.BLUEMIDDLE ,this);

        waitForStart();


        if(opModeIsActive()) {
            autonomousWrapper.RunAutonomous();
        }


        telemetry.addData("status: ", "done");
    }
}