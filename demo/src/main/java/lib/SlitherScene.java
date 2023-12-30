package lib;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.util.List;

public abstract class SlitherScene extends Scene {
    public static final double windowWidth =  javafx.stage.Screen.getPrimary().getBounds().getWidth();
    public static final double windowHeight =  Screen.getPrimary().getBounds().getHeight()-20;
    public SlitherScene(Parent parent) {
        super(parent, windowWidth, windowHeight);
    }

    /** to add the listener and every necessary things to the scene */
    protected abstract void init(List<Snake> snakes);
}
