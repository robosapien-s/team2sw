package org.firstinspires.ftc.teamcode.testopmodes;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ConeDetectionPipeline extends OpenCvPipeline {
    // Color range of cone to detect
    private static final Scalar CONE_COLOR_LOWER = new Scalar(0, 100, 100);
    private static final Scalar CONE_COLOR_UPPER = new Scalar(10, 255, 255);

    // Variables for storing cone detection results
    private static boolean coneDetected = false;
    private static Rect coneRect = null;

    // Method for processing each frame from the camera
    @Override
    public Mat processFrame(Mat input) {
        // Convert input frame to HSV color space
        Mat hsv = new Mat();
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        // Create mask for cone color range
        Mat coneMask = new Mat();
        Core.inRange(hsv, CONE_COLOR_LOWER, CONE_COLOR_UPPER, coneMask);

        // Find contours in mask
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(coneMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Check if a single contour was found
        if (contours.size() == 1) {
            // Get bounding rectangle of contour
            coneRect = Imgproc.boundingRect(contours.get(0));

            // Set cone detected flag
            coneDetected = true;

            // Draw bounding rectangle on input frame
            Imgproc.rectangle(input, coneRect, new Scalar(0, 255, 0), 2);
        } else {
            // No cone detected
            coneDetected = false;
            coneRect = null;
        }

        // Resize input frame for display
        Mat resizedInput = new Mat();
        Imgproc.resize(input, resizedInput, new Size(320, 240));

        // Return processed frame
        return resizedInput;
    }

    public static boolean isConeDetected() {
        return coneDetected;
    }

    public static Rect getConeRect() {
        return coneRect;
    }

    public static double getConeX() {
        if (coneRect != null) {
            return coneRect.x + coneRect.width / 2;
        } else {
            return 0.0;
        }
    }

    public static double getConeY() {
        if (coneRect != null) {
            return coneRect.y + coneRect.height / 2;
        } else {
            return 0.0;
        }
    }
}
