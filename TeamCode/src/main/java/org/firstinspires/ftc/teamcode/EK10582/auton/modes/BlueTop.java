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
        Pose2d startPos = new Pose2d(12,60, Math.toRadians(90));

        waitForStart();
        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;
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
            case LEFT:
                robot.aprilTags.targetAprilTag = 1;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(26,32, Math.toRadians(90)))
                        .forward(6)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,43, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,43, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(7)
                        .strafeRight(12)
                        .build();
                break;
            case RIGHT:
                robot.aprilTags.targetAprilTag = 3;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(12,40, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(17,31, Math.toRadians(0)))
                        .back(13)
                        .forward(10)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,29, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,29, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(7)
                        .strafeRight(30)
                        .build();
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 2;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .lineToLinearHeading(new Pose2d(14,28, Math.toRadians(90)))
                        .forward(6)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(36,36, Math.toRadians(180)))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52,37, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(7)
                        .strafeRight(18)
                        .build();
                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(50);
        robot.roadRunner.followTrajectorySequence(traj_toBackboard);
        sleep(50);

        robot.update();
        robot.roadRunner.setPoseEstimate(robot.aprilTags.relocalize());
        sleep(1000);


        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        sleep(50);

        runAction(new Dump(SubsystemConstants.SlideStates.LOW));
        sleep(50);

        robot.roadRunner.followTrajectorySequence(traj_park);
    }
}