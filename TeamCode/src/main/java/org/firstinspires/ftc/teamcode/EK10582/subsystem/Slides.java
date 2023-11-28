package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;

public class Slides extends Subsystem {
    public double joystickInput; // joystick input
    PIDController slidesPID = new PIDController(0.4, 0, 0);

    public SubsystemConstants.SlideStates currentState = SubsystemConstants.SlideStates.FREE;

    @Override
    public void init(boolean auton) {

    }

    @Override
    public void update(boolean auton) {
        if(currentState == SubsystemConstants.SlideStates.FREE) {
            setSlidesPower(joystickInput);
        } else {
            setSlidesLength(currentState.position);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("slidePower", joystickInput);
        telemetry.addData("currentState", currentState);
        telemetry.addData("targetPosition", currentState.position);
        telemetry.addData("currentPosition", getSlidesPosition());
    }

    public void setSlidesLength(double targetPosition) {
        double error = targetPosition - getSlidesPosition();
        if (error >= SubsystemConstants.slidesTolerance) {
            double speed = slidesPID.update(error);
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            setSlidesPower(speed);
        } else {
            currentState = SubsystemConstants.SlideStates.FREE;
        }
    }

    public void setSlidesPower(double speed) {
        Robot.getInstance().slide1.setPower(speed);
        Robot.getInstance().slide2.setPower(speed);
    }

    public double getSlidesPosition() {
        return Robot.getInstance().slide1.getCurrentPosition() * SubsystemConstants.SLIDES_TICKS_TO_INCHES;
    }
}
