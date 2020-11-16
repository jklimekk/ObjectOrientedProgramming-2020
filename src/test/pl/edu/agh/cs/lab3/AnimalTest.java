package pl.edu.agh.cs.lab3;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab4.RectangularMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void moveRight() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.RIGHT;

        // when
        zwierze.move(direction);

        // then
        assertEquals(MapDirection.EAST, zwierze.getOrientation());
    }

    @Test
    void moveLeft() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.LEFT;

        // when
        zwierze.move(direction);

        // then
        assertEquals(MapDirection.WEST, zwierze.getOrientation());
    }

    @Test
    void moveForward() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.FORWARD;

        // when
        zwierze.move(direction);

        // then
        assertEquals(new Vector2d(2, 3), zwierze.getPosition());
    }

    @Test
    void moveForwardTooFar() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.FORWARD;

        // when
        zwierze.move(direction);
        zwierze.move(direction);
        zwierze.move(direction);

        // then
        assertEquals(new Vector2d(2, 4), zwierze.getPosition());
    }

    @Test
    void moveBackward() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.BACKWARD;

        // when
        zwierze.move(direction);

        // then
        assertEquals(new Vector2d(2, 1), zwierze.getPosition());
    }

    @Test
    void moveBackwardTooFar() {
        // given
        Animal zwierze = new Animal(new RectangularMap(4,4));
        MoveDirection direction = MoveDirection.BACKWARD;

        // when
        zwierze.move(direction);
        zwierze.move(direction);
        zwierze.move(direction);

        // then
        assertEquals(new Vector2d(2, 0), zwierze.getPosition());
    }

    @Test
    void calosciowyZeZlymiDanymi() {
        // błędne ciagi znaków + wychodzenie poza tablice
        Animal zwierze = new Animal(new RectangularMap(4,4));
        String[] args = new String[] {"f", "l", "a", "b", "b", "r", "r", "f", "g", "f"};

        OptionsParser parser = new OptionsParser();
        List<MoveDirection> ruchy = parser.parse(args);

        // czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane
        assertEquals(ruchy, List.of(MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                                    MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD));

        List<MapDirection> orientacja = List.of(MapDirection.NORTH, MapDirection.WEST, MapDirection.WEST, MapDirection.WEST,
                                                MapDirection.NORTH, MapDirection.EAST, MapDirection.EAST, MapDirection.EAST);
        List<Vector2d> polozenie = List.of(new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(3, 3), new Vector2d(4, 3),
                                            new Vector2d(4, 3), new Vector2d(4, 3), new Vector2d(4, 3), new Vector2d(4, 3));
        int i = 0;
        for( MoveDirection ruch : ruchy) {
            zwierze.move(ruch);

            // czy zwierzę ma właściwą orientację
            assertEquals(orientacja.get(i), zwierze.getOrientation());

            //czy zwierzę przemieszcza się na właściwe pozycje
            assertEquals(polozenie.get(i), zwierze.getPosition());

            //czy zwierzę nie wychodzi poza mapę
            assertTrue(zwierze.getPosition().precedes(new Vector2d(4, 4)));
            assertTrue(zwierze.getPosition().follows(new Vector2d(0, 0)));

            i++;
        }
    }

    @Test
    void calosciowyDlaDobrychDanych() {
        Animal zwierze = new Animal(new RectangularMap(4,4));
        String[] args = new String[] {"l", "f", "r", "b", "r", "f", "f", "r"};

        OptionsParser parser = new OptionsParser();
        List<MoveDirection> ruchy = parser.parse(args);

        // czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane
        assertEquals(ruchy, List.of(MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD,
                                    MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT));

        List<MapDirection> orientacja = List.of(MapDirection.WEST, MapDirection.WEST, MapDirection.NORTH, MapDirection.NORTH,
                                                MapDirection.EAST, MapDirection.EAST, MapDirection.EAST, MapDirection.SOUTH);
        List<Vector2d> polozenie = List.of(new Vector2d(2, 2), new Vector2d(1, 2), new Vector2d(1, 2), new Vector2d(1, 1),
                                            new Vector2d(1, 1), new Vector2d(2, 1), new Vector2d(3, 1), new Vector2d(3, 1));
        int i = 0;
        for( MoveDirection ruch : ruchy) {
            zwierze.move(ruch);

            // czy zwierzę ma właściwą orientację
            assertEquals(orientacja.get(i), zwierze.getOrientation());

            //czy zwierzę przemieszcza się na właściwe pozycje
            assertEquals(polozenie.get(i), zwierze.getPosition());

            //czy zwierzę nie wychodzi poza mapę
            assertTrue(zwierze.getPosition().precedes(new Vector2d(4, 4)));
            assertTrue(zwierze.getPosition().follows(new Vector2d(0, 0)));

            i++;
        }
    }

}