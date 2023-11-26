package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="New Drive")
public class Drive extends EKLinear {

    @Override
    public void runOpMode(){
        waitForStart();
        while(opModeIsActive()) {
            /*
            Controls:
            dpad_up is raise arm
            dpad_down is lower arm
            LT is spin
             */

            //drive
            robot.mecanumDrive.lx = driverStation.getXVel();
            robot.mecanumDrive.ly = driverStation.getYVel();
            robot.mecanumDrive.rx = driverStation.getRotVel();
            robot.mecanumDrive.slowMode = driverStation.slowmode();

            //intake
            robot.intake.servoUp = driverStation.raiseIntakeArm(); //dpad up
            robot.intake.servoDown = driverStation.lowerIntakeArm(); //dpad down
            robot.intake.intakeSpeed = driverStation.getLT2(); //left trigger
            robot.intake.intakeBack = driverStation.getRT2(); //right trigger

            //slides
            robot.slides.joystickInput = driverStation.getSlidePower();
            if (driverStation.getSlideCollect()) {
                robot.slides.currentState = SubsystemConstants.SlideStates.COLLECT;
            }
//            else if (driverStation.getSlideCollect())

            //housing
            robot.housing.right = driverStation.clampRight(); //
            robot.housing.left = driverStation.clampLeft();
            robot.housing.dump = driverStation.dump();

            robot.update();

        }
    }
}