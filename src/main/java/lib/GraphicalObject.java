package lib;

public interface GraphicalObject {
    /** Display the object.
     * @param screen the screen where the object must be display.
     */
    abstract void display(Screen screen);

    /**@return the position of the object*/
    abstract Position getPos();

    /**@return the radius of the object. Corresponding to its hitbox.*/
    abstract double getRadius();

    /**Set the position of the object
     * @param x the position on x axes
     * @param y the position on y axes*/
    abstract void setPosition(double x, double y);
}
