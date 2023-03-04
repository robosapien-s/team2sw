package org.firstinspires.ftc.teamcode.autonomous;

public interface IAutonomousRunner {
    public void run();

    default void initializeServo() {

    }
}