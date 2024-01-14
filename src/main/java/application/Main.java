package application;

import implementation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //PaneScreen canvas = new PaneScreen();
        //MySlitherScene scene = new MySlitherScene(canvas);
        //GameControllerArrow controller = new GameControllerArrow(canvas,scene,true,true,false);
        //controller.initialize(null,null);
        MainScene mainScene = new MainScene(new PaneScreen());
        SceneController sceneController = new SceneController(stage);
        mainScene.init();

        stage.setTitle("Slither");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}