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

        Pose2d startPos = new Pose2d(-36,60, Math.toRadians(90));


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
                        .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                        .back(6)
                        .forward(15)
                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        //under truss
                        .lineToLinearHeading(new Pose2d(-30,6, Math.toRadians(0)))
                        .lineToLinearHeading(new Pose2d(30,6, Math.toRadians(0)))

                        //toBoard
                        .splineToLinearHeading(new Pose2d(42,42, Math.toRadians(-180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52, 42, Math.toRadians(-180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(28)
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
                        .lineToLinearHeading(new Pose2d(-35,8, Math.toRadians(0)))
                        .lineToConstantHeading(new Vector2d(30,8))
                        //toBackboard
                        .splineToLinearHeading(new Pose2d(42,30,Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        //place pixel
                        .lineToSplineHeading(new Pose2d(52, 30, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(6)
                        .strafeLeft(20)
                        .build();
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 2;
                traj_pushPixel = robot.roadRunner.trajectorySequenceBuilder(startPos)
                        .back(32)
                        .forward(14)

                        .build();
                traj_toBackboard = robot.roadRunner.trajectorySequenceBuilder(traj_pushPixel.end())
                        //under truss
                        .splineToConstantHeading(new Vector2d(-54,16),Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-30,8, Math.toRadians(90)),Math.toRadians(0))
                        .turn(Math.toRadians(-90))
                        .lineToConstantHeading(new Vector2d(30,8))

                        //to board
                        .splineToLinearHeading(new Pose2d(42,36, Math.toRadians(180)), Math.toRadians(0))
                        .build();
                traj_placePixel = robot.roadRunner.trajectorySequenceBuilder(traj_toBackboard.end())
                        .lineToLinearHeading(new Pose2d(52, 36, Math.toRadians(180)))
                        .build();
                traj_park = robot.roadRunner.trajectorySequenceBuilder(traj_placePixel.end())
                        .forward(3)
                        .strafeLeft(24)
                        .build();

                break;
        }

        robot.roadRunner.followTrajectorySequence(traj_pushPixel);
        sleep(7000);

        robot.roadRunner.followTrajectorySequence(traj_toBackboard);

        sleep(2000);
        robot.update();
        robot.roadRunner.setPoseEstimate(robot.aprilTags.relocalize());

        sleep(100);

        robot.roadRunner.followTrajectorySequence(traj_placePixel);
        sleep(100);

        runAction(new Dump(SubsystemConstants.SlideStates.MEDIUM));
        sleep(100);

        robot.roadRunner.followTrajectorySequence(traj_park);
    }
}