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
        return -filterJoystick(gamepad1.left_stick_y);
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

    public double getSlidePower(){return filterJoystick(gamepad2.left_stick_y);}

    public boolean getDPadL(){return gamepad2.dpad_left;}
    public boolean getDPadR(){return gamepad2.dpad_right;}

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
        return gamepad2.dpad_up;
    }

    public boolean lowerIntakeArm() {
        return gamepad2.dpad_down;
    }

    boolean lateRight = false;
    public boolean clampRight(){
        boolean out;
        out = gamepad2.x && !lateRight;
        lateRight = gamepad2.x;
        return out;
//        return gamepad1.x;
    }

    boolean lateLeft = false;
    public boolean clampLeft(){
        boolean out;
        out = gamepad2.y && !lateLeft;
        lateLeft = gamepad2.y;
        return out;
//        return gamepad1.y;
    }

    boolean lateDump = false;
    public boolean dump(){
        boolean out;
        out = gamepad2.b && !lateDump;
        lateDump = gamepad2.b;
        return out;
//        return gamepad1.b;
    }

    boolean latea = false;
    public boolean slowmode(){
        boolean out;
        out = gamepad1.a && !latea;
        latea = gamepad1.a;
        return out;
    }

//    boolean lateA = false;
//    public boolean getCollection() {
//        boolean out;
//        out = gamepad1.a && !lateA;
//        lateA = gamepad1.a;
//        return out;
//    }

    //in teleop: DriverStation.getCollection() would return whether or not button a was just pressed & also update a
    //associates button press combinations with the functions that they want to accomplish rather than clouding up teleop



}
