package implementation;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class PaneScreen extends AnchorPane implements lib.Screen {

    @Override
    public void add(Node obj) {

        this.getChildren().add(obj);

    }
}
