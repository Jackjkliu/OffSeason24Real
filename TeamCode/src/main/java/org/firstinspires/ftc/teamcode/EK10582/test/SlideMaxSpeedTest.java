package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Slide Max Speed Test")
public class SlideMaxSpeedTest extends EKLinear {

    public static double time = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.seconds() < time) {
            robot.slides.setSlidesPower(0.8);
        }
        robot.slides.setSlidesPower(0);
        telemetry.addData("slides position", robot.slides.getSlidesPosition());
        telemetry.addData("slides max speed", robot.slides.getSlidesPosition() / time);
        telemetry.update();
        while(opModeIsActive()) {
            //hi
        }
    }
}