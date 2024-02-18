package com.example.meepmeeptesting;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedBottomMeepMeep {

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity blueBottom = null;
        RoadRunnerBotEntity redBottom = null;

        blueBottom = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,60, Math.toRadians(90)))
                                //push pixel
                                .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                                .back(6)
                                .forward(12)

                                //under truss
                                .lineToLinearHeading(new Pose2d(-30,8, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(10,8, Math.toRadians(0)))

                                //toBoard
                                .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))

                                //placePixel
                                .lineToLinearHeading(new Pose2d(52, 35, Math.toRadians(180)))

                                //park
                                .lineTo(new Vector2d(52, 60))
                                .lineTo(new Vector2d(60, 60))

                                .build());


        redBottom = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36,-60, Math.toRadians(-90)))
                                //push pixel
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .back(6)
                                .forward(12)

                                //under truss
                                .lineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(10,-8, Math.toRadians(0)))

                                //toBoard
                                .splineToLinearHeading(new Pose2d(40,-36,Math.toRadians(180)), Math.toRadians(0))

                                //placePixel
                                .lineToLinearHeading(new Pose2d(52, -35, Math.toRadians(180)))

                                //park
                                .lineTo(new Vector2d(52, -60))
                                .lineTo(new Vector2d(60, -60))

                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueBottom)
                .addEntity(redBottom)
                .start();
    }

}