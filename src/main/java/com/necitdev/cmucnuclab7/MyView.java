package com.necitdev.cmucnuclab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MyView  {
    private final VBox root;
    private final Label statusLabel;
    private final Label timerLabel;
    private final Button powerOnBtn;
    private final Button startBtn;
    private final Button stopBtn;

    private final String noTime = "-- сек";
    private final String statusText = "Статус: ";
    public MyView() {
        statusLabel = new Label(statusText + "Микроволновка выключена");
        timerLabel = new Label(noTime);
        startBtn = new Button("Запуск");
        powerOnBtn = new Button("Вкл/Выкл");
        stopBtn = new Button("Пауза/Стоп");

//        root = new VBox(statusLabel, timerLabel, startBtn, stopBtn, powerOnBtn);
        // Пока без stopBtn
        root = new VBox(10,statusLabel, timerLabel, startBtn, powerOnBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));
        // Начальное состояние
        startBtn.setDisable(true);

    }

    public Parent getRoot() {
        return root;
    }

    public Button getPowerOnBtn() {
        return powerOnBtn;
    }

    public Button getStartBtn() {
        return startBtn;
    }

    public Button getStopBtn() {
        return stopBtn;
    }

    public void render(MyState state, int seconds) {
        timerLabel.setText(seconds + " сек");
        startBtn.setDisable(false);
        switch (state){
            case IDLE -> {
                statusLabel.setText(statusText + "Микроволновка выключена");
                timerLabel.setText(noTime);
                startBtn.setDisable(true);
            }
            case READY -> {
                statusLabel.setText(statusText + "Микроволновка готова к работе");
            }
            case HEATING -> {
                statusLabel.setText(statusText + "Микроволновка греет");
                startBtn.setDisable(true);
            }
            case PAUSED -> {
                statusLabel.setText(statusText + "Микроволновка на паузе");
            }
            case FINISHED -> {
                statusLabel.setText(statusText + "Микроволновка приготовила");
            }
            case ERROR -> {
                statusLabel.setText(statusText + "ERROR");
            }
        }
    }
}
