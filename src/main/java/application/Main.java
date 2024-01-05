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

import static java.lang.Thread.sleep;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PaneScreen canvas = new PaneScreen();
        MySlitherScene scene = new MySlitherScene(canvas);
        GameController controller = new GameController(canvas,scene);
        controller.initialize(null,null); //C'EST PAS DU TOUT COMME CA QU'ON FAIT NORMALEMENT FAUT UTILISER LE FXML


        stage.setTitle("Slither");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}