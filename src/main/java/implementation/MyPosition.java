package implementation;

import lib.*;

public class MyPosition implements Position {
    private double x;
    private double y;

    public MyPosition(double x,double y){
        this.setXY(x,y);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setX(double x) {
        this.x= x;
    }

    @Override
    public void setY(double y) {
        this.y= y;
    }

    @Override
    public void setXY(double x, double y) {
        this.x= x;
        this.y= y;
    }

    public static Position getRandPos(){
        double x = Math.random()* SlitherScene.windowWidth;
        double y = Math.random()*SlitherScene.windowHeight;

        return new MyPosition(x,y);
    }

    @Override
    public boolean equals(Position position) {
        return position.getX()==this.getX() && position.getY()==this.getY();
    }
}
