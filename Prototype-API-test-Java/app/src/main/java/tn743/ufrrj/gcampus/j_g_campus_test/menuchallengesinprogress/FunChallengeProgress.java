package tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress;

public class FunChallengeProgress {
    private String mChallengeType = null;
    private String mFileName = null;
    private String mTheme = null;

    private boolean mOnline = false;


    public String getChallengeType() {return mChallengeType; }
    public String getFileName() { return mFileName; }
    public String getTheme() { return mTheme; }
    public boolean isOnline() { return mOnline; }



    public FunChallengeProgress(String challengetype, String filename, String theme, boolean online){
        mChallengeType = new String(challengetype);
        mFileName = new String(filename);
        mTheme = new String(theme);
        mOnline = online;
    }


}//public class FunChallengeProgress {
