package implementation;

import lib.Position;

public class MyPosition implements Position {
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
}
