package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;

import java.util.Objects;

public class Grass implements IMapElement {
    Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Grass))
            return false;
        Grass that = (Grass) other;

        return this.position.x == that.position.x && this.position.y == that.position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
