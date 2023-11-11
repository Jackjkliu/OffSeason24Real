package org.firstinspires.ftc.teamcode.EK10582.test;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.EK10582.auton.AutonBase;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.MecanumDrive.AngleMove;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@Autonomous(name="Angle Move Test")
public class AngleMoveTest extends AutonBase {
    @Override
    public void runOpMode() {
        waitForStart();
        runAction(new AngleMove(Math.PI/2, 24, 0.6));
        double leftDiagonal = (Robot.getInstance().leftFront.getCurrentPosition() + Robot.getInstance().rightBack.getCurrentPosition())/2.0;
        double rightDiagonal = (Robot.getInstance().leftBack.getCurrentPosition() + Robot.getInstance().rightFront.getCurrentPosition())/2.0;
        telemetry.addData("movement measure", (Math.sqrt(Math.pow(leftDiagonal, 2) + Math.pow(rightDiagonal, 2))));
        telemetry.update();
        sleep(5000);
    }
}
