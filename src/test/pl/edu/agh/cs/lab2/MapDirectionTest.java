package pl.edu.agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        // given
        MapDirection pn = MapDirection.NORTH;
        MapDirection pd = MapDirection.SOUTH;
        MapDirection wsch = MapDirection.EAST;
        MapDirection zach = MapDirection.WEST;

        // when
        MapDirection pnNext = pn.next();
        MapDirection pdNext = pd.next();
        MapDirection wschNext = wsch.next();
        MapDirection zachNext = zach.next();

        // then
        assertEquals(MapDirection.EAST, pnNext);
        assertEquals(MapDirection.WEST, pdNext);
        assertEquals(MapDirection.SOUTH, wschNext);
        assertEquals(MapDirection.NORTH, zachNext);
    }

    @Test
    void previous() {
        // given
        MapDirection pn = MapDirection.NORTH;
        MapDirection pd = MapDirection.SOUTH;
        MapDirection wsch = MapDirection.EAST;
        MapDirection zach = MapDirection.WEST;

        // when
        MapDirection pnPrev = pn.previous();
        MapDirection pdPrev = pd.previous();
        MapDirection wschPrev = wsch.previous();
        MapDirection zachPrev = zach.previous();

        // then
        assertEquals(MapDirection.WEST, pnPrev);
        assertEquals(MapDirection.EAST, pdPrev);
        assertEquals(MapDirection.NORTH, wschPrev);
        assertEquals(MapDirection.SOUTH, zachPrev);
    }
}