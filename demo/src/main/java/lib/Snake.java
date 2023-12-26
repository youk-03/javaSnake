package lib;

public interface Snake extends GraphicalObject{
    /**@return return true if the segment of the snake is the head*/
    abstract boolean isHead();

    /**@return the next segment of the snake. Null if it does not exist.*/
    abstract Snake next();

    /**@return the previous segment of the snake. Null if it does not exist.*/
    abstract Snake prev();

    /**Add a segment at the end of the snake*/
    abstract void add();

    /** Remove the segment and all the next one. If the segment isHead(), call the method killSnake().*/
    abstract void removeFrom();
}
