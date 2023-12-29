package org.firstinspires.ftc.teamcode.EK10582.auton.modes.oldOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;

@Autonomous(name="TopRedRR")
@Config
public class TopRedRR extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;

        waitForStart();

        SpikePipeline.SpikePositionsRed pos = SpikePipeline.spikePositionR;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        switch (pos) {
            case RIGHT:
                robot.aprilTags.targetAprilTag = 6;
                break;

            case LEFT:
                robot.aprilTags.targetAprilTag = 4;
                break;

            default: //case middle
                robot.aprilTags.targetAprilTag = 5;
                break;
        }

        robot.aprilTags.init(true);
        sleep(1000);


        switch (pos) {
            case LEFT:
                Trajectory pushPixelL = robot.roadRunner.trajectoryBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .strafeTo(new Vector2d(0,26))
                        .build();
                Trajectory backL = robot.roadRunner.trajectoryBuilder(pushPixelL.end())
                        .back(8)
                        .build();
                Trajectory Right1L = robot.roadRunner.trajectoryBuilder(backL.end())
                        .strafeRight(1)
                        .build();
                TrajectorySequence toBoardL = robot.roadRunner.trajectorySequenceBuilder(Right1L.end())
                        .strafeTo(new Vector2d(19, 25))
                        .build();
                TrajectorySequence turn1 = robot.roadRunner.trajectorySequenceBuilder(toBoardL.end())
                        .turn(Math.toRadians(-95))
                        .build();

                robot.roadRunner.followTrajectory(pushPixelL);
                sleep(200);
                robot.roadRunner.followTrajectory(backL);
                sleep(200);
                robot.roadRunner.followTrajectory(Right1L);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(toBoardL);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(turn1);


                //get apriltag values
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                if(distFromAprilTagForward == -1){
                    distFromAprilTagForward = 27;
                    distFromAprilTagX = 0;
                }

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(2000);
                TrajectorySequence turnToSlides = robot.roadRunner.trajectorySequenceBuilder(turn1.end())
                        .turn(Math.toRadians(-95))
                        .build();

                Trajectory alignAprilTagL = robot.roadRunner.trajectoryBuilder(turnToSlides.end())
                        .strafeTo(new Vector2d(20 + distFromAprilTagForward - 2, 28 - distFromAprilTagX - 4))
                        .build();
                robot.roadRunner.followTrajectorySequence(turnToSlides);
                sleep(1000);
                robot.roadRunner.followTrajectory(alignAprilTagL);
                robot.dumper.setPosition(SubsystemConstants.dumperTop);

                Trajectory awayL = robot.roadRunner.trajectoryBuilder(alignAprilTagL.end())
                        .forward(4)
                        .build();

                Trajectory parkL = robot.roadRunner.trajectoryBuilder(awayL.end())
                        .strafeLeft(32)
                        .build();

                sleep(3000);

                robot.dumper.setPosition(0.5);

                robot.roadRunner.followTrajectory(awayL);
                robot.roadRunner.followTrajectory(parkL);
                break;

            case RIGHT:
                Trajectory pushPixelR = robot.roadRunner.trajectoryBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .strafeTo(new Vector2d(15,28))
                        .build();
                Trajectory forwardR = robot.roadRunner.trajectoryBuilder(pushPixelR.end())
                        .strafeRight(3)
                        .build();
                Trajectory toBoardR = robot.roadRunner.trajectoryBuilder(forwardR.end())
                        .forward(10)
                        .build();
                TrajectorySequence turnRightR = robot.roadRunner.trajectorySequenceBuilder(toBoardR.end())
                        .turn(Math.toRadians(-95))
                        .build();
                TrajectorySequence turnRightR2 = robot.roadRunner.trajectorySequenceBuilder(turnRightR.end())
                        .turn(Math.toRadians(-95))
                        .build();
                robot.roadRunner.followTrajectory(pushPixelR);
                sleep(200);
                robot.roadRunner.followTrajectory(forwardR);
                sleep(200);
                robot.roadRunner.followTrajectory(toBoardR);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(turnRightR);
                sleep(200);


                //get apriltag values
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                if(distFromAprilTagForward == -1){
                    distFromAprilTagForward = 32;
                    distFromAprilTagX = -15;
                }

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(5000);
                robot.roadRunner.followTrajectorySequence(turnRightR2);


                Trajectory alignAprilTagR = robot.roadRunner.trajectoryBuilder(turnRightR2.end())
                        .strafeTo(new Vector2d(20 + distFromAprilTagForward + 5, 28 - distFromAprilTagX - 4))
                        .build();
                robot.roadRunner.followTrajectory(alignAprilTagR);
                robot.dumper.setPosition(SubsystemConstants.dumperTop);

                Trajectory awayR = robot.roadRunner.trajectoryBuilder(alignAprilTagR.end())
                        .forward(4)
                        .build();

                Trajectory parkR = robot.roadRunner.trajectoryBuilder(awayR.end())
                        .strafeLeft(16)
                        .build();

                sleep(3000);

                robot.dumper.setPosition(0.5);
                robot.roadRunner.followTrajectory(awayR);
                robot.roadRunner.followTrajectory(parkR);
                break;

            default: //case middle
                //declare trajectories
                Trajectory pushPixel = robot.roadRunner.trajectoryBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .strafeLeft(32)
                        .build();

                Trajectory back = robot.roadRunner.trajectoryBuilder(pushPixel.end())
                        .strafeRight(4)
                        .build();

                Trajectory toBoard = robot.roadRunner.trajectoryBuilder(back.end())
                        //This gives you a starting position
                        .forward(20)
                        .build();

                TrajectorySequence turnRight = robot.roadRunner.trajectorySequenceBuilder(toBoard.end())
                        .turn(Math.toRadians(-95))
                        .build();
                TrajectorySequence turnRight1 = robot.roadRunner.trajectorySequenceBuilder(turnRight.end())
                        .turn(Math.toRadians(-90))
                        .build();


                robot.roadRunner.followTrajectory(pushPixel);
//                sleep(1000);

                robot.roadRunner.followTrajectory(back);
//                sleep(1000);
                robot.roadRunner.followTrajectory(toBoard);
//                sleep(1000);
                robot.roadRunner.followTrajectorySequence(turnRight);
                sleep(1000);

                //get apriltag values
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                if(distFromAprilTagForward == -1){
                    distFromAprilTagForward = 27;
                    distFromAprilTagX = 0;
                }

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();

                sleep(500);
                robot.roadRunner.followTrajectorySequence(turnRight1);


                Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(turnRight1.end())
                        .strafeTo(new Vector2d(20 + distFromAprilTagForward - 0, 28 - distFromAprilTagX - 2))
                        .build();
                robot.roadRunner.followTrajectory(alignAprilTag);
                robot.dumper.setPosition(SubsystemConstants.dumperTop);

                Trajectory away = robot.roadRunner.trajectoryBuilder(alignAprilTag.end())
                        .forward(4)
                        .build();

                Trajectory park = robot.roadRunner.trajectoryBuilder(away.end())
                        .strafeLeft(24)
                        .build();

                sleep(3000);

                robot.dumper.setPosition(0.5);

                robot.roadRunner.followTrajectory(away);
                robot.roadRunner.followTrajectory(park);
                break;

            //end case middle
        }



    }
}