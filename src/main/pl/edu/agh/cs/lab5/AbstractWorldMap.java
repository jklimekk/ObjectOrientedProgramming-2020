package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.MapVisualiser;
import pl.edu.agh.cs.lab7.IPositionChangeObserver;
import pl.edu.agh.cs.lab7.MapBoundary;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected List<Animal> animals = new ArrayList<>();
    protected Map<Vector2d, Animal> animalsHash = new HashMap<>();
    private final MapVisualiser mv = new MapVisualiser(this);
    protected MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.add(animal);
            animalsHash.put(animal.getPosition(), animal);

            animal.addObserver(this);
            animal.addObserver(mapBoundary);
            mapBoundary.addElement(animal);

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

            animal.move(direction);

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

    @Override
    public String toString() {
        return mv.draw(mapBoundary.loverLeftCorner(), mapBoundary.upperRightCorner());
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        animalsHash.remove(oldPosition);
        animalsHash.put(newPosition, (Animal) movedElement);
    }

}
