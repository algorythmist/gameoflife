package com.tecacet.life;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

	@Test
    public void testHashCode() {
        Coordinate c = new Coordinate(0, 0);
        assertEquals(0, c.hashCode());
    }

	@Test
    public void testGetNeighborsIterator() {
        Coordinate c = new Coordinate(0, 0);
        Iterator i = c.getNeighbors().iterator();
        assertEquals(new Coordinate(-1, -1), i.next());
        assertEquals(new Coordinate(-1, 0), i.next());
        assertEquals(new Coordinate(-1, 1), i.next());
        assertEquals(new Coordinate(0, -1), i.next());
        assertEquals(new Coordinate(0, 1), i.next());
        assertEquals(new Coordinate(1, -1), i.next());
        assertEquals(new Coordinate(1, 0), i.next());
        assertEquals(new Coordinate(1, 1), i.next());
        assertFalse(i.hasNext());
    }

	@Test
    public void testGetNeighbors() {
        Coordinate c = new Coordinate(0, 0);
        List<Coordinate> neighbors = c.getNeighbors();
        assertEquals(8, neighbors.size());
    }

	@Test
    public void testEqualsObject() {
        Coordinate c1 = new Coordinate(1, 3);
        Coordinate c2 = new Coordinate(2, 3);
        Coordinate c3 = new Coordinate(1, 3);
        assertEquals(c1, c3);
        assertEquals(c1, c3);
        assertNotEquals(c1, c2);
    }

}
