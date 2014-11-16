package pl.edu.agh.ietanks.game;

import java.io.Serializable;
import java.util.List;

public interface BoardInterface extends Serializable {
    public List<TankInterface> getTanks();
    public TankInterface getTank(int id);
    public List<ProjectileInterface> getBullets();
    public void addTank(TankInterface tank);
    public void addBullet(ProjectileInterface bullet);
    public int getWidth();
    public int getHeight();
}