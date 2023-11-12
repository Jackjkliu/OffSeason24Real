package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsBlue;

@Autonomous(name="BottomBlue")
@Config
public class BottomBlue extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(false);
        robot.openCV.update();
        SpikePositionsBlue pos = cameraPipeline.spikePositionB;
        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case LEFT:
                //left case
                runAction(new AngleMove(-90, 31, 0.6));
                sleep(500);
                runAction(new AngleMove(-140, 23, 0.4));
                sleep(500);
                runAction(new AngleMove(90, 3, 0.4));
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
