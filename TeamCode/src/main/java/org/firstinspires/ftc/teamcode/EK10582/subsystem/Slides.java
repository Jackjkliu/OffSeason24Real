package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem{


    //not init
    public double slideUp, slideDown;

    public double speed;

    private int increment;

    private int slideTarget;



    //TODO: change vals to actual pos
    public final int[] slidepos = {0,200, 400};

    //why is boolean auton a parameter
    @Override
    public void init(boolean auton){
       speed = .2;
       increment = 0;

       slideTarget = 0;

    }

    //
    @Override
    public void update(){

//        if(slideUp > 0.01){
////            increment = (int)(slideUp*5);
////            slideTarget+=increment;
//            Robot.getInstance().slide1.setPower(0.2);
//        }
//
//        else if(slideDown > 0.01){
////            increment = (int)(slideDown*5);
////            slideTarget-=increment;
//            Robot.getInstance().slide1.setPower(-0.2);
//        }


//        else{
//            Robot.getInstance().slide1.setPower(0);
//        }



        //if increment is greater than the last element in the array
//        if(slideTarget > 100) {
//            slideTarget = 100;
//        }
//
//        if(slideTarget < 0) {
//            slideTarget = 0;
//        }
//
//
//        //sets the intakeArm to a certain pos and keeps it there
//        Robot.getInstance().slide1.setTargetPosition(slideTarget);

        Robot.getInstance().slide1.setPower((slideUp - slideDown) * 0.8);

    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("target Position: ", slideTarget);
        telemetry.addData("slideUp", slideUp);
        telemetry.addData("slideDown", slideDown);
    }

    //position goes from 0 to 6. 1 is ground, 6 is all the way up
    public void setSlidePreset(int position){
        slideTarget = slidepos[position];
        Robot.getInstance().slide1.setTargetPosition(slideTarget);
    }
}