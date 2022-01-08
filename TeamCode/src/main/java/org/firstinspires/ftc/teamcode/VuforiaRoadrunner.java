package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Vuforia Roadrunner Extension")
public class VuforiaRoadrunner extends VuforiaWebcamLocalization{

    @Override
    public void runOpMode(){
        initializeVuforia();
        runVuforia();
        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry);


        autonomousWrapper.RunAutonomous(place, this);



        while (!isStopRequested()){
            runVuforia();
        }

        targets.deactivate();
    }
}
