package pl.edu.agh.ietanks.game;

public interface ProjectileInterface extends MovableInterface {
    public int getShotBy(); // id of tank that shot the projectile
    public int getDirectionX();
    public int getDirectionY();
}
