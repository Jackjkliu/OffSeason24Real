package org.firstinspires.ftc.teamcode.EK10582.test;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline.SpikePositionsRed;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.TrajectorySequence;

@Autonomous(name="trajectoryTest")
@Config
@Disabled
public class trajTest extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        switch(pos){
            case RIGHT:
                robot.aprilTags.targetAprilTag = 3;
                break;

            case LEFT:
                robot.aprilTags.targetAprilTag = 1;
                break;

            default: //case middle
                robot.aprilTags.targetAprilTag = 2;
                break;
        }

        robot.aprilTags.init(true);
        sleep(1000);

        //declare trajectories
        Trajectory pushPixel = robot.roadRunner.trajectoryBuilder(new Pose2d(0,0,Math.toRadians(0)))
                .strafeLeft(32)
                .build();

        Trajectory back = robot.roadRunner.trajectoryBuilder(pushPixel.end())
                .strafeRight(4)
                .build();
        Trajectory toBoard = robot.roadRunner.trajectoryBuilder(back.end())
                //This gives you a starting position
                .back(20)
                .build();

        TrajectorySequence turnLeft = robot.roadRunner.trajectorySequenceBuilder(toBoard.end())
                .turn(Math.toRadians(92))
                .build();
        TrajectorySequence turnRight = robot.roadRunner.trajectorySequenceBuilder(turnLeft.end())
                .turn(Math.toRadians(-92))
                .build();


        robot.roadRunner.followTrajectory(pushPixel);
//                sleep(1000);

        robot.roadRunner.followTrajectory(back);
//                sleep(1000);
        robot.roadRunner.followTrajectory(toBoard);
//                sleep(1000);
        robot.roadRunner.followTrajectorySequence(turnLeft);
//                sleep(1000);

                //get apriltag values
        robot.aprilTags.update(true);
        distFromAprilTagX = robot.aprilTags.tagX;
        distFromAprilTagForward = robot.aprilTags.tagDistance;

        telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
        telemetry.addData("tagx ", robot.aprilTags.tagX);
        telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
        telemetry.update();



        robot.roadRunner.followTrajectorySequence(turnRight);


        Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(turnRight.end())
                .strafeTo(new Vector2d(-20 - distFromAprilTagForward + 2, 28 + distFromAprilTagX + 6))
                .build();
        robot.roadRunner.followTrajectory(alignAprilTag);
//
//        //TODO: dispense pixel
//
        Trajectory away = robot.roadRunner.trajectoryBuilder(alignAprilTag.end())
                .forward(4)
                        .build();

        Trajectory park = robot.roadRunner.trajectoryBuilder(away.end())
                .strafeRight(24)
                .build();

        sleep(3000);
        robot.roadRunner.followTrajectory(away);
        robot.roadRunner.followTrajectory(park);




    }
}