package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Subsystem {
    public abstract void init(boolean auton);

    public abstract void update();

    public abstract void stop();

    public abstract void printToTelemetry(Telemetry telemetry);
}
