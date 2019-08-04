/*
 * Copyright 2009 TecAceT Inc.
 * 
 * Software designed and written by Dimitri Papaioannou
 */
package com.tecacet.life;

import java.util.ArrayList;
import java.util.List;

/**
 * A location on the 2-dimensional world of the Game of Life
 * 
 * @author Dimitri Papaioannou
 *
 */
public class Coordinate {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinate> getNeighbors() {
        List<Coordinate> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                neighbors.add(new Coordinate(x + i, y + j));
            }
        }
        return neighbors;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Coordinate c = (Coordinate) o;
        return (x == c.x && y == c.y);
    }

    public int hashCode() {
        return new Integer(134791 * y + x).hashCode();
    }

    public String toString() {
        return String.format("(%d,%d)",x,y);
    }
}
