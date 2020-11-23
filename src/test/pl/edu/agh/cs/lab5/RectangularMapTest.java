package pl.edu.agh.cs.lab5;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab3.OptionsParser;
import pl.edu.agh.cs.lab4.IWorldMap;
import pl.edu.agh.cs.lab4.RectangularMap;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveToPoprawny() {
        IWorldMap map = new RectangularMap(3, 4);
        Vector2d position = new Vector2d(2, 3);

        assertTrue(map.canMoveTo(position));
    }

    @Test
    void canMoveToPozaMape() {
        IWorldMap map = new RectangularMap(3, 4);
        Vector2d position = new Vector2d(5, 1);

        assertFalse(map.canMoveTo(position));
    }

    @Test
    void canMoveToNaZajeteMiejsce() {
        IWorldMap map = new RectangularMap(3, 4);
        map.place(new Animal(map, new Vector2d(1, 1)));
        Vector2d position = new Vector2d(1, 1);

        assertFalse(map.canMoveTo(position));
    }

    @Test
    void placePoprawny() {
        IWorldMap map = new RectangularMap(3, 4);

        assertTrue(map.place(new Animal(map, new Vector2d(2, 3))));
    }

    @Test
    void placePozaMape() {
        IWorldMap map = new RectangularMap(3, 4);

        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(5, 1))));
    }

    @Test
    void placeNaZajeteMiejsce() {
        IWorldMap map = new RectangularMap(3, 4);
        map.place(new Animal(map, new Vector2d(1, 1)));

        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(1, 1))));
    }

    @Test
    void runPoprawny() {
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(2,3)));


        String[] args = new String[] {"r", "l", "f", "l", "r", "f", "f", "f"};
        List<MoveDirection> directions = new OptionsParser().parse(args);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                                            MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD));

        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(3, 1)));
        assertTrue(map.isOccupied(new Vector2d(2, 1)));
    }

    @Test
    void runWchodzenieZwierzatNaSiebie() {
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(2,3)));


        String[] args = new String[] {"f", "l", "f", "l", "f", "f", "f", "f"};
        List<MoveDirection> directions = new OptionsParser().parse(args);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                                            MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD));

        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void runWychodzeniePozaMape() {
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(2,3)));


        String[] args = new String[] {"r", "l", "f", "l", "f", "b", "f", "b"};
        List<MoveDirection> directions = new OptionsParser().parse(args);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                                          MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD));

        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(3, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void runZleDaneWejsciowe() {
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(2,3)));


        String[] args1 = new String[] {"f", "A", "l", "f", "l", "F", "f", "f", "f", "f", "Ala ma kota"};

        assertThrows(IllegalArgumentException.class, () -> {
            List<MoveDirection> badDirections = new OptionsParser().parse(args1);
        });

        String[] args2 = new String[] {"f", "l", "f", "l", "f", "f", "f", "f"};
        List<MoveDirection> directions = new OptionsParser().parse(args2);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD));

        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedTak() {
        IWorldMap map = new RectangularMap(3, 4);
        map.place(new Animal(map, new Vector2d(1, 1)));

        assertTrue(map.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void isOccupiedNie() {
        IWorldMap map = new RectangularMap(3, 4);
        map.place(new Animal(map, new Vector2d(1, 1)));

        assertFalse(map.isOccupied(new Vector2d(1, 2)));
    }

    @Test
    void objectAtZajeteMiejsce() {
        IWorldMap map = new RectangularMap(3, 4);
        Animal animal = new Animal(map, new Vector2d(1, 1));
        map.place(animal);

        assertEquals(Optional.of(animal), map.objectAt(new Vector2d(1, 1)));
    }

    @Test
    void objectAtPusteMiejsce() {
        IWorldMap map = new RectangularMap(3, 4);
        Animal animal = new Animal(map, new Vector2d(1, 1));
        map.place(animal);

        assertEquals(Optional.empty(), map.objectAt(new Vector2d(1, 2)));
    }
}