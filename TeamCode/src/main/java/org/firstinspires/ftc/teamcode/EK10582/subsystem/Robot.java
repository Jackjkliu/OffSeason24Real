package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

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
    public SampleMecanumDrive roadRunner;

    //declare hardware here
    public DcMotorEx leftFront, leftBack, rightFront, rightBack, intakeSpin;

    public DcMotorEx slide1;
    public DcMotorEx slide2;

    public BHI260IMU imu;
    public Servo intakeArm;

    public Servo pixelHolder;
    public Servo dumper;

    public WebcamName camera;

    public OpenCvWebcam webcam;


    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    //public MecanumDrive mecanumDrive = new MecanumDrive();
    //public Intake intake = new Intake();
    public AprilTags aprilTags = new AprilTags();
    //public Slides slides = new Slides();
   // public Housing housing = new Housing();
    public OpenCV openCV = new OpenCV();

    //Add all subsystems to a list to be initiated and updated through
    //private List<Subsystem> subsystems = Arrays.asList(mecanumDrive, intake, aprilTags, slides, housing, openCV);
    private List<Subsystem> subsystems = Arrays.asList(openCV, aprilTags);

    //add all subsystems that need to go through telemetry
//    private List<Subsystem> telemetrySubsystems = Arrays.asList();
    private List<Subsystem> telemetrySubsystems = Arrays.asList(openCV, aprilTags);


    //Creates an arraylist called actions that stores all the actions that are currently being done
    private ArrayList<Action> actions = new ArrayList<Action>();

    private ElapsedTime cycleTimer = new ElapsedTime();

    //sets values to declared but not instantiated values
    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        //roadRunner = new SampleMecanumDrive(hardwareMap);

        //leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        /*leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");


        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        slide1 = hardwareMap.get(DcMotorEx.class, "slide1");
        slide2 = hardwareMap.get(DcMotorEx.class, "slide2");
        slide1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slide2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        slide1.setTargetPosition(0);
        slide2.setTargetPosition(0);
        slide2.setDirection(DcMotorSimple.Direction.REVERSE);

        slide1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

//
        intakeArm = hardwareMap.get(Servo.class, "intakeArm"); */
        camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        /*dumper = hardwareMap.get(Servo.class, "dumper");
        pixelHolder = hardwareMap.get(Servo.class, "pixelHolder");

        intakeSpin = hardwareMap.get(DcMotorEx.class, "intakeSpin");

        intakeSpin = hardwareMap.get(DcMotor.class, "intakeSpin");
        */


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        //imu = hardwareMap.get(BHI260IMU.class, "imu");

        //.Parameters refers to a nested class within BNO055IMU
        //.Parameters class has more methods and uses specific to our use of imu
        //BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));

        //sets parameter obj to another nested class called AngleUnit to increase capabilities
        //imu.initialize(parameters);
        //imu.resetYaw();



        for(Subsystem subsystem : subsystems) {
            //initialize the subsystems
            subsystem.init(false); //change this
        }

        cycleTimer.reset();
    }

    //checks if a robot object is created
    //it is unnecessary and can get complicated if there multiple robot objects
    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }
    //if you see Robot.getInstance() the code will check if there
    //is a robot instantiated anywhere. if there is, it will use that object
    // if not, it will create a new robot obj

    //Add an action to the list of things the robot is currently doing.

    public void addAction(Action action) {
        action.start();
        actions.add(action);
    }

    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update(false);
            if(linearOpMode.isStopRequested()){
                return;
            }
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

    public void removeOpenCV(){
        subsystems.remove(openCV);
        subsystems.add(aprilTags);
    }
}