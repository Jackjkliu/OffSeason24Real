package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="Servo Tester (intake arm port)")
public class ServoTester extends LinearOpMode {
    //@Override
    public void run(){
        double servoPosition = 0.5;
        while(opModeIsActive()) {
//            servoPosition += driverStation.getLT1() - driverStation.getRT1();
//            robot.intakeArm.setPosition(servoPosition);

            telemetry.addData("Servo Position", servoPosition);
            telemetry.update();
            //robot.update();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
