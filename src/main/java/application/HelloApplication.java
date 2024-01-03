package application;

import implementation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import lib.SlitherScene;
import lib.Snake;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PaneScreen canvas = new PaneScreen();
        MySlitherScene scene = new MySlitherScene(canvas);
        //ArrowSnake head = new ArrowSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MouseSnake head = new MouseSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MyRandomSnake ia = new MyRandomSnake(new MyPosition(0, 0));
        List<Snake> snakes= new ArrayList<>();
        snakes.add(head);
        snakes.add(ia);
        for(Snake s:snakes){
            s.display(canvas);
        }

        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        //Scene scene = new Scene(canvas, Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-20); //padding dans fxml
        scene.init(snakes);


        Button addtail = new Button("Add Segment");
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



        stage.setTitle("Slither");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}