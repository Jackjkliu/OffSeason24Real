package com.example.meepmeeptesting;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class BlueTopMeepMeep {

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity BlueBot = null;
        RoadRunnerBotEntity RedBot = null;

        BlueBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,60, Math.toRadians(90)))

//                                BBL
                                //push pixel
                                .lineTo(new Vector2d(-46,38))
                                .forward(15)
                                //under truss
                                .strafeRight(10)
                                .lineToLinearHeading(new Pose2d(-36,8, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(15,8))
                                //toBackboard
                                .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))
                                //place pixel
                                .lineToSplineHeading(new Pose2d(52, 42, Math.toRadians(180)))
                                //park
                                .forward(6)
                                .lineTo(new Vector2d(46, 60))
                                .lineTo(new Vector2d(60, 60))
                                .build());

        RedBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,-60, Math.toRadians(-90)))

//                               //push pixel
                                .lineTo(new Vector2d(-46,-38))
                                .forward(15)
                                //under truss
                                .strafeLeft(10)
                                .lineToLinearHeading(new Pose2d(-36,-8, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(15,-8))
                                //toBackboard
                                .splineToLinearHeading(new Pose2d(40,-36,Math.toRadians(180)), Math.toRadians(0))
                                //place pixel
                                .lineToSplineHeading(new Pose2d(52, -42, Math.toRadians(180)))
                                //park
                                .forward(6)
                                .lineTo(new Vector2d(46, -60))
                                .lineTo(new Vector2d(60, -60))
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(BlueBot)
                .addEntity(RedBot)
                .start();
    }

}