package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Speak Tester")
public class SpeakTester extends EKLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        boolean lateA = false;
        while(opModeIsActive()) {
            if (gamepad1.a && !lateA) {
                telemetry.speak("Wucru is the cutest");
            }
            lateA = gamepad1.a;
            telemetry.update();
            //robot.update();
        }
    }
}
