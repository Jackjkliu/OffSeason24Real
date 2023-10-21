package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="New Drive")
public class Drive extends EKLinear {
    @Override
    public void run(){
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

            //intake
            robot.intake.servoUp = driverStation.raiseIntakeArm();
            robot.intake.servoDown = driverStation.lowerIntakeArm();
            robot.intake.intakeSpeed = driverStation.getLT();

            robot.update();
        }
    }
}
