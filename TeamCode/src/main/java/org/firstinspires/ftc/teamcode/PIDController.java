package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class PIDController {
    public double kP, kI, kD;
    public static double maxI = 0.25;
    double previousError;
    double p, i, d;
    ElapsedTime timer = new ElapsedTime();

    public PIDController(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setPID(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double update(double error) {
        p = kP * 1 * error;
        i += kI * error * (timer.seconds());
        if(i > maxI) {
            i = maxI;
        } else if(i < -maxI) {
            i = -maxI;
        }
        d = kD * (error - previousError) / (timer.seconds());

        double bias = p + i + d;
        previousError = error;
        timer.reset();
        return bias;
    }
}
