package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
//subsystem child class
public class AprilTags extends Subsystem {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    public int targetAprilTag = SubsystemConstants.targetAprilTag;
    //TODO: make this changable by buttons or sm so you can change what april tag you're looking for

    public boolean seeTag = false;
    public double tagX;
    public double tagDistance;

    private boolean runTelemetry;

    // Decimation: 1 means low rate high range, 3 means low range high rate
    // Note: Decimation can be changed on-the-fly to adapt during a match.
    private int decimation;
    public boolean aprilTagsEnabled = true;

    //makes a list called currentDetections
    private List<AprilTagDetection> currentDetections;


    //boolean auton will be set to true or false depending if in auton
    //if in auton, then certain things will happen due to certain if statements specific to auton
    @Override
    public void init(boolean auton) {
        if(!auton){
            return;
            //upon initialization, don't run init to save viewport for opencv
        }

        decimation = SubsystemConstants.decimation;
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                .build();


        aprilTag.setDecimation(decimation);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera
        builder.setCamera(Robot.getInstance().camera);

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        builder.enableLiveView(true);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Disable or re-enable the aprilTag processor at any time.
        visionPortal.setProcessorEnabled(aprilTag, aprilTagsEnabled);

    }   // end method initAprilTag()


    @Override
    public void update(boolean auton){

        if(!auton){
            runTelemetry = false;
            return;
        }
        runTelemetry = true;
        currentDetections = aprilTag.getDetections();
        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if(detection.id == targetAprilTag){
                seeTag = true;
                tagX= (double)Math.round(detection.ftcPose.x * 100)/100;
                tagDistance = ((double)Math.round(detection.ftcPose.y * 100)/100 ) / 1.102 - 0.4223;
            }
            //amount of detections in detections array
            if(currentDetections.size() == 0){
                seeTag = false;
            }
        }
        if(!seeTag){
            tagX = -1;
            tagDistance = -1;
            //if you don't see the stack return a negative number
        }
    }
    @Override
    public void stop() {
//        if(!aprilTagsEnabled){
//            return;
//        }
//        // Save more CPU resources when camera is no longer needed.
//        visionPortal.close();
    }


    @Override
    public void printToTelemetry(Telemetry telemetry) {
        if(!runTelemetry){
            return;
        }
        telemetry.addData("# AprilTags Detected", currentDetections.size());
        telemetry.addData("Apriltag " + targetAprilTag + "'s x value:", tagX);
        telemetry.addData("Apriltag " + targetAprilTag + "'s distance: ", tagDistance);
    }


    public void relocalize() {
        //Robot.getInstance().roadRunner.setPoseEstimate()
    }
}
