package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositions;

@Autonomous(name="TopBlue")
@Config
public class TopBlue extends AutonBase {
    SpikePositions spikePosition = SpikePositions.RIGHT;

    @Override
    public void runOpMode() {
        waitForStart();
        switch(cameraPipeline.spikePosition){
            case LEFT:
                runAction(new AngleMove(-99, 40, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 30, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 25, 0.6));
                break;
            case RIGHT:
                runAction(new AngleMove(-90, 26, 0.6));
                sleep(500);
//                runAction(new AngleTurn(-90,0.3, -1));
                runAction(new AngleMove(-40, 24, 0.4));
                sleep(500);
                runAction(new AngleMove(90, 3, 0.4));
                sleep(500);
                runAction(new AngleMove(180, 30, 0.4));
                sleep(500);
                runAction(new AngleMove(115, 43, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 15, 0.6));
                break;
            case MIDDLE:
                runAction(new AngleMove(-90, 49, 0.6));
                sleep(500);
                runAction(new AngleMove(90, 34, 0.6));
                sleep(500);
                runAction(new AngleMove(180, 30, 0.6));
                break;
        }
//        sleep(1000);
    }
}
