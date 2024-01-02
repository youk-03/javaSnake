package lib;

import javafx.scene.shape.Circle;

import java.security.InvalidParameterException;

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
    abstract void setPosition(double x, double y);
    abstract double getVelocity();

    /**Return the vector of the current direction of the snake. Only got sense for the head.*/
    abstract Position getDirection();

    /** Ask the snake to update its currentDirection. Only got sense for the head.
     * @param grid the grid where the snake evolve*/
    abstract void choseDirection(Grid grid);

    /** Change the Position of every Snake part, needs to be called on the head !*/
    default void move(){
        Snake tmp = this.last();
        if(!this.isHead()){
            throw new InvalidParameterException();
        }
        while(tmp.prev() != null){ //moving the segment
            tmp.setPosition(tmp.prev().getX(),tmp.prev().getY());
            tmp.moveCircle();
            tmp = tmp.prev();
        }
        //moving the head
        double velocity= getVelocity();
        Position dir= getDirection();
        Position pos= getPos();
        this.setPosition(
                pos.getX()+dir.getX(),
                pos.getY()+dir.getY());
        this.moveCircle();
    }
    /** change the pos of the circle of this on the scene */
    abstract void moveCircle();

     abstract Circle getSegment();


}
