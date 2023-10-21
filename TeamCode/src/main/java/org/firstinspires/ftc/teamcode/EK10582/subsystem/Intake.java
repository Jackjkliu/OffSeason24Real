package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends Subsystem{

    public double increment;
    //servoUpButton and servoDownButton are the buttons that control how much the arm servo go up and down
    public boolean servoUp, servoDown;
    public double servoTarget;
    public double intakeSpeed;
    @Override
    public void init(boolean auton){
        increment = 0.05;
        servoTarget= 0.5; //Change this to the resting/starting position of the servo
        servoDown = false;// these default to false in case you are in auton
        servoUp= false;
        //in auton, just set servoTarget directly

    }

    @Override
    public void update(){
        if (servoUp) {
            servoTarget += increment;
        }
        if(servoDown){
            servoTarget -= increment;
        }

        //set intake arm's position to the servotarget. Added failsafes
        if(servoTarget >1) {
            Robot.getInstance().intakeArm.setPosition(1);
        }
        else if(servoTarget<0){
            Robot.getInstance().intakeArm.setPosition(0);
        }
        else{
            Robot.getInstance().intakeArm.setPosition(servoTarget);
        }

        //set intake power to intake speed
        Robot.getInstance().intakeSpin.setPower(intakeSpeed * 0.8);
    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Arm Servo Position: ", Robot.getInstance().intakeArm.getPosition());
        telemetry.addData("Spintake speed: ", intakeSpeed);

    }
}
