package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.teamcode.util.QuikMaths;

import java.util.ArrayList;
import java.util.List;


// This is a comment LOL ¯\_(ツ)_/¯

/* this is also a comment


and also this ¯\_(ツ)_/¯
 */
public class OpenCvDetection2 {

    OpenCvCamera webcam;
    Point loc;

    HardwareMap hardwareMap;
    Telemetry telemetry;

    public int barcodeInt;

    public OpenCvDetection2(Telemetry inTelemtry, HardwareMap inHardwareMap){
        telemetry = inTelemtry;
        hardwareMap = inHardwareMap;
    }

    Mat cvtMat = new Mat();
    Mat hierarchy = new Mat();

    Mat RedMask = new Mat();
    Mat GreenMask = new Mat();
    Mat BlueMask = new Mat();

    double RedSize;
    double GreenSize;
    double BlueSize;

    int cameraHieght = 960;
    int cameraWidth = 1280;

    public void init(boolean webcamBool) {
        telemetry.addData("pipi und caki,", "in pipi cakalat.");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        if(webcamBool) {
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        } else {
            webcam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);


        }

        // OR...  Do Not Activate the Camera Monitor View
        //phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);

        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */
        webcam.setPipeline(new OpenCvPl());

        /*
         * Open the connection to the camera device. New in v1.4.0 is the ability
         * to open the camera asynchronously, and this is now the recommended way
         * to do it. The benefits of opening async include faster init time, and
         * better behavior when pressing stop during init (i.e. less of a chance
         * of tripping the stuck watchdog)
         *
         * If you really want to open synchronously, the old method is still available.
         */

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the camera to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Also, we specify the rotation that the camera is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */
                webcam.startStreaming(cameraWidth, cameraHieght, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("Error", "openCameraDeviceAsync");
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

    public class OpenCvPl extends OpenCvPipeline
    {
        boolean viewportPaused = false;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */

        @Override
        public Mat processFrame(Mat input)
        {
            /*
             * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
             * will only dereference to the same image for the duration of this particular
             * invocation of this method. That is, if for some reason you'd like to save a copy
             * of this particular frame for later use, you will need to either clone it or copy
             * it to another Mat.
             */


/*
            Scalar minValues = new Scalar(22, 93, 0);
            Scalar maxValues = new Scalar(45, 255, 255);
*/



            //Scalar minValues = new Scalar(0,0,0);
            //Scalar maxValues = new Scalar(255,255,164);

            Red(input);
            Green(input);
            Blue(input);

            int color = QuikMaths.LargestOfThree(RedSize,GreenSize,BlueSize);
            if (color == 0){
                return RedMask;
            }else if(color == 1){
                return GreenMask;
            }else {
                return BlueMask;
            }

            //cvtMat.release();
            //input.release();
            //hierarchy.release();



            //return input;
        }


        public int GetBarcodeInt(){
            return barcodeInt;
        }


        @Override
        public void onViewportTapped()
        {
            /*
             * The viewport (if one was specified in the constructor) can also be dynamically "paused"
             * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
             * when you need your vision pipeline running, but do not require a live preview on the
             * robot controller screen. For instance, this could be useful if you wish to see the live
             * camera preview as you are initializing your robot, but you no longer require the live
             * preview after you have finished your initialization process; pausing the viewport does
             * not stop running your pipeline.
             *
             * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
             */

            viewportPaused = !viewportPaused;

            if(viewportPaused)
            {
                webcam.pauseViewport();
            }
            else
            {
                webcam.resumeViewport();
            }
        }
    }
    public void StopCameraStream(){
        webcam.stopStreaming();
    }

    public Mat Blue(Mat input){
        Imgproc.cvtColor(input, cvtMat, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(cvtMat,cvtMat,new Size(3,3),0);


        Scalar minValues = new Scalar(0, 0, 0);
        Scalar maxValues = new Scalar(50, 50, 255);



        Core.inRange(cvtMat, minValues, maxValues, BlueMask);


        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(BlueMask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);


        //MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        //Rect[] boundRect = new Rect[contours.size()];
        //Point[] centers = new Point[contours.size()];
        //float[][] radius = new float[contours.size()][1];
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint2f contoursPoly = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly, 3, true);
            Rect boundRect = Imgproc.boundingRect(new MatOfPoint(contoursPoly.toArray()));
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(contoursPoly, center, radius);
            if(((boundRect.size().width+boundRect.size().height)/2)>50.0){
                telemetry.addData("Found:", "Blue");
                telemetry.update();
                contoursPoly.release();

                BlueSize = ((boundRect.size().width+boundRect.size().height)/2);

                break;
            }
        }
        return BlueMask;
    }

    public Mat Red(Mat input){
        Imgproc.cvtColor(input, cvtMat, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(cvtMat,cvtMat,new Size(3,3),0);


        Scalar minValues = new Scalar(0, 0, 0);
        Scalar maxValues = new Scalar(255, 50, 50);



        Core.inRange(cvtMat, minValues, maxValues, RedMask);


        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(RedMask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);


        //MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        //Rect[] boundRect = new Rect[contours.size()];
        //Point[] centers = new Point[contours.size()];
        //float[][] radius = new float[contours.size()][1];
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint2f contoursPoly = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly, 3, true);
            Rect boundRect = Imgproc.boundingRect(new MatOfPoint(contoursPoly.toArray()));
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(contoursPoly, center, radius);
            if(((boundRect.size().width+boundRect.size().height)/2)>50.0){
                telemetry.addData("Found:", "Red");
                telemetry.update();
                contoursPoly.release();

                RedSize = ((boundRect.size().width+boundRect.size().height)/2);

                break;
            }

        }
        return RedMask;
    }

    public Mat Green(Mat input){
        Imgproc.cvtColor(input, cvtMat, Imgproc.COLOR_RGB2HSV);
        Imgproc.GaussianBlur(cvtMat,cvtMat,new Size(3,3),0);


        Scalar minValues = new Scalar(0, 0, 0);
        Scalar maxValues = new Scalar(50, 255, 50);



        Core.inRange(cvtMat, minValues, maxValues, GreenMask);


        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(GreenMask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);


        //MatOfPoint2f[] contoursPoly  = new MatOfPoint2f[contours.size()];
        //Rect[] boundRect = new Rect[contours.size()];
        //Point[] centers = new Point[contours.size()];
        //float[][] radius = new float[contours.size()][1];
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint2f contoursPoly = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), contoursPoly, 3, true);
            Rect boundRect = Imgproc.boundingRect(new MatOfPoint(contoursPoly.toArray()));
            Point center = new Point();
            float[] radius = new float[1];
            Imgproc.minEnclosingCircle(contoursPoly, center, radius);
            if(((boundRect.size().width+boundRect.size().height)/2)>50.0){
                telemetry.addData("Found:", "Green");
                telemetry.update();
                contoursPoly.release();

                GreenSize = ((boundRect.size().width+boundRect.size().height)/2);

                break;
            }
        }

        return GreenMask;
    }
}
