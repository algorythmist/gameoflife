/*
 * Copyright 2009 TecAceT Inc.
 * 
 * Software designed and written by Dimitri Papaioannou
 */
package com.tecacet.life;

/**
 * Describes the laws that govern the game of life.
 * 
 * @author Dimitri Papaioannou
 *
 */
public interface LawsOfNature {

    /**
     * Indicates whether a cell having a certain number of neighbors can survice.
     * 
     * @param neighbors 
     * @return
     */
    boolean canSurvive(int neighbors);

    /**
     * Indicates whether a new cell can be born in an empty location with a certain number of neighbors.
     * 
     * @param neighbors
     * @return
     */
    boolean canBeBorn(int neighbors);

}
