package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;


//abstract class is a like a blueprint for a child class
//must include the listed following but can have additional methods
public abstract class Subsystem {
    public abstract void init(boolean auton);

    public abstract void update(boolean auton);

    public abstract void stop();

    public abstract void printToTelemetry(Telemetry telemetry);
}
