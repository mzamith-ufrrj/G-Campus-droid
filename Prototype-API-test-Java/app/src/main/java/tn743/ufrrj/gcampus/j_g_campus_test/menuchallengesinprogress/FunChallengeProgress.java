package tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress;

public class FunChallengeProgress {
    private String mChallengeType = null;
    private int mIndex = -1;
    private String mTheme = null;

    private String mGameID = null;

    private boolean mOnline = false;



    public String getChallengeType() {return mChallengeType; }
    public int getIndex() {
        return mIndex;
    }
    public String getTheme() { return mTheme; }
    public boolean isOnline() { return mOnline; }

    public String getGameID() {return mGameID;}

    public FunChallengeProgress(String challengetype,
                                String gameID,
                                String theme,
                                int index, boolean online){
        mChallengeType = new String(challengetype);
        mGameID = new String(gameID);
        mIndex = index;
        mTheme = new String(theme);
        mOnline = online;
    }


}//public class FunChallengeProgress {
