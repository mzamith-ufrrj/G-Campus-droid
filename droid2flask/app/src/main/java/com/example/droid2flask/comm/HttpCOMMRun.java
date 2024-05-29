package com.example.droid2flask.comm;

import android.util.Log;

import java.io.IOException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
public class HttpCOMMRun implements Runnable {
    private final String TAG = "HttpCOMMRun";
    private HttpCOMM mHttp_post = null;
    private Handler mHandler = null;
    private String mMsgKey = null;
    private Bundle mBundle = null;
    public HttpCOMMRun(HttpCOMM http_post){
        mHttp_post = http_post;
    }

    public void setHandler(Handler h){ mHandler = h; }
    public void setMsgKey(String s){ mMsgKey = new String(s);}



        @Override
    public void run() {
        try {
            mHttp_post.sendPOST();
        } catch (IOException e) {
            Log.d(TAG, e.toString());
        }

        String s = mHttp_post.getBuffer();
        if (s != null){
            s = s.substring(0, s.length()-1);
            Log.i(TAG, s);
        }else{
            s = new String ("EMPTY");
        }
        Message message = mHandler.obtainMessage();
        Bundle b = new Bundle();
        b.putString(mMsgKey, s);
        message.setData(b);
        mHandler.sendMessage(message);

    }//run
}//public class HttpCOMMRun implements Runnable {
