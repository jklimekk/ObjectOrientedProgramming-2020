package pl.edu.agh.cs.lab4;

import pl.edu.agh.cs.lab2.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap {
    List<Animal> animals = new ArrayList<>();
    MapVisualiser mv = new MapVisualiser(this);

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
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalsNumber = animals.size();
        int i = 0;

        for(MoveDirection direction : directions) {
            Animal animal = animals.get(i);
            animal.move(direction);

            i++;
            i = i % animalsNumber;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals) {
            if(animal.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for(Animal animal : animals) {
            if(animal.getPosition().equals(position))
                return Optional.of(animal);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return mv.draw(corner1, corner2);
    }
}
