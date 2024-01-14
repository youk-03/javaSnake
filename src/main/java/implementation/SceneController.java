package implementation;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;


    public SceneController(Stage stage){
        this.stage = stage;

    }

    public static void switchScene(Scene scene){
        stage.setScene(scene);
        stage.show();
    }
}
