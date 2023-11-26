package org.firstinspires.ftc.teamcode.EK10582.auton.action.Slides;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class SlidesMoveTo extends Action {
    private int targetPosition;

    public SlidesMoveTo(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    @Override
    public void start() {
        Robot.getInstance().slides.setFreeControl(false);
        Robot.getInstance().slides.setAutoSlidePower(targetPosition);
    }

    @Override
    public void update() {
        if(Math.abs(targetPosition - Robot.getInstance().slides.getSlidePosition()) <= 40) {
            Robot.getInstance().slides.setSpeed(0);
            isComplete = true;
        }
    }

    @Override
    public void end() {
        Robot.getInstance().slides.setFreeControl(true);
    }
}
