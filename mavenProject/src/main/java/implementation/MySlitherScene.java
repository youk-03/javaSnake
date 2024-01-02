package implementation;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import lib.SlitherScene;
import lib.Snake;

import java.util.List;

public class MySlitherScene extends SlitherScene {
    public MySlitherScene(PaneScreen pane) {
        super(pane);
    }

    @Override
    public void init(List<Snake> snakeList) {
        Snake playable= snakeList.get(0);
        if(!(playable instanceof ControllableSnake)){
            throw new IllegalArgumentException();
        }
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        ((ControllableSnake) playable).setLastInput(Direction.UP);
                        break;
                    case DOWN:
                        ((ControllableSnake) playable).setLastInput(Direction.DOWN);
                        break;
                    case LEFT:
                        ((ControllableSnake) playable).setLastInput(Direction.LEFT);
                        break;
                    case RIGHT:
                        ((ControllableSnake) playable).setLastInput(Direction.RIGHT);
                        break;
                }
                for(Snake s:snakeList){
                    s.choseDirection(null);
                    s.move();
                }
            }
        });

    }
}
