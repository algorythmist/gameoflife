/*
 * Copyright 2009 TecAceT Inc.
 * 
 * Software designed and written by Dimitri Papaioannou
 */
package com.tecacet.life;

/**
 * Facade that controls the board of the game of life
 * 
 * @author Dimitri Papaioannou
 * 
 */
public interface Life {

    /**
     * Queries if there is a newborn in the location
     * 
     * @param coord
     * @return
     */
    boolean hasNewBorn(Coordinate coord);

    /**
     * Queries if there is a live cell in the location
     * 
     * @param c
     * @return
     */
    boolean hasCell(Coordinate c);
    
    void iterate();

    /**
     * Add a cell at the coordinate location
     * @param c
     * @return
     */
    Cell addCell(Coordinate c);

    /**
     * Remove an existing cell from the coordinate location
     * @param c
     * @return
     */
    Cell removeCell(Coordinate c);

    /**
     * Start a new game
     */
    void newGame();

    /**
     * Get the laws that govern this game of life
     * @return
     */
    LawsOfNature getLawsOfNature();

    /**
     * Get the current population on the board.
     * 
     * @return
     */
    int getPopulation();

}
