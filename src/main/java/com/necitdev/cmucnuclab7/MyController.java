package com.necitdev.cmucnuclab7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MyController {
    private MyModel myModel;
    private MyView view;
    private Timeline timeline;

    public MyController(MyModel model, MyView view) {
        this.myModel = model;
        this.view = view;

        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tick();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        view.getPowerOnBtn().setOnAction(e -> powerOnOf());
        view.getStartBtn().setOnAction(e -> startProcess());
    }

    private void tick() {
        int current = myModel.getSecondsLeft();
        if (current > 0) {
            myModel.setSecondsLeft(--current);
        }else {
            timeline.stop();
            myModel.handleEvent(MyEvent.TIMES_UP);
        }
        updateUI();
    }

    private void startProcess() {

        myModel.handleEvent(MyEvent.START_CLICKED);
        if (myModel.getState() == MyState.HEATING) {
            myModel.setSecondsLeft(5);
            timeline.play();
        }
        updateUI();
    }

    private void powerOnOf() {
        if (myModel.getState() == MyState.IDLE){
            myModel.handleEvent(MyEvent.POWER_ON_CLICKED);
        }else {
            timeline.stop();
            myModel.handleEvent(MyEvent.POWER_OFF_CLICKED);
        }
        updateUI();
    }

    private void updateUI() {
        view.render(myModel.getState(), myModel.getSecondsLeft());
    }
}
