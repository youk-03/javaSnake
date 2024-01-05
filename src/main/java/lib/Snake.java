package lib;

import javafx.scene.shape.Circle;
import java.security.InvalidParameterException;
import java.util.List;

public interface Snake extends GraphicalObject{
    /**@return return true if the segment of the snake is the head*/
    abstract boolean isHead();

    /**@return the next segment of the snake. Null if it does not exist.*/
    abstract Snake next();

    /**@return the previous segment of the snake. Null if it does not exist.*/
    abstract Snake prev();
    /**@return the last segment of the snake. Null if it does not exist.*/
    abstract Snake last();

    /**Add a segment at the end of the snake*/
    abstract void add();

    /** Remove the segment and all the next one. If the segment isHead(), call the method killSnake().*/
    abstract void removeFrom();

    abstract double getX();
    abstract double getY();
    abstract double getVelocity();

    /**Return position toward the snake go.*/
    abstract Position getDirection();

    /**
     * Ask the snake to update its currentDirection. Only got sense for the head.
     *
     * @param scene the scene where the snake evolve
     */
    abstract void choseDirection(SlitherScene scene);

    /** Change the Position of every Snake part, needs to be called on the head ! If direction null, the snake doesn't move.*/
    default void move(){
        Snake tmp = this.last();
        if(!this.isHead()){
            throw new InvalidParameterException();
        }

        Position dir= getDirection();
        if(dir == null) return;
        Position pos= getPos();
        double[] vector= Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());

        if(pos.getX()+ vector[0]> SlitherScene.windowWidth || pos.getX()+ vector[0]<0 || pos.getY()+ vector[1]>SlitherScene.windowHeight || pos.getY()+ vector[1] < 0){
            return;
        }

        while(tmp.prev() != null){ //moving the segment
            tmp.setPosition(tmp.prev().getX(),tmp.prev().getY());
            tmp.moveCircle();
            tmp = tmp.prev();
        }
        //moving the head
        this.setPosition(
                pos.getX()+ vector[0],
                pos.getY()+ vector[1]);
        Snake next= this.next();
        if(next != null && this.isTouching(this.next())) {
            this.setPosition(pos.getX(), pos.getY());
            return;
        }
        this.moveCircle();
       if(this.isDead() && this instanceof ControllableSnake<?>){
           ///////////////TODO///////////////////
           System.out.println("dead");
           System.exit(0);
       }
    }

    public boolean isDead();
    abstract boolean isTouching (GraphicalObject obj);
    abstract boolean isTouchingSom (List<Fruit> list);

    /** change the pos of the circle of this on the scene */
    abstract void moveCircle();

     abstract Circle getSegment();
}
