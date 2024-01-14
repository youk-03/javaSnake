package application;

import implementation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage){
        PaneScreen canvas = new PaneScreen();
        MySlitherScene scene = new MySlitherScene(canvas);
        GameControllerArrow controller = new GameControllerArrow(canvas,scene,true,true,false);
        controller.initialize(null,null);


        stage.setTitle("Slither");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}