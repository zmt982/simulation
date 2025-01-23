package entity;

import java.util.Objects;

public class Coordinates {
    public final Integer X;
    public final Integer Y;

    public Coordinates(Integer x, Integer y) {
        this.X = x;
        this.Y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return X.equals(that.X) && Y.equals(that.Y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}