package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
class JoystickConstants {
    public static double DEADZONE = 0.01;
    public static double minJoystick = 0.1;
    public static double maxJoystick = 1;
}

public class DriverStation {
    Gamepad gamepad1, gamepad2;

    public DriverStation(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public double getYVel() {
        return filterJoystick(gamepad1.left_stick_y);
    }

    public double getXVel() {
        return filterJoystick(gamepad1.left_stick_x);
    }

    public double getRotVel() {
        return filterJoystick(gamepad1.right_stick_x);
    }

    public double filterJoystick(double input) {
        if(Math.abs(input) < JoystickConstants.DEADZONE) return 0;
        if(input > 0) {
            return JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        } else {
            input *= -1;
            return -JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        }
    }

    // example of button for subsystem
//    boolean lateA = false;
//    public boolean getCollection() {
//        boolean out;
//        out = gamepad1.a && !lateA;
//        lateA = gamepad1.a;
//        return out;
//    }

}
