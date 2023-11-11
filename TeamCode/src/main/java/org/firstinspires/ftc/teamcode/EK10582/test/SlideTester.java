package org.firstinspires.ftc.teamcode.EK10582.test;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="Slide test")
public class SlideTester extends EKLinear {
    @Override
    public void runOpMode(){
        waitForStart();
        while(opModeIsActive()) {

            robot.slides.slideUp = driverStation.getLT2();
            robot.slides.slideDown = driverStation.getRT2();
            robot.update();

        }
    }
}