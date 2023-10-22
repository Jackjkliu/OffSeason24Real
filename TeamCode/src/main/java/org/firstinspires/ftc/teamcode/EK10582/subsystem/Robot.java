package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Robot {

    //creates a Robot variable to be called later
    static Robot robot = null;

    HardwareMap hardwareMap;

    //declares a custom EK LinearOpMode named linearOpMode
    EKLinear linearOpMode;

    //declare hardware here
    public DcMotor leftFront, leftBack, rightFront, rightBack, intakeSpin;
    public BNO055IMU imu;
    public Servo intakeArm;

    public WebcamName camera;


    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
    public AprilTags aprilTags = new AprilTags();

    //Add all subsystems to a list to be initiated and updated through
    private List<Subsystem> subsystems = Arrays.asList(mecanumDrive, intake, aprilTags);

    //add all subsystems that need to go through telemetry
    private List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive, intake, aprilTags);

    //Creates an arraylist called actions that stores all the actions that are currently being done
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
//
        intakeArm = hardwareMap.get(Servo.class, "intakeArm");
        intakeSpin = hardwareMap.get(DcMotor.class, "intakeSpin");

        camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);



        for(Subsystem subsystem : subsystems) {
            //initialize the subsystems
            subsystem.init(false); //change this
        }

        cycleTimer.reset();
    }

    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }

    //Add an action to the list of things the robot is currently doing.
    public void addAction(Action action) {
        action.start();
        actions.add(action);
    }

    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update();
        }

        //Updates every single action in the list of actions that are currently being done
        for(Action action : actions) {
            action.update();

            //if an action is finished, end said action and remove it from the list of things to do
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