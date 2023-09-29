package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Auton", group="auto")
public class auton extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        robot bot = new robot(hardwareMap, this);


        waitForStart();
    }
}
