package implementation;

import lib.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameControllerArrow extends GameController {

    public GameControllerArrow(PaneScreen pane, MySlitherScene scene, boolean player2, boolean withIA, boolean scrolling){
        make(pane,scene,player2,withIA,player2);
        timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                //while true
                GameControllerArrow.this.play();
            }
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialise le jeu et lance la while true
        snakeList= new ArrayList<>();

        ArrowSnake head = new ArrowSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
        snakeList.add(head);
        if(player2){
            ArrowSnake player2= new ArrowSnake(new MyPosition(SlitherScene.windowWidth / 4, SlitherScene.windowHeight / 4));
            snakeList.add(player2);
        }
        if(withIA){
            IaSnake ia = new IaSnake(new MyPosition(SlitherScene.windowWidth / 3, SlitherScene.windowHeight / 3));
            snakeList.add(ia);
        }

        for(Snake s:snakeList){
            s.display(pane);
        }

        MyFruit.init(pane,snakeList);
        fruitList= MyFruit.getListFruit();
        scene.init(snakeList,fruitList);

        timer.pause();
    }
}


