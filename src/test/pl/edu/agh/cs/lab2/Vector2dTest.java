package pl.edu.agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        // given
        Vector2d v = new Vector2d(1, 2);

        // when
        String str = v.toString();

        // then
        assertEquals("(1,2)", str);
    }

    @Test
    void precedes() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 2);
        Vector2d w = new Vector2d(1, 1);

        // when
        boolean result1 = v.precedes(u);
        boolean result2 = v.precedes(w);

        // then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void follows() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 2);
        Vector2d w = new Vector2d(1, 1);

        // when
        boolean result1 = v.follows(u);
        boolean result2 = v.follows(w);

        // then
        assertFalse(result1);
        assertTrue(result2);
    }

    @Test
    void upperRight() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 1);

        // when
        Vector2d w = v.upperRight(u);

        // then
        assertEquals(new Vector2d(2, 2), w);
    }

    @Test
    void lowerLeft() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 1);

        // when
        Vector2d w = v.lowerLeft(u);

        // then
        assertEquals(new Vector2d(1, 1), w);
    }

    @Test
    void add() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 1);

        // when
        Vector2d w = v.add(u);

        // then
        assertEquals(new Vector2d(3, 3), w);
    }

    @Test
    void subtract() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(2, 1);

        // when
        Vector2d w = v.subtract(u);

        // then
        assertEquals(new Vector2d(-1, 1), w);
    }

    @Test
    void testEquals() {
        // given
        Vector2d v = new Vector2d(1, 2);
        Vector2d u = new Vector2d(1, 2);
        Vector2d w = new Vector2d(2, 2);

        // when
        boolean result1 = v.equals(u);
        boolean result2 = v.equals(w);

        // then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void opposite() {
        // given
        Vector2d v = new Vector2d(1, 2);

        // when
        Vector2d u = v.opposite();

        // then
        assertEquals(new Vector2d(-1, -2), u);

    }
}