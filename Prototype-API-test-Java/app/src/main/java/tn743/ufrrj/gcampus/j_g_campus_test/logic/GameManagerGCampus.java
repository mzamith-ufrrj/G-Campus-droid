package tn743.ufrrj.gcampus.j_g_campus_test.logic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.util.ArrayList;


import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMM;
import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMMRun;
import tn743.ufrrj.gcampus.j_g_campus_test.gui.acti.ChallengePlayActivity;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges.ChallengeFun;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress.FunChallengeProgress;

/*
 - This class is responsible for manager all games, providing file names and ID games
 - This class accesses the server, gets the challenge and informs the file name used for the game
    class saves its state
 */
public class GameManagerGCampus {
    private static final String TAG =  "GameManager";
    private static final String MSGKEYHANGMAN = "FROM-HTTP-THREAD-TO-HANGMAN";
    private static volatile GameManagerGCampus INSTANCE = null;

    private ArrayList<FunChallengeProgress> mListInProgress;
    private Context mContext = null;
    private Handler mHandlerHangman = null;
    private ChallengePlayActivity mChallengePlayActivity = null;
    //private static String mConfigFile = "GameManger.config";
    //private HashMap<String, String> mFilesName = null;
    //private Vector<String> mFilesName = null;

    public static GameManagerGCampus getInstance() {
        // Check if the instance is already created
        if(INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (GameManagerGCampus.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new GameManagerGCampus();
                    INSTANCE.init();
                }
            }
        }

        // return the singleton instance
        return INSTANCE;
    }//public static GameManager getInstance() {


    private void init(){
        mHandlerHangman = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString(MSGKEYHANGMAN);
                //callback
                mChallengePlayActivity.showHangman(string, 0);

            }
        };

    }

    public void setContext(Context c){
        mContext = c;
    }

    public ArrayList<FunChallengeProgress> getChallengesInProgress(){ return mListInProgress; }
    public void loadChallengesInProgress(){
        File dir     = new File(mContext.getFilesDir().toString()) ;
        File[] files = dir.listFiles();
        int index    = 0,
            indexGame = 0;
        mListInProgress = new ArrayList<FunChallengeProgress>();
        //The first type is HANGMAN
        while ((index < files.length)){
            int a = files[index].toString().indexOf("hangman.");
            if (a >= 0){
                int  b = files[index].toString().indexOf(".txt");
                String theme = new String (files[index].toString().substring(a + 8, b).toUpperCase());
                theme = theme.replace(".txt", "");
                b = theme.indexOf(".");
                if (b >= 0){
                    theme = theme.substring(0, b);
                }
                 theme += " <" + Integer.toString(++indexGame) + ">";
                FunChallengeProgress fun = new FunChallengeProgress("Forca",  files[index].toString(), theme, true);
                mListInProgress.add(fun);
            }

            index++;
        }//while ((index < files.length) && (!flag)){
    }



    public void getHangman(ChallengePlayActivity a){
        mChallengePlayActivity = a;

        String url = new String(Configure.getInstance().getMainURL() + Configure.getInstance().getHangmanAPI());
        HttpCOMM http_post = new HttpCOMM();

        http_post.setURL(url);
        http_post.mParams.clear();
        http_post.mParams.put("user", Configure.getInstance().getMD5User());
        http_post.mParams.put("passwd", Configure.getInstance().getMD5Passwd());

        HttpCOMMRun task = new HttpCOMMRun(http_post);
        task.setHandler(mHandlerHangman);
        task.setMsgKey(MSGKEYHANGMAN);
        Thread t = new Thread(task);
        t.start();
    }//public void getHangman(ChallengePlayActivity a){

}//public class GameManager {
