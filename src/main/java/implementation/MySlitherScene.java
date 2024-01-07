package implementation;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lib.*;
import java.util.List;

public class MySlitherScene extends SlitherScene {
    private Position<Double> mousePos;
    private  PaneScreen pane;
    private List<Snake> snakeList;
    private List<Fruit> fruitList;

    public MySlitherScene(PaneScreen pane) {
        super(pane);
        this.pane = pane;
    }

    public void play(){
        for (Snake s:snakeList){
            s.choseDirection(this);
            s.move();
            //if snake is touching a fruit add a segment to snake and display it

            if(s.isTouchingSom(fruitList)){
                s.add();
                s.last().display(pane);
                //add a new fruits to map
                Fruit.displayAFruit(fruitList,snakeList);
            }
        }
    }

    public List<Snake> getSnakeList() {
        return snakeList;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    @Override
    public void init(List<Snake> snakeList, List<Fruit> fruitList) {
        this.snakeList= snakeList;
        this.fruitList= fruitList;
        Snake playable= snakeList.get(0);
        mousePos= new MyPosition(0,0);
        if(!(playable instanceof ControllableSnake)){
            throw new IllegalArgumentException();
        }

        if(playable instanceof ArrowSnake){
            this.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case UP: if(((ArrowSnake) playable).lastInput() == Direction.DOWN) {return;}
                            ((ArrowSnake) playable).setLastInput(Direction.UP);
                            break;
                        case DOWN: if(((ArrowSnake) playable).lastInput() == Direction.UP) {return;}
                            ((ArrowSnake) playable).setLastInput(Direction.DOWN);
                            break;
                        case LEFT: if(((ArrowSnake) playable).lastInput() == Direction.RIGHT) {return;}
                            ((ArrowSnake) playable).setLastInput(Direction.LEFT);
                            break;
                        case RIGHT: if(((ArrowSnake) playable).lastInput() == Direction.LEFT){return;}
                            ((ArrowSnake) playable).setLastInput(Direction.RIGHT);
                            break;
                    }
                }
            });
        }

        else if (playable instanceof MouseSnake){
            this.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                   ((MouseSnake) playable).setLastInput(new MyPosition(event.getX(), event.getY()));
                }
            });
        }
    }

    public Position<Double> getMousePos() {
        return new MyPosition(mousePos.getX(),mousePos.getY());
    }
}
