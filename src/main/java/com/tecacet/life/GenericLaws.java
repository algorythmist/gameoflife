package com.tecacet.life;

public class GenericLaws implements LawsOfNature {

    private static final int DEFAULT_NEIGHBORS = 8;

    public boolean[] survivalLaw;
    public boolean[] birthLaw;

    public static LawsOfNature getOrginalLaws() {
        GenericLaws laws = new GenericLaws(DEFAULT_NEIGHBORS);
        laws.survivalLaw[2] = true;
        laws.survivalLaw[3] = true;
        laws.birthLaw[3] = true;
        return laws;
    }

    private GenericLaws(int neighbors) {
        // 0 to 8 inclusive
        survivalLaw = new boolean[neighbors + 1];
        birthLaw = new boolean[neighbors + 1];
    }

    public boolean canSurvive(int neighbors) {
        return survivalLaw[neighbors];
    }

    public boolean canBeBorn(int neighbors) {
        return birthLaw[neighbors];
    }

}
