//package org.firstinspires.ftc.teamcode.EK10582.subsystem;
//
//import com.qualcomm.hardware.bosch.BHI260IMU;
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.IMU;
//import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//
//public class RobotPrac{
//
//    static RobotPrac robot = null;
//
//    HardwareMap hardwareMap;
//
//    EKLinear linearOpMode;
//
//    public DcMotor leftFront,leftBack,rightFront,rightBack;
//
//    public DcMotor slide1,slide2; //Use encoders
//
//    public Servo intake, pixelDumper, holder;
//
//    public BHI260IMU imu;
//
//    public WebcamName webcam;
//
//    public OpenCvCamera cvCam;
//
//    public void init(HardwareMap hardwareMap, EKLinear linearOpMode){
//        this.hardwareMap = hardwareMap;
//        this.linearOpMode = (EKLinear)linearOpMode;
//
//        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
//        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
//        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
//        rightBack = hardwareMap.get(DcMotor.class,"rightBack");
//
//        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        slide1 = hardwareMap.get(DcMotor.class, "slide1");
//        slide2 = hardwareMap.get(DcMotor.class, "slide2");
//
//        slide1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        slide2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        slide2.setTargetPosition(0);
//        slide1.setTargetPosition(0);
//
//        slide1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        slide2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        slide2.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        slide2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        slide1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        intake = hardwareMap.get(Servo.class, "intake");
//        pixelDumper = hardwareMap.get(Servo.class, "pixelDumper");
//        holder = hardwareMap.get(Servo.class, "holder");
//
//        webcam = hardwareMap.get(WebcamName.class, "webcam");
//        int cameraMoniterID = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        cvCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class,""),cameraMoniterID);
//
//
//
//
//        imu = hardwareMap.get(BHI260IMU.class, "imu");
//
//        //.Parameters refers to a nested class within BNO055IMU
//        //.Parameters class has more methods and uses specific to our use of imu
//        BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));
//
//
//        imu.initialize(parameters);
//        imu.resetYaw();
//
//    }
//}
//
//    //create motor for 4 wheels
//    //2 motor for slides
//    //servo for intake, holder, dumper
//    //imu
//    //webcam and opencvwebcam
//
//
//    //declares a custom EK LinearOpMode named linearOpMode
