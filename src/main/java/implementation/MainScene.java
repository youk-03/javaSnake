package implementation;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import lib.GameController;
import lib.SlitherScene;

import java.util.Arrays;
import java.util.List;

public class MainScene extends Scene {

    PaneScreen pane;
    public static boolean scrolling,arrowKey,twoP,ia,mouseKey;
    Button play;
    public MainScene(PaneScreen pane) {
        super(pane, SlitherScene.windowWidth, SlitherScene.windowHeight);
        this.pane = pane;
        scrolling = false;
        arrowKey= false;
        twoP= false;
        ia= false;
        mouseKey = false;
    }

    public void init(){
        double x = SlitherScene.windowWidth/2;
        double y = SlitherScene.windowHeight/9;



        Button scrolling, arrowKey,twoP,ia, mouseKey;
        scrolling = new Button("Scrolling");
        scrolling.relocate(x,y*2);

        arrowKey = new Button("ArrowKey");
        arrowKey.relocate(x,y*3);

        mouseKey = new Button("MouseKey");
        mouseKey.relocate(x,y*4);

        twoP = new Button("two Player");
        twoP.relocate(x,y*5);
        ia = new Button("Ia");
        ia.relocate(x,y*6);

        this.play = new Button("Play");
        this.play.relocate(x,y*8);
        this.play.setDisable(true);

        setEvent(scrolling, Arrays.asList(twoP), MainScene.scrolling,null);
        setEvent(arrowKey, Arrays.asList(mouseKey), MainScene.arrowKey,play);
        setEvent(twoP, Arrays.asList(scrolling), MainScene.twoP,null);
        setEvent(mouseKey, Arrays.asList(arrowKey), MainScene.mouseKey, play);
        setEvent(ia,null, MainScene.ia, null);
        scrolling.addEventHandler(ActionEvent.ACTION, e -> {
            MainScene.scrolling = true;
        });
        arrowKey.addEventHandler(ActionEvent.ACTION, e -> {
            MainScene.arrowKey = true;
        });
        twoP.addEventHandler(ActionEvent.ACTION, e -> {
            MainScene.twoP = true;
        });
        ia.addEventHandler(ActionEvent.ACTION, e -> {
            MainScene.ia = true;
        });
        playSetEvent(play);

        pane.add(scrolling);
        pane.add(arrowKey);
        pane.add(mouseKey);
        pane.add(twoP);
        pane.add(ia);
        pane.add(play);
    }


    private static void toBlock(List<Button> toBlock){
        for (Button button:toBlock) {
            button.setDisable(true);
        }
    }

    private static void setEvent(Button button, List<Button> toBlock, boolean toChange, Button play){
        button.setOnAction(actionEvent -> {

            if(toBlock != null) {
                toBlock(toBlock);
            }
            if(play!=null) {
                play.setDisable(false);
            }
            button.setDisable(true);
        });
    }

    private static void playSetEvent(Button play){
        play.setOnAction(actionEvent -> {
            //creer gamecontroller etc
            GameController gameController = newGameController();
            gameController.initialize(null,null);
            SceneController.switchScene(gameController.getScene());
            gameController.setTimerStart();
            //switchscene
        });
    }
    private static GameController newGameController(){
        PaneScreen pane = new PaneScreen();
        MySlitherScene scene = new MySlitherScene(pane);
        GameController res;
        if(arrowKey){
            res = new GameControllerArrow(pane,scene, twoP, ia, scrolling);
        }
        else{
            res = new GameControllerMouse(pane, scene, twoP, ia, scrolling);
        }

        return res;
    }
}
