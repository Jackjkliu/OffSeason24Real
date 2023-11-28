package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline.SpikePositionsRed;

@Autonomous(name="BottomRedRR")
@Config
public class BottomRedRR extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(true);
        robot.openCV.update(true);
        SpikePositionsRed pos = SpikePipeline.spikePositionR;

        //close opencv and open apriltags
        robot.openCV.stop();
        robot.subsystems.remove(robot.openCV);
        robot.subsystems.add(robot.aprilTags);


        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case RIGHT:
                robot.aprilTags.targetAprilTag = 6;
                //left case
                //TODO: THIS IS AN EXAMPLE OF A TRAJECTORY!! ADD YOUR TRAJECTORIES HERE
                Trajectory trajRR1 = robot.roadRunner.trajectoryBuilder(new Pose2d(-36, -60, 0))
                        //This gives you a starting position
                        .lineToSplineHeading(new Pose2d(-40, -32, Math.toRadians(90)))
                        .strafeRight(4)
                        .back(24)
                        .strafeRight(36)
                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
                        .forward(3)
                        .strafeLeft(24)
                        .back(6)
                        .build();


                break;
            case LEFT:
                //right case
                robot.aprilTags.targetAprilTag = 4;
                Trajectory trajRR2 = robot.roadRunner.trajectoryBuilder(new Pose2d(-36, -60, 0))
                        //This gives you a starting position
                        .lineToSplineHeading(new Pose2d(-30, -30, Math.toRadians(-90)))
                        .lineToSplineHeading(new Pose2d(-36, -60, 0))
                        .lineToSplineHeading(new Pose2d(0, -60, 0))
                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
                        .forward(3)
                        .strafeLeft(24)
                        .back(6)
                        .build();

                break;
            case MIDDLE:
                //middle case

                robot.aprilTags.targetAprilTag = 5;
                Trajectory trajRR3 = robot.roadRunner.trajectoryBuilder(new Pose2d(-36, -60, 0))
                        //This gives you a starting position
                        .lineToSplineHeading(new Pose2d(-36, -30, 0))
                        .lineToSplineHeading(new Pose2d(-36, -60, 0))
                        .lineToSplineHeading(new Pose2d(0, -60, 0))
                        .splineToSplineHeading(new Pose2d(56, -36, Math.toRadians(180)), 0)
                        .forward(3)
                        .strafeLeft(24)
                        .back(6)
                        .build();
                break;
        }
    }
}