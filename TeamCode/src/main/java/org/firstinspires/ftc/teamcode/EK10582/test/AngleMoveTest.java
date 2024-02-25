package org.firstinspires.ftc.teamcode.EK10582.test;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Housing.Dump;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@Autonomous(name="Dump Test")
@Disabled
public class AngleMoveTest extends AutonBase {
    @Override
    public void runOpMode() {
        waitForStart();
        runAction(new Dump());
        sleep(5000);
    }
}
