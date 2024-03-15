package org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class Dump extends Action {
    ElapsedTime timer = new ElapsedTime();
    SubsystemConstants.SlideStates slideState;

    public Dump(SubsystemConstants.SlideStates slideState) {
        this.slideState = slideState;
    }

    @Override
    public void start() {
        timer.reset();
    }

    @Override
    public void update() {
        if(timer.milliseconds() >= SubsystemConstants.timingsForDump[4]) {
            Robot.getInstance().slides.currentState = SubsystemConstants.SlideStates.FREE;
            isComplete = true;
        } else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[3]) {
            Robot.getInstance().slides.currentState = SubsystemConstants.SlideStates.BOTTOM;
        }  else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[2]) {
            Robot.getInstance().housing.dumperState = SubsystemConstants.DumperStates.PRESET;
        } else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[1]) {
            Robot.getInstance().housing.pixelHolderState = SubsystemConstants.PixelHolderStates.UP;
        } else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[0]) {
            Robot.getInstance().housing.dumperState = SubsystemConstants.DumperStates.LOW;
            Robot.getInstance().slides.currentState = SubsystemConstants.SlideStates.FREE;
        } else if (timer.milliseconds() >= 0) {
            Robot.getInstance().slides.currentState = SubsystemConstants.SlideStates.LOW;                                                                   ;
        }
    }

    @Override
    public void end() {

    }
}
