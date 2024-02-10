package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Housing extends Subsystem {


    public SubsystemConstants.DumperStates dumperState = SubsystemConstants.DumperStates.PRESET;
    public SubsystemConstants.PixelHolderStates pixelHolderState = SubsystemConstants.PixelHolderStates.UP;

    @Override
    public void init(boolean auton) {
    }


    @Override
    public void update(boolean auton) {
        if (auton) {
            return;
        }

        Robot.getInstance().dumper.setPosition(dumperState.position);
        Robot.getInstance().pixelHolder.setPosition(pixelHolderState.position);
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("ClawPos: ", Robot.getInstance().pixelHolder.getPosition());
        telemetry.addData("Dumper: ", Robot.getInstance().dumper.getPosition());
    }
    
}


