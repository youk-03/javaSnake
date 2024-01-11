package lib;

import java.util.List;

public interface Position {
    abstract double getX();
    abstract double getY();
    abstract void setX(double x);
    abstract void setY(double y);
    abstract void setXY(double x,double y);

    abstract boolean equals(Position p);


    /**
     * @param list a list of instance of GraphicalObject
     * @return true if one element of the given list have the same position.*/
    default boolean isIn(List list){
        for(Object o : list){
            if((o instanceof GraphicalObject)){
                if(this.equals(((GraphicalObject) o).getPos())) return true;
            } else {
                throw new IllegalArgumentException("The list must contain only instance of GraphicalObject");
            }
        }
        return false;
    }

    /**@return true if SnakeCell of the given Snake have the same position*/
    default boolean isIn(Snake s){
        SnakeCell tmp= s.head();
        while(tmp != null){
            if(this.equals(tmp.getPos())) return true;
            tmp= tmp.next();
        }
        return false;
    }

    default boolean isTouching(GraphicalObject obj){
        double x= getX();
        double y= getY();
        return Utils.isTouching(obj,x,y);
    }
}
