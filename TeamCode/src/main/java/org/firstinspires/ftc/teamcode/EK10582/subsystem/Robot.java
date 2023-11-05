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


//robot class to create a robot object that will be used
//to do auton and teleop stuff
public class Robot {

    //creates a Robot variable to be called later
    static Robot robot = null;

    HardwareMap hardwareMap;

    //declares a custom EK LinearOpMode named linearOpMode
    EKLinear linearOpMode;

    //declare hardware here
    public DcMotor leftFront, leftBack, rightFront, rightBack, intakeSpin;

    public DcMotor slide1;

    public BNO055IMU imu;
    public Servo intakeArm;

    public Servo pixelHolder;
    public Servo dumper;

    public WebcamName camera;

    //why are subsystems and other elements declared here instead of init?
    //__

    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Intake intake = new Intake();
//    public AprilTags aprilTags = new AprilTags();
    public Slides slides = new Slides();

    public housing housing = new housing();

    //Add all subsystems to a list to be initiated and updated through
    private List<Subsystem> subsystems = Arrays.asList(mecanumDrive, intake, slides,housing);

    //add all subsystems that need to go through telemetry
    private List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive, intake, slides);

    //Creates an arraylist called actions that stores all the actions that are currently being done
    private ArrayList<Action> actions = new ArrayList<Action>();

    private ElapsedTime cycleTimer = new ElapsedTime();

    //sets values to declared but not instantiated values
    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        //deviceName refers to what will be shown on the phone

        //hardwareMap is a java map(not hashmap)
        //map - a way to keep track of information in the form of key-value pairs.
        //hardwareMap.get - method used to retrieve a specific hardware device from the hardwareMap
        //___.class - referencing the class instead of using an object of the class
        //deviceName refers to what will be shown on the phone
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slide1 = hardwareMap.get(DcMotor.class, "slide1");
        slide1.setTargetPosition(0);
        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
//
        intakeArm = hardwareMap.get(Servo.class, "intakeArm");

        dumper = hardwareMap.get(Servo.class, "Dumper");
        pixelHolder = hardwareMap.get(Servo.class, "pixelHolder");
//        intakeSpin = hardwareMap.get(DcMotor.class, "intakeSpin");

//        camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        //.Parameters refers to a nested class within BNO055IMU
        //.Parameters class has more methods and uses specific to our use of imu
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        //sets parameter obj to another nested class called AngleUnit to increase capabilities
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);



        for(Subsystem subsystem : subsystems) {
            //initialize the subsystems
            subsystem.init(false); //change this
        }

        cycleTimer.reset();
    }

    //checks if a robot object is created
    //it is unnecessarily and can get complicated if there multiple robot objects
    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }
    //if you see Robot.getInstance() the code will check if there
    //is a robot instantiated anywhere. if there is, it will use that object
    // if not, it will create a new robot obj

    //Add an action to the list of things the robot is currently doing.
    //what is action and why do we need this?
    //__
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