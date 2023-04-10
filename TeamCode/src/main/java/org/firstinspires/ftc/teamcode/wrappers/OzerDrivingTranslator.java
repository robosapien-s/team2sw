package org.firstinspires.ftc.teamcode.wrappers;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class OzerDrivingTranslator implements IDrivingTranslator{
    int N=4;
    double oldX = 0;
    double oldY = 0;
    double oldRX = 0;

    double curPowerX = 0;
    double curPowerY = 0;
    double curPowerRX = 0;

    double newPowerX = 0;
    double newPowerY = 0;
    double newPowerRX = 0;

    double x;
    double y;
    double rx;

    int countX = 1;
    int countY = 1;
    int countRX = 1;
    @Override
    public Pose2d update(Pose2d pose2d) {
        double deltaX = oldX - x;
        oldX = x;

        double deltaY = oldY - y;
        oldY = y;

        double deltaRX = oldRX - rx;
        oldRX = rx;

        //the above section should be repeated everytime the function is called

        if (deltaX != 0) {
            countX = 1;
            newPowerX = curPowerX - (countX/N) * deltaX;
            curPowerX = newPowerX;
            countX++;
        } else {
            if (countX > N) {
                countX = N;
            }
            newPowerX = curPowerX - (countX/N) * deltaX;
            countX++;
        }

        if (deltaY != 0) {
            countY = 1;
            newPowerY = curPowerY - (countY/N) * deltaY;
            curPowerY = newPowerY;
            countY++;
        } else {
            if (countY > N) {
                countY = N;
            }
            newPowerY = curPowerY - (countY/N) * deltaY;
            countY++;
        }

        if (deltaRX != 0) {
            countRX = 1;
            newPowerRX = curPowerRX - (countRX/N) * deltaRX;
            curPowerRX = newPowerRX;
            countRX++;
        } else {
            if (countRX > N) {
                countRX = N;
            }
            newPowerRX = curPowerRX - (countRX/N) * deltaRX;
            countRX++;
        }

        return new Pose2d(newPowerX,newPowerY,newPowerRX);
    }
}