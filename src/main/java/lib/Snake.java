package lib;

import javafx.scene.shape.Circle;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

public interface Snake extends GraphicalObject{
    /**@return the first SnakeCell of the snake*/
    abstract SnakeCell head();

    /**@return the last SnakeCell of the snake*/
    abstract SnakeCell last();

    /**Add a segment at the end of the snake*/
    abstract void add();

    /** Remove the segment and all the next one. If the segment isHead(), call the method killSnake().*/
    abstract void removeFrom();

    /**@return the velocity of the snake (in pixel/tick)*/
    abstract double getVelocity();

    /**Return position toward the snake go.*/
    abstract Position getDirection();

    /**
     * Ask the snake to update its currentDirection.
     *
     * @param scene the scene where the snake evolve
     */
    abstract void choseDirection(SlitherScene scene);

    /** Change the Position of every Snake part. If direction null, the snake doesn't move.*/
    default void move(){
        Position dir= getDirection();
        if(dir == null) return;
        Position pos= getPos();
        double[] vector= Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());

        //check if new pos in screen
        if(pos.getX()+ vector[0]> SlitherScene.windowWidth || pos.getX()+ vector[0]<0 || pos.getY()+ vector[1]>SlitherScene.windowHeight || pos.getY()+ vector[1] < 0){
            return;
        }

        //check if movement don't kill the snake on his neck
        if(!isValidMove(vector)) return;

        SnakeCell tmp = this.last();
        while(tmp.prev() != null) { //moving the segment
            SnakeCell prev = tmp.prev();
            tmp.setPosition(prev.getX(), prev.getY());
            tmp.moveCircle();
            tmp = prev;
        }

        //moving the head
        SnakeCell next= head().next();
        double newHeadX= pos.getX()+ vector[0];
        double newHeadY= pos.getY()+ vector[1];
        head().setPosition(newHeadX,newHeadY);
        head().moveCircle();


        if(this.isDead() && this instanceof ControllableSnake<?>){
           ///////////////TODO///////////////////
           System.out.println("dead");
           System.exit(0);
        }
    }

    default boolean isValidMove(double[] vector){
        if(vector == null) return true;
        Position pos= getPos();

        //check if movement don't kill the snake on his neck
        SnakeCell next= head().next();
        double newHeadX= pos.getX()+ vector[0];
        double newHeadY= pos.getY()+ vector[1];
        return !(next != null && willDieOnHisNeck(newHeadX,newHeadY));
    }

    default boolean isValidMove(Position dir){
        if(dir == null) return true;
        Position pos= getPos();
        return isValidMove(Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity()));
    }

    private double[] actualVector(){
        SnakeCell head= head();
        SnakeCell next= head.next();
        return Utils.velocityVector(next.getX(), next.getY(),head.getX()+10, head.getY()+10, this.getVelocity());
    }

    abstract boolean isDead();

    default boolean isTouching (GraphicalObject obj){
        return head().isTouching(obj);
    }

    private boolean willDieOnHisNeck(double x, double y){
        SnakeCell next= head().next();
        if(next == null) return false;

        boolean res=false;

        Position lastPosHead= head().getPos();

        setPosition(x,y);
        next.setPosition(lastPosHead.getX(),lastPosHead.getY());
        if (this.isTouching(next)) res= true;
        this.setPosition(lastPosHead.getX(),lastPosHead.getY());
        return res;
    }

    private boolean willDie(double x, double y){;
        boolean res=false;

        Position lastPosHead= head().getPos();
        setPosition(x,y);
        if(this.isTouching(this.last())) return false;
        if(this.isDead()){
            res= true;
        }
        this.setPosition(lastPosHead.getX(),lastPosHead.getY());
        return res;
    }

    default boolean willDie(Position dir){
        return willDie(dir.getX(),dir.getY());
    }

    default boolean isTouchingSom (List<Fruit> list){
       return head().isTouchingSom(list);
    }
}
