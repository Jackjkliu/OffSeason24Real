package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositions;

@Autonomous(name="TopBlue")
@Config
public class TopBlue extends AutonBase {

    SpikePositions spikePosition = SpikePositions.LEFT;

    @Override
    public void runOpMode() {
        waitForStart();
        switch(spikePosition){
            case LEFT:
                runAction(new AngleMove(Math.PI/2, 40, 0.6));
                runAction(new AngleMove(0, 40, 0.6));
                break;
            case RIGHT:
                runAction(new AngleMove(Math.PI/2, 35, 0.6));
                runAction(new AngleMove(3 * Math.PI/2, 8, 0.6));
                runAction(new AngleMove(0, 48, 0.6));
                break;
            case MIDDLE:
                runAction(new AngleMove(Math.PI/2, 48, 0.6));
                runAction(new AngleMove(0, 50, 0.6));
                break;
        }
    }
}
