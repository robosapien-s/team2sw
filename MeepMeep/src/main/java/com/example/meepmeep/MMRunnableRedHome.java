package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MMRunnableRedHome implements IMeepMeepRunnable {
    @Override
    public void run() {
        MeepMeep meepMeep = new MeepMeep(800);


        Pose2d[] poseLocationsRed = new Pose2d[] {
                new Pose2d(10, -60, Math.toRadians(90)),
                new Pose2d(5,-38,Math.toRadians(130)),
                new Pose2d(10,-60,0),
                new Pose2d(45,-60,0),
                new Pose2d(20,-60, 0),
                new Pose2d(-6,-39, Math.toRadians(-250))

        };
        Pose2d startPose = new Pose2d(10, -60, Math.toRadians(90));
        //Float[] locations = new Pose2d
        //Float[] rotations;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(levelInt);*/})
                                .splineTo(new Vector2d(5,-38),Math.toRadians(120))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.Intake(.25);*/})
                                .waitSeconds(.5)
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineToLinearHeading(new Pose2d(10,-60,0))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(0);*/})
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.IntakeReverse(1);*/})
                                .lineTo(new Vector2d(45,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineTo(new Vector2d(10,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(3);*/})

                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(levelInt);*/})
                                .splineTo(new Vector2d(5,-38),Math.toRadians(120))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.Intake(.25);*/})
                                .waitSeconds(.5)
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineToLinearHeading(new Pose2d(10,-60,0))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                                    /*armWrapper.SetLevel(0);
                                    armWrapper.IntakeReverse(1);*/
                                })
                                .lineTo(new Vector2d(45,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineTo(new Vector2d(10,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(3);*/})

                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(levelInt);*/})
                                .splineTo(new Vector2d(5,-38),Math.toRadians(120))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.Intake(.25);*/})
                                .waitSeconds(.5)
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineToLinearHeading(new Pose2d(10,-60,0))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                                    /*armWrapper.SetLevel(0);
                                    armWrapper.IntakeReverse(1);*/
                                })
                                .lineTo(new Vector2d(45,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineTo(new Vector2d(10,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(3);*/})

                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(levelInt);*/})
                                .splineTo(new Vector2d(5,-38),Math.toRadians(120))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.Intake(.25);*/})
                                .waitSeconds(.5)
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineToLinearHeading(new Pose2d(10,-60,0))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                                    /*armWrapper.SetLevel(0);
                                    armWrapper.IntakeReverse(1);*/
                                })
                                .lineTo(new Vector2d(45,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})
                                .lineTo(new Vector2d(10,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.SetLevel(3);*/})

                                .lineToLinearHeading(new Pose2d(5,-35,Math.toRadians(130)))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.Intake(.25);*/})
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(10,-60,0))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                                    /*armWrapper.SetLevel(0);
                                    armWrapper.IntakeReverse(1);*/
                                })
                                .lineTo(new Vector2d(45,-60))
                                .UNSTABLE_addTemporalMarkerOffset(0,()->{/*armWrapper.StopIntake();*/})

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
