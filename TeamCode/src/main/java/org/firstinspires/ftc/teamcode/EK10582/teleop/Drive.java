package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Slides.SlidesMoveTo;

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
            robot.slides.slideControl = driverStation.getSlidePower();
            if(driverStation.getSlideToCollectionPos()) {
                robot.addAction(new SlidesMoveTo(-109));
            }

            //housing
            robot.housing.right = driverStation.clampRight(); //
            robot.housing.left = driverStation.clampLeft();
            robot.housing.dump = driverStation.dump();

            robot.update();

        }
    }
}