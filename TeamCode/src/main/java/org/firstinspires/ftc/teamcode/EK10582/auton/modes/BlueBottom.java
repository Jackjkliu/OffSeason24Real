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

@Autonomous(name="BlueBottom")
@Config
public class BlueBottom extends AutonBase {

    @Override
    public void runOpMode() {

        Pose2d startPos = new Pose2d(-36,-60, Math.toRadians(90));

        waitForStart();

        SpikePipeline.SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();

        telemetry.addData("pos: ", pos);
        telemetry.update();

        robot.roadRunner.setPoseEstimate(startPos);

        TrajectorySequence traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        TrajectorySequence traj_park = robot.roadRunner.trajectorySequenceBuilder(startPos).forward(1).build();
        switch (pos) {
            case LEFT:
                robot.aprilTags.targetAprilTag = 1;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        //push pixel
                        .lineTo(new Vector2d(-46,38))
                        .forward(15)

                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        //under truss
                        .strafeRight(10)
                        .lineToLinearHeading(new Pose2d(-36,8, Math.toRadians(0)))
                        .lineToConstantHeading(new Vector2d(15,8))
                        //toBackboard
                        .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))

                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        //place pixel
                        .lineToSplineHeading(new Pose2d(52, 42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(32)
                        .build();
                break;


            case RIGHT:
                robot.aprilTags.targetAprilTag = 3;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        //push pixel
                        .lineTo(new Vector2d(-46,38))
                        .forward(15)
                        .build();

                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        //under truss
                        .strafeRight(10)
                        .lineToLinearHeading(new Pose2d(-36,8, Math.toRadians(0)))
                        .lineToConstantHeading(new Vector2d(15,8))
                        //toBackboard
                        .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        //place pixel
                        .lineToSplineHeading(new Pose2d(52, 42, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(6)
                        .strafeLeft(28)
                        .build();
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 2;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        //push pixel
                        .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                        .back(6)
                        .forward(12)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())

                        //under truss
                        .lineToLinearHeading(new Pose2d(-30,8, Math.toRadians(0)))
                        .lineToLinearHeading(new Pose2d(10,8, Math.toRadians(0)))

                        //toBoard
                        .splineToLinearHeading(new Pose2d(40,36,Math.toRadians(180)), Math.toRadians(0))

                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())

                        //placePixel
                        .lineToLinearHeading(new Pose2d(52, 35, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeRight(24)
                        .build();

                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(500);

        robot.roadRunner.followTrajectorySequence(traj_toBackboard);

        sleep(500);
        robot.aprilTags.update(true);

        telemetry.addData("Seetag for " + robot.aprilTags.targetAprilTag + " : ", robot.aprilTags.seeTag);
        telemetry.addData("Y: ", robot.aprilTags.relocalize().getY());
        telemetry.addData("X: ", robot.aprilTags.relocalize().getX());
        telemetry.addData("Yaw: ", robot.aprilTags.relocalize().getHeading());
        telemetry.update();
        sleep(500);

        robot.roadRunner.setPoseEstimate(robot.aprilTags.relocalize());

        sleep(500);

        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        sleep(500);

        runAction(new Dump());
        sleep(500);

        robot.roadRunner.followTrajectorySequence(traj_park);
    }
}