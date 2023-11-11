package org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class AngleTurn extends Action {
    private final double theta;
    private final double speed;
    private int direction = 1; // 1 is counterclockwise, -1 is clockwise

    public AngleTurn(double theta, double speed) {
        this.theta = theta;
        this.speed = speed;
    }

    public AngleTurn(double theta, double speed, int dir) {
        this.theta = theta;
        this.speed = speed;
        this.direction = dir;
    }

    @Override
    public void start() {
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
        if (Math.abs(Robot.getInstance().imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle - theta) <= 2) {
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
        double currentAngle = Robot.getInstance().imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
        // should work for all cases
        if (Math.abs(currentAngle - theta) >= 180) {
            direction = (currentAngle >= theta) ? -1 : 1;
        }
        else if (currentAngle <= theta) {
            direction = -1;
        }
    }
}
