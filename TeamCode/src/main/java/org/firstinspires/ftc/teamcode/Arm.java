package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

class Arm {
    // Double total = 537.7;
    int[] levels = {0, 538, 1075, 1613};
    int[] positions = {0, 10, 20, 30};
    DcMotor motor;
    CRServo rightServo;
    CRServo leftServo;
    Servo servo;
    public enum Level {
        START,
        ONE,
        TWO,
        THREE
    }
    Level level = Level.START;
    int levelHelper(Level in) {
        int out=-1;
        /*
        switch (in) {
            case Level.START:
                out = 0;
                break;
            case Level.ONE:
                out = 1;
                break;
            case Level.TWO:
                out = 2;
                break;
            case Level.THREE:
                out = 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + in);
        }
         */
        return out;
    }
    int getArmPosition(Level prev, Level New) {
        return levels[levelHelper(New)] - levels[levelHelper(prev)];
    }
    int getServoPosition(Level prev, Level New) {
        return positions[levelHelper(New)] - positions[levelHelper(prev)];
    }
    public void Arm(DcMotor inMotor, CRServo inRightServo, CRServo inLeftServo, Servo inServo) {
        motor = inMotor;
        rightServo = inRightServo;
        leftServo = inLeftServo;
        servo = inServo;
    }
}
