package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

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

@Autonomous(name="BlueBottom")
@Config
public class BlueBottom extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;
        Pose2d startPos = new Pose2d(-36,-60, Math.toRadians(-90));

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        robot.roadRunner.setPoseEstimate(startPos);

        switch (pos) {
            case LEFT:
                Trajectory pushPixelL = robot.roadRunner.trajectoryBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(-35,-32, Math.toRadians(180)))
                        .back(4)
                        .forward(4)
                        .splineToConstantHeading(new Vector2d(-60,-24), Math.toRadians(180))
                        .build();
                TrajectorySequence splinetoBoardL = robot.roadRunner.trajectorySequenceBuilder(pushPixelL.end())
                        .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(40,-36), Math.toRadians(0))
                        .build();
                robot.roadRunner.followTrajectory(pushPixelL);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(splinetoBoardL);
                sleep(200);
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(1000);
                robot.roadRunner.followTrajectorySequence(splinetoBoardL);


                Trajectory alignAprilTagL = robot.roadRunner.trajectoryBuilder(splinetoBoardL.end())
                        .strafeTo(new Vector2d(-20 - distFromAprilTagForward, 28 + distFromAprilTagX - 2))
                        .build();
                robot.roadRunner.followTrajectory(alignAprilTagL);


                robot.dumper.setPosition(SubsystemConstants.dumperTop);


                Trajectory awayL = robot.roadRunner.trajectoryBuilder(alignAprilTagL.end())
                        .forward(4)
                        .build();

                Trajectory parkL = robot.roadRunner.trajectoryBuilder(awayL.end())
                        .strafeRight(16)
                        .build();

                sleep(3000);

                robot.dumper.setPosition(0.5);

                robot.roadRunner.followTrajectory(awayL);
                robot.roadRunner.followTrajectory(parkL);
                break;

            case RIGHT:
                Trajectory pushPixelR = robot.roadRunner.trajectoryBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(-35,-32,Math.toRadians(0)))
                        .back(4)
                        .forward(4)
                        .splineToConstantHeading(new Vector2d(-60,-12), Math.toRadians(180))
                        .build();
                TrajectorySequence splinetoBoardR = robot.roadRunner.trajectorySequenceBuilder(pushPixelR.end())
                        .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(40,-36), Math.toRadians(0))
                        .build();

                robot.roadRunner.followTrajectory(pushPixelR);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(splinetoBoardR);
                sleep(200);
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(2000);
                robot.roadRunner.followTrajectorySequence(splinetoBoardR);

                Trajectory alignAprilTagR = robot.roadRunner.trajectoryBuilder(splinetoBoardR.end())
                        .strafeTo(new Vector2d(-20 - distFromAprilTagForward + 2, 28 + distFromAprilTagX + 5))
                        .build();


                robot.roadRunner.followTrajectory(alignAprilTagR);
                robot.dumper.setPosition(SubsystemConstants.dumperTop);



                Trajectory awayR = robot.roadRunner.trajectoryBuilder(alignAprilTagR.end())
                        .forward(4)
                        .build();

                Trajectory parkR = robot.roadRunner.trajectoryBuilder(awayR.end())
                        .strafeRight(28)
                        .build();

                sleep(3000);

                robot.dumper.setPosition(0.5);

                robot.roadRunner.followTrajectory(awayR);
                robot.roadRunner.followTrajectory(parkR);
                break;

            default: //case middle

                //declare trajectories
                Trajectory strafeBack = robot.roadRunner.trajectoryBuilder(startPos)
                        .back(25)
                        .forward(10)
                        .build();
                TrajectorySequence turnLeft = robot.roadRunner.trajectorySequenceBuilder(strafeBack.end())
                        .turn(Math.toRadians(90))
                        .build();
                TrajectorySequence splinetoStack = robot.roadRunner.trajectorySequenceBuilder(turnLeft.end())
                        .splineToConstantHeading(new Vector2d(-60,-24), Math.toRadians(180))
                        .build();
                TrajectorySequence splinetoBoard = robot.roadRunner.trajectorySequenceBuilder(splinetoStack.end())
                        .splineToConstantHeading(new Vector2d(0,0), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(40,-36), Math.toRadians(0))
                        .build();

                robot.roadRunner.followTrajectory(strafeBack);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(turnLeft);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(splinetoStack);
                sleep(200);
                robot.roadRunner.followTrajectorySequence(splinetoBoard);
                robot.aprilTags.update(true);
                distFromAprilTagX = robot.aprilTags.tagX;
                distFromAprilTagForward = robot.aprilTags.tagDistance;

                telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
                telemetry.addData("tagx ", robot.aprilTags.tagX);
                telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
                telemetry.update();


                sleep(500);
                robot.roadRunner.followTrajectorySequence(splinetoBoard);


                Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(splinetoBoard.end())
                        .strafeTo(new Vector2d(-20 - distFromAprilTagForward + 2, 28 + distFromAprilTagX + 3))
                        .build();
                robot.roadRunner.followTrajectory(alignAprilTag);

                robot.dumper.setPosition(SubsystemConstants.dumperTop);

                Trajectory away = robot.roadRunner.trajectoryBuilder(alignAprilTag.end())
                        .forward(4)
                        .build();

                Trajectory park = robot.roadRunner.trajectoryBuilder(away.end())
                        .strafeRight(24)
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