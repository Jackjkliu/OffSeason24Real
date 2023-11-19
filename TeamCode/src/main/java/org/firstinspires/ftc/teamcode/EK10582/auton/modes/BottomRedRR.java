package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsRed;

@Autonomous(name="BottomRed")
@Config
public class BottomRedRR extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(true);
        robot.openCV.update(true);
        SpikePositionsRed pos = cameraPipeline.spikePositionR;
        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case RIGHT:
                //left case
                //TODO: THIS IS AN EXAMPLE OF A TRAJECTORY!! ADD YOUR TRAJECTORIES HERE
                Trajectory traj1 = robot.roadRunner.trajectoryBuilder(new Pose2d(-36, -60, 0))
                        //This gives you a starting position
                        .strafeLeft(27)
                        .strafeRight(3)
                        .forward(40)
                        .lineToLinearHeading(new Pose2d(48, -38, Math.toRadians(180)))
                        .forward(8)
                        .strafeLeft(20)
                        .back(16)
                        .build();

                break;
            case LEFT:
                //right case

                break;
            case MIDDLE:
                //middle case

                break;
        }
        sleep(1000);
    }
}
