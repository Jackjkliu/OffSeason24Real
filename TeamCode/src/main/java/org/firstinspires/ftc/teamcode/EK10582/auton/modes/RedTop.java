package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;

@Autonomous(name="RedTop")
@Config
public class RedTop extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;
        Pose2d startPos = new Pose2d(12,-60, Math.toRadians(0));

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        robot.roadRunner.setPoseEstimate(startPos);

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
                Trajectory pushPixelL = robot.roadRunner.trajectoryBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,-36,Math.toRadians(0)))
                        .build();
                Trajectory toBoard = robot.roadRunner.trajectoryBuilder(pushPixelL.end())
                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                        .build();

                robot.roadRunner.followTrajectory(pushPixelL);
                sleep(200);
                robot.roadRunner.followTrajectory(toBoard);
                sleep(200);
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                //failsafe
                if(distFromAprilTagForward == -1){
                    distFromAprilTagForward = 27;
                    distFromAprilTagX = 0;
                }

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(2000);
                TrajectorySequence turnToSlides = robot.roadRunner.trajectorySequenceBuilder(toBoard.end())
                        .turn(Math.toRadians(-95))
                        .build();

                Trajectory alignAprilTagL = robot.roadRunner.trajectoryBuilder(turnToSlides.end())
                        .strafeTo(new Vector2d(20 + distFromAprilTagForward - 2, 28 - distFromAprilTagX - 4))
                        .build();
                robot.roadRunner.followTrajectorySequence(turnToSlides);
                sleep(1000);
                robot.roadRunner.followTrajectory(alignAprilTagL);

                //TODO: fix dumper position
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
                Trajectory toSpike = robot.roadRunner.trajectoryBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,-36,Math.toRadians(180)))
                        .build();
                Trajectory strafeRightR = robot.roadRunner.trajectoryBuilder(toSpike.end())
                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                        .build();

                robot.roadRunner.followTrajectory(toSpike);
                sleep(200);
                robot.roadRunner.followTrajectory(strafeRightR);
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
                robot.roadRunner.followTrajectory(strafeRightR);


                Trajectory alignAprilTagR = robot.roadRunner.trajectoryBuilder(strafeRightR.end())
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
                Trajectory pushPixel = robot.roadRunner.trajectoryBuilder(startPos)
                        .back(24)
                        .build();

                Trajectory back = robot.roadRunner.trajectoryBuilder(pushPixel.end())
                        .lineToLinearHeading(new Pose2d(45, -36, Math.toRadians(-180)))
                        .build();

                robot.roadRunner.followTrajectory(pushPixel);
                sleep(200);

                robot.roadRunner.followTrajectory(back);
                sleep(200);

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
                robot.roadRunner.followTrajectory(back);


                Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(back.end())
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