package tn743.ufrrj.gcampus.j_g_campus_test.logic;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tn743.ufrrj.gcampus.j_g_campus_test.R;

public class Configure {
    private static final String TAG =  "Configure";
    private static volatile Configure INSTANCE = null;

    private Context mContext = null;

    private String mMainURL = null;
    private String mUser = null;
    private String mPass = null;

    private String mMD5User = null;
    private String mMD5Pass = null;

    public static Configure getInstance() {
        // Check if the instance is already created
        if(INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (Configure.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new Configure();
                    INSTANCE.init();
                }
            }
        }

        // return the singleton instance
        return INSTANCE;
    }//public static GameManager getInstance() {

    void Configure(){
        //Default value

    }

    private void init (){
        mMainURL = new String("http://192.168.1.20/~r-droid/");
        mUser = new String("r-droid");
        mPass = new String("r-droid");
        setMD5();
    }
    private void setMD5(){
        mMD5User = TOMD5(mUser);
        mMD5Pass = TOMD5(mPass);
    }

    public void setContext(Context c){
        mContext  = c;
    }

    public String getHangmanAPI(){ return mContext.getString(R.string.hangman_page); }

    public String getMainURL(){ return mMainURL; }

    public void setMainURL(String m){ mMainURL = m;}
    public String getUser(){ return mUser; }
    public String getPasswd(){ return mPass; }
    public String getMD5User(){ return mMD5User; }
    public String getMD5Passwd(){ return mMD5Pass; }

    public void setUserPass(String u, String p){

        mUser = new String(u);
        mPass = new String(p);
        setMD5();

    }
    public void saveCondig(){
        String text =  "URL=" + mMainURL + "\n"
                + "USER="+mUser +"\n"
                +"PASSWD="+mPass+"\n";
        String filename = mContext.getFilesDir() + "/config.ini";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "saveCondig(): " + filename + " is not salved [ERROR]");
        }

    }//private void saveCondig(){
    public void loadConfig() {
        File file = new File(mContext.getFilesDir(), "config.ini");
        if (!file.exists()) {
            saveCondig();
        } else {//if (! file.exists()){
            FileReader fr = null;
            try {
                fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    String[] token = line.split("=");
                    String param = token[0].trim();
                    if (param.compareTo("URL") == 0) {
                        mMainURL = new String(token[1].trim());
                    } else if (param.compareTo("USER") == 0) {
                        mUser = new String(token[1].trim());
                    } else if (param.compareTo("PASSWD") == 0) {
                        mPass = new String(token[1].trim());
                    }
                }// while((line = br.readLine()) != null){
                setMD5();

            } catch (FileNotFoundException e) {
                Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
            } catch (IOException e) {
                Log.e(TAG, "loadConfig():  is not loaded [ERROR]");
            }

        }////if (! file.exists()){

    }// private void loadConfig() {
    private String TOMD5(String s) {
        MessageDigest m = null;
        String ret = new String("");
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            ret = new String(new BigInteger(1,m.digest()).toString(16));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }//private String TOMD5(String s)
}//public class Configure {
