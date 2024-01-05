package application;

import implementation.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import lib.SlitherScene;
import lib.Snake;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PaneScreen canvas = new PaneScreen();
        MySlitherScene scene = new MySlitherScene(canvas);
        //ArrowSnake head = new ArrowSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MouseSnake head = new MouseSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MyRandomSnake ia = new MyRandomSnake(new MyPosition(0, 0));
        ArrayList<Snake> snakes= new ArrayList<>();
        snakes.add(head);
        //snakes.add(ia);
        for(Snake s:snakes){
            s.display(canvas);
        }

        MyFruit.init(canvas,snakes);

        //Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        //Scene scene = new Scene(canvas, Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()-20); //padding dans fxml
        scene.init(snakes, MyFruit.getListFruit());


        Button addtail = new Button("Add Segment");
        addtail.relocate(SlitherScene.windowWidth/2,20);
        addtail.setOnAction(actionEvent -> {
            head.add();
            head.last().display(canvas);
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