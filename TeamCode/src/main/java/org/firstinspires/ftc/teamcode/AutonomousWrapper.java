package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonomousWrapper {

    SampleMecanumDrive roadRunner;
    DrivingWrapper driver;
    ArmWrapper arm;
    double speed = .25;
    double rotSpeed = .25;
    HardwareMap hardwareMap;
    Telemetry telemetry;



    public AutonomousWrapper(HardwareMap map, Telemetry inTelemetry){
        hardwareMap = map;
        telemetry = inTelemetry;
        roadRunner = new SampleMecanumDrive(hardwareMap);

        init();
    }

    public void init(){
        driver = new DrivingWrapper(hardwareMap,telemetry);
        arm = new ArmWrapper(hardwareMap, telemetry);



    }

    public void RunAutonomous(VuforiaWebcamLocalization.ELocation location, LinearOpMode opMode){


        telemetry.addData("Location" ,location.toString());

        arm.init(false);
        opMode.sleep(1000);
        arm.SetLevel(2);
        opMode.sleep(1000);
        driver.AutonomousDrive(DrivingWrapper.Direction.FORWARD, .4, speed);
        opMode.sleep(1700);
        driver.AutonomousDriveStop();


        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL){
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, 1, rotSpeed);

        }else if(location == VuforiaWebcamLocalization.ELocation.BLUEHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, 1, rotSpeed);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINRIGHT, 1, rotSpeed);
        }else if(location == VuforiaWebcamLocalization.ELocation.REDHOME) {
            driver.AutonomousDrive(DrivingWrapper.Direction.SPINLEFT, 1, rotSpeed);
        }
        opMode.sleep(600);
        driver.AutonomousDriveStop();

        driver.AutonomousDrive(DrivingWrapper.Direction.FORWARD, 1, speed);

        opMode.sleep(1000);
        driver.AutonomousDriveStop();

        arm.AutonomousOutput(2);

        //Checks if is Carousel, because if we are not going to the carousel, we need to go over the barrier.
        if(location == VuforiaWebcamLocalization.ELocation.BLUECAROUSEL || location == VuforiaWebcamLocalization.ELocation.REDCAROUSEL) {
//            arm.Carousel(3, 1, false);
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 2.5, speed);
            opMode.sleep(4000);
            driver.AutonomousDriveStop();



//            arm.Carousel(3, 1, false);
            opMode.sleep(6000);

        }else {
            driver.AutonomousDrive(DrivingWrapper.Direction.BACKWARD, 1.5, speed*2);
            opMode.sleep(3500);
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
}
