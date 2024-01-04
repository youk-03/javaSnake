package implementation;

import lib.GraphicalObject;
import lib.Position;
import lib.SlitherScene;
import lib.Snake;
import java.util.List;

public class MyPosition implements Position<Double> {
    private double x;
    private double y;

    public MyPosition(double x,double y){
        this.setXY(x,y);
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public void setX(Double x) {
        this.x= x;
    }

    @Override
    public void setY(Double y) {
        this.y= y;
    }

    @Override
    public void setXY(Double x, Double y) {
        this.x= x;
        this.y= y;
    }

    public static Position<Double> getRandPos(){
        double x = Math.random()* SlitherScene.windowWidth;
        double y = Math.random()*SlitherScene.windowHeight;

        return new MyPosition(x,y);
    }

    public boolean equals(Position position) {
        return position.getX().equals(this.getX()) && position.getY().equals(this.getY());
    }

    public boolean isValid(List<GraphicalObject> e){

        for (GraphicalObject g: e) {
            if(this.equals(g.getPos())) return false;
        }
        return true;

    }

    public boolean isValid(Snake head){
        if (!head.isHead()){
            throw new IllegalCallerException();
        }
        Snake tmp = head;
        while(tmp != null){

            if(this.equals(tmp.getPos())) return false;

            tmp= tmp.next();
        }

        return true;
    }
}
