package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="Motor Tester")
@Disabled
public class MotorTester extends EKLinear {
    @Override
    public void runOpMode() {
        waitForStart();
        DcMotor intake1 = hardwareMap.get(DcMotor.class, "intakeSpin");
        while(opModeIsActive()) {
            intake1.setPower(gamepad1.right_trigger*.5);
            intake1.setPower(-gamepad1.left_trigger*.5);
            telemetry.addData("intake speed: ", gamepad1.right_trigger);
            telemetry.update();
        }
    }
}
