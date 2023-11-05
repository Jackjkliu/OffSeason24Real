package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class housing extends Subsystem {

    //TODO: First value is the where the claw should go to clamp the left pixel.
    //TODO: Second value is the claws middle position
    //TODO: Third value is the where the claw should go to clamp the right pixel.
    //TODO: 4th value is the housing should be before dumping the pixels
    //TODO: 5th value is the where the housing should go to in order to dump the pixels
    public final double[] slidepos = {0, .5, 1, 0.1, .8};

    public boolean left, right, dump;

    //servoUpButton and servoDownButton are the buttons that control how much the arm servo go up and down

    //this is supposed to have 7 values in it but rn it only has 2 because we haven't tested heights yet


    //why is boolean auton a parameter
    @Override
    public void init(boolean auton) {
        //TODO: Change the values to its appropriate values

        left = false;
        right = false;
        dump = false;


        //in auton, just set servoTarget directly

    }

    //
    @Override
    public void update() {

        //if servoup/down is true, it will change pos of servo
        if (right) {
            Robot.getInstance().pixelHolder.setPosition(slidepos[2]);
        }
        if (left) {
            Robot.getInstance().pixelHolder.setPosition(slidepos[0]);
        }

        if (dump) {
            Robot.getInstance().pixelHolder.setPosition(slidepos[1]);
            Robot.getInstance().dumper.setPosition(slidepos[4]);

        } else if (!dump) {
            Robot.getInstance().dumper.setPosition(slidepos[3]);
        }


        //intakeSpin is the motor that controls spinning of surgical tube
        //sets power to motor based of left trigger
        //set intake power to intake speed
//        Robot.getInstance().intakeSpin.setPower(intakeSpeed * -0.6);
        //0.6 is the hard limit for intake speed. change as needed.
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("ClawPos: ", Robot.getInstance().pixelHolder.getPosition());
        telemetry.addData("Dumper: ", Robot.getInstance().dumper.getPosition());

    }

    // 1 = rest dumper pos, 2 = dumper dumping pos
    public void dumper(int pos){
        if(pos == 1) Robot.getInstance().dumper.setPosition(0);
        else Robot.getInstance().dumper.setPosition(1);
    }

    // 1 = left, 2 = middle, 3 = right *if a number is not 1,2,3 it will go to right pos
    public void clawMove(int pos) {
        if(pos == 1) Robot.getInstance().pixelHolder.setPosition(0);
        else if(pos==2) Robot.getInstance().pixelHolder.setPosition(.5);
        else Robot.getInstance().pixelHolder.setPosition(1);
    }
}
//    //position goes from 0 to 6. 1 is ground, 6 is all the way up.
//    //1 is one pixel from ground, etc.
//    public void setIntakeArmPos(int position){
//        Robot.getInstance().intakeArm.setPosition(servoPos[position]);
//    }
//}


