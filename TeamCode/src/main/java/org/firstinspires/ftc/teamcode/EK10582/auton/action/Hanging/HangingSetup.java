package org.firstinspires.ftc.teamcode.EK10582.auton.action.Hanging;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class HangingSetup extends Action {
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void start() {
        timer.reset();
    }
    @Override
    public void update() {
        if(timer.milliseconds() >= SubsystemConstants.timingsForDump[1]) {
            Robot.getInstance().hangingMotor.setPower(0);                                                               ;
            Robot.getInstance().hanging.isSetup = false;
            isComplete = true;
        } else if(timer.milliseconds() >= SubsystemConstants.timingsForDump[0]) {
            Robot.getInstance().hanging.currentState = SubsystemConstants.HangingStates.UP;
        } else if (timer.milliseconds() >= 0) {
            Robot.getInstance().hanging.isSetup = true;
            Robot.getInstance().hangingMotor.setPower(0.8);                                                               ;
        }
        Robot.getInstance().update();
    }
    @Override
    public void end() {

    }
}
