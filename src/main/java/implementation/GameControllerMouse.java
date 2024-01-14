package implementation;

import javafx.fxml.Initializable;
import lib.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameControllerMouse extends GameController {
    public GameControllerMouse(PaneScreen pane, MySlitherScene scene, boolean player2, boolean withIA, boolean scrolling){
        make(pane,scene,player2,withIA,scrolling);
        timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                //while true
                GameControllerMouse.this.play();
            }
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initialise le jeu et lance la while true
        snakeList= new ArrayList<>();
        MouseSnake head = new MouseSnake(new MyPosition(SlitherScene.windowWidth / 2, SlitherScene.windowHeight / 2));
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


