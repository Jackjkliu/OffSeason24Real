package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class cameraPipeline extends OpenCvPipeline {

    public enum SpikePositions{
        LEFT, RIGHT, MIDDLE;
    }
    public static SpikePositions spikePosition;


    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}
