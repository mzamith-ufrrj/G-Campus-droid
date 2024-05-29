package tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges;

import java.util.ArrayList;

import tn743.ufrrj.gcampus.j_g_campus_test.menumain.Function;

public class ChallengeFun {
    private String mName;
    private boolean mOnline;
    public ChallengeFun(String name, boolean online) {
        mName = name;
        mOnline = online;
    }//public Function(String name, boolean online) {
    public String getName() {return mName;}
    public boolean isOnline() {return mOnline;}

    private static int lastFunctionId = 0;

    public static ArrayList<ChallengeFun> createFunctionList() {
        ArrayList<ChallengeFun> options = new ArrayList<ChallengeFun>();
        //options.add(new ChallengeFun("Baixar", false));
        options.add(new ChallengeFun("Forca", true));
        options.add(new ChallengeFun("GPS", false));
        options.add(new ChallengeFun("Pula-Pula", false));

        //functions.add(new Function("Forca", true));


        return options;
    }
}
