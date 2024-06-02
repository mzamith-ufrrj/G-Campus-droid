package tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress;

public class FunChallengeProgress {
    private String mChallengeType = null;
    private int mLogic = -1;
    private String mTheme = null;

    private boolean mOnline = false;


    public String getChallengeType() {return mChallengeType; }
    public int getGameLogic() { return mLogic; }
    public String getTheme() { return mTheme; }
    public boolean isOnline() { return mOnline; }



    public FunChallengeProgress(String challengetype,
                                String theme,
                                int index, boolean online){
        mChallengeType = new String(challengetype);
        mLogic = index;
        mTheme = new String(theme);
        mOnline = online;
    }


}//public class FunChallengeProgress {
