package pl.edu.agh.ietanks.game;

import java.util.List;

public interface BoardInterface {
    public List<TankInterface> getTanks();
    public TankInterface getTank(int id);
    public List<ProjectileInterface> getBullets();
    public void addTank(TankInterface tank);
    public void addBullet(ProjectileInterface bullet);
    public int getWidth();
    public int getHeight();
    public Object getBoard(); // graphical representation of board with all elements
}