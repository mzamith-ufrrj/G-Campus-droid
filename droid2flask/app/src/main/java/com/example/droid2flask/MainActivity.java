package com.example.droid2flask;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.droid2flask.comm.*;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private String mMsgKey = null;
    private Handler mHandler = null;
    private Button mConnect = null;
    private TextView mLog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mConnect = findViewById(R.id.idConnect);
        mLog = findViewById(R.id.idLog);
        mConnect.setOnClickListener(v -> onButtonClick(v));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString(mMsgKey);
                receive(string);


            }
        };

        mMsgKey = new String ("FROM-HTTP-THREAD-TO-SETUP");

    }

    private void receive(String s){
        mLog.setText(s);
    }
    private void onButtonClick(View v){
        HttpCOMM http_post = new HttpCOMM();

        http_post.setURL("http://192.168.1.20:5000");
        http_post.mParams.clear();
        http_post.mParams.put("user", "droid");
        http_post.mParams.put("pass", "localuser");

        HttpCOMMRun task = new HttpCOMMRun(http_post);
        task.setHandler(mHandler);
        task.setMsgKey(mMsgKey);
        Thread t = new Thread(task);
        t.start();
        Toast.makeText(getApplicationContext(), "All your bases are belong to us!", Toast.LENGTH_SHORT).show();
    }
}//public class MainActivity extends AppCompatActivity {