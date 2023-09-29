package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class robot {

    HardwareMap hardwareMap;
    public DcMotor leftBack, leftFront, rightFront, rightBack, fourBar;
    public Servo arm, clawLeft, clawRight;
    public DistanceSensor dist;
    public BNO055IMU imu;
    public OpenCvWebcam webcam;



    final static double TICKS_TO_INCH_STRAIGHT = 19.5;
    final static double TICKS_TO_INCH_STRAFE = 30;


    public robot(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        leftBack = hardwareMap.get(DcMotorEx.class, "lb");
        rightBack = hardwareMap.get(DcMotorEx.class, "rb");
        leftFront = hardwareMap.get(DcMotorEx.class, "lf");
        rightFront = hardwareMap.get(DcMotorEx.class, "rf");

        fourBar = hardwareMap.get(DcMotorEx.class, "fourBar");
        arm = hardwareMap.get(Servo.class, "arm");
        clawLeft = hardwareMap.get(Servo.class, "leftClaw");
        clawRight = hardwareMap.get(Servo.class, "rightClaw");

        dist = hardwareMap.get(DistanceSensor.class, "dist");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setTargetPosition(0);
        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        fourBar.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        this.hardwareMap = hardwareMap;

    }
}