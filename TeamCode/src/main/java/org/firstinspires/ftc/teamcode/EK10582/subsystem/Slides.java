package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem {
    private final int SLIDEMAX = SubsystemConstants.SLIDEMAX;
    private final int[] slidepos = SubsystemConstants.slidepos; // array of target positions
    private double kP = SubsystemConstants.kP;
    private double kI = SubsystemConstants.kI;
    private double kD = SubsystemConstants.kD;
    private double maxI = 0.25; // maximum value for I
    private double p, i, d; // 'adjusted' PID, terms of f of e of t function lol, helps to calculate motor speeds
    public double slideControl; // joystick input
    private boolean freeControl = true;
    private double autoSlidePower = 0;
    private double speed;
    private int targetPosition;
    private double previousError; // used for de
    ElapsedTime timer = new ElapsedTime(); // used for dt

    @Override
    public void init(boolean auton) {
       speed = SubsystemConstants.speed;
    }

    public void update(boolean auton) {
        if (auton) {
            double error = targetPosition - Robot.getInstance().slide1.getCurrentPosition();

            p = kP * -1 * error;
            i += kI * error * (timer.seconds());
            if(i > maxI) {
                i = maxI;
            } else if(i < -maxI) {
                i = -maxI;
            }
            d = kD * (error - previousError) / (timer.seconds());

            speed = p + i + d;

            timer.reset(); // used to calculate dt in next iteration
            previousError = error; // used to calculate de in next iteration

            if(speed == 0 && getSlidePosition() < SLIDEMAX) {
                // if no change needed to slide position, keep small amount of speed to fight gravity etc
                speed = 0.12;
            }

            Robot.getInstance().slide1.setPower(speed);
            Robot.getInstance().slide2.setPower(speed);
        }
        else {
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

            speed = slideControl;
            if(speed == 0 && getSlidePosition() < SLIDEMAX) {
                speed = 0.12;
            }
            if(freeControl) {
                Robot.getInstance().slide1.setPower(speed);
                Robot.getInstance().slide2.setPower(speed);
            } else {
                Robot.getInstance().slide1.setPower(autoSlidePower);
                Robot.getInstance().slide2.setPower(autoSlidePower);
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("target Position: ", targetPosition);
        telemetry.addData("slideControl", slideControl);
        telemetry.addData("autoSlidePower", autoSlidePower);
        telemetry.addData("freeControl", freeControl);
        telemetry.addData("slide position", getSlidePosition());
    }

    public void setTargetPosition(int position){
        targetPosition = slidepos[position];
        Robot.getInstance().slide1.setTargetPosition(targetPosition);
        Robot.getInstance().slide2.setTargetPosition(targetPosition);
    }

    public double getSlidePosition() {
        return Robot.getInstance().slide1.getCurrentPosition();
    }

    public void setFreeControl(boolean freeControl) {
        this.freeControl = freeControl;
    }

    public void setAutoSlidePower(double targetPosition) {
        double error = targetPosition - Robot.getInstance().slide1.getCurrentPosition();

        p = kP * -1 * error;
        i += kI * error * (timer.seconds());
        if(i > maxI) {
            i = maxI;
        } else if(i < -maxI) {
            i = -maxI;
        }
        d = kD * (error - previousError) / (timer.seconds());

        speed = p + i + d;

        timer.reset(); // used to calculate dt in next iteration
        previousError = error; // used to calculate de in next iteration

        if(speed == 0 && getSlidePosition() < SLIDEMAX) {
            // if no change needed to slide position, keep small amount of speed to fight gravity etc
            speed = 0.12;
        }

        autoSlidePower = speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
