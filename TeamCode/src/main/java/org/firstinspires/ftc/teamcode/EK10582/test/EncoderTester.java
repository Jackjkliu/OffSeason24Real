package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Encoder Tester")
@Disabled
public class EncoderTester extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("lf", Robot.getInstance().leftFront.getCurrentPosition());
            telemetry.addData("lb", Robot.getInstance().leftBack.getCurrentPosition());
            telemetry.addData("rf", Robot.getInstance().rightFront.getCurrentPosition());
            telemetry.addData("rb", Robot.getInstance().rightBack.getCurrentPosition());
            telemetry.update();
        }
    }
}
