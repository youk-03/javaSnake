package implementation;

import javafx.fxml.Initializable;
import lib.*;

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
        IaSnake ia = new IaSnake(new MyPosition(10, 10));

        snakeList= new ArrayList<>();
        snakeList.add(head);
        snakeList.add(ia);
        for(Snake s:snakeList){
            s.display(pane);
        }

        MyFruit.init(pane,snakeList);
        fruitList= MyFruit.getListFruit();
        scene.init(snakeList,fruitList);

        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                //while true
                GameController.this.play();
            }
        };
        timer.start();
    }

    public void play(){
        for (Snake s:snakeList){
            s.choseDirection(scene);
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


