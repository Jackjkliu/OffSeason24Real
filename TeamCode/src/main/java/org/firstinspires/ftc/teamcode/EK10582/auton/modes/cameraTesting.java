package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsBlue;

@Autonomous(name="camtest")
@Config
public class cameraTesting extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        SpikePositionsBlue pos = cameraPipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();
        sleep(1000);

        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(2000);

        switch(pos){
            case RIGHT:
                robot.aprilTags.targetAprilTag = 3;
                break;


            case LEFT:
                robot.aprilTags.targetAprilTag = 1;
                break;

            case MIDDLE:
                robot.aprilTags.targetAprilTag = 2;
                break;
        }
        robot.aprilTags.init(true);
        sleep(1000);

        while(!isStopRequested()){
            robot.aprilTags.update(true);
            telemetry.addData("seetag for " + robot.aprilTags.targetAprilTag + ": ", robot.aprilTags.seeTag);
            telemetry.addData("tagx ", robot.aprilTags.tagX);
            telemetry.addData("tagdistance ", robot.aprilTags.tagDistance);
            telemetry.update();
        }
    }
}
