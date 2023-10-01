package org.firstinspires.ftc.teamcode.EK10582.auton.action;

public abstract class Action {
    public boolean isComplete = false;

    public abstract void start();

    public abstract void update();

    public abstract void end();
}
