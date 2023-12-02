package org.firstinspires.ftc.teamcode.EK10582.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SpikePipeline.SpikePositionsBlue;

@Autonomous(name="Camera Testing")
@Config
public class CameraTesting extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        SpikePositionsBlue pos = SpikePipeline.spikePositionB;

        //close opencv and open apriltags
        robot.openCV.stop();
        sleep(1000);

        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(2000);

        switch(pos){
            case RIGHT:
                robot.aprilTags.targetAprilTag = 6;
                break;

            case LEFT:
                robot.aprilTags.targetAprilTag = 4;
                break;

            default:
                robot.aprilTags.targetAprilTag = 5;
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
