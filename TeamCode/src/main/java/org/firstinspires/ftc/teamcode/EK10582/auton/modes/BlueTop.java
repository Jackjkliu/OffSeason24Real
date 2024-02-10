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

@Autonomous(name="BlueTop")
@Config
public class BlueTop extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTagX, distFromAprilTagForward;
        Pose2d startPos = new Pose2d(12,60, Math.toRadians(90));

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        robot.roadRunner.setPoseEstimate(startPos);

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        switch (pos) {
            case LEFT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(37,36, Math.toRadians(0)))
                        .back(4)
                        .forward(4)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(42,36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .back(7)
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineToLinearHeading(new Pose2d(38,58, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(60,58, Math.toRadians(180)))
                        .build();
                break;
            case RIGHT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(15,36, Math.toRadians(0)))
                        .back(4)
                        .forward(4)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(42,36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .back(7)
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineToLinearHeading(new Pose2d(38,58, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(60,58, Math.toRadians(180)))
                        .build();
                break;

            case MIDDLE:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,38, Math.toRadians(90)))
                        .back(4)
                        .forward(4)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(42,36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .back(7)
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .lineToLinearHeading(new Pose2d(38,58, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(60,58, Math.toRadians(180)))
                        .build();
                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(1000);
        robot.roadRunner.followTrajectorySequence(traj_toBackboard);
        sleep(1000);
        robot.aprilTags.relocalize();
        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        //runAction(new DepositHousing());
        sleep(1000);
        robot.roadRunner.followTrajectorySequence(traj_park);

//        double distFromAprilTagX, distFromAprilTagForward;
//        Pose2d startPos = new Pose2d(12,60, Math.toRadians(0));
//
//        waitForStart();
//
//        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;
//
//        //close opencv and open apriltags
//        robot.openCV.stop();
//
//        telemetry.addData("pos: ", pos);
//        telemetry.update();
//
//        robot.roadRunner.setPoseEstimate(startPos);
//
//        switch (pos) {
//            case LEFT:
//                Trajectory pushPixelL = robot.roadRunner.trajectoryBuilder(startPos)
//                        .lineToLinearHeading(new Pose2d(12,36, Math.toRadians(180)))
//                        .build();
//                Trajectory strafeRight = robot.roadRunner.trajectoryBuilder(pushPixelL.end())
//                        .back(33)
//                        .build();
//
//                robot.roadRunner.followTrajectory(pushPixelL);
//                sleep(200);
//                robot.roadRunner.followTrajectory(strafeRight);
//                sleep(200);
//                break;
//
//            case RIGHT:
//                Trajectory forward = robot.roadRunner.trajectoryBuilder(startPos)
//                        .lineToLinearHeading(new Pose2d(12,36,Math.toRadians(180)))
//                        .build();
//                Trajectory strafeRightR = robot.roadRunner.trajectoryBuilder(forward.end())
//                        .lineToLinearHeading(new Pose2d(45, 36, Math.toRadians(-180)))
//                        .build();
//
//                robot.roadRunner.followTrajectory(forward);
//                sleep(200);
//                robot.roadRunner.followTrajectory(strafeRightR);
//                sleep(200);
//                break;
//
//            default: //case middle
//
//                //declare trajectories
//                Trajectory pushPixel = robot.roadRunner.trajectoryBuilder(startPos)
//                        .back(24)
//                        .build();
//
//                Trajectory back = robot.roadRunner.trajectoryBuilder(pushPixel.end())
//                        .lineToLinearHeading(new Pose2d(45, 36, Math.toRadians(-180)))
//                        .build();
//
//
//                robot.roadRunner.followTrajectory(pushPixel);
//                sleep(200);
//
//                robot.roadRunner.followTrajectory(back);
//                sleep(200);
//
//                break;
//
//            //end case middle
//
//


    }
}