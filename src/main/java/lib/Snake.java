package lib;

import implementation.IaSnake;
import implementation.MainScene;

import java.util.List;

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

    /**@return position toward the snake go.*/
    abstract Position getDirection();

    /**
     * Ask the snake to update its currentDirection.
     *
     * @param scene the scene where the snake evolve
     */
    abstract void choseDirection(SlitherScene scene);

    /** Change the Position of every Snake part. If direction null, the snake doesn't move.
     * @param fruitList the fruitList of the scene where the snake evolve*/
    default void move(List<Fruit> fruitList, List<Snake> snakeList){

        double paddingX = 0;
        double paddingY = 0;

        if(MainScene.scrolling){
            paddingY = SlitherScene.paddingY;
            paddingX = SlitherScene.paddingX;
        }


        Position dir= getDirection();
        if(dir == null) return;
        Position pos= getPos();
        double[] vector= Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());

        //To mitigate rotation
        if(head().next() != null){
            double maxAngle= 15;
            double[] actual= actualVector();
            double angle= Utils.angleBetweenVector(actual[0],actual[1],vector[0],vector[1]);
            if(angle>maxAngle){
                Utils.rotatePoint(dir,pos,angle-20);
                vector= Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());
            }
        }

        //check if movement don't kill the snake on his neck
        if(!isValidMove(vector)) return;


        //check if new pos in screen
            if(pos.getX()+ vector[0]> SlitherScene.windowWidth - paddingX || pos.getX()+ vector[0]<0 + paddingX || pos.getY()+ vector[1]>SlitherScene.windowHeight - paddingY || pos.getY()+ vector[1] < 0 + paddingY){
                if(MainScene.scrolling) {
                    if (this instanceof ControllableSnake<?>) {
                        Fruit.setPosForAllFruit(vector[0], vector[1], fruitList);
                    }
                }
                return;
           }


        //move all SnakeCell (exept the head)
        SnakeCell tmp = this.last();
        while(tmp.prev() != null) { //moving the segment
            SnakeCell prev = tmp.prev();
            tmp.setPosition(prev.getX(), prev.getY());
            tmp.moveCircle();
            tmp = prev;
        }

        //moving the head
        double newHeadX= pos.getX()+ vector[0];
        double newHeadY= pos.getY()+ vector[1];
        //if the snake exit the screeen
        if(newHeadX> SlitherScene.windowWidth){
            newHeadX= 0 + (newHeadX - SlitherScene.windowWidth);
        } else if(pos.getX()+ vector[0]<0 + paddingX){
            newHeadX= SlitherScene.windowWidth + newHeadX;
        }
        if(pos.getY()+ vector[1]>SlitherScene.windowHeight){
            newHeadY= 0 + (newHeadY - SlitherScene.windowHeight);
        } else if(pos.getY()+ vector[1] < 0 + paddingY){
            newHeadY= SlitherScene.windowHeight + newHeadY;
        }
        head().setPosition(newHeadX,newHeadY);
        head().moveCircle();
        if(MainScene.scrolling) {
            if (this instanceof ControllableSnake<?>) {
                Fruit.setPosForAllFruit(vector[0], vector[1], fruitList);
            }
        }

        if(this instanceof IaSnake && this.isBeingKilled(snakeList)){

            for(int i=0; i<10; i++){
                Fruit.displayAFruit(fruitList,snakeList,false,true,this.getPos());
            }
            this.disappear();
            snakeList.remove(this);
        }

        if(this.isDead(snakeList) && this instanceof ControllableSnake<?>){
            if(isThereAnotherP(snakeList, this)){
                for(int i=0; i<10; i++){
                    Fruit.displayAFruit(fruitList,snakeList,false,true,this.getPos());
                }
                this.disappear();
                snakeList.remove(this);
            }
            else {  ///////////////TODO///////////////////
                System.out.println("dead");
                System.exit(0);
            }
        }
    }

    /**if the snake is dead make it disappear*/
    public void disappear();

    /**@return true if the move is accepted by move()
     * @param vector the vectore of the mouvement (can be find with Utils.velocityVector()*/
    default boolean isValidMove(double[] vector){
        if(vector == null) return true;
        Position pos= getPos();

        //check if movement don't kill the snake on his neck
        SnakeCell next= head().next();
        double newHeadX= pos.getX()+ vector[0];
        double newHeadY= pos.getY()+ vector[1];
        return !(next != null && willDieOnHisNeck(newHeadX,newHeadY));
    }

    /**@return true if the move is accepted by move()
     * @param dir the direction toward the snake want to go*/
    default boolean isValidMove(Position dir){
        if(dir == null) return true;
        Position pos= getPos();
        return isValidMove(Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity()));
    }

    /**@return the vector of the actual mouvement of the snake (before change)*/
    private double[] actualVector(){
        SnakeCell head= head();
        SnakeCell next= head.next();
        return Utils.velocityVector(next.getX(), next.getY(),head.getX()+10, head.getY()+10, this.getVelocity());
    }

    default boolean isBeingKilled(List<Snake> snakeList){
        SnakeCell tmp;

        for (Snake snake : snakeList) {

            if (!(snake == this)) {
                tmp = snake.head();
                while (tmp != null) {
                    if (this.head().isTouching(tmp)) {
                        return true;
                    }
                    tmp = tmp.next();
                }
            }
        }

        return false;

    }

    /**@return true if the snake have been killed or killed itself*/
    default boolean isDead(List<Snake> snakeList){
        //compare avec tout les segment du serpent , a appelé avant de bouger le serpent une fois que la nouvelle pos a été calculée
        SnakeCell tmp = head().next();
        if(tmp == null) return false;
        tmp = tmp.next();
        while(tmp != null){
            if(head().isTouching(tmp)) { return true; }
            tmp = tmp.next();
        }
        if(snakeList != null) {
           return this.isBeingKilled(snakeList);
        }

        return false;
    }

    /**@return true if the head of the snake touch the object*/
    default boolean isTouching (GraphicalObject obj){
        return head().isTouching(obj);
    }

    /**@return true if the snake will die on his neck while going in this direction. !willDieOnHisNeck is one of the rule of isValidMove()*/
    private boolean willDieOnHisNeck(double x, double y){
        SnakeCell next= head().next();
        if(next == null) return false;

        boolean res=false;

        Position lastPosHead= head().getPos();

        setPosition(x,y);
        if (this.isTouching(next)) res= true;
        this.setPosition(lastPosHead.getX(),lastPosHead.getY());
        return res;
    }

    /**@return true if the snake will die going in this direction*/
    private boolean willDie(double x, double y){;
        boolean res=false;

        Position lastPosHead= head().getPos();
        setPosition(x,y);
        if(this.isTouching(this.last())) return false;
        if(this.isDead(null)){
            res= true;
        }
        this.setPosition(lastPosHead.getX(),lastPosHead.getY());
        return res;
    }

    /**@return true if the snake will die going in this direction
     * @param dir the direction toward the snake go*/
    default boolean willDie(Position dir){
        if(dir == null) return true;
        Position pos= getPos();
        double[] vector= Utils.velocityVector(pos.getX(), pos.getY(), dir.getX(), dir.getY(), this.getVelocity());
        return willDie(pos.getX()+vector[0],pos.getY()+vector[1]);
    }

    /**@return true if the snake is touching some fruit
     * @param list the list of fruit*/
    default boolean isTouchingSom (List<Fruit> list){
       return head().isTouchingSom(list);
    }

    default boolean isThereAnotherP (List<Snake> snakeList, Snake snake){
        for (Snake s: snakeList) {
           if(s instanceof ControllableSnake<?> && s != snake){
               return true;
           }
        }
        return false;
    }
}
