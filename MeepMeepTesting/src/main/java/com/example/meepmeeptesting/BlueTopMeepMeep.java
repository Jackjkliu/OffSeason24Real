package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueTopMeepMeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = null;

        myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12,60, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(15,36, Math.toRadians(0)))
                                .back(4)
                                .forward(4)

                                .lineToLinearHeading(new Pose2d(42,36, Math.toRadians(180)))
                                .back(7)

                                .lineToLinearHeading(new Pose2d(38,58, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(60,58, Math.toRadians(180)))

//                                .lineToLinearHeading(new Pose2d(-35,-32, Math.toRadians(180)))
//                                .back(4)
//                                .forward(4)
//
//                                .lineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(10,-8, Math.toRadians(180)))
//                                .splineTo(new Vector2d(40,-36), Math.toRadians(0))
//                                .lineToSplineHeading(new Pose2d(52, -30, Math.toRadians(180)))
//                                .lineTo(new Vector2d(52, -60))
//                                .lineTo(new Vector2d(60, -60))
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
