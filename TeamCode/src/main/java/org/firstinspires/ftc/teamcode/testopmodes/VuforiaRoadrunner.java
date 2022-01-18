package org.firstinspires.ftc.teamcode.testopmodes;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.wrappers.RoadRunnerWrapper;

@TeleOp(name="Vuforia Roadrunner Extension")
public class VuforiaRoadrunner extends VuforiaWebcamLocalization{

    @Override
    public void runOpMode(){
        initializeVuforia();
        runVuforia();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        RoadRunnerWrapper wrapper = new RoadRunnerWrapper(drive, this);

        while (!isStarted());

        wrapper.init();

        while (!isStopRequested()){
            runVuforia();
        }

        targets.deactivate();
    }
}
