package lib;

public interface Game {
    /**Initialize a game for start*/
    abstract void init();

    /**Update the game relative to it evolution*/
    abstract void update();

    /**Print the game interface*/
    abstract void affiche();
}