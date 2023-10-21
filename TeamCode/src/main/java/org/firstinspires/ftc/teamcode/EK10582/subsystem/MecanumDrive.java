package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;

@Config
public class MecanumDrive extends Subsystem{
    //MecanumDrive is a subsystem that controls the drive. It sets the motor powers
    //according to the lx, ly and rx values.
    public double SPEED = 0.8;
    public boolean gyroOn = false;
    //PIDController gyroPID = new PIDController(4, 0, 0.03);

    //these values are set in Drive
    public double rx, lx, ly;
    private double targetAngle = 0;


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
}
