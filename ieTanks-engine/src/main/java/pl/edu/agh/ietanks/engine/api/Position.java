package pl.edu.agh.ietanks.engine.api;

public class Position {
    private int fromTop;
    private int fromLeft;

    public Position(int fromTop, int fromLeft) {
        this.fromTop = fromTop;
        this.fromLeft = fromLeft;
    }

    public Position oneRight() {
        return new Position(fromTop, fromLeft+1);
    }

    public int fromLeft() {
        return fromLeft;
    }

    public int fromTop() {
        return fromTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (fromLeft != position.fromLeft) return false;
        if (fromTop != position.fromTop) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromLeft;
        result = 31 * result + fromTop;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "fromLeft=" + fromLeft +
                ", fromTop=" + fromTop +
                '}';
    }
}
