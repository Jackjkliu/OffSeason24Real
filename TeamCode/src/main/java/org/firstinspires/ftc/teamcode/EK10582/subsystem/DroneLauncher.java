package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.DroneStates;

public class DroneLauncher extends Subsystem{

    public DroneStates currentState = DroneStates.CLOSED;
    private DroneStates lastState = DroneStates.CLOSED;

    @Override
    public void init(boolean auton) {
        currentState = DroneStates.CLOSED;
        Robot.getInstance().drone.setPosition(DroneStates.CLOSED.position);
    }

    @Override
    public void update(boolean auton) {
        if(currentState != lastState) {
            Robot.getInstance().drone.setPosition(currentState.position);
        }
        lastState = currentState;
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("State", currentState);
    }
}
