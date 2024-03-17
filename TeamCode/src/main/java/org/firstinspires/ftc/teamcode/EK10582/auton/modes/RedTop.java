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

@Autonomous(name="RedTop")
@Config
public class RedTop extends AutonBase {

    @Override
    public void runOpMode() {

        Pose2d startPos = new Pose2d(12,-63, Math.toRadians(-90));

        waitForStart();
        SpikePipeline.SpikePositionsRed pos = SpikePipeline.spikePositionR;
        robot.openCV.stop();
        telemetry.addData("pos: ", pos);
        telemetry.update();
        robot.aprilTags.init(true);
        sleep(500);
        robot.roadRunner.setPoseEstimate(startPos);

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
         switch (pos) {
            case RIGHT:
                robot.aprilTags.targetAprilTag = 6;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(21,-36, Math.toRadians(-90)))
                        .forward(11)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,-42, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,-42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(4)
                        .strafeLeft(18)
                        .build();
                break;
            case LEFT:
                robot.aprilTags.targetAprilTag = 4;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(13,-36, Math.toRadians(0)))
                        .back(7)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .forward(7)
                        .lineToLinearHeading(new Pose2d(36,-29, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,-29, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(4)
                        .strafeLeft(28)
                        .build();
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 5;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,-31.5, Math.toRadians(-90)))
                        .forward(9)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,-36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,-36, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(4)
                        .strafeLeft(24)
                        .build();
                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(50);
        robot.roadRunner.followTrajectorySequence(traj_toBackboard);
        sleep(50);

        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.DOWN;
        robot.update();

        robot.roadRunner.setPoseEstimate(robot.aprilTags.relocalize());
        telemetry.addData("currentY: ", robot.roadRunner.getPoseEstimate().getY());
        telemetry.update();
        sleep(1000);

        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        sleep(50);

        runAction(new Dump(SubsystemConstants.SlideStates.LOW));
        sleep(50);

        robot.roadRunner.followTrajectorySequence(traj_park);


    }
}