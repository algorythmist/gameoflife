package com.tecacet.life;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class CellTest {

	@Test
    public void testCountNeighbors() throws Exception {
        Life life = BasicLife.getALife();

        Cell c1 = life.addCell(new Coordinate(0, 0));
        Cell c2 = life.addCell(new Coordinate(0, 1));
        Cell newborn = new Cell(new Coordinate(1, 1));
        Integer n = (Integer) invoke(newborn, "countNeighbors", null);
        assertEquals(2, n.intValue());

    }

	@Test
    public void testSurvives() throws Exception {
        Coordinate coord = new Coordinate(0, 0);
        Life life = BasicLife.getALife();

        Cell cell = life.addCell(coord);
        boolean b = ((Boolean) invoke(cell, "survives", null)).booleanValue();
        assertFalse(b);
        life.addCell(coord.getNeighbors().get(0));
        life.addCell(coord.getNeighbors().get(3));
        assertTrue(((Boolean) invoke(cell, "survives", null)).booleanValue());
    }

	@Test
    public void testIsBorn() throws Exception {
        Life life = BasicLife.getALife();
        Cell c1 = life.addCell(new Coordinate(0, 0));
        Cell c2 = life.addCell(new Coordinate(0, 1));
        Cell newborn = new Cell(new Coordinate(1, 1));
        assertFalse(((Boolean) invoke(newborn, "canBeBorn", null)).booleanValue());
        Cell c3 = life.addCell(new Coordinate(0, 2));
        assertTrue(((Boolean) invoke(newborn, "canBeBorn", null)).booleanValue());
    }

    private static Object invoke(Object object, String methodName, Object[] params) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class clazz = object.getClass();
        Method method = clazz.getDeclaredMethod(methodName, null);
        method.setAccessible(true);
        return method.invoke(object, params);
    }
}
