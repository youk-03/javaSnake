package implementation;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class PaneScreen extends AnchorPane implements lib.Screen {

    public static final double windowWidth =  Screen.getPrimary().getBounds().getWidth();
    public static final double windowHeight =  Screen.getPrimary().getBounds().getHeight()-20;
    @Override
    public void add(Node obj) {

        this.getChildren().add(obj);

    }
}
