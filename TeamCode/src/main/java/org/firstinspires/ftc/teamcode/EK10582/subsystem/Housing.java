package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Housing extends Subsystem {


    public SubsystemConstants.DumperStates dumperState = SubsystemConstants.DumperStates.PRESET;
    public SubsystemConstants.PixelHolderStates pixelHolderState = SubsystemConstants.PixelHolderStates.UP;
    public boolean autoDumper = false;
    private double backDistance;

    @Override
    public void init(boolean auton) {
        dumperState = SubsystemConstants.DumperStates.PRESET;
        pixelHolderState = SubsystemConstants.PixelHolderStates.UP;
    }


    @Override
    public void update(boolean auton) {
        if(autoDumper) {
            backDistance = Robot.getInstance().backboardDistanceSensor.getDistance(DistanceUnit.INCH);
            if(backDistance <= 7.5) {
                Robot.getInstance().dumper.setPosition(getAutoDumper(backDistance));
            } else {
                Robot.getInstance().dumper.setPosition(dumperState.position);
            }
        } else {
            Robot.getInstance().dumper.setPosition(dumperState.position);
        }
        Robot.getInstance().pixelHolder.setPosition(pixelHolderState.position);
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("ClawPos: ", pixelHolderState);
        telemetry.addData("Dumper: ", dumperState);
        telemetry.addData("back distance:", backDistance);
    }

    public double getAutoDumper(double backDistance) {
        double slideHeight = Robot.getInstance().slides.getSlidesPosition();
        double raw = backDistance * SubsystemConstants.cB + slideHeight * SubsystemConstants.cH + SubsystemConstants.cC;
        double output = Math.min(Math.max(raw, 0), 1);
        return output;
    }
    
}


