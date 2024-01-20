package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Housing extends Subsystem {


    public boolean left, right, dump;
    boolean leftLate, rightLate, isDump;

    private double increment = SubsystemConstants.housingIncrement;
    private double pixelHolderTarget = SubsystemConstants.pixelHolderMiddle;
    private double dumperTarget;

    //servoUpButton and servoDownButton are the buttons that control how much the arm servo go up and down

    //this is supposed to have 7 values in it but rn it only has 2 because we haven't tested heights yet


    //why is boolean auton a parameter
    @Override
    public void init(boolean auton) {
        //TODO: Change the values to its appropriate values

        left = false;
        right = false;
        dump = false;

        leftLate = false;
        rightLate = false;


        //in auton, just set servoTarget directly

    }

    //
    @Override
    public void update(boolean auton) {
        if (auton) {
            return;
        }

        Robot.getInstance().pixelHolder.setPosition(0.585);

        if(dump) {
            isDump = !isDump;
        }

        if(isDump) {
            dumperTarget = SubsystemConstants.dumperTop; //dumped position
        } else {
            dumperTarget = SubsystemConstants.dumperBottom; //reset and ready position
        }

        Robot.getInstance().dumper.setPosition(dumperTarget);

    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("ClawPos: ", Robot.getInstance().pixelHolder.getPosition());
        telemetry.addData("Dumper: ", Robot.getInstance().dumper.getPosition());
        telemetry.addData("Dumper Position: ", isDump);
        telemetry.addData("Dumper button: ", dump);
    }
    
}


