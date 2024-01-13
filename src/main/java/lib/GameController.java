package lib;

import implementation.*;
import javafx.fxml.Initializable;
import java.util.List;

public abstract class GameController implements Initializable {
    protected PaneScreen pane;
    protected List<Snake> snakeList;
    protected List<Fruit> fruitList;

    protected SlitherScene scene;

    protected boolean player2;
    protected boolean withIA;
    protected boolean scrolling;

    protected void make(PaneScreen pane, SlitherScene scene, boolean player2, boolean withIA, boolean scrolling){
        this.pane= pane;
        this.scene= scene;
        this.player2= player2;
        this.withIA= withIA;
        this.scrolling= scrolling;
    }

    public void play(){
        //a chaque mouvement du joueur bouger les fruits
        for (Snake s:snakeList){
            s.choseDirection(scene);
            s.move(fruitList);
            //if snake is touching a fruit add a segment to snake and display it

            if(s.isTouchingSom(fruitList)){
                s.add();
                s.last().display(pane);
                //add a new fruits to map
                Fruit.displayAFruit(fruitList,snakeList,true);
            }
        }
    }
}
