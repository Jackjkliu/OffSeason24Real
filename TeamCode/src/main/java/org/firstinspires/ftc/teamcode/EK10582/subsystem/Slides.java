package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;

public class Slides extends Subsystem {
    public double joystickInput; // joystick input
    PIDController slidesPID = new PIDController(0.003, 0, 0);

    public SubsystemConstants.SlideStates currentState = SubsystemConstants.SlideStates.FREE;
    private double targetPosition = 0;
    private double motorSpeed = 0;
    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.SlideStates.FREE;
    }

    @Override
    public void update(boolean auton) {
        if(currentState == SubsystemConstants.SlideStates.FREE || joystickInput > 0.05) {
            currentState = SubsystemConstants.SlideStates.FREE;
            setSlidesPower(joystickInput);
            targetPosition = getSlidesPosition();
        } else {
            setSlidesLength(currentState.position);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("slidePower", motorSpeed);
        telemetry.addData("currentState", currentState);
        telemetry.addData("finalPosition", currentState.position);
        telemetry.addData("targetPosition", targetPosition);
        telemetry.addData("currentPosition", getSlidesPosition());
    }

    public void setSlidesLength(double finalPosition) {
        double direction = (finalPosition - getSlidesPosition()) / Math.abs(finalPosition - getSlidesPosition());
        double delta = Math.min(SubsystemConstants.MAX_SLIDE_SPEED * Robot.getInstance().cycleTimer.seconds(), Math.abs(finalPosition - getSlidesPosition()));
        delta *= direction;
        targetPosition = getSlidesPosition() + delta;
        if (Math.abs(delta) >= SubsystemConstants.slidesTolerance) {
            double speed = slidesPID.update(delta);
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            motorSpeed = speed;
            setSlidesPower(speed);
        }
    }

    //add feedforward slides
    public void setSlidesPower(double speed) {
        Robot.getInstance().slide1.setPower(speed);
        Robot.getInstance().slide2.setPower(speed);
    }

    public double getSlidesPosition() {
        return Robot.getInstance().slide1.getCurrentPosition();
    }
}
