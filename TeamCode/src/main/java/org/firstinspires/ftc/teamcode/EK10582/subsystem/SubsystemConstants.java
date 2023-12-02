package org.firstinspires.ftc.teamcode.EK10582.subsystem;

public class SubsystemConstants {
    //AprilTags
    public static int targetAprilTag = 1;
    public static boolean aprilTagsEnabled = true;
    public static int decimation = 3;

    //Housing
    public static final double[] housingpos = {.4, .5, .6, .5, .6};
    public static double housingIncrement = 0.001;
    public static double pixelHolderMiddle = 0.55;
    public static double pixelHolderLeft = 0.52;
    public static double pixelHolderRight = 0.58;
    public static double dumperTop = 1;
    public static double dumperBottom = 0.39;

    //Intake
    public static double intakeIncrement = 0.0015;
    public static final double[] servoPos = {0.21, 0.7};

    //MecanumDrive
    public static double SPEED = 0.8;
    public static boolean gyroOn = false;
    public static double targetAngle = 0;

    //Slides
    public static double SLIDES_TICKS_TO_INCHES = 0.010722;
    public static double slidesTolerance = 0.3;
    public enum SlideStates {
        FREE(0), BOTTOM(0), LOW(2.47), COLLECT(1.265);

        public final double position;

        SlideStates(double position) {
            this.position = position;
        }
    }


}
