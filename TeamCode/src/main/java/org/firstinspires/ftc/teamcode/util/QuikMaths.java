package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class QuikMaths {

    public double RadToDeg = 57.2957795,
    DegToRad = 0.01745329,
    FtToM = 0.3048,
    MToFt = 3.2808399,
    MiToFt = 5280,
    FtToMi = 0.00018939,
    InToFt = 0.08333333,
    FtToIn = 12;

    public float Clamp(float min, float max, float n){
        if (n>max){
            return max;
        }
        if (n<min){
            return min;
        }
        return n;
    }

    public double Atan2(float x, float y){
        return Math.atan2(y,x);
    }
    public double Atan2(double x, double y){
        return Math.atan2(y,x);
    }
    public double Atan2(Vector2d vector2d){
        return Math.atan2(vector2d.getY(), vector2d.getX());
    }
    public double Atan2(Pose2d pose2d){
        return Math.atan2(pose2d.getY(),pose2d.getX());
    }

    public double GetAngleFromVector(float x, float y){
       return Atan2(x, y) * RadToDeg;
    }
    public double GetAngleFromVector(double x, double y){
        return Atan2(x, y) * RadToDeg;
    }
    public double GetAngleFromVector(Vector2d vector2d){
        return Atan2(vector2d) * RadToDeg;
    }
    public double GetAngleFromVector(Pose2d pose2d){
        return Atan2(pose2d) * RadToDeg;
    }

    public void QuikMeths(){
        QuikMeths();
    }

}
