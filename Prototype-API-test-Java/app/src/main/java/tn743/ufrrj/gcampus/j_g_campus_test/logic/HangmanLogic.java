package tn743.ufrrj.gcampus.j_g_campus_test.logic;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
/*
 HangmanLogic has the hangman game logic. Receving n words and the theme
 Each character of each word has a score, 100%. Once player gets a helping,
 the score is reduced in according to the characters used
 */
public class HangmanLogic  implements  Game{
    private static final String TAG = "HangmanLogic";

    private String mWord = null;
    private String mMask = null;

    private boolean mAnswered = false;
    private String mTheme = null;

    private String mGameID = null;

    private boolean mUpdate = false;
    public HangmanLogic(){

        mWord = new String();
        mMask = new String();
        mAnswered = mUpdate = false;
    }//public HangmanLogic(){

    public HangmanLogic(boolean update){
        mWord = new String();
        mMask = new String();
        mAnswered = false;
        mUpdate = update;
    }
    //public int Words() { return mWords.size(); }

    public String getGameID() { return mGameID; }
    public boolean guessAnswer(String s, String callTAG){
        boolean flag = false;
        if (mWord.compareTo(s) == 0){
            if (!mAnswered){
                mAnswered = mUpdate = true;
                mMask = new String (s);
            }
            return mAnswered;
        }//if (mWord.compareTo(s) == 0){
        return false;
    }//public boolean guessAnswer(String s){

    public String getTheme() {return new String(mTheme);}
    public String getWord(){ return new String(mWord); }
    public String getMask(){ return new String(mMask);}

    public void setWords(String msg){
        String[] parts = msg.split("\n");
        int len = parts.length;
        mGameID = new String("/hangman." + parts[0] );
        mTheme = new String(parts[1]);

        mWord = new String(parts[2]);
        mMask = new String("#".repeat(mWord.length()));
        mAnswered = false;
    }//public void getWords(){
    @Override
    public boolean gameConcluded() { return mAnswered; }

    @Override
    public int getScore()  {return mAnswered ? 10 : 0; }

    public boolean recoveryLetters(){


        String mask = "#".repeat(mWord.length());
        char[] cAnswer = mWord.toCharArray();
        char[] inout_answer = mask.toCharArray();
        boolean flag  = BagResources.getInstance().getAndSub(cAnswer, inout_answer);
        mMask = new String(inout_answer);

        if (flag){
            mUpdate = true;
            if (!mAnswered) mAnswered = (mWord.compareTo(mMask) == 0);
        }//if (ret){
        return flag;
    }//public void recoveryLetters(){


    @Override
    public boolean loadState(String filename){
        File file = new File(filename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            mGameID = new String(br.readLine());
            mTheme = new String(br.readLine());
            mWord = new String(br.readLine());
            mMask = new String(br.readLine());

            mAnswered = Boolean.parseBoolean(br.readLine());
        } catch (FileNotFoundException e) {
            Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
            return false;
        } catch (IOException e) {
            Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
            return false;
        }
        return true;
    }

    @Override
    public int saveState(String path){
        if (!mUpdate) return 0;

        String filename = path +  mGameID;
        String text = mGameID + "\n"
                    + mTheme  + "\n"
                    + mWord   + "\n"
                    + mMask   + "\n"
                    + Boolean.toString(mAnswered) + "\n";


        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "save(): " + filename + " is not salved [ERROR]");
            return -1;
        }
        mUpdate = false;
        return 1;
    }//public void save(String path){




}//public class HangmanLogic {
