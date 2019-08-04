/*
 * Copyright 2009 TecAceT Inc.
 * 
 * Software designed and written by Dimitri Papaioannou
 */
package com.tecacet.life;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class BasicLife implements Life, CellListener {

    private static BasicLife INSTANCE = new BasicLife();

    public static Life getALife() {
        return INSTANCE;
    }

    private Map<Coordinate, Cell> population = Collections.synchronizedMap(new HashMap<>());
    private Map<Coordinate, Cell> newborns = new HashMap<>();
    private Set<Coordinate> dead = new HashSet<>();
    private int counter = 0;
    private LawsOfNature laws = GenericLaws.getOrginalLaws();
    private static Logger log = Logger.getLogger(BasicLife.class.getName());

    private BasicLife() {
    }

    public synchronized Cell addCell(Coordinate coord) {
        Cell cell = new Cell(coord);
        cell.addCellListener(this);
        population.put(coord, cell);
        return cell;
    }

    public synchronized Cell removeCell(Coordinate coord) {
        return population.remove(coord);
    }

    public void died(Coordinate coord) {
        log.fine("Cell died at " + coord);
        dead.add(coord);
    }

    public void born(Cell cell) {
        Coordinate coord = cell.getCoordinate();
        log.fine("Cell born at " + coord);
        cell.addCellListener(this);
        newborns.put(coord, cell);
    }

    public boolean hasNewBorn(Coordinate coord) {
        return newborns.containsKey(coord);
    }

    public boolean hasCell(Coordinate coord) {
        return population.containsKey(coord);
    }

    public void done() {
        counter++;
        if (counter == population.size()) {
            counter = 0;
            giveBirths();
            burryDead();
        }
    }

    private void giveBirths() {
        log.fine("There are " + newborns.size() + " newborn cells");
        for (Map.Entry<Coordinate, Cell> entry : newborns.entrySet()) {
            Coordinate c = entry.getKey();
            Cell cell = entry.getValue();
            population.put(c, cell);
        }
        newborns.clear();
    }

    private void burryDead() {
        for (Coordinate c : dead) {
            population.remove(c);
        }
        dead.clear();
    }

    private void lifeGoesOn() {
        log.fine("Starting life iteration");
        for (Coordinate coord : population.keySet()) {
            Cell cell = population.get(coord);
            cell.live();
        }
    }

    public void iterate() {
        lifeGoesOn();
        giveBirths();
        burryDead();
    }

    public void newGame() {
        population.clear();
        dead.clear();
        newborns.clear();
        counter = 0;
    }

    public LawsOfNature getLawsOfNature() {
        return laws;
    }

    public synchronized int getPopulation() {
        return population.size();
    }
}
