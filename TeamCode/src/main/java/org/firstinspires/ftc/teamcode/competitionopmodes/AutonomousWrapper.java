package org.firstinspires.ftc.teamcode.competitionopmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.wrappers.ArmWrapper;
import org.firstinspires.ftc.teamcode.wrappers.DrivingWrapper;
import org.firstinspires.ftc.teamcode.wrappers.OpenCvDetection;
import org.firstinspires.ftc.teamcode.testopmodes.VuforiaWebcamLocalization;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class AutonomousWrapper {

    public interface IAutonomousRunner {
        public void run();
    }


    public class BlueHomeRunner implements IAutonomousRunner {


        Trajectory trajectory1;
        Trajectory trajectory2;
        Trajectory trajectory3;
        Trajectory trajectory4;
        Trajectory trajectory5;
        SampleMecanumDrive drive;
        ArmWrapper armWrapper;
        int levelInt = 3;


        BlueHomeRunner(SampleMecanumDrive inDrive, ArmWrapper inArm) {
            drive = inDrive;
            armWrapper = inArm;
        }

        @Override
        public void run() {

            Pose2d startPose = new Pose2d(10, 60, Math.toRadians(-90));

            drive.setPoseEstimate(startPose);

            armWrapper.init(true);
            trajectory1 = drive.trajectoryBuilder(new Pose2d(10,60, Math.toRadians(-90)))
                    .splineTo(new Vector2d(0,40),Math.toRadians(-135))
                    .build();

            trajectory2 = drive.trajectoryBuilder(trajectory1.end()).lineToLinearHeading(new Pose2d(10,58,0)).build();

            trajectory3 = drive.trajectoryBuilder(trajectory2.end()).lineTo(new Vector2d(55,58)).build();

            trajectory4 = drive.trajectoryBuilder(trajectory3.end()).lineTo(new Vector2d(10,58)).build();

            trajectory5 = drive.trajectoryBuilder(trajectory4.end()).lineToLinearHeading(new Pose2d(0,40,Math.toRadians(-135))).build();



            //armWrapper.SetLevel(levelInt);
            drive.followTrajectory(trajectory1);
            armWrapper.IntakeReverse(1);
            drive.followTrajectory(trajectory2);
            armWrapper.Intake(1);
            armWrapper.SetLevel(1);
            drive.followTrajectory(trajectory3);
            armWrapper.StopIntake();

            drive.followTrajectory(trajectory4);

            for (int i = 0; i < 3; i++) {
                PickupDrop();
            }

        }

        public void PickupDrop(){
            drive.followTrajectory(trajectory5);
            armWrapper.IntakeReverse(1);
            drive.followTrajectory(trajectory2);
            drive.followTrajectory(trajectory3);
            armWrapper.Intake(1);
            armWrapper.SetLevel(1);
            drive.followTrajectory(trajectory4);
            armWrapper.SetLevel(levelInt);
        }
    }





    SampleMecanumDrive roadRunner;
    DrivingWrapper driver;
    ArmWrapper arm;
    double speed = .25;
    double rotSpeed = .25;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DcMotor crMotor;

    OpenCvDetection OpenCVWrapper;



    public AutonomousWrapper(HardwareMap map, Telemetry inTelemetry){
        hardwareMap = map;
        telemetry = inTelemetry;
        roadRunner = new SampleMecanumDrive(hardwareMap);
        crMotor  = hardwareMap.get(DcMotor.class, "carouselMotor");
        init();
    }

    public void init(){
        driver = new DrivingWrapper(hardwareMap,telemetry);
        arm = new ArmWrapper(hardwareMap, telemetry);

        OpenCVWrapper = new OpenCvDetection(telemetry, hardwareMap);

        OpenCVWrapper.init(true);

        telemetry.addData("barcode", OpenCVWrapper.barcodeInt);
    }
    public void RunAutonomous(VuforiaWebcamLocalization.ELocation location, LinearOpMode opMode){

        while (OpenCVWrapper.barcodeInt!=0);

        telemetry.addData("Location" ,location.toString());
        telemetry.addData("Barcode", OpenCVWrapper.barcodeInt);


        telemetry.update();


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        IAutonomousRunner runner = null;

        arm.init(false);
        opMode.sleep(1000);
        arm.SetLevel(OpenCVWrapper.barcodeInt);

        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .9, rotSpeed);

        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            runner = new BlueHomeRunner(drive, arm);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, .8, rotSpeed);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .9, rotSpeed);
        }

        runner.run();




        /*
        arm.init(false);
        opMode.sleep(1000);
        arm.SetLevel(OpenCVWrapper.barcodeInt);
        opMode.sleep(1000);
        driver.AutonomousDrive(DrivingWrapper.Direction.FORWARD, .4, speed);
        opMode.sleep(1700);
        driver.AutonomousDriveStop();


        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .9, rotSpeed);

        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, .8, rotSpeed);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, .8, rotSpeed);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .9, rotSpeed);
        }
        opMode.sleep(900);
        driver.AutonomousDriveStop();

        driver.AutonomousDrive(DrivingWrapper.Direction.FORWARD, 1, speed);

        opMode.sleep(1000);
        driver.AutonomousDriveStop();

        arm.Intake(1);
        opMode.sleep(1000);
        arm.StopIntake();


        //Checks if is Carousel, because if we are not going to the carousel, we need to go over the barrier.
        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL) {
//            arm.Carousel(3, 1, false);
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1.5, speed);
            opMode.sleep(1500);
            driver.AutonomousDriveStop();

            arm.ResetArm();

            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .6, rotSpeed);
//            arm.Carousel(3, 1, false);
            opMode.sleep(600);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 2, speed);
            opMode.sleep(2000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.RIGHT, 2.4, speed);
            opMode.sleep(2400);
            driver.AutonomousDriveStop();

            crMotor.setPower(.5);
            opMode.sleep(4000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.LEFT, 2.2, speed);
            opMode.sleep(2200);
            driver.AutonomousDriveStop();


        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1.5, speed);
            opMode.sleep(1500);
            driver.AutonomousDriveStop();

            arm.ResetArm();

            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .6, rotSpeed);
//            arm.Carousel(3, 1, false);
            opMode.sleep(600);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 2, speed);
            opMode.sleep(2000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.RIGHT, 3, speed);
            opMode.sleep(2400);
            driver.AutonomousDriveStop();

            crMotor.setPower(-.5);
            opMode.sleep(4000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.LEFT, 2.2, speed);
            opMode.sleep(2200);
            driver.AutonomousDriveStop();
        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1, speed);
            opMode.sleep(1000);
            driver.AutonomousDriveStop();


            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, .7, rotSpeed);
//            arm.Carousel(3, 1, false);
            opMode.sleep(700);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 2, speed);
            opMode.sleep(2000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.RIGHT, 5, speed);
            opMode.sleep(5000);
            driver.AutonomousDriveStop();

            arm.ResetArm();
            opMode.sleep(5000);

        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1, speed);
            opMode.sleep(1000);
            driver.AutonomousDriveStop();


            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, .7, rotSpeed);
//            arm.Carousel(3, 1, false);
            opMode.sleep(700);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 2, speed);
            opMode.sleep(2000);
            driver.AutonomousDriveStop();

            driver.AutonomousDrive(DrivingWrapper.Direction.LEFT, 5, speed);
            opMode.sleep(5000);
            driver.AutonomousDriveStop();

            arm.ResetArm();
            opMode.sleep(5000);

        }else {
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1.5, speed*2);
            opMode.sleep(1500);
            driver.AutonomousDriveStop();
        }

//
//            driver.AutonomousDrive(DrivingWrapper.Direction.RIGHT, 2, speed);
//            arm.AutonomousArmMove(1);
//            driver.AutonomousDrive(DrivingWrapper.Direction.FORWARD, 1,speed);
//            //arm.AutonomousIntake(1);
//            arm.AutonomousOutput(2);
//            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1,speed);
//            driver.AutonomousDrive(DrivingWrapper.Direction.LEFT, 4,speed);
//            arm.Carousel(10, -1);
        arm.SetLevel(0);

         */

    }
//    public void RunCarousel(boolean isBlue){
//        if(isBlue){
//            driver.AutonomousDrive(DrivingWrapper.Direction.RIGHT, 1, speed);
//        }else {
//            driver.AutonomousDrive(DrivingWrapper.Direction.LEFT, 1, speed);
//            arm.Carousel(2, 1);
//        }
//
//
//    }
    public void RunAutonomousV2(VuforiaWebcamLocalization.ELocation location, LinearOpMode opMode){
//        arm.init(false);
//        opMode.sleep(1000);
//        arm.SetLevel(2);
//        Pose2d start = new Pose2d(0,0);
//        Trajectory tr1;
//        Trajectory tr2;
//        Trajectory tr3;
//        double rot;
//        DrivingWrapper.Direction rotDir;
//        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
//            tr1 = roadRunner.trajectoryBuilder(start).forward(14*2).build();
//            tr2 = roadRunner.trajectoryBuilder(start).forward(17*2).build();
//            tr3 = roadRunner.trajectoryBuilder(start).back(34*2).build();
//            rot = -45;
//            rotDir = DrivingWrapper.Direction.SPINLEFT;
//
//        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
//            tr1 = roadRunner.trajectoryBuilder(start).forward(14*2).build();
//            tr2 = roadRunner.trajectoryBuilder(start).forward(17*2).build();
//            tr3 = roadRunner.trajectoryBuilder(start).back(34*2).build();
//            rot = 45;
//            rotDir = DrivingWrapper.Direction.SPINRIGHT;
//        }else {
//            telemetry.addData("Bad","bad");
//            return;
//        }
//        roadRunner.followTrajectory(tr1);
//        driver.AutonomousDrive(rotDir, 1, rotSpeed);
//        roadRunner.followTrajectory(tr2);
//        arm.IntakeReverse(1);
//        opMode.sleep(1000);
//        arm.StopIntake();
//        roadRunner.followTrajectory(tr3);
//        crMotor.setPower(1);
//        opMode.sleep(1000);
//        crMotor.setPower(0);
    }
    public void RunAutonomousV3(VuforiaWebcamLocalization.ELocation location, LinearOpMode opMode){
//        arm.init(false);
//        opMode.sleep(1000);
//        arm.SetLevel(2);
//        Pose2d start = new Pose2d(0,0);
//        Trajectory tr1;
//        Trajectory tr2;
//        Trajectory tr3;
//        double rot;
//        DrivingWrapper.Direction rotDir;
//        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
//            tr1 = roadRunner.trajectoryBuilder(start).splineTo(new Vector2d(-24,34), roadRunner).build();
//            tr2 = roadRunner.trajectoryBuilder(start).forward(17*2).build();
//            tr3 = roadRunner.trajectoryBuilder(start).back(34*2).build();
//            rot = -45;
//            rotDir = DrivingWrapper.Direction.SPINLEFT;
//
//        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
//            tr1 = roadRunner.trajectoryBuilder(start).forward(14*2).build();
//            tr2 = roadRunner.trajectoryBuilder(start).forward(17*2).build();
//            tr3 = roadRunner.trajectoryBuilder(start).back(34*2).build();
//            rot = 45;
//            rotDir = DrivingWrapper.Direction.SPINRIGHT;
//        }else {
//            telemetry.addData("Bad","bad");
//            return;
//        }
//        roadRunner.followTrajectory(tr1);
//        driver.AutonomousDrive(rotDir, 1, rotSpeed);
//        roadRunner.followTrajectory(tr2);
//        arm.IntakeReverse(1);
//        opMode.sleep(1000);
//        arm.StopIntake();
//        roadRunner.followTrajectory(tr3);
//        crMotor.setPower(1);
//        opMode.sleep(1000);
//        crMotor.setPower(0);
    }
}
