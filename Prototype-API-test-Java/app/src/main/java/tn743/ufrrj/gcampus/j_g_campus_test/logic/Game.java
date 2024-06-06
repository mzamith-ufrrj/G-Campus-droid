package tn743.ufrrj.gcampus.j_g_campus_test.logic;

public interface Game {


    boolean gameConcluded();
    int getScore();

    int saveState(String path);
    boolean loadState(String filename);
}
