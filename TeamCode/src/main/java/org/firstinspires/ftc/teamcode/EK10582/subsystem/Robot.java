package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Robot {

    static Robot robot = null;
    HardwareMap hardwareMap;
    EKLinear linearOpMode;

    //declare hardware here
    public DcMotor leftFront, leftBack, rightFront, rightBack;
    public BNO055IMU imu;

    public MecanumDrive mecanumDrive = new MecanumDrive();
    private List<Subsystem> subsystems = Arrays.asList(mecanumDrive);
    private List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive);
    private ArrayList<Action> actions = new ArrayList<Action>();

    private ElapsedTime cycleTimer = new ElapsedTime();

    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        for(Subsystem subsystem : subsystems) {
            subsystem.init(false); //change this
        }

        cycleTimer.reset();
    }

    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }

    public void addAction(Action action) {
        action.start();
        actions.add(action);
    }

    public void update() {
        for(Subsystem subsystem : subsystems) {
            subsystem.update();
        }
        for(Action action : actions) {
            action.update();
            if(action.isComplete) {
                action.end();
                actions.remove(action);
            }
        }

        //telemetry
        linearOpMode.allTelemetry.addData("Match Time", linearOpMode.matchTimer.milliseconds());
        linearOpMode.allTelemetry.addData("Cycle Time", cycleTimer.milliseconds());
        cycleTimer.reset();
        for(Subsystem subsystem : telemetrySubsystems) {
            linearOpMode.allTelemetry.addData("  --  ", (subsystem.getClass().getSimpleName() + "  --  "));
            subsystem.printToTelemetry(linearOpMode.allTelemetry);
        }
        linearOpMode.allTelemetry.update();
    }
}