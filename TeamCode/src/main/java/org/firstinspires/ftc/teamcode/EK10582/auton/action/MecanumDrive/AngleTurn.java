package org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class AngleTurn extends Action {
    private double theta;
    private double speed;
    private int direction = 1; // 1 is counterclockwise, -1 is clockwise

    public AngleTurn(double theta, double speed) {
        this.theta = theta;
        this.speed = speed;
    }


    @Override
    public void start() {
        //reset motor encoders
        // might not be necessary for turning?
//        Robot.getInstance().leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Robot.getInstance().leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Robot.getInstance().leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Robot.getInstance().leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Robot.getInstance().rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Robot.getInstance().rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        Robot.getInstance().rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Robot.getInstance().rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setDirection();

        //set motor powers
        Robot.getInstance().leftFront.setPower((-1) * speed * direction);
        Robot.getInstance().leftBack.setPower((-1) * speed * direction);
        Robot.getInstance().rightFront.setPower(speed * direction);
        Robot.getInstance().rightBack.setPower(speed * direction);
    }

    @Override
    public void update() {
        // stop motors once the current angle is close enough to the target angle (theta)
        if (Math.abs(Robot.getInstance().imu.getAngularOrientation().firstAngle - theta) <= 0.01) {
            Robot.getInstance().leftFront.setPower(0);
            Robot.getInstance().leftBack.setPower(0);
            Robot.getInstance().rightFront.setPower(0);
            Robot.getInstance().rightBack.setPower(0);
            isComplete = true;
        }
    }

    @Override
    public void end() {

    }

    private void setDirection() {
        double currentAngle = Robot.getInstance().imu.getAngularOrientation().firstAngle;
        // should work for all cases
        if ((currentAngle - theta) >= 180) {
            direction = -1;
        }
        else if (currentAngle <= theta) {
            direction = -1;
        }
        else { // might not be necessary
            direction = 1;
        }
    }
}
