package pl.edu.agh.ietanks.game;

public interface TankInterface extends MovableInterface {
    public int getId();
    public int getWidth(); // for now I guess we can just hardcode "3"
    public int getHeight(); // same as width
    public ProjectileInterface shoot(int directionX, int directionY);
}
