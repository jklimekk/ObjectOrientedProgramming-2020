package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals = new ArrayList<>();
    private final MapVisualiser mv = new MapVisualiser(this);

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
            i %= animalsNumber;
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

    protected abstract Vector2d[] corners();

    @Override
    public String toString() {
        Vector2d lowerLeftCorner = corners()[0];
        Vector2d upperRightCorner = corners()[1];

        return mv.draw(lowerLeftCorner, upperRightCorner);
    }

}
