package implementation;

import lib.*;

import java.util.Random;

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

    public static Position getRandPos(double maxX, double maxY, double minX, double minY){
        double x = ( Math.random()* maxX ) + minX;
        double y = ( Math.random()* maxY ) + minY;

        return new MyPosition(x,y);
    }
    /**get valid a rand pos not on the screen but outside of the screen */
    public static Position getRandPosInvisible(){

        boolean test = false;
        Random random = new Random();
        double maxX = SlitherScene.windowWidth*2;
        double minX = SlitherScene.windowWidth;
        double maxY = SlitherScene.windowHeight*2;
        double minY = SlitherScene.windowHeight;

        double newY = random.nextDouble(maxY + 1 + minY) - minY;
        double newX = random.nextDouble(maxX + 1 + minX) - minX ; //-windowX --- windowX*2

        while (!test){

            if(newX > 0 && newX <= SlitherScene.windowWidth){
                if(!(newY > 0 && newY <= SlitherScene.windowHeight)){
                    test = true;
                }
                else{
                    newY = random.nextDouble(maxY + 1 + minY) - minY;
                    newX = random.nextDouble(maxX + 1 + minX) - minX ; //-windowX --- windowX*2
                }
            }
            else {
                test = true;
            }
        }

        return new MyPosition(newX, newY);

    }

    @Override
    public boolean equals(Position position) {
        return position.getX()==this.getX() && position.getY()==this.getY();
    }
}
