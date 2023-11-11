package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class cameraPipeline extends OpenCvPipeline {

    public enum SpikePositions{
        LEFT, RIGHT, MIDDLE;
    }
    public static SpikePositions spikePosition;

    double[] targetBlueRGB = {12, 135, 176};
    double[] targetRedRGB = {200, 20, 20};
    double[] replacementColor = {0, 255, 0, 1};

    double percentError = 0.6;
    public static int max = 0;

    @Override
    public Mat processFrame(Mat input) {

        Size dimensions = input.size();
        double height = dimensions.height;
        double width = dimensions.width;



        Mat output = input.clone();


        Rect line1 = new Rect(new Point(width / 3, 0),new Point(2 * width / 3, height));
        Imgproc.rectangle(output, line1, new Scalar(4,233,78),3,8);


        int[] counters = new int[3];

        for (int i = 0; i < 3; i++) { //boxes
            for(int j = 0; j < height; j++){ //height (all rows)
                for(int k = (int) (width * i / 3); k < (width * (i + 1) / 3); k++){ //width (column)
                    double[] currentColor = output.get(j,k); //color of each pixel
                    if(compareColor(targetBlueRGB, currentColor)){
                        output.put(j,k, replacementColor); //if color is target color, change color
                        counters[i]++;
                    }
                }
            }
        }

        max = 0;
        for (int i = 1; i < 3; i++) {
            if (counters[i] > counters[max])
                max = i;
        }

        switch(max){
            case 0: spikePosition = SpikePositions.LEFT;
            case 1: spikePosition = SpikePositions.MIDDLE;
            case 2: spikePosition = SpikePositions.RIGHT;
        }

        return output;

    }

    public boolean compareColor(double[] targ, double[] cur){
        if (Math.abs(targ[0] - cur[0]) < percentError * targ[0] && Math.abs(targ[1] - cur[1]) < percentError*targ[1] && Math.abs(targ[2] - cur[2]) < percentError*targ[2]){
            return true;
        }
        return false;
    }

}
