package org.firstinspires.ftc.teamcode.competitionopmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;

import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;

@Autonomous(name="Left Middle", group="Autonomous")
public class AutonomousCarouselBlue extends LinearOpMode {


    @Override
    public void runOpMode() {

        DrivingWrapper driver = new DrivingWrapper(hardwareMap,telemetry);
//        ArmWrapper arm = new ArmWrapper(hardwareMap, telemetry);

        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry,VuforiaWebcamLocalization.ELocation.BLUECAROUSEL,this);

        waitForStart();


        if(opModeIsActive()) {
            autonomousWrapper.RunAutonomous(VuforiaWebcamLocalization.ELocation.BLUECAROUSEL, this);
        }


        telemetry.addData("status: ", "done");
    }
}