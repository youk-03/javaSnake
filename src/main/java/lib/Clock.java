package lib;

public interface Clock {
    /**Initialize and return a clock that will tick every given number
     * @param rythm the rythm at wich the clock will tick
     * @return an initialize clock, ready to tick
    */
    abstract Clock init(Number rythm);

    /**Make the code wait for a period of time set by the rythm of the clock. The clock must be initialized with .init() before usign this method.
     */
    abstract void tick();

    /**Change the rythm of the clock with the given number.
     *
     * @param rythm the nex rythm of the clock
     */
    abstract void changeRythm(Number rythm);
}
