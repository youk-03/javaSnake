package com.example.demo;

import implementation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import lib.SlitherScene;
import lib.Snake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*
        ControllableSnake head = new ControllableSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MyRandomSnake ia = new MyRandomSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        PaneScreen canvas = new PaneScreen();
        head.display(canvas);
        ia.display(canvas);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        //Scene scene = new Scene(canvas, Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-20); //padding dans fxml
        MySlitherScene scene = new MySlitherScene(canvas);
        List<Snake> snakes= new ArrayList<>();
        snakes.add(head);
        snakes.add(ia);
        scene.init(snakes);



        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    */}
}