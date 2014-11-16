package pl.edu.agh.ietanks.engine.api;

public interface MutableBoard extends Board {
    public void replaceTank(int tankId, Position destination);
    public void removeTank(int tankId);
    public void createMissile(Missile missile);
    public void removeMissile(Missile missile);
    public void replaceMissile(Missile missile, Position destination);
}
