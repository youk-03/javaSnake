package lib;

import javafx.scene.shape.Circle;

import java.security.InvalidParameterException;
import java.util.List;

public interface SnakeCell extends GraphicalObject {
    /**@return return true if the segment of the snake cell is the head*/
    abstract boolean isHead();

    /**@return return true if the segment of the snake cell is the last*/
    abstract boolean isLast();

    /**@return the next segment of the snake cell. Null if it does not exist.*/
    abstract SnakeCell next();

    /**@return the previous segment of the snake cell. Null if it does not exist.*/
    abstract SnakeCell prev();

    /**link the calling snake cell with the two given*/
    abstract void link(SnakeCell previous,SnakeCell next);

    /**@return the pos of the cell on the x axes.*/
    abstract double getX();
    /**@return the pos of the cell on the y axes.*/
    abstract double getY();
    /**change the position of the cell*/
    abstract void setPosition(double x, double y);

    /**@return true if the cell is touching the given object*/
    abstract boolean isTouching (GraphicalObject obj);

    /**@return true if the cell is touching some food*/
    abstract boolean isTouchingSom (List<Fruit> list);

    /**@return the graphical object representing a cell*/
    abstract Circle getSegment();

    /** change the pos of the circle of this on the scene */
    abstract void moveCircle();
}
