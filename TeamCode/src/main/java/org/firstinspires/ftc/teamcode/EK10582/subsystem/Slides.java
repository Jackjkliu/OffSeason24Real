package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem{


    //not init
    public double slideUp,slideDown;

    public double speed;

    public int increment;

    public int slideTarget;

    public double curPos;


    //TODO: change vals to actual pos
    public final int[] slidepos = {0,200, 400};

    //why is boolean auton a parameter
    @Override
    public void init(boolean auton){
       speed = .4;
       increment = 0;

       slideTarget = 0;
    }

    //
    @Override
    public void update(){

        if(slideUp > 0.01){
            increment = (int)(slideUp*5);
        }

        if(slideDown > 0.01){
            increment = -(int)(slideDown*5);
        }

        slideTarget+=increment;

        //if increment is greater than the last element in the array
        if(slideTarget > slidepos[slidepos.length-1]) {
            slideTarget = slidepos[slidepos.length-1];
        }

        if(slideTarget < slidepos[0]) {
            slideTarget = slidepos[0];
        }

        Robot.getInstance().slide1.setPower(speed);

        //sets the intakeArm to a certain pos and keeps it there
        Robot.getInstance().intakeArm.setPosition(slideTarget);

    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Slide Position: ", Robot.getInstance().slide1.getCurrentPosition());

    }

    //position goes from 0 to 6. 1 is ground, 6 is all the way up.
    //1 is one pixel from ground, etc.
    public void setSlideUp(int position){
        slideTarget = slidepos[position];
        Robot.getInstance().slide1.setTargetPosition(slideTarget);
    }
}