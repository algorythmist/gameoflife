package com.tecacet.life;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GenericLawsTest {

	@Test
	public void testGetOrginalLaws() {

		LawsOfNature laws = GenericLaws.getOrginalLaws();
		assertFalse(laws.canBeBorn(0));
		assertFalse(laws.canBeBorn(1));
		assertFalse(laws.canBeBorn(2));
		assertTrue(laws.canBeBorn(3));
		assertFalse(laws.canBeBorn(4));
		
		assertFalse(laws.canSurvive(0));
		assertFalse(laws.canSurvive(1));
		assertTrue(laws.canSurvive(2));
		assertTrue(laws.canSurvive(3));
		assertFalse(laws.canSurvive(4));
	}

}
