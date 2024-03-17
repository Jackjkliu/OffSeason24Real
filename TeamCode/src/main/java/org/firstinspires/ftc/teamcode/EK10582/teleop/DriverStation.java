package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

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
 
    public double getYVel() {
        return -filterJoystick(gamepad1.left_stick_y);
    }

    public double getXVel() {
        return filterJoystick(gamepad1.left_stick_x);
    }

    public double getRotVel() {
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
    }

    boolean lateRightBump = false;
    public boolean slowMode(){
        boolean out;
        out = gamepad1.right_bumper && !lateRightBump;
        lateRightBump = gamepad1.right_bumper;
        return out;
    }

    //-----------------------second controller-----------------------------

    public double getSlidePower(){
        return -filterJoystick(gamepad2.left_stick_y);
    }

    public double getHangingPower() { return -gamepad2.right_stick_y; }

    ElapsedTime droneButtonDown = new ElapsedTime();
    boolean droneButtonLate = false;
    boolean droneRelease = false;
    boolean droneReleaseLate = false;
    public boolean getDroneDown() {
        if(gamepad1.y && !droneButtonLate) {
            droneButtonDown.reset();
        }
        if(!gamepad1.y) {
            droneButtonDown.reset();
        }
        droneButtonLate = gamepad1.y;

        if(droneButtonDown.milliseconds() > 1000) {
            droneRelease = true;
        } else {
            droneRelease = false;
        }
        boolean out = droneRelease && !droneReleaseLate;
        droneReleaseLate = droneRelease;
        return out;
    }

    boolean isLateRStick = false;
    public boolean getHangingServo() {
        boolean out;
        out = gamepad2.right_stick_button && !isLateRStick;
        isLateRStick = gamepad2.right_stick_button;
        return out;
    }

    public double intakeOut(){
        return filterJoystick(gamepad2.left_trigger);
    }

    public double intakeIn(){
        return filterJoystick(gamepad2.right_trigger);
    }

    public boolean raiseDumperOver() {
        return gamepad2.dpad_up;
    }

    public boolean autoDumperUnder() {
        return gamepad2.dpad_down;
    }

    public boolean resetDumper() {
        return gamepad2.dpad_right;
    }

    public boolean lowerDumperUnder() {
        return gamepad2.dpad_left;
    }

    boolean lateA2 = false;
    public boolean movePixelHolder() {
        boolean out;
        out = gamepad2.a && !lateA2;
        lateA2 = gamepad2.a;
        return out;
    }

    boolean lateY2 = false;
    public boolean getSlideFree() {
        boolean out;
        out = gamepad2.y && !lateY2;
        lateY2 = gamepad2.y;
        return out;
    }
//
//    boolean lateX2 = false;
//    public boolean getSlideLow() {
//        boolean out;
//        out = gamepad2.x && !lateX2;
//        lateX2 = gamepad2.x;
//        return out;
//    }
//
//    boolean lateB2 = false;
//    public boolean getSlidePreset() {
//        boolean out;
//        out = gamepad2.b && !lateB2;
//        lateB2 = gamepad2.b;
//        return out;
//    }

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
