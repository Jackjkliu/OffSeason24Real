package org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class AngleMove extends Action {

    double theta;
    double distance;
    double speed; //0 to 0.8 because negative is already accounted in Math.sin
    final double TICKS_TO_INCHES = 0.029; // placeholder, change later

    public AngleMove(double theta, double distance, double speed) {
        this.theta = Math.toRadians(theta);
        this.distance = distance;
        this.speed = speed;
    }


    @Override
    public void start() {
        //reset motor encoders
        Robot.getInstance().leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.getInstance().leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.getInstance().rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Robot.getInstance().rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set motor powers
        Robot.getInstance().leftFront.setPower(speed*Math.sin(theta+Math.PI/4));
        Robot.getInstance().leftBack.setPower(speed*Math.sin(theta+3*Math.PI/4));
        Robot.getInstance().rightFront.setPower(speed*Math.sin(theta+3*Math.PI/4));
        Robot.getInstance().rightBack.setPower(speed*Math.sin(theta+Math.PI/4));
    }

    @Override
    public void update() {
        double leftDiagonal = (Robot.getInstance().leftFront.getCurrentPosition() + Robot.getInstance().rightBack.getCurrentPosition())/2.0;
        double rightDiagonal = (Robot.getInstance().leftBack.getCurrentPosition() + Robot.getInstance().rightFront.getCurrentPosition())/2.0;
        if(Math.sqrt(Math.pow(leftDiagonal, 2) + Math.pow(rightDiagonal, 2)) * TICKS_TO_INCHES >= distance) {
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
}
