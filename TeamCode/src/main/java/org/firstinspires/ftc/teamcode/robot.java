package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class robot {

    HardwareMap hardwareMap;
    public DcMotor leftBack, leftFront, rightFront, rightBack;

    public BNO055IMU imu;




    final static double TICKS_TO_INCH_STRAIGHT = 19.5;
    final static double TICKS_TO_INCH_STRAFE = 30;


    public robot(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        leftBack = hardwareMap.get(DcMotorEx.class, "lb");
        rightBack = hardwareMap.get(DcMotorEx.class, "rb");
        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");


        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        this.hardwareMap = hardwareMap;

    }
}