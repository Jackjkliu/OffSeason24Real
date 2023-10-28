package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;


//What is mecanumDrive:
//drive system is the use of special wheels called mecanum wheels
//child class of subsystem
@Config
public class MecanumDrive extends Subsystem{
    //MecanumDrive is a subsystem that controls the drive. It sets the motor powers
    //according to the lx, ly and rx values.
    public double SPEED = 0.8;

    //what is gyro0n for?
    //__
    public boolean gyroOn = false;
    //PIDController gyroPID = new PIDController(4, 0, 0.03);

    //these values are set in Drive
    public double rx, lx, ly;
    private double targetAngle = 0;


    //why are var not instantiated in init?
    //is it becuase they keep needing to be updated?
    //__
    @Override
    public void init(boolean auton) {
//run once and never run again
    }

    @Override
    public void update() {
        //keep updating, runtime loop
        double bias = 0;
//        if(gyroOn) {
//            bias = calculateBias();
//        }

        //what is bias?
        //__
        double lf = ly + rx + lx + bias;
        double lb = ly + rx - lx + bias;
        double rf = ly - rx - lx - bias;
        double rb = ly - rx + lx - bias;

        double ratio;
        double max = Math.max(Math.max(Math.abs(lb), Math.abs(lf)), Math.max(Math.abs(rb), Math.abs(rf)));
        double magnitude = Math.sqrt((lx * lx) + (ly * ly) + (rx * rx));
        if (max == 0) {
            ratio = 0;
        } else {
            ratio = magnitude / max * SPEED;
        }
        Robot.getInstance().leftFront.setPower(lf * ratio);
        Robot.getInstance().leftBack.setPower(lb * ratio);
        Robot.getInstance().rightFront.setPower(rf * ratio);
        Robot.getInstance().rightBack.setPower(rb * ratio);
    }

//    public double calculateBias() {
//        double currentAngle = Robot.getInstance().imu.getAngularOrientation().firstAngle;
//        if(Math.abs(rx) < 0.01) {
//            double error;
//            if(Math.abs(currentAngle - targetAngle) > Math.PI * 3/4) {
//                error = currentAngle - Math.PI * 2 - targetAngle;
//            } else {
//                error = currentAngle - targetAngle;
//            }
//            return gyroPID.update(error);
//        } else {
//            targetAngle = currentAngle;
//            return 0;
//        }
//    }

    @Override
    public void stop() {
        //what happens when you press stop or when the program ends

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("ly", ly);
        telemetry.addData("lx", lx);
        telemetry.addData("rx", rx);
        telemetry.addData("targetAngle", targetAngle);
    }

    public void resetEncoders(){
        Robot.getInstance().leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Robot.getInstance().rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int averageTicks(){
        return (Math.abs(Robot.getInstance().leftFront.getCurrentPosition()) +
                Math.abs(Robot.getInstance().rightFront.getCurrentPosition()) +
                Math.abs(Robot.getInstance().rightBack.getCurrentPosition()) +
                Math.abs(Robot.getInstance().leftBack.getCurrentPosition()))/4;
    }

    public void straight(double distance, double speed, double direction){
        resetEncoders();

        double velocity = speed * direction;

        Robot.getInstance().leftBack.setPower(velocity);
        Robot.getInstance().rightBack.setPower(velocity);
        Robot.getInstance().leftFront.setPower(velocity);
        Robot.getInstance().rightFront.setPower(velocity);

        while (true) {
            if (averageTicks() >= distance) {
                Robot.getInstance().leftBack.setPower(0);
                Robot.getInstance().rightBack.setPower(0);
                Robot.getInstance().leftFront.setPower(0);
                Robot.getInstance().rightFront.setPower(0);
                break;
            }
        }
    }

    public void strafe(double distance, double speed, double direction){
        resetEncoders();

        while (averageTicks() < distance){
            Robot.getInstance().leftBack.setPower(speed*-1*direction);
            Robot.getInstance().rightBack.setPower(speed*direction);
            Robot.getInstance().leftFront.setPower(speed*direction);
            Robot.getInstance().rightFront.setPower(speed*-1*direction);
        }
        Robot.getInstance().leftFront.setPower(0);
        Robot.getInstance().leftBack.setPower(0);
        Robot.getInstance().rightFront.setPower(0);
        Robot.getInstance().rightBack.setPower(0);

    }

    public void turnTo(double targetAngle, double speed, double direction){

        while (Math.abs(Robot.getInstance().imu.getAngularOrientation().firstAngle - targetAngle) > 0.01){
            Robot.getInstance().leftBack.setPower(speed*direction);
            Robot.getInstance().rightBack.setPower(speed*-1*direction);
            Robot.getInstance().leftFront.setPower(speed*direction);
            Robot.getInstance().rightFront.setPower(speed*-1*direction);
        }
        Robot.getInstance().leftFront.setPower(0);
        Robot.getInstance().leftBack.setPower(0);
        Robot.getInstance().rightFront.setPower(0);
        Robot.getInstance().rightBack.setPower(0);
    }
}
