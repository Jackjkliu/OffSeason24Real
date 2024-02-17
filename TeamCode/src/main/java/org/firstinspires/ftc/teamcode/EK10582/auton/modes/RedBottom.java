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

@Autonomous(name="RedBottom")
@Config
public class RedBottom extends AutonBase {

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

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).build();
        switch (pos) {
            case LEFT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .strafeLeft(10)
                        .lineToLinearHeading(new Pose2d(-46,-38, Math.toRadians(90)))
                        .forward(10)
                        .strafeRight(10)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(-36,-8, Math.toRadians(0)))
                        .lineToConstantHeading(new Vector2d(15,-8))
                        .splineToLinearHeading(new Pose2d(36,-30,Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(54, -30, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(26)
                        .build();
                break;
            case RIGHT:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .turn(Math.toRadians(90))
                        .back(4)
                        .forward(10)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .lineToLinearHeading(new Pose2d(-36,-8, Math.toRadians(0)))
                        .lineToConstantHeading(new Vector2d(15,-8))
                        .splineToLinearHeading(new Pose2d(36,-42,Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(54, -42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(16)
                        .build();
                break;

            case MIDDLE:
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .forward(25)
                        .back(10)
                        .splineToConstantHeading(new Vector2d(-54,-24),Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-30,-8, Math.toRadians(90)),Math.toRadians(180))
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        .turn(Math.toRadians(-90))
                        .lineToConstantHeading(new Vector2d(15,-8))
                        .splineToLinearHeading(new Pose2d(36,-36,Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToSplineHeading(new Pose2d(54, -36, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(24)
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
    }
}