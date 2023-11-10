package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Housing extends Subsystem {

    //TODO: First value is the where the claw should go to clamp the left pixel.
    //TODO: Second value is the claws middle position
    //TODO: Third value is the where the claw should go to clamp the right pixel.
    //TODO: 4th value is the housing should be before dumping the pixels
    //TODO: 5th value is the where the housing should go to in order to dump the pixels
    public final double[] housingpos = {.4, .5, .6, .5, .6};

    public boolean left, right, dump;
    boolean leftLate, rightLate, isDump;

    private double increment;
    private double pixelHolderTarget;
    private double dumperTarget;

    //servoUpButton and servoDownButton are the buttons that control how much the arm servo go up and down

    //this is supposed to have 7 values in it but rn it only has 2 because we haven't tested heights yet


    //why is boolean auton a parameter
    @Override
    public void init(boolean auton) {
        //TODO: Change the values to its appropriate values
        increment = 0.001;

        left = false;
        right = false;
        dump = false;

        leftLate = false;
        rightLate = false;

        pixelHolderTarget = 0.55; //default
        dumperTarget = 0.375;

        //in auton, just set servoTarget directly

    }

    //
    @Override
    public void update() {

        //if servoup/down is true, it will change pos of servo
//        if (right) { //x
//
//            pixelHolderTarget = 0.7; //right is x
//
//
//            //rightlate means holder position is already set to the right
////            if(!rightLate) {
////                Robot.getInstance().pixelHolder.setPosition(housingpos[2]);
////                rightLate = true;
////            }
////            else{
////                Robot.getInstance().pixelHolder.setPosition(housingpos[1]);
////                rightLate = false;
////            }
//        }
//        else if (left) {
//
//            pixelHolderTarget = 0.45;
//
//
////            if(!leftLate) {
////                Robot.getInstance().pixelHolder.setPosition(housingpos[0]);
////                leftLate = true;
////            }
////            else{
////                Robot.getInstance().pixelHolder.setPosition(housingpos[1]);
////                leftLate = false;
////            }
//        }
        Robot.getInstance().pixelHolder.setPosition(0.585);

//        if (dump) {
//
//            dumperTarget = 1;
//
//            if(!dumpLate) {
//                dumperTarget = 0.96;
//                dumpLate = true;
//            }
//            else {
//                dumperTarget = 0.4;
//                dumpLate = false;
//            }
//        }
        if(dump) {
            isDump = !isDump;
        }

        if(isDump) {
            dumperTarget = 0.96;
        } else {
            dumperTarget = 0.39;
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


