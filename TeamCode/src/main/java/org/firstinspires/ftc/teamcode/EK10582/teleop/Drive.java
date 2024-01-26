package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="New Drive")
public class Drive extends EKLinear {

    @Override
    public void runOpMode(){
        waitForStart();


        while(opModeIsActive()) {

            //drive
            robot.mecanumDrive.lx = driverStation.getXVel();
            robot.mecanumDrive.ly = driverStation.getYVel();
            robot.mecanumDrive.rx = driverStation.getRotVel();
            robot.mecanumDrive.slowMode = driverStation.slowMode();

            //intake
            robot.intake.intakeSpeed = driverStation.intakeOut(); //left trigger
            robot.intake.intakeBack = driverStation.intakeIn(); //right trigger

            //slides
            robot.slides.joystickInput = driverStation.getSlidePower();
            if (driverStation.getSlideLow()) {
                robot.slides.currentState = SubsystemConstants.SlideStates.LOW;
            }
            else if (driverStation.getSlidePreset()) {
                robot.slides.currentState = SubsystemConstants.SlideStates.PRESET;
            }
            else if (driverStation.getSlideFree()) {
                robot.slides.currentState = SubsystemConstants.SlideStates.FREE;
            }

            //housing
            if (driverStation.raiseDumperOver()) {
                robot.housing.dumperState = SubsystemConstants.DumperStates.HIGH;
            }
            else if (driverStation.lowerDumperUnder()) {
                robot.housing.dumperState = SubsystemConstants.DumperStates.LOW;
            }
            else if (driverStation.resetDumper()) {
                robot.housing.dumperState = SubsystemConstants.DumperStates.PRESET;
            }

            if (driverStation.movePixelHolder()) {
                switch(robot.housing.pixelHolderState) {
                    case LEFT:
                        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.MIDDLE;
                    case RIGHT:
                        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.LEFT;
                    case MIDDLE:
                        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.RIGHT;
                }
            }

            robot.update();

        }
    }
}