package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class OzerDrivingTranslator implements IDrivingTranslator{
    double N = 4;
    double targetX = 0;
    double countX = N;
    double new_targetX = 0;
    double deltaX = 0;
    double startingX = 0;
    double linear_deltaX = 0;
    double motor_outX = 0;
    double targetY = 0;
    double countY = N;
    double new_targetY = 0;
    double deltaY = 0;
    double startingY = 0;
    double linear_deltaY = 0;
    double motor_outY = 0;
    double targetRX = 0;
    double countRX = N;
    double new_targetRX = 0;
    double deltaRX = 0;
    double startingRX = 0;
    double linear_deltaRX = 0;
    double motor_outRX = 0;
    @Override
    public Pose2d update(Pose2d pose2d, Telemetry telemetry) {
        new_targetX = pose2d.getX();
        deltaX = new_targetX - targetX;

        new_targetY = pose2d.getY();
        deltaY = new_targetY - targetY;

        new_targetRX = pose2d.getHeading();
        deltaRX = new_targetRX - targetRX;

        if (deltaX != 0) {
            countX = 2;
            startingX = targetX;
            targetX = new_targetX;
            linear_deltaX = deltaX;
        }


        if (countX < N) {
            countX++;
            motor_outX = startingX + countX / N * linear_deltaX;
        }
        else {
            motor_outX = new_targetX;
        }

        if (deltaY != 0) {
            countY = 2;
            startingY = targetY;
            targetY = new_targetY;
            linear_deltaY = deltaY;
        }


        if (countY < N) {
            countY++;
            motor_outY = startingY + countY / N * linear_deltaY;
        }
        else {
            motor_outY = new_targetY;
        }

        if (deltaRX != 0) {
            countRX = 2;
            startingRX = targetRX;
            targetRX = new_targetRX;
            linear_deltaRX = deltaRX;
        }


        if (countRX < N) {
            countRX++;
            motor_outRX = startingRX + countRX / N * linear_deltaRX;
        }
        else {
            motor_outRX = new_targetRX;
        }

        return new Pose2d(motor_outX,motor_outY,motor_outRX);
    }
}