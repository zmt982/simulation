package simulation;

import java.util.Objects;

public class Coordinates {
    public final int X;
    public final int Y;

    public Coordinates(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(this.X + shift.X_SHIFT, this.Y + shift.Y_SHIFT);
    }

    // ограничено размером карты
    public boolean canShift(CoordinatesShift shift) {
        int x = X + shift.X_SHIFT;
        int y = Y + shift.Y_SHIFT;

        return x >= 0 && x <= Renderer.MAP_X_SIZE && y >= 0 && y <= Renderer.MAP_Y_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return X == that.X && Y == that.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }

    @Override
    public String toString() {
        return "[X=" + X +
                ", Y=" + Y +
                "]";
    }
}