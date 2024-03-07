package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.acmerobotics.dashboard.config.Config;

@Config
public class SubsystemConstants {
    //AprilTags
    public static int targetAprilTag = 1;
    public static boolean aprilTagsEnabled = true;
    public static int decimation = 3;

    //Housing
    public static final double[] housingpos = {.4, .5, .6, .5, .6};
    public static double housingIncrement = 0.001;
    public static int[] timingsForDump = {1000, 2400, 3400};
    public static int[] timingsForHighDump = {1000, 1200, 1400};


    //Intake
    public static double intakeIncrement = 0.0015;
    public static final double[] servoPos = {0.21, 0.7};

    //MecanumDrive
    public static double SPEED = 0.8;
    public static boolean gyroOn = false;
    public static double targetAngle = 0;

    //Slides
    public static double SLIDES_TICKS_TO_INCHES = 0.010722;
    public static double MAX_SLIDE_SPEED = 10000;
    public static double slidesTolerance = 50;
    public enum SlideStates {
        FREE(0), LOW(1400), PRESET(1.265);

        public final double position;

        SlideStates(double position) {
            this.position = position;
        }
    }

    public enum DumperStates {
        HIGH(0.90), LOW(0.212), PRESET(0.02), ABOVERAMP(0), MIDDUMP(0.6);

        public final double position;

        DumperStates(double position) {
            this.position = position;
        }
    }

    public enum PixelHolderStates {
        MIDDLE(0.7769), DOWN(0.73), UP(0.81);

        public final double position;

        PixelHolderStates(double position) {
            this.position = position;
        }
    }

    public enum DroneStates {
        CLOSED(0.4), RELEASE(0.7);

        public final double position;

        DroneStates(double position) {
            this.position = position;
        }
    }

    public enum HangingStates {
        UP(0.713), DOWN(0.362);

        public final double position;

        HangingStates(double position) {
            this.position = position;
        }
    }
}
