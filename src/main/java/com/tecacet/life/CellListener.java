package com.tecacet.life;

public interface CellListener {
	
	/**
	 * A cell notifies Life that it died at a partuclar place.
	 * @param coord the location where the cell died
	 */
	void died(Coordinate coord);
	
	/**
	 * A cell notifies Life that it was born in a particular location.
	 * 
	 * @param cell the newborn cell
	 */
	void born(Cell cell);
	

	/**
	 * A cell notifies Life that it has completed it's lifeGoesOn iteration.
	 *
	 */
	void done();
}
