package lib;

import implementation.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.util.List;

public abstract class GameController implements Initializable {
    protected PaneScreen pane;
    protected List<Snake> snakeList;
    protected List<Fruit> fruitList;

    protected SlitherScene scene;

    protected boolean player2;
    protected boolean withIA;
    protected boolean scrolling;

    protected GameLoopTimer timer;

    /**Initialaze the attribute of the current object
     * @param pane the pane where the game is display
     * @param scene the SlitherScene of the game
     * @param player2 true to add a second playable snake
     * @param withIA  true to add non playable snake to the game
     * @param scrolling true to make the game scroll with the player. If false, the snake will enter a side of the screen and exit from the other. Will be set at false if player2.
     */
    protected void make(PaneScreen pane, SlitherScene scene, boolean player2, boolean withIA, boolean scrolling){
        this.pane= pane;
        this.scene= scene;
        this.player2= player2;
        this.withIA= withIA;
        this.scrolling= scrolling;
    }

    /** Play one tick of the game*/
    public void play(){
        //each move of the player move the fruits
        for (Snake s:snakeList){
            s.choseDirection(scene);
            s.move(fruitList,snakeList);
            //if snake is touching a fruit add a segment to snake and display it

            if(s.isTouchingSom(fruitList)){
                s.add();
                s.last().display(pane);
                //add a new fruits to map
                Fruit.displayAFruit(fruitList,snakeList,true);
            }
        }
    }

    public Scene getScene(){
        return this.scene;
    }

    public void setTimerStart(){
        timer.start();
    }
}
