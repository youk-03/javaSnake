package com.example.demo;

import implementation.MyPosition;
import implementation.MySlitherScene;
import implementation.MySnake;
import implementation.PaneScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lib.SlitherScene;

import java.io.IOException;

public class Main2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        MySnake head = new MySnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2), true);
        PaneScreen canvas = new PaneScreen();
        head.display(canvas);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        //Scene scene = new Scene(canvas, Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-20); //padding dans fxml
        MySlitherScene scene = new MySlitherScene(canvas);
        scene.init(head);


      Button addtail = new Button("addSegment");
        addtail.relocate(SlitherScene.windowWidth/2,20);
        addtail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                head.add();
                head.last().display(canvas);
            }
        });
        addtail.setFocusTraversable(false);
       canvas.add(addtail);



        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}



