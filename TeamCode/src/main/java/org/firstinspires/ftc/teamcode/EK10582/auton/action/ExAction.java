package org.firstinspires.ftc.teamcode.EK10582.auton.action;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@Config
public class ExAction extends Action {

//    ElapsedTime timer = new ElapsedTime();
//    private int[] timingsFromDrop = {250, 500};
//    private int[] timingsFromScan = {170, 500};
//    private Collection.CollectionState original = Collection.CollectionState.DROP;
    @Override
    public void start() {
//        timer.reset();
//        original = Robot.getInstance().collection.currState;
    }

    @Override
    public void update() {
//        if(original == Collection.CollectionState.DROP) {
//            if (timer.milliseconds() < timingsFromDrop[0]) {
//                Robot.getInstance().collection.currState = Collection.CollectionState.COLLECT;
//            } else if (timer.milliseconds() < timingsFromDrop[1]) {
//                Robot.getInstance().collection.currState = Collection.CollectionState.HOLD;
//            } else {
//                isComplete = true;
//            }
//        }
//        else if(original == Collection.CollectionState.SCANNING) {
//            if (timer.milliseconds() < timingsFromScan[0]) {
//                Robot.getInstance().collection.currState = Collection.CollectionState.COLLECT;
//            } else if (timer.milliseconds() < timingsFromScan[1]) {
//                Robot.getInstance().collection.currState = Collection.CollectionState.HOLD;
//            } else {
//                isComplete = true;
//            }
//        }
    }

    @Override
    public void end() {

    }
}