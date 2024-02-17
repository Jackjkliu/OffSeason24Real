package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;


public abstract class AutonBase extends EKLinear {

    public void runAction(Action action) {
        action.start();
        while(!action.isComplete) {
            action.update();
            Robot.getInstance().update();
        }
        action.end();
    }

}
