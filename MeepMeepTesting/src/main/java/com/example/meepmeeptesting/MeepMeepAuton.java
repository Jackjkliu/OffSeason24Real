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

        RoadRunnerBotEntity RB = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                // TODO: Set these later lmao --Shining
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, 0))
                                //This gives you a starting position
                                .strafeLeft(27)
                                .strafeRight(3)
                                .forward(40)
                                .lineToLinearHeading(new Pose2d(48, -38, Math.toRadians(180)))
                                .forward(8)
                                .strafeLeft(20)
                                .back(16)
                                .build()
                );

        RoadRunnerBotEntity BB = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                // TODO: Set these later lmao --Shining
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 60, 0))
                                //This gives you a starting position
                                .strafeRight(27)
                                .strafeLeft(3)
                                .forward(40)
                                .lineToLinearHeading(new Pose2d(48, 38, Math.toRadians(180)))
                                .forward(8)
                                .strafeRight(20)
                                .back(16)
                                .build()
                );

        RoadRunnerBotEntity RF = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                // TODO: Set these later lmao --Shining
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -60, 0))
                                //This gives you a starting position
                                .strafeLeft(27)
                                .strafeRight(3)
                                .lineToLinearHeading(new Pose2d(48, -38, Math.toRadians(180)))
                                .forward(8)
                                .strafeRight(26)
                                .back(16)
                                .build()
                );

        RoadRunnerBotEntity BF = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                // TODO: Set these later lmao --Shining
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 60, 0))
                                //This gives you a starting position
                                .strafeRight(27)
                                .strafeLeft(3)
                                .lineToLinearHeading(new Pose2d(48, 38, Math.toRadians(180)))
                                .forward(8)
                                .strafeLeft(26)
                                .back(16)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(RB)
                .addEntity(BB)
                .addEntity(RF)
                .addEntity(BF)
                .start();
    }
}