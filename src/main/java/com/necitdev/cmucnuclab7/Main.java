package com.necitdev.cmucnuclab7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        MyModel myModel = new MyModel();
        MyView myView = new MyView();

        new MyController(myModel, myView);

        Scene scene = new Scene(myView.getRoot(), 400, 300);
        stage.setTitle("Мое приложение");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
