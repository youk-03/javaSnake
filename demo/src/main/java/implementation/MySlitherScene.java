package implementation;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import lib.SlitherScene;
import lib.Snake;

public class MySlitherScene extends SlitherScene {
    public MySlitherScene(Parent parent) {
        super(parent);
    }

    @Override
    public void init(Snake head) {
        if(!(head instanceof MySnake)){
            throw new IllegalArgumentException();
        }
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (((MySnake) head).getCurrentDirection() != Direction.DOWN) {
                            ((MySnake) head).setCurrentDirection(Direction.UP);
                            head.move();
                        }
                        break;
                    case DOWN:   if (((MySnake) head).getCurrentDirection() != Direction.UP) {
                        ((MySnake) head).setCurrentDirection(Direction.DOWN);
                        head.move();
                    }
                        break;
                    case LEFT:  if (((MySnake) head).getCurrentDirection() != Direction.RIGHT) {
                        ((MySnake) head).setCurrentDirection(Direction.LEFT);
                        head.move();
                    }
                        break;
                    case RIGHT:  if (((MySnake) head).getCurrentDirection() != Direction.LEFT) {
                        ((MySnake) head).setCurrentDirection(Direction.RIGHT);
                        head.move();
                    }
                        break;
                }
            }
        });

    }
}
