package com.tecacet.life;

import org.junit.Test;


public class LifeImplTest {

	@Test
	public void testIterate() {
		Coordinate c1 = new Coordinate(0,0);
		Coordinate c2 = new Coordinate(0,1);
		
		Life life = BasicLife.getALife();
		life.addCell(c1);
		life.addCell(c2);
		life.iterate();
	}

}
