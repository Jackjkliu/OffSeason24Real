package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepAuton {

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = null;

        myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(-90)))
                                //straight
                                .back(25)
                                .forward(10)
                                .turn(Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(-60,-24), Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(40,-36), Math.toRadians(0))
                                //left
                                /*.lineToLinearHeading(new Pose2d(-35,32, Math.toRadians(180)))
                                .back(4)
                                .forward(4)
                                .splineToConstantHeading(new Vector2d(-60,24), Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(40,36), Math.toRadians(0))*/
                                //right
                                /* .lineToLinearHeading(new Pose2d(-35,32,Math.toRadians(0)))
                                 .back(4)
                                 .forward(4)
                                 .splineToConstantHeading(new Vector2d(-60,12), Math.toRadians(180))
                                 .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                                 .splineToConstantHeading(new Vector2d(40,36), Math.toRadians(0))*/
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

}