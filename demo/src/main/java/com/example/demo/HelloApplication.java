package com.example.demo;

import implementation.Direction;
import implementation.MyPosition;
import implementation.MySnake;
import implementation.PaneScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import lib.Snake;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
         /* Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: black;");
        //canvas.setPrefSize(200,200);
        Circle circle = new Circle(50, Color.BLUE);
        circle.relocate(300, 20);
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,400);
        canvas.getChildren().addAll(circle,rectangle);*/
        MySnake head = new MySnake(new MyPosition(600,300),true,null);
        head.setLast(head);
        PaneScreen canvas = new PaneScreen();
        head.display(canvas);






        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        Scene scene = new Scene(canvas, 1200, 600);
        /////////////////////////////////////////////////////////////////: scene.init
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (((MySnake) head).getCurrentDirection() != Direction.DOWN) {
                            ((MySnake) head).setCurrentDirection(Direction.UP);
                            head.move();
                        }
                        break;
                    case DOWN:   if (((MySnake) head).getCurrentDirection() != Direction.UP) {
                        ((MySnake) head).setCurrentDirection(Direction.DOWN);
                        head.move();
                    }
                        break;
                    case LEFT:  if (((MySnake) head).getCurrentDirection() != Direction.RIGHT) {
                        ((MySnake) head).setCurrentDirection(Direction.LEFT);
                        head.move();
                    }
                        break;
                    case RIGHT:  if (((MySnake) head).getCurrentDirection() != Direction.LEFT) {
                        ((MySnake) head).setCurrentDirection(Direction.RIGHT);
                        head.move();
                    }
                        break;
                }
            }
        });




        ////////////////////////////////////////////////////////////////////

        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}