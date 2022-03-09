package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

class MeepMeepTesting {

    final static int IndexStart = 0;
    final static int IndexDrop1 = 1;
    final static int IndexOutsideWH = 2;
    final static int IndexInsideWH = 3;
    final static int IndexOutsideWH2 = 4;
    final static int IndexDrop2 = 5;



    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        IMeepMeepRunnable runnable = new MMRunnableRedHome();
        runnable.run();
    }
}