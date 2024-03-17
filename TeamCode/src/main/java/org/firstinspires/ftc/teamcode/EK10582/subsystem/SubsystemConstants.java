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
    public static int[] timingsForDump = {1000, 1600, 2200, 2700, 3500};
    public static int[] timingsForHangingSetup = {500, 1000};
    public static int[] timingsForHighDump = {100, 500, 800};


    //Intake
    public static double intakeIncrement = 0.0015;
    public static final double[] servoPos = {0.21, 0.7};

    //MecanumDrive
    public static double SPEED = 0.8;
    public static boolean gyroOn = false;
    public static double targetAngle = 0;

    //Slides
    public static double SLIDES_TICKS_TO_INCHES = 0.010722;
    public static double MAX_SLIDE_HEIGHT = 1632;
    public static double MAX_FEEDFORWARD = 0.2;
    public static double slidesTolerance = 50;
    public enum SlideStates {
        FREE(0), BOTTOM(0), LOW(1115), MEDIUM(1300), ADJUSTABLE(-5);

        public final double position;

        SlideStates(double position) {
            this.position = position;
        }
    }


    public enum DumperStates {
        HIGH(0.90), LOW(0.212), PRESET(0), ABOVERAMP(0), MIDDUMP(0.6);

        public final double position;

        DumperStates(double position) {
            this.position = position;
        }
    }

    public enum PixelHolderStates {
        MIDDLE(0.7769), DOWN(0.73), UP(0.84);

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
        UP(0.85), DOWN(0.53);

        public final double position;

        HangingStates(double position) {
            this.position = position;
        }
    }
}
