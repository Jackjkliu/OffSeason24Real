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
//            if (driverStation.getSlideLow()) {
//                robot.slides.currentState = SubsystemConstants.SlideStates.LOW;
//            }
//            else if (driverStation.getSlidePreset()) {
//                robot.slides.currentState = SubsystemConstants.SlideStates.PRESET;
//            }
//            else if (driverStation.getSlideFree()) {
//                robot.slides.currentState = SubsystemConstants.SlideStates.FREE;
//            }

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
                    case UP:
                        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.DOWN;
                        break;
                    case DOWN:
                        robot.housing.pixelHolderState = SubsystemConstants.PixelHolderStates.UP;
                        break;
                }
            }

            //hanging
            robot.hanging.hangingPower = driverStation.getHangingPower() * 0.8;
            if(driverStation.getHangingServo()) {
                if(robot.hanging.currentState == SubsystemConstants.HangingStates.DOWN) {
                    robot.hanging.currentState = SubsystemConstants.HangingStates.UP;
                } else {
                    robot.hanging.currentState = SubsystemConstants.HangingStates.DOWN;
                }
            }

            //drone
            //telemetry.addData("droneButton", driverStation.getDroneDown());
            if(driverStation.getDroneDown()) {
                if(robot.droneLauncher.currentState == SubsystemConstants.DroneStates.CLOSED)
                    robot.droneLauncher.currentState = SubsystemConstants.DroneStates.RELEASE;
                else
                    robot.droneLauncher.currentState = SubsystemConstants.DroneStates.CLOSED;
            }

            robot.update();

        }
    }
}