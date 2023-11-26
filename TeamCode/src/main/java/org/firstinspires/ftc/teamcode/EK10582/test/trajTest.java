package org.firstinspires.ftc.teamcode.EK10582.test;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsRed;

@Autonomous(name="trajectoryTest")
@Config
public class trajTest extends AutonBase {

    @Override
    public void runOpMode() {

        double distFromAprilTag = 1;

        waitForStart();
        Trajectory pushPixel = robot.roadRunner.trajectoryBuilder(new Pose2d(0,0,Math.toRadians(0)))
                .strafeLeft(32)
                .build();

        Trajectory back = robot.roadRunner.trajectoryBuilder(pushPixel.end())
                .strafeRight(4)
                .build();
        Trajectory toBoard = robot.roadRunner.trajectoryBuilder(back.end())
                //This gives you a starting position
                .back(24)
                .build();

//        Trajectory alignAprilTag = robot.roadRunner.trajectoryBuilder(toBoard.end())
//                .strafeTo(new Vector2d(-24, 28+distFromAprilTag))
//                .build();

        Trajectory toBoard2 = robot.roadRunner.trajectoryBuilder(toBoard.end())
                .back(12)
                .build();

        Trajectory park = robot.roadRunner.trajectoryBuilder(toBoard2.end())
                .splineToConstantHeading(new Vector2d(-24,14), 0)
                .splineToConstantHeading(new Vector2d(-40,4),0)
                .build();


        robot.roadRunner.followTrajectory(pushPixel);
                sleep(1000);
//
        robot.roadRunner.followTrajectory(back);
                sleep(1000);
        robot.roadRunner.followTrajectory(toBoard);

        //TODO: write camera vision code to align with apriltag
//        robot.roadRunner.followTrajectory(alignAprilTag);
        robot.roadRunner.followTrajectory(toBoard2);
////        //TODO: dump the pixel
        sleep(1000);
                robot.roadRunner.followTrajectory(park);




    }
}