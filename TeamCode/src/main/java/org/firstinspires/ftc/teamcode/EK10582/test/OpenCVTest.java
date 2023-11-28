package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="OpenCV Test")
public class OpenCVTest extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();

        while(opModeIsActive()) {
//            telemetry.addData("pixelpos: ", cameraPipeline.max);
            robot.update();
            telemetry.update();
        }
    }
}
