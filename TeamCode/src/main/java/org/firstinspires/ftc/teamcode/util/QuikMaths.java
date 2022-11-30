package org.firstinspires.ftc.teamcode.util;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class QuikMaths {
    public static final double PI = 3.14159265358979323846;
    public static double RadToDeg = 180/PI;
    public static double DegToRad = 0.01745329;
    public static double FtToM = 0.3048;
    public static double MToFt = 3.2808399;
    public static double MiToFt = 5280;
    public static double FtToMi = 1/5280;
    public static double InToFt = 1/12;
    public static double FtToIn = 12;

    public static float Clamp(float min, float max, float n){
        if (n>max){
            return max;
        }
        if (n<min){
            return min;
        }
        return n;
    }

    public static double Atan2(float x, float y){
        return Math.atan2(y,x);
    }
    public static double Atan2(double x, double y){
        return Math.atan2(y,x);
    }
    public static double Atan2(@NonNull Vector2d vector2d){
        return Math.atan2(vector2d.getY(), vector2d.getX());
    }
    public static double Atan2(@NonNull Pose2d pose2d){
        return Math.atan2(pose2d.getY(),pose2d.getX());
    }

    public static double GetAngleFromVector(float x, float y){
        return Atan2(-y, x) * RadToDeg;
    }
    public static double GetAngleFromVector(double x, double y){
        return Atan2(-y, x) * RadToDeg + 90;
    }
    public static double GetAngleFromVector(Vector2d vector2d){
        return Atan2(-vector2d.getY(), vector2d.getX()) * RadToDeg;
    }
    public static double GetAngleFromVector(Pose2d pose2d){
        return Atan2(-pose2d.getY(), pose2d.getX()) * RadToDeg;
    }

    public static void QuikMeths(){
        QuikMeths();
    }

}
