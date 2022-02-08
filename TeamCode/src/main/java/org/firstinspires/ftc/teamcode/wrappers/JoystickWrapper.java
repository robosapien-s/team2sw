package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class JoystickWrapper {

    Gamepad gamepad1;  //setting a variable gamepad of type Gamepad
    Gamepad gamepad2;


    //all the different variables to track if keys are pressed or not.

    boolean gamepad1xPressed = false;
    boolean gamepad1yPressed = false;
    boolean gamepad1aPressed = false;
    boolean gamepad1bPressed = false;

    boolean gamepad1dUpPressed = false;
    boolean gamepad1dDownPressed = false;
    boolean gamepad1dRightPressed = false;
    boolean gamepad1dLeftPressed = false;

    boolean gamepad1leftBumperPressed = false;
    boolean gamepad1rightBumperPressed = false;

    boolean gamepad1leftStickPressed = false;
    boolean gamepad1rightStickPressed = false;

    boolean gamepad2xPressed = false;
    boolean gamepad2yPressed = false;
    boolean gamepad2aPressed = false;
    boolean gamepad2bPressed = false;

    boolean gamepad2dUpPressed = false;
    boolean gamepad2dDownPressed = false;
    boolean gamepad2dRightPressed = false;
    boolean gamepad2dLeftPressed = false;

    boolean gamepad2leftBumperPressed = false;
    boolean gamepad2rightBumperPressed = false;


    public JoystickWrapper(Gamepad inGamepad1, Gamepad inGamepad2) {
        gamepad1 = inGamepad1; //Making a reference to the type Gampad in opmodes
        gamepad2 = inGamepad2;
    }

    /*Everything below is what happens in certain cases of keys being pressed to prevent them from
     being pressed multiple times when we don't want them to
     */
    public boolean gamepad1GetX() {
        if (!gamepad1xPressed && gamepad1.x) {
            gamepad1xPressed = true;
            return true;
        } else if (gamepad1xPressed && !gamepad1.x) {
            gamepad1xPressed = false;
            return false;
        } else {
            return false;
        }
    }


    public boolean gamepad1GetY() {
        if (!gamepad1yPressed && gamepad1.y) {
            gamepad1yPressed = true;
            return true;
        } else if (gamepad1yPressed && !gamepad1.y) {
            gamepad1yPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetA() {
        if (!gamepad1aPressed && gamepad1.a) {
            gamepad1aPressed = true;
            return true;
        } else if (gamepad1aPressed && !gamepad1.a) {
            gamepad1aPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetB() {
        if (!gamepad1bPressed && gamepad1.b) {
            gamepad1bPressed = true;
            return true;
        } else if (gamepad1bPressed && !gamepad1.b) {
            gamepad1bPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetDUp() {
        if (!gamepad1dUpPressed && gamepad1.dpad_up) {
            gamepad1dUpPressed = true;
            return true;
        } else if (gamepad1dUpPressed && !gamepad1.dpad_up) {
            gamepad1dUpPressed = false;
            return false;
        } else {
            return false;
        }
    }


    public boolean gamepad1GetDDown() {
        if (!gamepad1dDownPressed && gamepad1.dpad_down) {
            gamepad1dDownPressed = true;
            return true;
        } else if (gamepad1dDownPressed && !gamepad1.dpad_down) {
            gamepad1dDownPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetDRight() {
        if (!gamepad1dRightPressed && gamepad1.dpad_right) {
            gamepad1dRightPressed = true;
            return true;
        } else if (gamepad1dRightPressed && !gamepad1.dpad_right) {
            gamepad1dRightPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetDLeft() {
        if (!gamepad1dLeftPressed && gamepad1.dpad_left) {
            gamepad1dLeftPressed = true;
            return true;
        } else if (gamepad1dLeftPressed && !gamepad1.dpad_left) {
            gamepad1dLeftPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetLeftBumper() {
        if (!gamepad1leftBumperPressed && gamepad1.left_bumper) {
            gamepad1leftBumperPressed = true;
            return true;
        } else if (gamepad1leftBumperPressed && !gamepad1.left_bumper) {
            gamepad1leftBumperPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetRightBumper() {
        if (!gamepad1rightBumperPressed && gamepad1.right_bumper) {
            gamepad1rightBumperPressed = true;
            return true;
        } else if (gamepad1rightBumperPressed && !gamepad1.right_bumper) {
            gamepad1rightBumperPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetLeftStick() {
        if (!gamepad1leftStickPressed && gamepad1.left_stick_button) {
            gamepad1leftStickPressed = true;
            return true;
        } else if (gamepad1leftStickPressed && !gamepad1.left_stick_button) {
            gamepad1leftStickPressed = false;
            return false;
        } else {
            return false;
        }
    }
    public boolean gamepad1GetRightStick() {
        if (!gamepad1rightStickPressed && gamepad1.right_stick_button) {
            gamepad1rightStickPressed = true;
            return true;
        } else if (gamepad1rightStickPressed && !gamepad1.right_stick_button) {
            gamepad1rightStickPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad1GetRightBumperRaw() {
        return gamepad1.right_bumper;
    }

    public boolean gamepad1GetLeftBumperRaw() {
        return gamepad1.left_bumper;
    }

    public boolean gamepad1GetRightStickDown() { return gamepad1.right_stick_button; }

    public boolean gamepad1GetLeftStickDown() { return gamepad1.left_stick_button; }

    public double gamepad1GetRightTrigger() {
        return gamepad1.right_trigger;
    }

    public double gamepad1GetLeftTrigger() {
        return gamepad1.left_trigger;
    }

    public double gamepad1GetRightStickX() {
        return gamepad1.right_stick_x;
    }

    public double gamepad1GetRightStickY() {
        return gamepad1.right_stick_y;
    }

    public double gamepad1GetLeftStickX() {
        return gamepad1.left_stick_x;
    }

    public double gamepad1GetLeftStickY() {
        return gamepad1.left_stick_y;
    }


    public boolean gamepad2GetX() {
        if (!gamepad2xPressed && gamepad2.x) {
            gamepad2xPressed = true;
            return true;
        } else if (gamepad2xPressed && !gamepad2.x) {
            gamepad2xPressed = false;
            return false;
        } else {
            return false;
        }
    }


    public boolean gamepad2GetY() {
        if (!gamepad2yPressed && gamepad2.y) {
            gamepad2yPressed = true;
            return true;
        } else if (gamepad2yPressed && !gamepad2.y) {
            gamepad2yPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetA() {
        if (!gamepad2aPressed && gamepad2.a) {
            gamepad2aPressed = true;
            return true;
        } else if (gamepad2aPressed && !gamepad2.a) {
            gamepad2aPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetB() {
        if (!gamepad2bPressed && gamepad2.b) {
            gamepad2bPressed = true;
            return true;
        } else if (gamepad2bPressed && !gamepad2.b) {
            gamepad2bPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetDUp() {
        if (!gamepad2dUpPressed && gamepad2.dpad_up) {
            gamepad2dUpPressed = true;
            return true;
        } else if (gamepad2dUpPressed && !gamepad2.dpad_up) {
            gamepad2dUpPressed = false;
            return false;
        } else {
            return false;
        }
    }


    public boolean gamepad2GetDDown() {
        if (!gamepad2dDownPressed && gamepad2.dpad_down) {
            gamepad2dDownPressed = true;
            return true;
        } else if (gamepad2dDownPressed && !gamepad2.dpad_down) {
            gamepad2dDownPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetDRight() {
        if (!gamepad2dRightPressed && gamepad2.dpad_right) {
            gamepad2dRightPressed = true;
            return true;
        } else if (gamepad2dRightPressed && !gamepad2.dpad_right) {
            gamepad2dRightPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetDLeft() {
        if (!gamepad2dLeftPressed && gamepad2.dpad_left) {
            gamepad2dLeftPressed = true;
            return true;
        } else if (gamepad2dLeftPressed && !gamepad2.dpad_left) {
            gamepad2dLeftPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetLeftBumper() {
        if (!gamepad2leftBumperPressed && gamepad2.left_bumper) {
            gamepad2leftBumperPressed = true;
            return true;
        } else if (gamepad2leftBumperPressed && !gamepad2.left_bumper) {
            gamepad2leftBumperPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetRightBumper() {
        if (!gamepad2rightBumperPressed && gamepad2.right_bumper) {
            gamepad2rightBumperPressed = true;
            return true;
        } else if (gamepad2rightBumperPressed && !gamepad2.right_bumper) {
            gamepad2rightBumperPressed = false;
            return false;
        } else {
            return false;
        }
    }

    public boolean gamepad2GetRightBumperRaw() {
        return gamepad2.right_bumper;
    }

    public boolean gamepad2GetLeftBumperRaw() {
        return gamepad2.left_bumper;
    }

    public boolean gamepad2GetRightStickDown() { return gamepad2.right_stick_button; }

    public boolean gamepad2GetLeftStickDown() {
        return gamepad2.left_stick_button;
    }

    public double gamepad2GetRightTrigger() {
        return gamepad2.right_trigger;
    }

    public double gamepad2GetLeftTrigger() {
        return gamepad2.left_trigger;
    }

    public double gamepad2GetRightStickX() {
        return gamepad2.right_stick_x;
    }

    public double gamepad2GetRightStickY() {
        return gamepad2.right_stick_y;
    }

    public double gamepad2GetLeftStickX() {
        return gamepad2.left_stick_x;
    }

    public double gamepad2GetLeftStickY() {
        return gamepad2.left_stick_y;
    }
}
