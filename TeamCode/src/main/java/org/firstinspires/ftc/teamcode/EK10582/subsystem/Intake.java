package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//child class of subsystem
public class Intake extends Subsystem{

    public double intakeSpeed, intakeBack;

    @Override
    public void init(boolean auton){

    }

    @Override
    public void update(boolean auton){
        if (auton) {
            return;
        }
        Robot.getInstance().intakeSpin.setPower((intakeSpeed - intakeBack) * -0.8);
    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Spintake speed: ", intakeSpeed - intakeBack);

    }

}
