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

    private Vector<String> mWords = null;
    private Vector<String> mMask = null;
    private boolean[] mAnswered = null;
    private String mTheme = null;

    private String mGameID = null;
    private boolean mGameOff = false;
    public HangmanLogic(){

        mWords = new Vector<String>();
        mMask = new Vector<String>();
    }//public HangmanLogic(){

    public int Words() { return mWords.size(); }

    public boolean guessAnswer(String s){
        int indexL = 0;
        boolean flag = false;
        while ((indexL < mWords.size()) && (!flag)){
            String word = mWords.get(indexL);
            flag = (word.compareTo(s) == 0);
            indexL++;
        }
        if (flag){
            indexL--;
            if (!mAnswered[indexL]){
                mMask.set(indexL, new String(s) );
                mAnswered[indexL] = true;
                return true;
            }//if (!mAnswered[indexL]){

        }//if (flag){


        return false;
    }//public boolean guessAnswer(String s){

    public String getTheme() {return new String(mTheme);}
    public String getWord(int index){
        if ((index < 0) || (index >= mWords.size())) return null;
        return new String(mWords.get(index));
    }

    public String getMask(int index){
        if ((index  < 0) || (index >= mMask.size())) return null;
        return new String(mMask.get(index));
    }
    public void getWords(String msg){
        String[] parts = msg.split("\n");
        int len = parts.length;
        mGameID = new String(parts[0]);
        mTheme = new String(parts[1]);

        mWords.clear();
        for (int i = 2; i < len; i++){
            mWords.add(new String(parts[i]));
        }



        mAnswered = new boolean[mWords.size()];
        mMask.clear();
        for (int i = 0; i < mWords.size(); i++){
            String word = mWords.get(i);
            String mask = "#".repeat(word.length());
            mMask.add(new String(mask));
            mAnswered[i] = false;
        }



        //7+7+6=20
    }//public void getWords(){
    @Override
    public boolean gameoff() { return mGameOff; }

    @Override
    public int getScore() {
        int score = 0;
        for (int i = 0; i < mAnswered.length; i++){
            if (mAnswered[i]) score++;
        }
        return score;
    }

    public boolean recoveryLetters(){
        assert mWords.size() == mMask.size():"Erro between Word and Mask";
        boolean ret = false;
        for (int i = 0; i < mWords.size(); i++){
            String word = mWords.get(i);
            String mask = "#".repeat(word.length());
            char[] cAnswer = word.toCharArray();
            char[] inout_answer = mask.toCharArray();
            boolean flag  = BagResources.getInstance().getAndSub(cAnswer, inout_answer);
            if (flag) ret = flag;
            mask = new String(inout_answer);
            mMask.set(i, mask);
        }//for (int i = 0; i < mWords.size()){

        if (ret){
            for (int i = 0; i < mWords.size(); i++) {
                if (!mAnswered[i]){
                    String word = mWords.get(i);
                    String mask = mMask.get(i);
                    mAnswered[i] = (word.compareTo(mask) == 0);
                }

            }// for (int i = 0; i < mWords.size(); i++) {
        }//if (ret){
        return ret;
    }//public void recoveryLetters(){

    public void load (String filename){
        File file = new File(filename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            mGameID = br.readLine();
            mTheme = br.readLine();
            String line;
            mWords.clear();
            int state = 0, index = 0;
            while ((line = br.readLine()) != null) {
                int i = -1;

                if (state == 0) {
                    i = line.indexOf("MASK");
                    if (i == -1)
                        mWords.add(new String(line));

                }else if (state == 1){
                    i = line.indexOf("ANSWER");
                    if (i == -1)
                        mMask.add(new String (line));
                    else
                        mAnswered = new boolean[mWords.size()];
                }
                else if (state == 2){

                    mAnswered[index++] = Boolean.parseBoolean(line);
                }

                if (i != -1)state++;
                /*
                String[] token = line.split("=");
                String param = token[0].trim();
                if (param.compareTo("URL") == 0) {
                    mMainURL = new String(token[1].trim());
                } else if (param.compareTo("USER") == 0) {
                    mUser = new String(token[1].trim());
                } else if (param.compareTo("PASSWD") == 0) {
                    mPass = new String(token[1].trim());
                }

                 */
            }// while((line = br.readLine()) != null){


        } catch (FileNotFoundException e) {
            Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
        } catch (IOException e) {
            Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
        }

    }
    public void save(String path){
        String filename = path + "/hangman." + mGameID;
        String text = mGameID + "\n"
                    + mTheme  + "\n";
        for (int i = 0; i < mWords.size(); i++) {
            String word = mWords.get(i);
            text += word + "\n";
        }//for (int i = 0; i < mWords.size(); i++) {

        text += "MASK\n";
        for (int i = 0; i < mMask.size(); i++) {
            String word = mMask.get(i);
            text += word + "\n";
        }//for (int i = 0; i < mWords.size(); i++) {

        text += "ANSWER\n";
        for (int i = 0; i < mAnswered.length; i++) {
            String word = Boolean.toString(mAnswered[i]);
            text += word + "\n";
        }//for (int i = 0; i < mWords.size(); i++) {

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "save(): " + filename + " is not salved [ERROR]");
        }

        GameManagerGCampus.getInstance().loadChallengesInProgress();
    }//public void save(String path){


}//public class HangmanLogic {
