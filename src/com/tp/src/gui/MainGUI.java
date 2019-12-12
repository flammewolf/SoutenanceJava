package com.tp.src.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        Application.launch(MainGUI.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        ClientPanel clientPanel = new ClientPanel();
        Group root = new Group();

        root.getChildren().add(clientPanel);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Chat de ouf");
        stage.setScene(scene);
        stage.show();

    }

}
