package implementation;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lib.Position;
import lib.SlitherScene;
import lib.Snake;

import java.util.List;

public class MySlitherScene extends SlitherScene {
    private Position<Double> mousePos;
    public MySlitherScene(PaneScreen pane) {
        super(pane);
    }

    @Override
    public void init(List<Snake> snakeList) {
        Snake playable= snakeList.get(0);
        mousePos= new MyPosition(0,0);
        if(!(playable instanceof ControllableSnake)){
            throw new IllegalArgumentException();
        }
        MySlitherScene scene= this;

        if(playable instanceof ArrowSnake){
            this.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {

                    switch (event.getCode()) {
                        case UP: if(((ArrowSnake) playable).lastInput() == Direction.DOWN) {
                            return;
                        }

                            ((ArrowSnake) playable).setLastInput(Direction.UP);
                            break;
                        case DOWN: if(((ArrowSnake) playable).lastInput() == Direction.UP) {
                            return;
                        }

                            ((ArrowSnake) playable).setLastInput(Direction.DOWN);
                            break;
                        case LEFT: if(((ArrowSnake) playable).lastInput() == Direction.RIGHT) {
                           return;
                        }

                            ((ArrowSnake) playable).setLastInput(Direction.LEFT);
                            break;
                        case RIGHT: if(((ArrowSnake) playable).lastInput() == Direction.LEFT){
                            return;
                        }

                            ((ArrowSnake) playable).setLastInput(Direction.RIGHT);
                            break;
                    }
                    for (Snake s:snakeList){
                        s.choseDirection(null);
                        s.move();
                    }
                }
            });
        }

        else if (playable instanceof MouseSnake){

            this.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                   ((MouseSnake) playable).setLastInput(new MyPosition(event.getX(), event.getY()));
                    for (Snake s:snakeList){
                        s.choseDirection(null);
                        s.move();
                    }
                }
            });
        }
    }

    public Position<Double> getMousePos() {
        return new MyPosition(mousePos.getX(),mousePos.getY());
    }
}
