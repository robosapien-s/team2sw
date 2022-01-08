package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Vuforia Roadrunner Extension")
public class VuforiaRoadrunner extends VuforiaWebcamLocalization{

    @Override
    public void runOpMode(){
        initializeVuforia();
        runVuforia();
        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry);

        if (this.place == ELocation.BLUECAROUSEL){
                autonomousWrapper.RunAutonomous(true,true, this);
        }else if (this.place == ELocation.BLUEHOME){
            autonomousWrapper.RunAutonomous(true,false, this);
        }else if (this.place == ELocation.REDCAROUSEL){
            autonomousWrapper.RunAutonomous(false,true, this);
        }else if (this.place == ELocation.REDHOME){
            autonomousWrapper.RunAutonomous(false,false, this);
        }


        while (!isStopRequested()){
            runVuforia();
        }
        targets.deactivate();
    }
}
