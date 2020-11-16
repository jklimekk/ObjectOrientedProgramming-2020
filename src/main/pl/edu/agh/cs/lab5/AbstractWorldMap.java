package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals = new ArrayList<>();
    protected Map<Vector2d, Animal> animalsHash = new HashMap<>();
    private final MapVisualiser mv = new MapVisualiser(this);

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.add(animal);
            animalsHash.put(animal.getPosition(), animal);
            return true;
        } else {
            throw new IllegalArgumentException("You can't place animal at: " + animal.getPosition());
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalsNumber = animals.size();
        int i = 0;

        for(MoveDirection direction : directions) {
            Animal animal = animals.get(i);
            // wydaje mi się że gdybyśmy wykorzystywali values() zgubilibyśmy kolejność zwierząt
            // (która jest ważna przy ich poruszaniu) przez usuwanie i dodawanie wartości do hashmapy

            Vector2d beforePosition = animal.getPosition();

            animal.move(direction);

            Vector2d afterPosition = animal.getPosition();

            // sprawdzenie czy się ruszył
            if(! (beforePosition.equals(afterPosition))) {
                animalsHash.remove(beforePosition);
                animalsHash.put(afterPosition, animal);
            }

            i++;
            i %= animalsNumber;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animalsHash.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(animalsHash.containsKey(position)) {
            return Optional.of(animalsHash.get(position));
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
