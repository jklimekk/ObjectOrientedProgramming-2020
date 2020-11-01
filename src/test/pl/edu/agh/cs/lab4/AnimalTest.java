package pl.edu.agh.cs.lab4;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.*;
import pl.edu.agh.cs.lab3.OptionsParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void calosciowyZTresci() {
        String[] args = new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = new OptionsParser().parse(args);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
                                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD));

        IWorldMap map = new RectangularMap(10, 5);

        // poprawne umieszczenie na mapie
        assertTrue(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map,new Vector2d(3,4))));

        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(3, 5)));
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
    }

    @Test
    void calosciowyZeZlymiDanymi() {
        String[] args = new String[] {"f", "l", "f", "l", "f", "f", "f", "b"};
        List<MoveDirection> directions = new OptionsParser().parse(args);

        // poprawna interpretacja danych wejsciowych
        assertEquals(directions, List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD));

        IWorldMap map = new RectangularMap(3, 3);

        // poprawne umieszczenie na mapie
        assertTrue(map.place(new Animal(map)));
        assertTrue(map.place(new Animal(map,new Vector2d(2,3))));

        // wstawianie poza zakres mapy
        assertFalse(map.place(new Animal(map,new Vector2d(11,3))));
        assertFalse(map.place(new Animal(map,new Vector2d(1,-3))));

        // wstawianie w zajęte już miejsce
        assertFalse(map.place(new Animal(map,new Vector2d(2,3))));

        // zwierzęta będą próbować wchodzić na siebie i wychodzić poza mapę
        map.run(directions);

        // poprawne położenie na koniec
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }
}