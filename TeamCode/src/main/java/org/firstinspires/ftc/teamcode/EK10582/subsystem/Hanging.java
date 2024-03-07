package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.HangingStates;

public class Hanging extends Subsystem {

    public double hangingPower = 0;
    public HangingStates currentState = HangingStates.DOWN;

    @Override
    public void init(boolean auton) {
        currentState = HangingStates.DOWN;
    }

    @Override
    public void update(boolean auton) {
        Robot.getInstance().hangingMotor.setPower(hangingPower);
        Robot.getInstance().hangingServo.setPosition(currentState.position);
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {

    }
}
