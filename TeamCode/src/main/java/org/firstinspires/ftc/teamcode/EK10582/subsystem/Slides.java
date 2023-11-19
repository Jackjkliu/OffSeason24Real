package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem{


    //not init
    public double slideControl;

    public double speed;

    private final int SLIDEMAX = SubsystemConstants.SLIDEMAX;

    private int slideTarget;



    //TODO: change vals to actual pos
    public final int[] slidepos = SubsystemConstants.slidepos;

    //why is boolean auton a parameter
    @Override
    public void init(boolean auton){
       speed = SubsystemConstants.speed;
    }

    @Override
    public void update(){
//        failsafes: only run in one direction if it hits a max
//        if(Robot.getInstance().slide1.getCurrentPosition() <= 0 || (slideUp - slideDown) < 0){
//            Robot.getInstance().slide1.setPower(0);
//            Robot.getInstance().slide2.setPower(0);
//        }
//        else if (Robot.getInstance().slide1.getCurrentPosition() >= SLIDEMAX || (slideUp - slideDown) >0){
//            Robot.getInstance().slide1.setPower(0);
//            Robot.getInstance().slide2.setPower(0);
//        }
//        else{
            Robot.getInstance().slide1.setPower((slideControl) * speed);
            Robot.getInstance().slide2.setPower((slideControl) * speed);
//        }
    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("target Position: ", slideTarget);
        telemetry.addData("slideControl", slideControl);
    }


    public void setSlidePreset(int position){
        slideTarget = slidepos[position];
        Robot.getInstance().slide1.setTargetPosition(slideTarget);
    }
}