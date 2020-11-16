package pl.edu.agh.cs.lab4;

import pl.edu.agh.cs.lab2.*;
import pl.edu.agh.cs.lab5.AbstractWorldMap;

public class RectangularMap extends AbstractWorldMap {

    public static Vector2d corner1 = new Vector2d(0,0);
    public static Vector2d corner2;

    public RectangularMap(int width, int height) {
        corner2 = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(isOccupied(position)) && position.precedes(corner2) && position.follows(corner1);
    }

    @Override
    public Vector2d[] corners() {
        return new Vector2d[] {corner1, corner2};
    }
}