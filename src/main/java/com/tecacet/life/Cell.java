/*
 * Copyright 2009 TecAceT Inc.
 * 
 * Software designed and written by Dimitri Papaioannou
 */
package com.tecacet.life;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private Coordinate coordinate;
    private List<CellListener> listeners = new ArrayList<>();
    private Life life;
    private LawsOfNature laws;

    public Cell(Coordinate c) {
        coordinate = c;
        life = BasicLife.getALife();
        laws = life.getLawsOfNature();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void live() {
        spawn();
        persevere();
        broadcastDone();
    }

    public void addCellListener(CellListener l) {
        listeners.add(l);
    }

    private void spawn() {
        for (Coordinate c : coordinate.getNeighbors()) {
            if (life.hasCell(c) || life.hasNewBorn(c)) {
                continue; // no need to give birth to existing cells
            }
            Cell child = new Cell(c);
            if (child.canBeBorn()) {
                broadcastBorn(child);
            }
        }
    }

    private boolean canBeBorn() {
        int neighbors = countNeighbors();
        return laws.canBeBorn(neighbors);
    }

    private void broadcastBorn(Cell cell) {
        for (CellListener l : listeners) {
            l.born(cell);
        }
    }

    private void persevere() {
        if (!survives()) {
            broadcastDied();
        }
    }

    private void broadcastDied() {
        for (CellListener l : listeners) {
            l.died(coordinate);
        }
    }

    private void broadcastDone() {
        for (CellListener l : listeners) {
            l.done();
        }
    }

    private boolean survives() {
        int neighbors = countNeighbors();
        return laws.canSurvive(neighbors);
    }

    private int countNeighbors() {
        int n = 0;
        for (Coordinate c : coordinate.getNeighbors()) {
            if (life.hasCell(c)) {
                n++;
            }
        }
        return n;
    }

    public String toString() {
        return coordinate.toString();
    }
}
