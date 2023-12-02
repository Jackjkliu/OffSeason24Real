package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Servo Tester (intake arm port)")
public class ServoTester extends EKLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        double servoPosition = 0.5;
        while(opModeIsActive()) {

            Robot.getInstance().pixelHolder.setPosition(0.47);

            telemetry.addData("Servo Position", servoPosition);
            telemetry.update();
            //robot.update();
        }
    }
}
