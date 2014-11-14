package pl.edu.agh.ietanks.game;

public interface MovableInterface {
    public int getSpeed();
    public int getX();
    public int getY();
    public void moveTo(int X, int Y);
}
