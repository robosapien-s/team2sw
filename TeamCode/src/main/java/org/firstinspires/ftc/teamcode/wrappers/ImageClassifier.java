package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.outoftheboxrobotics.tensorflowapi.ImageClassification.TFICBuilder;
import org.outoftheboxrobotics.tensorflowapi.ImageClassification.TensorImageClassifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageClassifier {

    TensorImageClassifier tfic;
    OpenCvCamera phoneCam;

    HardwareMap hardWareMap;

    Telemetry telemetry;

    public ArrayList<String> SRecognitions = new ArrayList<String>();



    public void Initialize(HardwareMap hardwareMap, Telemetry inTelemetry){

        try {
            telemetry = inTelemetry;
            tfic = new TFICBuilder(hardwareMap, "BarcodeModelMeta.tflite", "1","2","3").setQuantized(true).build();

            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            OpenCvCameraFactory openCvCameraFactory = OpenCvCameraFactory.getInstance();
            phoneCam = openCvCameraFactory.createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
            // OR...  Do Not Activate the Camera Monitor View
            //phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);

            /*
             * Specify the image processing pipeline we wish to invoke upon receipt
             * of a frame from the camera. Note that switching pipelines on-the-fly
             * (while a streaming session is in flight) *IS* supported.
             */
            phoneCam.setPipeline(new ImageClassifier.SamplePipeline());

            /*
             * Open the connection to the camera device. New in v1.4.0 is the ability
             * to open the camera asynchronously, and this is now the recommended way
             * to do it. The benefits of opening async include faster init time, and
             * better behavior when pressing stop during init (i.e. less of a chance
             * of tripping the stuck watchdog)
             *
             * If you really want to open synchronously, the old method is still available.
             */
            phoneCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
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
                    phoneCam.startStreaming(1920, 1080, OpenCvCameraRotation.SIDEWAYS_LEFT);
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

            //tfic.recognize(new Mat())
            //tfic = new TensorImageClassifier(hardwareMap, "BarcodeModelMeta.tflite", false, new Interpreter.Options(), new String[]{"1","2","3"}, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SamplePipeline extends OpenCvPipeline
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





            Mat resizedMat = new Mat();

            Size sz = new Size(224, 224);

            /*
             * Draw a simple box around the middle 1/2 of the entire frame
             */
            Imgproc.rectangle(
                    input,
                    new Point(
                            input.cols()/4,
                            input.rows()/4),
                    new Point(
                            input.cols()*(3f/4f),
                            input.rows()*(3f/4f)),
                    new Scalar(0, 255, 0), 4);

            /**
             * NOTE: to see how to get data from your pipeline to your OpMode as well as how
             * to change which stage of the pipeline is rendered to the viewport when it is
             * tapped, please see {@link PipelineStageSwitchingExample}
             */




            Imgproc.resize(input, resizedMat, sz);


            List<TensorImageClassifier.Recognition> recs = tfic.recognize(resizedMat);

            if(recs.size()>0) {
                telemetry.addData("Running","Recognitions");
                GetCurrentRecognition();
                SRecognitions.add(recs.get(0).getTitle());
            }

            for (TensorImageClassifier.Recognition rec : recs)
            {
                String logVal = rec.getTitle() + ": " + String.valueOf(rec.getConfidence());
            }



            return resizedMat;
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
                phoneCam.pauseViewport();
            }
            else
            {
                phoneCam.resumeViewport();
            }
        }
    }
    public String GetCurrentRecognition(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Integer currValue;

        ArrayList<String> SRecs = (ArrayList<String>) SRecognitions.clone();

        for(String rec : SRecs){
            if(map.containsKey(rec)) {
                currValue = map.get(rec);
                currValue++;
                map.put(rec, currValue);
                telemetry.addData(rec, currValue);
            } else {
                map.put(rec, 1);
            }

        }
        telemetry.update();

        String recognition = "";
        int highCount = 0;
        for(String recognitionKey : map.keySet()) {
            int currCount = map.get(recognitionKey);
            if(currCount>= highCount) {
                recognition = recognitionKey;
                highCount = currCount;
            }
        }
        return recognition;
    }

    public void StopStreaming(){
//        phoneCam.stopStreaming();
        phoneCam.closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {

            }
        });

    }
}
