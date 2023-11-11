package org.firstinspires.ftc.teamcode.EK10582.auton.modes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline.SpikePositions;

@Autonomous(name="TopBlue")
@Config
public class TopBlue extends AutonBase {

    SpikePositions spikePosition = SpikePositions.LEFT;

    @Override
    public void run() {
        switch(spikePosition){
            case SpikePositions.LEFT:
                runAction(AngleMove(Math.PI/2, 40, 0.6));
                runAction(AngleMove(0, 40, 0.6));
                break;
            case SpikePositions.RIGHT:
                runAction(AngleMove(Math.PI/2, 35, 0.6));
                runAction(AngleMove(3 * Math.PI/2, 8, 0.6));
                runAction(AngleMove(0, 48, 0.6));
                break;
            case SpikePositions.MIDDLE:
                runAction(AngleMove(Math.PI/2, 48, 0.6));
                runAction(AngleMove(0, 50, 0.6));
                break;
        }
    }
}
