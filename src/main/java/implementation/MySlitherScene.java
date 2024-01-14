package implementation;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lib.*;
import java.util.List;

public class MySlitherScene extends SlitherScene {
    private Position mousePos;
    private List<Snake> snakeList;
    private List<Fruit> fruitList;

    public MySlitherScene(PaneScreen pane) {
        super(pane);
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

        //get player2 if exist
        ArrowSnake player2 = null;
        if(snakeList.size() >= 2 && snakeList.get(1) instanceof ArrowSnake) {
            player2= (ArrowSnake) snakeList.get(1);
        }

        //add handler on arrow snakes
        if(playable instanceof ArrowSnake){
            ArrowSnake p2= player2;
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
                        case Z:
                            if(p2 == null) return;
                            if(p2.lastInput() == Direction.DOWN) {return;}
                            else p2.setLastInput(Direction.UP);
                            break;
                        case S:
                            if(p2 == null) return;
                            if(p2.lastInput() == Direction.UP) {return;}
                            else p2.setLastInput(Direction.DOWN);
                            break;
                        case Q:
                            if(p2 == null) return;
                            if(p2.lastInput() == Direction.RIGHT) {return;}
                            else p2.setLastInput(Direction.LEFT);
                            break;
                        case D:
                            if(p2 == null) return;
                            if(p2.lastInput() == Direction.LEFT){return;}
                            else p2.setLastInput(Direction.RIGHT);
                            break;
                    }
                }
            });
        }

        //add handler on player MouseSnake and player2 ArrowSnake
        else if (playable instanceof MouseSnake){
            this.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent event) {
                   ((MouseSnake) playable).setLastInput(new MyPosition(event.getX(), event.getY()));
                }
            });
            if(player2 != null){
                ArrowSnake p2= player2;
                this.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case Z:
                                if(p2 == null) return;
                                if(p2.lastInput() == Direction.DOWN) {return;}
                                else p2.setLastInput(Direction.UP);
                                break;
                            case S:
                                if(p2 == null) return;
                                if(p2.lastInput() == Direction.UP) {return;}
                                else p2.setLastInput(Direction.DOWN);
                                break;
                            case Q:
                                if(p2 == null) return;
                                if(p2.lastInput() == Direction.RIGHT) {return;}
                                else p2.setLastInput(Direction.LEFT);
                                break;
                            case D:
                                if(p2 == null) return;
                                if(p2.lastInput() == Direction.LEFT){return;}
                                else p2.setLastInput(Direction.RIGHT);
                                break;
                        }
                    }
                });
            }
        }
    }

    public Position getMousePos() {
        return new MyPosition(mousePos.getX(),mousePos.getY());
    }
}
