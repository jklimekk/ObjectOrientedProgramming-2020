package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.*;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab5.IMapElement;
import pl.edu.agh.cs.lab7.IPositionChangeObserver;
import pl.edu.agh.cs.lab7.IPositionChangedPublisher;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement, IPositionChangedPublisher {
    private MapDirection orientation;
    private Vector2d position;
    public IWorldMap map;
    private final List<IPositionChangeObserver> observators= new ArrayList<>();

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
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

                if(map.canMoveTo(temp)) {
                    positionChanged(this.position, temp);
                    this.position = temp;
                }
            }
            case BACKWARD -> {
                Vector2d temp = this.position.subtract(this.orientation.toUnitVector());

                if(map.canMoveTo(temp)) {
                    positionChanged(this.position, temp);
                    this.position = temp;
                }
            }
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observators.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observators.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observator : observators) {
            observator.positionChanged(this, oldPosition, newPosition);
        }
    }
}
