package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Basic Drive")
public class BasicDrive extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();

//        robot.pixelHolder.setPosition();

        while(opModeIsActive()) {


            robot.update();
        }
    }
}
