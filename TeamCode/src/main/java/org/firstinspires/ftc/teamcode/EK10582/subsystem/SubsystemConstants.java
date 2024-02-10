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
    public static int[] timingsForDump = {100, 200};

//    public static double dumperTop = 1;
//    public static double dumperBottom = 0.39;

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
        FREE(0), LOW(2.47), PRESET(1.265);

        public final double position;

        SlideStates(double position) {
            this.position = position;
        }
    }

    public enum DumperStates {
        HIGH(1), LOW(0.22), PRESET(0.14);

        public final double position;

        DumperStates(double position) {
            this.position = position;
        }
    }

    public enum PixelHolderStates {
        MIDDLE(0.55), LEFT(0.48), RIGHT(0.58);

        public final double position;

        PixelHolderStates(double position) {
            this.position = position;
        }
    }

    public enum DroneStates {
        CLOSED(0.5), RELEASE(1);

        public final double position;

        DroneStates(double position) {
            this.position = position;
        }
    }

    public enum HangingStates {
        UP(0.5), DOWN(1);

        public final double position;

        HangingStates(double position) {
            this.position = position;
        }
    }
}
