package pl.edu.agh.cs.lab5;

import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private List<Grass> grasses = new ArrayList<>();

    private static final Random generator = new Random();

    public GrassField(int number) {
        generateGrass(number);
    }

    private void generateGrass(int number){
        Vector2d v;

        while(grasses.size() < number){
            v = new Vector2d(generator.nextInt((int) sqrt(number*10))+1, generator.nextInt((int) sqrt(number*10))+1);

            Grass grass = new Grass(v);
            grasses.add(grass);

            HashSet<Grass> set = new HashSet<>(grasses);
            grasses = new ArrayList<>(set);
        }
    }

    @Override
    public Vector2d[] corners(){
        Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        Vector2d position;

        for(Animal animal : animals) {
            position = animal.getPosition();
            lowerLeftCorner = position.lowerLeft(lowerLeftCorner);
            upperRightCorner = position.upperRight(upperRightCorner);
        }
        for(Grass grass : grasses) {
            position = grass.getPosition();
            lowerLeftCorner = position.lowerLeft(lowerLeftCorner);
            upperRightCorner = position.upperRight(upperRightCorner);
        }

        return new Vector2d[] {lowerLeftCorner, upperRightCorner};
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(super.isOccupied(position));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        boolean result = super.isOccupied(position);

        for(Grass grass : grasses)
            if(grass.getPosition().equals(position))
                return true;

        return result;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        Object object = super.objectAt(position);

        for(Grass grass : grasses)
            if(grass.getPosition().equals(position) && object.equals(Optional.empty()))
                return Optional.of(grass);

        return super.objectAt(position);
    }
}
