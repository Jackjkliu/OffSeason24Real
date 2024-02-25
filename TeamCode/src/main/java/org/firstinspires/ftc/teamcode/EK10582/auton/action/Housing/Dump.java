package org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class Dump extends Action {
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void start() {
        timer.reset();
    }
    @Override
    public void update() {
        if(timer.milliseconds() >= SubsystemConstants.timingsForDump[2]) {
            isComplete = true;
        }
        else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[1]) {
            Robot.getInstance().housing.pixelHolderState = SubsystemConstants.PixelHolderStates.UP;
        }
        else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[0]) {
            Robot.getInstance().housing.dumperState = SubsystemConstants.DumperStates.LOW;
            Robot.getInstance().slides.joystickInput = 0;
        }
        else if (timer.milliseconds() >= 0) {
            Robot.getInstance().slides.joystickInput = 0.6;                                                                     ;
        }
    }
    @Override
    public void end() {

    }
}
