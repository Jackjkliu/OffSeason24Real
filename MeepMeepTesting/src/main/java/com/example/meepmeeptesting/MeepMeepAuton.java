package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepAuton {
    private static int teamPropPosition = (int) (2);//(Math.random() * 3); // for testing

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = null;

        switch (teamPropPosition) {
            case 0:
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                        // TODO: This is for RB
//                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//
//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36, -60, 0))
//                                        //This gives you a starting position
//                                        .lineToSplineHeading(new Pose2d(-40, -32, Math.toRadians(90)))
//                                        .strafeRight(4)
//                                        .back(24)
//                                        .strafeRight(36)
//                                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
//                                        .forward(3)
//                                        .strafeLeft(24)
//                                        .back(6)
//                                        .build()
//
//
//                        )
////                        //TODO: This is for BB
//                        .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36,60,0))
//                                        .lineToSplineHeading(new Pose2d(-40,32,Math.toRadians(90)))
//                                        .strafeRight(4)
//                                        .forward(24)
//                                        .strafeRight(34)
//                                        .splineToSplineHeading(new Pose2d(56,36, Math.toRadians(180)),0)
//                                        .forward(3)
//                                        .strafeRight(24)
//                                        .back(6)
//                                        .build()
                        //TODO: This is for BT
//                        .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
//                         .followTrajectorySequence(drive ->
//                            drive.trajectorySequenceBuilder(new Pose2d(10,60,Math.toRadians(90)))
//                                    .lineToSplineHeading(new Pose2d(10,30,Math.toRadians(90)))
//                                    .lineToSplineHeading(new Pose2d(10,56,Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(56,36, Math.toRadians(180)))
//                                    .forward(3)
//                                    .strafeRight(24)
//                                    .back(6)
//                                    .build()
//
//
//
//
//                        );
                        //TODO: This is for RT
                .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(10,-60,0))
                                    .lineToSplineHeading(new Pose2d(12,-30,Math.toRadians(90)))
                                    .lineToSplineHeading(new Pose2d(12,-56,Math.toRadians(180)))
                                    .lineToSplineHeading(new Pose2d(56,-36, Math.toRadians(180)))
                                    .forward(3)
                                    .strafeLeft(24)
                                    .back(6)
                                    .build()

                    );

                break;
            case 1:
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                        // TODO: Set these later lmao --Shining
//                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36, -60, 0))
//                                        //This gives you a starting position
//                                        .lineToSplineHeading(new Pose2d(-36, -30, 0))
//                                        .lineToSplineHeading(new Pose2d(-36, -60, 0))
//                                        .lineToSplineHeading(new Pose2d(0, -60, 0))
//                                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
//                                        .forward(3)
//                                        .strafeLeft(24)
//                                        .back(6)
//                                        .build()

//                        );
//                        //TODO: This is for BB
//                           .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
//                           .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36,60,0))
//                                        //starting position
//                                        .lineToSplineHeading(new Pose2d(-36,32, 0))
//                                        .lineToSplineHeading(new Pose2d(-36,60,0))
//                                        .lineToSplineHeading(new Pose2d(0,60,0))
//                                        .splineToSplineHeading(new Pose2d(56,36, Math.toRadians(180)),0)
//                                        .forward(3)
//                                        .strafeRight(24)
//                                        .back(6)
//                                        .build()
//
//
//
//                        );
                        //TODO: This is for BT
//                            .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
//                            .followTrajectorySequence(drive ->
//                            drive.trajectorySequenceBuilder(new Pose2d(10,60,0))
////                                    starting position
//                                    .lineToSplineHeading(new Pose2d(10,32, 0))
//                                    .lineToSplineHeading(new Pose2d(10,60,Math.toRadians(180)))
//                                    .lineToSplineHeading(new Pose2d(56,36,Math.toRadians(180)))
//                                    .forward(3)
//                                    .strafeRight(24)
//                                    .back(6)
//                                    .build()
//                    );
                        //TODO: This is for RT
                            .setConstraints(60,60,Math.toRadians(180),Math.toRadians(180),15)
                                .followTrajectorySequence(drive ->
                                    drive.trajectorySequenceBuilder(new Pose2d(10,-60,Math.toRadians(0)))
//                                    starting position
                                            .lineToSplineHeading(new Pose2d(10,-32, 0))
                                            .lineToSplineHeading(new Pose2d(10,-60,Math.toRadians(180)))
                                            .lineToSplineHeading(new Pose2d(56,-36,Math.toRadians(180)))
                                            .forward(3)
                                            .strafeRight(24)
                                            .back(6)
                                            .build()
                    );
                break;
            case 2:
                myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                        // TODO: Set these later lmao --Shining
//                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//
//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36, -60, 0))
//                                        //This gives you a starting position
//                                        .lineToSplineHeading(new Pose2d(-30, -30, Math.toRadians(-90)))
//                                        .lineToSplineHeading(new Pose2d(-36, -60, 0))
//                                        .lineToSplineHeading(new Pose2d(0, -60, 0))
//                                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
//                                        .forward(3)
//                                        .strafeLeft(24)
//                                        .back(6)
//                                        .build()
//                        );
                        //TODO: This is for BB
//                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//
//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-36, 60, 0))
//                                        //This gives you a starting position
//                                        .lineToSplineHeading(new Pose2d(-30, 30, Math.toRadians(-90)))
//                                        .lineToSplineHeading(new Pose2d(-36, 60, Math.toRadians(180)))
//                                        .lineToSplineHeading(new Pose2d(0, 60, 0))
//                                        .splineToSplineHeading(new Pose2d(56, 36, Math.toRadians(180)), 0)
//                                        .forward(3)
//                                        .strafeRight(24)
//                                        .back(6)
//                                        .build()
//                        );
                        //TODO: This is for BT
//                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//
//                        .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(10, 60, 0))
//                                        //This gives you a starting position
//                                        .lineToSplineHeading(new Pose2d(16, 30, Math.toRadians(-90)))
//                                        .lineToSplineHeading(new Pose2d(10, 60, Math.toRadians(180)))
//                                        .lineToSplineHeading(new Pose2d(56, 36, Math.toRadians(180)))
//                                        .forward(3)
//                                        .strafeRight(24)
//                                        .back(6)
//                                        .build()
//                        );
                        //TODO: This is for RT
                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)

                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(10, -60, 0))
                                    //This gives you a starting position
                                    .lineToSplineHeading(new Pose2d(16, -30, Math.toRadians(-90)))
                                    .lineToSplineHeading(new Pose2d(10, -60, Math.toRadians(180)))
                                    .lineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)))
                                    .forward(3)
                                    .strafeRight(24)
                                    .back(6)
                                    .build()
                    );
                break;
        }

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

    public void setTeamPropPosition(int teamPropPosition) {
        this.teamPropPosition = teamPropPosition;
    }
}
