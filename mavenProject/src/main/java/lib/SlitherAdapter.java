package lib;
import javafx.scene.input.InputEvent;

import java.util.EventListener;

public interface SlitherAdapter {
    /** Start to watch event on subject
     * @param  subject the EventListener watch by the controller
     */
    abstract void start(EventListener subject);

    /**Stop to watch event on subject*/
    abstract void sleep();

    /**@return the next input listen*/
    abstract InputEvent getInput();

    /** Get the position of the mouse relative to the given pos
     *
     * @param pos the origin of the plan of the returned position
     * @return the position of the mouse relative to pos
     * @throws java.security.InvalidParameterException
     */
    abstract double posOfMouse(Position pos);

    /** Get the position of the mouse relative to the given pos
     *
     * @param pos the origin of the plan of the returned position
     * @return the position of the next event relative to pos
     * @throws java.security.InvalidParameterException
     */
    abstract double posOfEvent(Position pos);
}
