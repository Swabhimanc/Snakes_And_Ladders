package com.example.snakeandladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Group root;

    @Override
    public void start(Stage stage) throws IOException
    {
        root=new Group();
        GamePage page=new GamePage();
        root.getChildren().add(page.root);
        stage.setTitle("Snake And Ladder");
        stage.setScene(new Scene(root, 1000, 650));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}