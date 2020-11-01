package pl.edu.agh.cs.lab4;

import pl.edu.agh.cs.lab2.*;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    public IWorldMap map;

    public Vector2d getPosition() {
        return position;
    }

    public Animal(IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case WEST -> "<";
            case SOUTH ->  "v";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d temp = this.position.add(this.orientation.toUnitVector());

                if(map.canMoveTo(temp))
                    this.position = temp;
            }
            case BACKWARD -> {
                Vector2d temp = this.position.subtract(this.orientation.toUnitVector());

                if(map.canMoveTo(temp))
                    this.position = temp;
            }
        }
    }
}