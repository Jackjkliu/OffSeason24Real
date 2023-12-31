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
        int pos = 4;

        switch (pos) {
            case 0:
                //Blue Top Right
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(90)))
                                        .lineToLinearHeading(new Pose2d(12,36,Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(45, 36, Math.toRadians(-180)))
                                        //align with april tag here
                                        .strafeRight(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;
            case 1:
                //Blue Top Middle
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(90)))
                                        .back(24)
                                        .lineToLinearHeading(new Pose2d(45, 36, Math.toRadians(-180)))
                                        //align with april tag here
                                        .strafeRight(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;
            case 2:
                //Blue Top Left
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(90)))
                                        .lineToLinearHeading(new Pose2d(12,36, Math.toRadians(180)))
                                        .back(33)
                                        //align with april tag here
                                        .strafeRight(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;
            case 3:
                //Red Top Right
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(-90)))
                                        .lineToLinearHeading(new Pose2d(12,-36,Math.toRadians(180)))
                                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                                        //align with april tag here
                                        .strafeLeft(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;
            case 4:
                //Red Top Middle
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(-90)))
                                        .back(24)
                                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                                        //align with april tag here
                                        .strafeLeft(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;
            case 5:
                //Red Top Left
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .setColorScheme(new ColorSchemeBlueDark())
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(-90)))
                                        .lineToLinearHeading(new Pose2d(12,-36,Math.toRadians(0)))
                                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                                        //align with april tag here
                                        .strafeLeft(24)
                                        .build());
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
                break;


        }

    }
}
