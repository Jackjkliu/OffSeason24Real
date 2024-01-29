package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DroneLauncher extends Subsystem{

    public SubsystemConstants.DroneStates currentState = SubsystemConstants.DroneStates.CLOSED;
    private SubsystemConstants.DroneStates lastState = SubsystemConstants.DroneStates.CLOSED;

    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.DroneStates.CLOSED;
        Robot.getInstance().drone.setPosition(SubsystemConstants.DroneStates.CLOSED.position);
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
