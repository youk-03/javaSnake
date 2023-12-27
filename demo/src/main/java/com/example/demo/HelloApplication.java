package com.example.demo;

import implementation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import lib.Snake;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        MySnake head = new MySnake(new MyPosition(600,300),true,null);
        head.setLast(head);
        PaneScreen canvas = new PaneScreen();
        head.display(canvas);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        //Scene scene = new Scene(canvas, Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-20); //padding dans fxml
        MySlitherScene scene = new MySlitherScene(canvas);
        scene.init(head);

        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}