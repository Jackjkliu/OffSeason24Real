package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.cameraPipeline;

@TeleOp(name="OPenXCV Tester")
public class OpenCVTest extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("pixelpos: ", cameraPipeline.max);
            robot.update();
            telemetry.update();
        }
    }
}
