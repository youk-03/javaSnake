package implementation;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

public class PaneScreen extends AnchorPane implements lib.Screen {

    @Override
    public void add(Node obj) {

        this.getChildren().add(obj);

    }
}
