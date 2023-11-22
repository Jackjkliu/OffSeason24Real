package org.firstinspires.ftc.teamcode.EK10582.subsystem;

/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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


    // Decimation: 1 means low rate high range, 3 means low range high rate
    // Note: Decimation can be changed on-the-fly to adapt during a match.
    private int decimation;
    public boolean aprilTagsEnabled = false;
    //TODO: set this to true when opencv closes

    //makes a list called currentDetections
    private List<AprilTagDetection> currentDetections;


    //boolean auton will be set to true or false depending if in auton
    //if in auton, then certain things will happen due to certain if statements specific to auton
    @Override
    public void init(boolean auton) {
        if(auton){
            return;
        }
        decimation = SubsystemConstants.decimation;
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                .setDrawAxes(false)
                .setDrawCubeProjection(false)
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
        if (auton) {
            return;
        }

        currentDetections = aprilTag.getDetections();
        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if(detection.id == targetAprilTag){
                seeTag = true;
                tagX= (double)Math.round(detection.ftcPose.x * 100)/100;
                tagDistance = (double)Math.round(detection.ftcPose.y * 100)/100;
            }
            else{
                seeTag = false;
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
        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();
    }


    @Override
    public void printToTelemetry(Telemetry telemetry) {

        telemetry.addData("# AprilTags Detected", currentDetections.size());
        telemetry.addData("Apriltag " + targetAprilTag + "'s x value:", tagX);
        telemetry.addData("Apriltag " + targetAprilTag + "'s distance: ", tagDistance);
    }


}
