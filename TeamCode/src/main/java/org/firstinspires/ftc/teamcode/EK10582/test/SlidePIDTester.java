package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="Slide PID Tester")
public class SlidePIDTester extends EKLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        robot.slides.currentState = SubsystemConstants.SlideStates.ADJUSTABLE;
        telemetry.addData("slides position", robot.slides.getSlidesPosition());
        telemetry.addData("slides max speed", robot.slides.getSlidesPosition() / time);
        telemetry.update();
        while(opModeIsActive()) {
            robot.update();
        }
    }
}