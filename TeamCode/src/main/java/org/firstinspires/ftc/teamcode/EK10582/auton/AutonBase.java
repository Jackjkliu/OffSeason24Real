package org.firstinspires.ftc.teamcode.EK10582.auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;


public abstract class AutonBase extends LinearOpMode {

    public abstract void run();

    @Override
    public void runOpMode() throws InterruptedException{
        waitForStart();

        if(opModeIsActive() && !isStopRequested()) {
            run();
        }
    }

    public void runAction(Action action) {
        action.start();
        while(!action.isComplete && opModeIsActive() && !isStopRequested()) {
            action.update();
            Robot.getInstance().update();
        }
        action.end();
    }

}
