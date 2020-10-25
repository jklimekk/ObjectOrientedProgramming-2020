package pl.edu.agh.cs.lab3;

import pl.edu.agh.cs.lab2.*;

public class Animal {
    private MapDirection orientacja;
    private Vector2d polozenie;

    public MapDirection getOrientacja() {
        return orientacja;
    }

    public void setOrientacja(MapDirection orientacja) {
        this.orientacja = orientacja;
    }

    public Vector2d getPolozenie() {
        return polozenie;
    }

    public void setPolozenie(Vector2d polozenie) {
        this.polozenie = polozenie;
    }

    public static final Vector2d zera = new Vector2d(0,0);
    public static final Vector2d czworki = new Vector2d(4,4);

    public Animal() {
        this.orientacja = MapDirection.NORTH;
        this.polozenie = new Vector2d(2,2);
    }

    @Override

    public String toString() {
        return "Animal{" +
                "orientacja=" + orientacja +
                ", polozenie=" + polozenie +
                '}';
    }

    private void miescisie (MoveDirection direction) {

        if(!(this.polozenie.precedes(czworki)) || !(this.polozenie.follows(zera))){
            if(direction == MoveDirection.FORWARD)
                this.polozenie = this.polozenie.subtract(this.orientacja.toUnitVector());
            else
                this.polozenie = this.polozenie.add(this.orientacja.toUnitVector());
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientacja = this.orientacja.next();
            case LEFT -> this.orientacja = this.orientacja.previous();
            case FORWARD -> {
                this.polozenie = this.polozenie.add(this.orientacja.toUnitVector());
                miescisie(direction);
            }
            case BACKWARD -> {
                this.polozenie = this.polozenie.subtract(this.orientacja.toUnitVector());
                miescisie(direction);
            }
        }
    }
}
