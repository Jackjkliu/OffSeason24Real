package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;

@TeleOp(name="Motor Tester")
public class MotorTester extends EKLinear {
    @Override
    public void run() {
        DcMotor motor = hardwareMap.get(DcMotor.class, "leftFront");
        while(opModeIsActive()) {
            motor.setPower((driverStation.getLT1() - driverStation.getRT1()) * 0.8);
        }
    }
}
