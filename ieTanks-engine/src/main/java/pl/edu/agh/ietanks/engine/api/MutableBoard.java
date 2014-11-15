package pl.edu.agh.ietanks.engine.api;

public interface MutableBoard extends Board {
    public void replaceTank(int tankId, Position destination);
}
