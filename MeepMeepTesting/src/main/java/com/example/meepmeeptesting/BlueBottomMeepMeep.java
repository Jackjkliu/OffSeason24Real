package com.example.meepmeeptesting;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueBottomMeepMeep {

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity blueBot = null;
        RoadRunnerBotEntity redBot = null;

        blueBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,60, Math.toRadians(90)))
                                //push pixel
                                .back(25)
                                .forward(10)
                                //under truss
                                .splineToConstantHeading(new Vector2d(-54,24),Math.toRadians(-90))
                                .splineToLinearHeading(new Pose2d(-30,8, Math.toRadians(90)),Math.toRadians(0))
                                .turn(Math.toRadians(-90))
                                .lineToConstantHeading(new Vector2d(15,8))
                                //toBoard
                                .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))
                                .lineTo(new Vector2d(52, 36))

                                //park
                                .forward(6)
                                .lineTo(new Vector2d(46, 60))
                                .lineTo(new Vector2d(60, 60))
                                .build());

        redBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,-60, Math.toRadians(-90)))
                                //push pixel
                                .back(25)
                                .forward(10)

                                //under truss
                                .splineToConstantHeading(new Vector2d(-54,-24),Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(-90)),Math.toRadians(0))
                                .turn(Math.toRadians(90))
                                .lineToConstantHeading(new Vector2d(15,-8))

                                //to board
                                .splineToLinearHeading(new Pose2d(40,-36,Math.toRadians(180)), Math.toRadians(0))
                                .lineToSplineHeading(new Pose2d(52, -36, Math.toRadians(180)))

                                //park
                                .forward(6)
                                .lineTo(new Vector2d(46, -60))
                                .lineTo(new Vector2d(60, -60))
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueBot)
                .addEntity(redBot)
                .start();
    }

}