package implementation;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lib.*;
import java.util.List;

public class MySnake implements Snake {
    public SnakeCell head;
    public SnakeCell last;
    private Position currentDirection;
    double radius=5;
    final double velocity= radius*2;
    private Color headColor, bodyColor;

    /**Create a snake*
     * @param pos the position of the head of the snake
     */
    public MySnake(Position pos){
        currentDirection = new MyPosition(0,0);
        headColor= Color.DARKGREEN;
        bodyColor= Color.FORESTGREEN;
        head= new MySnakeCell(pos);
        last= head;
    }

    /**Create a head a snake and precise its color
     * @param  pos the position of the head of the snake
     * @param headColor color of the head of the snake when display
     * @param bodyColor color of all other snake segment*/
    public MySnake(Position pos,Color headColor, Color bodyColor){
        currentDirection = new MyPosition(0,0);
        this.headColor= headColor;
        this.bodyColor= bodyColor;
        head= new MySnakeCell(pos);
        last= head;
    }

    @Override
    public SnakeCell head(){
        return head;
    }

    @Override
    public SnakeCell last() {
        return last;
    }

    @Override
    public void display(Screen screen) {
        SnakeCell current= head;
        while (current != null){
            current.display(screen);
            current= current.next();
        }
    }

    @Override
    public Position getPos() {
        return head.getPos();
    }

    @Override
    public Double getRadius() {
        return radius;
    }

    @Override
    public void add() {
        //add segment if the snake is not only a head
        if(head.next() != null) {
            Position posSegm = last.getPos();
            Position posSegmPrev = last.prev().getPos();
            Position posNew= new MyPosition(
                    posSegm.getX() + (posSegm.getX() - posSegmPrev.getX()),
                    posSegm.getY() + (posSegm.getY() - posSegmPrev.getY()));
            last = new MySnakeCell(last, null, posNew);
        } else { //add segment if the snake is not only a head
            double[] vpos= Utils.velocityVector(head.getX(),head.getY(),currentDirection.getX(), currentDirection.getY(), velocity);
            Position posNew = new MyPosition(
                    head.getX() - vpos[0],
                    head.getY() - vpos[1]);
            last = new MySnakeCell(head, null, posNew);
        }
    }

    @Override
    public void removeFrom() {
        //TODO
    }

    @Override
    public void setPosition(double x, double y) {
        head.setPosition(x,y);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public Position getDirection() {
        if(currentDirection==null) return null;
        return new MyPosition(currentDirection.getX(),currentDirection.getY());
    }

    @Override
    public void choseDirection(SlitherScene scene) {
        throw new UnsupportedOperationException();
    }

    public void setCurrentDirection(Position pos){
        if(pos == null) currentDirection= null;
        else currentDirection = new MyPosition(pos.getX(),pos.getY());
    }

    public class MySnakeCell implements SnakeCell{
        private Position position;
        private double radius;
        private SnakeCell next;
        private SnakeCell previous;
        private Color color;
        private Circle segment;

        /**Create a head cell
         * @param pos the position of the cell*/
        public MySnakeCell(Position pos){
            this.next= null;
            this.previous= null;
            this.position= pos;
            segment = null;
            radius = MySnake.this.radius;
            color= MySnake.this.headColor;
        }

        /**Create a body cell, link to prev and next
         * @parm prev the previous SnakeCell link to this one
         * @param next the next SnakeCell link to this one
         * @param pos the position of the cell*/
        public MySnakeCell(SnakeCell previous,SnakeCell next,Position pos){
            this.link(previous,next);
            this.position= pos;
            segment = null;
            radius= MySnake.this.radius;
            color= MySnake.this.bodyColor;
        }

        @Override
        public void display(Screen screen) {
            segment = new Circle();
            segment.setCenterX(position.getX());
            segment.setCenterY(position.getY());
            segment.setRadius(radius);
            segment.setFill(color);
            screen.add(segment);
        }

        @Override
        public Position getPos() {
            return new MyPosition(position.getX(),position.getY());
        }

        @Override
        public Double getRadius() {
            return radius;
        }

        @Override
        public boolean isHead() {
            return this.previous == null;
        }

        @Override
        public boolean isLast() {
            return this.next == null;
        }

        @Override
        public SnakeCell next() {
            return next;
        }

        @Override
        public SnakeCell prev() {
            return previous;
        }

        @Override
        public void link(SnakeCell previous, SnakeCell next) {
           if(this.previous != previous){
               this.previous=previous;
               if(previous != null) previous.link(previous.prev(),this);
           }
           if(this.next != next){
               this.next=next;
               if(previous != null) next.link(this,next.next());
           }
        }

        @Override
        public double getX() {
            return position.getX();
        }

        @Override
        public double getY() {
            return position.getY();
        }

        @Override
        public void setPosition(double x, double y) {
            position.setXY(x,y);
        }

        @Override
        public boolean isTouching(GraphicalObject obj) {
            //comparer avec this
            double xObj = obj.getPos().getX();
            double yObj = obj.getPos().getY();

            double rad= radius;
            if(obj instanceof Snake) rad--; //to make sure
            if(yObj - (rad+obj.getRadius()) <= this.getY() && this.getY() <= yObj + (rad+obj.getRadius())){
                return (xObj + rad >= this.getX() && this.getX() >= xObj - rad);
            } else if(xObj - (rad+obj.getRadius()) <= this.getX() && this.getX() <= xObj + (rad+obj.getRadius())){
                return (yObj + rad >= this.getY() && this.getY() >= yObj - rad);
            }
            return false;
        }

        @Override
        public boolean isTouchingSom(List<Fruit> list) {
            for (Fruit f: list) {
                if(f.isTakable() && this.isTouching(f)) {
                    f.setInvisible();
                    return true;
                }
            }
            return false;
        }

        @Override
        public Circle getSegment() {
            return segment;
        }

        @Override
        public void moveCircle() {
            segment.setCenterX(position.getX());
            segment.setCenterY(position.getY());
        }
    }
}

