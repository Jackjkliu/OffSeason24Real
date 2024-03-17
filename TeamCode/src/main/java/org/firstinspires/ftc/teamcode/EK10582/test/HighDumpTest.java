package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing.Dump;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing.highDump;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="High Dump Tester")
public class HighDumpTest extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();
        boolean lateA = false;
        while(opModeIsActive()) {
            if(gamepad1.a && !lateA) {
                robot.addAction(new highDump());
            }
            lateA = gamepad1.a;

            robot.update();
        }
    }
}

