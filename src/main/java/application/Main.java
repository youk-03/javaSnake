package application;

import implementation.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

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