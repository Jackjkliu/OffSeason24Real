package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;

public class Slides extends Subsystem {
    public static double p = 0.003, i = 0, d = 0, f = 0;
    public static double adjustableTarget = 0;

    public double joystickInput; // joystick input
    PIDController slidesPID = new PIDController(0.003, 0, 0);

    public SubsystemConstants.SlideStates currentState = SubsystemConstants.SlideStates.FREE;
    private double motorSpeed = 0;
    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.SlideStates.FREE;
    }

    @Override
    public void update(boolean auton) {
        slidesPID.setPID(p, i, d);
        SubsystemConstants.MAX_FEEDFORWARD = f;

        if(currentState == SubsystemConstants.SlideStates.FREE || joystickInput > 0.05) {
            currentState = SubsystemConstants.SlideStates.FREE;
            setSlidesPower(joystickInput);
        } else if(currentState == SubsystemConstants.SlideStates.ADJUSTABLE) {
            setSlidesLength(adjustableTarget);
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
        telemetry.addData("currentPosition", getSlidesPosition());
    }

    public void setSlidesLength(double targetPosition) {
        double direction = (targetPosition - getSlidesPosition()) / Math.abs(targetPosition - getSlidesPosition());
        double error = targetPosition - getSlidesPosition();
        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
            double speed = slidesPID.update(error);
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            motorSpeed = speed;
            setSlidesPower(speed);
        }
    }

    //ADD IT TO SPEED AFTER TUNING
    public void setSlidesPower(double speed) {
        double ff = Math.pow(getSlidesPosition() / SubsystemConstants.MAX_SLIDE_HEIGHT, 4) * SubsystemConstants.MAX_FEEDFORWARD;
        Robot.getInstance().slide1.setPower(speed);
        Robot.getInstance().slide2.setPower(speed);
    }

    public double getSlidesPosition() {
        return Robot.getInstance().slide1.getCurrentPosition();
    }
}
