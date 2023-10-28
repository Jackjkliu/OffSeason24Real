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
    public double getLT1(){
        return filterJoystick(gamepad1.left_trigger);
    }
    public double getLT2(){
        return filterJoystick(gamepad2.left_trigger);
    }

    public double getRT1(){
        return filterJoystick(gamepad1.right_trigger);
    }
    public double getRT2(){
        return filterJoystick(gamepad2.right_trigger);
    }

    public double filterJoystick(double input) {
        //implements both deadzone and scaled drive
        if(Math.abs(input) < JoystickConstants.DEADZONE) return 0;
        if(input > 0) {
            return JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        } else {
            input *= -1;
            return -JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        }
    }

    // example of button for subsystem

    public boolean raiseIntakeArm() {
        return gamepad1.dpad_up;
    }

    public boolean lowerIntakeArm() {
        return gamepad1.dpad_down;
    }
    //in teleop: DriverStation.getCollection() would return whether or not button a was just pressed & also update a
    //associates button press combinations with the functions that they want to accomplish rather than clouding up teleop


}
