package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Carousel Red", group="Iterative Opmode")
public class AutonomousCarouselRed extends LinearOpMode {

    @Override
    public void runOpMode() {
        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry);

        waitForStart();


        if(opModeIsActive()) {
            autonomousWrapper.RunAutonomous(VuforiaWebcamLocalization.ELocation.REDCAROUSEL, this);

        }


        telemetry.addData("status: ", "done");
    }
}