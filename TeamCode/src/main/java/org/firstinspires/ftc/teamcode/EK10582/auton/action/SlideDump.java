package org.firstinspires.ftc.teamcode.EK10582.auton.action;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

public class SlideDump extends Action {
    private int slideHeight;
    private boolean dumpDone = false;
    private boolean dumpReturn = false;
    private boolean slideRaised = false;

    private double slideSpeed;

    private double dumpTarget = SubsystemConstants.dumperTop;

    public SlideDump(int slideHeight, double slideSpeed){
        this.slideHeight = slideHeight;
        this.slideSpeed = slideSpeed;
    }

    @Override
    public void start() {
        Robot.getInstance().slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.getInstance().slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Robot.getInstance().slide1.setPower(slideSpeed);
        Robot.getInstance().slide2.setPower(slideSpeed);
        Robot.getInstance().slide1.setTargetPosition(slideHeight);
        Robot.getInstance().slide2.setTargetPosition(slideHeight);


    }

    @Override
    public void update() {

        //TODO: Make variables to track completed actions: //declare with instance variables
        //Ex. boolean dumpDone = false;
        // boolean slideRaised = false;
        // (if slide is raised, then start dumping; if dumping is donw, then start lowering;
        // at the beginning, set all of them to false because none of them are completed
        //also, probably make convenience variables



        //if slide is at top
        if (Math.abs(slideHeight - Robot.getInstance().slide1.getCurrentPosition())  <= 5) {
            //try to avoid using getTargetPosition and getCurrentPosition as your stop conditions because these are usually dynaimc and change,
            //instead try making variables like dumperTarget or smth, which doesn't change as your stop condition
            //also, consider not using == because currentPosition may never equal TargetPosition
            //the motor spins too fast and may skip over it, best to use >= to be safe
            slideRaised = true;
            Robot.getInstance().dumper.setPosition(dumpTarget);

        }
        //if dumped
        if (Robot.getInstance().dumper.getPosition() == dumpTarget && slideRaised) {
            Robot.getInstance().dumper.setPosition(SubsystemConstants.dumperBottom);
            dumpDone = true;

        }
        if (dumpDone){
            Robot.getInstance().slide1.setTargetPosition(0);
            Robot.getInstance().slide2.setTargetPosition(0);
            isComplete = true;
        }
    }
    @Override
    public void end(){

    }
}
