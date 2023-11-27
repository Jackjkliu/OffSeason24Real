package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.acmerobotics.dashboard.config.Config;
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

    //-----------------------first controller------------------------------
 
    public double moveFrontAndBack() {
        return -filterJoystick(gamepad1.left_stick_y);
    }

    public double moveLeftAndRight() {
        return filterJoystick(gamepad1.left_stick_x);
    }

    public double rotate() {
        return filterJoystick(gamepad1.right_stick_x);
    }

    //------put clampLeft, clampRight on controller 2, maybe dpad, buttons?? idk-------------
    boolean lateRight = false;
    public boolean clampRight(){
        boolean out;
        out = gamepad1.x && !lateRight;
        lateRight = gamepad1.x;
        return out;
    }

    boolean lateLeft = false;
    public boolean clampLeft(){
        boolean out;
        out = gamepad1.y && !lateLeft;
        lateLeft = gamepad1.y;
        return out;
//        return gamepad1.y;
    }

    boolean lateA = false;
    public boolean slowMode(){
        boolean out;
        out = gamepad1.a && !lateA;
        lateA = gamepad1.a;
        return out;
    }

    //-----------------------second controller-----------------------------

    public double getSlidePower(){ return filterJoystick(gamepad2.left_stick_y); }

    public double intakeOut(){
        return filterJoystick(gamepad2.left_trigger);
    }

    public double intakeIn(){
        return filterJoystick(gamepad2.right_trigger);
    }

    public boolean raiseIntakeArm() {
        return gamepad2.dpad_up;
    }

    public boolean lowerIntakeArm() {
        return gamepad2.dpad_down;
    }

    // second controller buttons

    boolean lateX2 = false;
    public boolean getSlideCollect() {
        boolean out;
        out = gamepad2.x && !lateX2;
        lateX2 = gamepad2.x;
        return out;
    }

    boolean lateY2 = false;
    public boolean getSlideFree() {
        boolean out;
        out = gamepad2.y && !lateY2;
        lateY2 = gamepad2.y;
        return out;
    }

    boolean lateB2 = false;
    public boolean getSlideLow() {
        boolean out;
        out = gamepad2.b && !lateB2;
        lateB2 = gamepad2.b;
        return out;
    }

    boolean lateA2 = false;
    public boolean dump() {
        boolean out;
        out = gamepad2.a && !lateA2;
        lateA2 = gamepad2.a;
        return out;
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

    //in teleop: DriverStation.getCollection() would return whether or not button a was just pressed & also update a
    //associates button press combinations with the functions that they want to accomplish rather than clouding up teleop
}
