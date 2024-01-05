package implementation;

import javafx.fxml.Initializable;
import lib.Fruit;
import lib.GameLoopTimer;
import lib.SlitherScene;
import lib.Snake;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

   public PaneScreen pane;
   private List<Snake> snakeList;
   private List<Fruit> fruitList;

   MySlitherScene scene;


    public GameController(PaneScreen pane, MySlitherScene scene){
        this.pane = pane;
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialise le jeu et lance la while true
        //ArrowSnake head = new ArrowSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        MouseSnake head = new MouseSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        //MyRandomSnake ia = new MyRandomSnake(new MyPosition(0, 0));

        snakeList= new ArrayList<>();
        snakeList.add(head);
        //snakes.add(ia);
        for(Snake s:snakeList){
            s.display(pane);
        }

        MyFruit.init(pane,snakeList);
        scene.init(snakeList,MyFruit.getListFruit());

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                //while true
                play();
            }
        };
        timer.start();
    }

    public void play(){
        for (Snake s:snakeList){
            s.choseDirection(null);
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

    //update player movement ?
}


