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
        Robot.getInstance().slides.freeControl = false;
        double diff = targetPosition - Robot.getInstance().slides.getSlidePosition();
        Robot.getInstance().slides.autoSlidePower = 0.4 * diff / Math.abs(diff);
    }

    @Override
    public void update() {
        if(Math.abs(targetPosition - Robot.getInstance().slides.getSlidePosition()) <= 40) {
            Robot.getInstance().slides.autoSlidePower = 0;
            isComplete = true;
        }
    }

    @Override
    public void end() {
        Robot.getInstance().slides.freeControl = true;
    }
}
