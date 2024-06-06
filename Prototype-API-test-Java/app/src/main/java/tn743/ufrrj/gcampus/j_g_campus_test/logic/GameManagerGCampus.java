package tn743.ufrrj.gcampus.j_g_campus_test.logic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;


import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMM;
import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMMRun;
import tn743.ufrrj.gcampus.j_g_campus_test.gui.acti.ChallengePlayActivity;
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
    private Context mContext;
    private Handler mHandlerHangman = null;
    private ChallengePlayActivity mChallengePlayActivity = null;

    private Vector<HangmanLogic> mHangsmanInProgress = null,
                                 mHangsmanConcluded = null;
    private Vector<String> mFilesGames = null; /* struct with the file name of each game in progress or finalized */
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

    public void init(){
        mHandlerHangman = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString(MSGKEYHANGMAN);
                //callback
                HangmanLogic  hl = new HangmanLogic(true);
                hl.setWords(string);
                if (!findInVector(hl.getGameID())){
                    mFilesGames.add(hl.getGameID());
                    hl.saveState(mContext.getFilesDir().toString());
                    saveFileStructure();
                    mHangsmanInProgress.add(hl);
                    mChallengePlayActivity.showHangman(0, -1);
                }else {
                    Toast.makeText(mContext, "Desafio já baixado!", Toast.LENGTH_SHORT).show();
                    mChallengePlayActivity.onBackPressed();
                }
            }
        };

        if (mHangsmanInProgress == null)  mHangsmanInProgress = new Vector<HangmanLogic>();
        if (mHangsmanConcluded == null)  mHangsmanConcluded = new Vector<HangmanLogic>();
        if (mFilesGames == null) mFilesGames = new Vector<String>();
    }

    private boolean findInVector(String sin){
        boolean found = false;
        int index = 0;
        String s = new String("");
        while ((index < mFilesGames.size()) && (!found)){
            s  = mFilesGames.get(index++);
            found = (s.compareTo(sin) == 0);

        }
        return found;
    }
    public void setContext(Context c){
        mContext = c;
        loadFileStructure();
    }


    public HangmanLogic getLastHangman() { return mHangsmanInProgress.get(mHangsmanInProgress.size()-1); }

    public HangmanLogic getHangmanInProgress(int index) { return mHangsmanInProgress.get(index); }

    private void loadFileStructure(){
        String filename = mContext.getFilesDir() + "/games-file-list.txt";

        File file = new File(filename);
        if (!file.exists()) return;

        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            mFilesGames.clear();
            while ((line = br.readLine()) != null) {
                mFilesGames.add(new String(line));
            }// while((line = br.readLine()) != null){


        } catch (FileNotFoundException e) {
            Log.e(TAG, "loadFileStructure():  is not loaded [ERROR]");
        } catch (IOException e) {
            Log.e(TAG, "loadFileStructure():  is not loaded [ERROR]");
        }

    }//private void loadFileStructure(){
    private void saveFileStructure(){
        String filename = mContext.getFilesDir() + "/games-file-list.txt";

        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < mFilesGames.size(); i++){
                String text = mFilesGames.get(i) + "\n";
                writer.write(text);
            }

            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "saveFileStructure(): " + filename + " is not salved [ERROR]");

        }
    }//private void saveFileStructure(){
    public void setChallengesInProgress(){
        for (int i = 0; i < mHangsmanInProgress.size(); i++){
            HangmanLogic hl = mHangsmanInProgress.get(i);
            int ret = hl.saveState(mContext.getFilesDir().toString());
            String msg = new String("Não definido");
            switch (ret){
                case -1: msg = new String ("ERROR AO GRAVAR O ARQUIVO!");break;
                case  0: msg = new String ("ARQUIVO NÃO ATUALIZADO");break;
                case 1: msg = new String ("ARQUIVO SALVO COM SUCESSO!");break;
            }
           Toast.makeText(mContext,  msg, Toast.LENGTH_SHORT).show();
        }
    }//public void setChallengesInProgress(){

    public ArrayList<FunChallengeProgress> getChallengesInProgress(){
        loadGames();
        loadChallengesInProgress();
        return mListInProgress;
    }
    private void loadChallengesInProgress(){
        mListInProgress = null;
        mListInProgress = new ArrayList<FunChallengeProgress>();
        for (int i = 0; i < mHangsmanInProgress.size(); i++){
            HangmanLogic hl = mHangsmanInProgress.get(i);
            String theme = hl.getTheme();
            String gameid = hl.getGameID();
            FunChallengeProgress fun = new FunChallengeProgress("Forca",
                    gameid,
                    theme,
                    i,
                    true);
            mListInProgress.add(fun);
        }

    }//private void loadChallengesInProgress(){

    public void loadGames(){
        File dir     = new File(mContext.getFilesDir().toString()) ;

        //The first type is HANGMAN
        mHangsmanInProgress.clear();
        mHangsmanConcluded.clear();
        for (int i = 0; i < mFilesGames.size(); i++){
            String filename = mFilesGames.get(i);

            if ((filename.toString().indexOf("hangman.") >= 0)){
                HangmanLogic hl = new HangmanLogic();
                hl.loadState( dir + filename);
                if (!hl.gameConcluded())
                    mHangsmanInProgress.add(hl);
                else
                    mHangsmanConcluded.add(hl);
            }
        }//for (int i = 0; i < mFilesGames.size(); i++){

    }//private void init(){



    public void getHangman(ChallengePlayActivity a){ //Hangman from www - new game
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
