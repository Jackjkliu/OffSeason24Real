package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hanging extends Subsystem {

    public double hangingPower = 0;
    public SubsystemConstants.HangingStates currentState = SubsystemConstants.HangingStates.DOWN;
    public SubsystemConstants.HangingStates lastState = SubsystemConstants.HangingStates.DOWN;

    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.HangingStates.DOWN;
    }

    @Override
    public void update(boolean auton) {
        Robot.getInstance().hangingMotor.setPower(hangingPower);
        if(currentState != lastState) {
            Robot.getInstance().hangingServo.setPosition(currentState.position);
        }
        lastState = currentState;
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {

    }
}
