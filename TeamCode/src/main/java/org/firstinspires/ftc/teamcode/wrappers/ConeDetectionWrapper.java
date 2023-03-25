package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ConeDetectionWrapper {
    private final DrivingWrapperPara drivingWrapper;
    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private final Mat workingImage;

    private final Scalar lowerBound;
    private final Scalar upperBound;

    private final double DRIVE_POWER = 0.5;
    private final double ROTATE_POWER = 0.3;

    public ConeDetectionWrapper(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.drivingWrapper = new DrivingWrapperPara(hardwareMap, telemetry);
        this.workingImage = new Mat();

        this.lowerBound = new Scalar(10, 100, 100);
        this.upperBound = new Scalar(30, 255, 255);
    }

    public void coneDetectionLoop() {
        while (!Thread.currentThread().isInterrupted()) {
            //Mat input = drivingWrapper.getWorkingImage();    //doesn't work

           // Imgproc.cvtColor(input, workingImage, Imgproc.COLOR_RGB2HSV);

            Mat mask = new Mat();
            Core.inRange(workingImage, lowerBound, upperBound, mask);

            // Detect cones and drive towards them
            // ...

            // Drive the robot
           // drivingWrapper.Drive(joystickWrapper, DRIVE_POWER, ROTATE_POWER); //doesn;t work
        }
    }


}
