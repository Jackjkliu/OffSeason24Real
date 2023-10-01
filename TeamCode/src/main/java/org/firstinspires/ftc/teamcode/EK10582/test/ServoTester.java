package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name = "Servo Tester")
public class ServoTester extends EKLinear {

    @Override
    public void run() {
        Robot.getInstance().init(hardwareMap, this);
        waitForStart();
        double currPos = 0;
        while(opModeIsActive()) {
            if(gamepad1.y) {
                currPos += 0.0005;
            }
            if(gamepad1.a) {
                currPos -= 0.0005;
            }

            Robot.getInstance().armServo.setPosition(currPos);
            telemetry.addData("pos", currPos);
            telemetry.update();
        }
    }
}
