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
        MeepMeep meepMeep = new MeepMeep(800);


        Pose2d[] poseLocationsRed = new Pose2d[] {
                new Pose2d(10, -60, Math.toRadians(90)),
                new Pose2d(5,-38,Math.toRadians(130)),
                new Pose2d(10,-60,0),
                new Pose2d(45,-60,0),
                new Pose2d(20,-60, 0),
                new Pose2d(-6,-39, Math.toRadians(-250))

        };

        //Float[] locations = new Pose2d
        //Float[] rotations;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(poseLocationsRed[IndexStart])



                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop1].getX(),poseLocationsRed[IndexDrop1].getY()),poseLocationsRed[IndexDrop1].getHeading()) //1
                                .lineToLinearHeading(poseLocationsRed[IndexOutsideWH]) //2
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3
                                .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY())) //4
                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(),poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
                                .waitSeconds(.1)
                                .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY()),poseLocationsRed[IndexOutsideWH2].getHeading()) //4
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3


                                .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY())) //4
                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(),poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
                                .waitSeconds(.1)
                                .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY()),poseLocationsRed[IndexOutsideWH2].getHeading()) //4
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3

                                .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY())) //4
                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(),poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
                                .waitSeconds(.1)
                                .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY()),poseLocationsRed[IndexOutsideWH2].getHeading()) //4
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3


                                .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY())) //4
                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(),poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
                                .waitSeconds(.1)
                                .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY()),poseLocationsRed[IndexOutsideWH2].getHeading()) //4
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3


                                .lineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY())) //4
                                .splineTo(new Vector2d(poseLocationsRed[IndexDrop2].getX(),poseLocationsRed[IndexDrop2].getY()), poseLocationsRed[IndexDrop2].getHeading()) //5
                                .waitSeconds(.1)
                                .splineTo(new Vector2d(poseLocationsRed[IndexOutsideWH2].getX(),poseLocationsRed[IndexOutsideWH2].getY()),poseLocationsRed[IndexOutsideWH2].getHeading()) //4
                                .lineTo(new Vector2d(poseLocationsRed[IndexInsideWH].getX(),poseLocationsRed[IndexInsideWH].getY())) //3

                                .build()


                );

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}