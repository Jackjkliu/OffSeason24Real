package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing.Dump;
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

        robot.aprilTags.init(true);

        sleep(1000);

        robot.roadRunner.setPoseEstimate(startPos);

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();

        switch (pos) {
            case LEFT:
                robot.aprilTags.targetAprilTag = 1;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(26,32, Math.toRadians(90)))
                        .forward(6)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,42, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(5)
                        .strafeRight(18)
                        .build();
                break;
            case RIGHT:
                robot.aprilTags.targetAprilTag = 3;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,40, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(17,31, Math.toRadians(0)))
                        .back(9)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,30, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(54,30, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(5)
                        .strafeRight(38)
                        .build();
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 2;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(14,30, Math.toRadians(90)))
                        .forward(4)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(54,36, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeRight(24)
                        .build();
                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(1000);


        robot.roadRunner.followTrajectorySequence(traj_toBackboard);
        sleep(1000);

        robot.aprilTags.update(true);
        robot.roadRunner.setPoseEstimate(robot.aprilTags.relocalize());

        telemetry.addData("Seetag for " + robot.aprilTags.targetAprilTag + " : ", robot.aprilTags.seeTag);
        telemetry.addData("Y: ", robot.aprilTags.relocalize().getY());
        telemetry.addData("X: ", robot.aprilTags.relocalize().getX());
        telemetry.addData("Yaw: ", robot.aprilTags.relocalize().getHeading());
        telemetry.update();
        sleep(1000);


        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        sleep(1000);

        runAction(new Dump());
        sleep(1000);

        robot.roadRunner.followTrajectorySequence(traj_park);


    }
}