package pl.edu.agh.ietanks.gameplay.bot.api.adapters;

import pl.edu.agh.ietanks.gameplay.bot.api.Position;

public class PositionAdapter implements Position {

    private pl.edu.agh.ietanks.engine.api.Position enginePosition;

    public PositionAdapter(pl.edu.agh.ietanks.engine.api.Position enginePosition) {
        this.enginePosition = enginePosition;
    }

    @Override
    public Integer fromLeft() {
        return enginePosition.fromLeft();
    }

    @Override
    public Integer fromTop() {
        return enginePosition.fromTop();
    }

}
