package com.necitdev.cmucnuclab7;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

import java.util.function.UnaryOperator;

public class MyView  {
    private final VBox root;
    private final Label statusLabel;
    private final Label timerLabel;
    private final TextField timerTextField;
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
        timerTextField = new TextField("5");
        timerTextField.setMaxWidth(50);
//        root = new VBox(statusLabel, timerLabel, startBtn, stopBtn, powerOnBtn);
        // Пока без stopBtn
        root = new VBox(10,statusLabel, timerLabel, timerTextField, startBtn, stopBtn, powerOnBtn);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(16));
        // Начальное состояние
        startBtn.setDisable(true);
        stopBtn.setDisable(true);
        timerTextField.setDisable(true);
        // Фильтрация для инпута
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                if (text.equals("0")) return null;
                return change;
            }
            return null;
        };

        TextFormatter<String> formatter = new TextFormatter<>(filter);
        timerTextField.setTextFormatter(formatter);
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

    public TextField getTimerTextField() {
        return timerTextField;
    }

    public void render(MyState state, int seconds) {
        timerLabel.setText(seconds + " сек");
        startBtn.setDisable(false);
        stopBtn.setDisable(false);
        timerTextField.setDisable(true);
        switch (state){
            case IDLE -> {
                statusLabel.setText(statusText + "Микроволновка выключена");
                timerLabel.setText(noTime);
                startBtn.setDisable(true);
                stopBtn.setDisable(true);
            }
            case READY -> {
                statusLabel.setText(statusText + "Микроволновка готова к работе");
                stopBtn.setDisable(true);
                timerTextField.setDisable(false);
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
                timerTextField.setDisable(false);
            }
            case ERROR -> {
                statusLabel.setText(statusText + "ERROR");
            }
        }
    }
}
