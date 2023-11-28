package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsBlue;

@Autonomous(name="BottomBlue")
@Disabled
public class BottomBlue extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(true);
        robot.openCV.update(true);
        SpikePositionsBlue pos = cameraPipeline.spikePositionB;
        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case LEFT:
                //left case
                Trajectory trajRR1 = robot.roadRunner.trajectoryBuilder(new Pose2d(-36,60,0))
                        .lineToSplineHeading(new Pose2d(-40,32,Math.toRadians(90)))
                        .strafeRight(4)
                        .forward(24)
                        .strafeRight(34)
                        .splineToSplineHeading(new Pose2d(56,36, Math.toRadians(180)),0)
                        .forward(3)
                        .strafeRight(24)
                        .back(6)
                        .build();

                break;

            case RIGHT:
                //right case
                runAction(new AngleMove(-70, 38, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 30, 0.6));
                break;
            case MIDDLE:
                //middle case
                runAction(new AngleMove(-90, 47, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 5, 0.6));
                break;
        }
        sleep(1000);
    }
}
