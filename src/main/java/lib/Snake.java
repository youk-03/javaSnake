package lib;

import implementation.MyFruit;
import javafx.scene.shape.Circle;

import java.security.InvalidParameterException;
import java.util.ArrayList;

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

    /** Ask the snake to update its currentDirection. Only got sense for the head.
     * @param grid the grid where the snake evolve*/
    abstract void choseDirection(Grid grid);

    private double[] velocityVector(double x, double y, double a, double b, double velocity){
        double vx= (a-x);
        double vy= (b-y);
        double normalized= Math.sqrt(Math.pow(vx,2)+Math.pow(vy,2));
        double[] vpos= new double[2];
        vpos[0]= (vx / normalized) * velocity;
        vpos[1]= (vy / normalized) * velocity;
        return vpos;
    }

    /** Change the Position of every Snake part, needs to be called on the head !*/
    default void move(){

        Snake tmp = this.last();
        if(!this.isHead()){
            throw new InvalidParameterException();
        }

        Position dir= getDirection();
        Position pos= getPos();
        double[] vector= velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());

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

       if(this.isDead()){
           System.out.println("dead");
           System.exit(1); /////////////////////////////////////////////
       }

        this.moveCircle();
    }

    public boolean isDead();

    abstract boolean isTouchingSom (ArrayList<Fruit> list);

    /** change the pos of the circle of this on the scene */
    abstract void moveCircle();

     abstract Circle getSegment();
}
