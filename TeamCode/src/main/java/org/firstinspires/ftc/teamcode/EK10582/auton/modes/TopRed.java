package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositionsRed;

@Autonomous(name="TopRed")
@Config
public class TopRed extends AutonBase {

    @Override
    public void runOpMode() {

        waitForStart();

        robot.openCV.init(false);
        robot.openCV.update();
        SpikePositionsRed pos = cameraPipeline.spikePositionR;
        telemetry.addData("pos: ", pos);
        telemetry.update();
        sleep(1000);

        switch(pos){
            case RIGHT:
                //left case
                runAction(new AngleMove(-70, 38, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 30, 0.6));
                sleep(500);
                runAction(new AngleMove(0, 30, 0.6));
                break;
            case LEFT:
                //right case
                runAction(new AngleMove(-90, 31, 0.6));
                sleep(500);
//                runAction(new AngleTurn(-90,0.3, -1));
                runAction(new AngleMove(-140, 23, 0.4));
                sleep(500);
                runAction(new AngleMove(90, 3, 0.4));
                sleep(500);
                runAction(new AngleMove(0, 30, 0.4));
                sleep(500);
                runAction(new AngleMove(65, 43, 0.6));
                sleep(500);
                runAction(new AngleMove(0, 15, 0.6));
                break;
            case MIDDLE:
                //middle case
                runAction(new AngleMove(-90, 47, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 34, 0.6));
                sleep(500);
                runAction(new AngleMove(0, 52, 0.6));
                break;
        }
        sleep(1000);
    }
}
