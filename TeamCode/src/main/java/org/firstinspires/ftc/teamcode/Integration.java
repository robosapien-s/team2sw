package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

class Integration {
    double prevA = 0;
    double currA = 0;
    long prevT = 0;
    long currT = 0;
    double deltaA = 0;
    long deltaT = 0;
    double prevV = 0;
    BNO055IMU imu;

    public void Integration(HardwareMap hardwareMap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public double[] getPos() {
        preprocess();
        return z(prevV, deltaA, deltaT, currT, currA);
    }

    public void preprocess() {
        currT = System.nanoTime();
        currA = record();
        deltaA = currA - prevA;
        prevT = currT;
        currT = System.nanoTime();
        deltaT = currT - prevT;
    }

    public double record() {
        //return imu.getGravity();
        return 1.01;
    }

    public double[] z(double initV, double deltaA, long deltaT, long currT, double currA) {
        double[] ret = new double[3];
        ret[0] = currA;
        ret[1] = initV + deltaA / deltaT * currT;
        ret[2] = initV * currT + currT * currT * deltaT / (2 * deltaA);
        prevV = ret[1];
        prevA = currA;
        return ret;
    }
}
