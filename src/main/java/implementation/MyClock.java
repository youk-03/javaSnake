package implementation;

import lib.Clock;

import static java.lang.Thread.sleep;

public class MyClock implements Clock {
    long rythm;
    boolean ready;

    @Override
    public void init(long rythm) {
        ready=true;
        this.rythm= rythm;
    }

    @Override
    public void tick(){
        if(ready){
            try{ sleep(rythm);}
            catch (InterruptedException ignored){return;};
        }
        else throw new Error("The clock isn't ready.");
    }

    @Override
    public void changeRythm(long rythm) {
        this.rythm= rythm;
    }
}
