package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends Subsystem{

    public double increment;
    //servoUpButton and servoDownButton are the buttons that control how much the arm servo go up and down
    public boolean servoUp, servoDown;
    public double servoTarget;
    public double intakeSpeed;

    //this is supposed to have 7 values in it but rn it only has 2 because we haven't tested heights yet
    public final double[] servoPos = {0.21, 0.7};
    @Override
    public void init(boolean auton){
        increment = 0.0001; //how fast the servo will move
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
            servoTarget = 1;
        }
        else if(servoTarget<0){
            servoTarget=0;
        }

        Robot.getInstance().intakeArm.setPosition(servoTarget);


        //set intake power to intake speed
        Robot.getInstance().intakeSpin.setPower(intakeSpeed * -0.6);
        //0.6 is the hard limit for intake speed. change as needed.
    }

    @Override
    public void stop(){

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Arm Servo Position: ", Robot.getInstance().intakeArm.getPosition());
        telemetry.addData("Spintake speed: ", intakeSpeed);

    }

    //position goes from 0 to 6. 1 is ground, 6 is all the way up.
    //1 is one pixel from ground, etc.
    public void setIntakeArmPos(int position){
        Robot.getInstance().intakeArm.setPosition(servoPos[position]);
    }
}
