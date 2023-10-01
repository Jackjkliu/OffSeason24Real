package org.firstinspires.ftc.teamcode.EK10582;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.teleop.DriverStation;

import java.util.ArrayList;

public abstract class EKLinear extends LinearOpMode {

    public Robot robot = Robot.getInstance();
    public FtcDashboard dashboard;
    public Telemetry allTelemetry;
    public ElapsedTime matchTimer;
    public DriverStation driverStation;

    public abstract void run();

    @Override
    public void runOpMode() throws InterruptedException {
        //initing things
        dashboard = FtcDashboard.getInstance();
        allTelemetry = new MultipleTelemetry(dashboard.getTelemetry(), this.telemetry);
        matchTimer = new ElapsedTime();
        driverStation = new DriverStation(gamepad1, gamepad2);
        robot.init(hardwareMap, this);

        waitForStart();

        //after start
        matchTimer.reset();
        run();
    }
}
