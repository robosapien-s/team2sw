package org.firstinspires.ftc.teamcode.competitionopmodes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;

@Autonomous(name="Carousel Red", group="Iterative Opmode")
public class AutonomousCarouselRed extends LinearOpMode {

    @Override
    public void runOpMode() {
        AutonomousWrapper autonomousWrapper = new AutonomousWrapper(hardwareMap, telemetry,VuforiaWebcamLocalization.ELocation.REDCAROUSEL,this);

        waitForStart();


        if(opModeIsActive()) {
//            autonomousWrapper.RunAutonomous(VuforiaWebcamLocalization.ELocation.REDCAROUSEL, this);

        }


        telemetry.addData("status: ", "done");
    }
}