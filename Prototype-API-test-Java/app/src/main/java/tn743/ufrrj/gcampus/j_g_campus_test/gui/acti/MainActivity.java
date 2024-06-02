package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.Configure;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.GameManagerGCampus;
import tn743.ufrrj.gcampus.j_g_campus_test.menumain.Function;
import tn743.ufrrj.gcampus.j_g_campus_test.menumain.FunctionsAdapter;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int SETUP = 0;
    public static final int CHALLENGES = 1;
    public static final int CHALLENGES_IN_PLAYING = 2;
    public static final int SCORE = 3;
    public static final int GPSCHALLENGES = 4;
    public static final int QRCODE_R = 5;
    public static final int QRCODE_W = 6;



    /*
    private lateinit var mRVFunctions: RecyclerView
    private lateinit var mMessageReceiver: MyBroadcastReceive
    private val TAG = "MainActivity"
 */
    private RecyclerView mRVFunctions = null;
    private MyBroadcastReceive mMessageReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRVFunctions = findViewById(R.id.rvFunctions);
        ArrayList<Function> functions = Function.createFunctionList();
        FunctionsAdapter adapter = new FunctionsAdapter(functions);
        mRVFunctions.setAdapter(adapter);
        mRVFunctions.setLayoutManager(new LinearLayoutManager(this));
        mMessageReceiver = new MyBroadcastReceive();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("selected-test"));

        Configure.getInstance().setContext(getApplicationContext());
        GameManagerGCampus.getInstance().init(getApplicationContext());
        GameManagerGCampus.getInstance().loadChallengesInProgress();

    }//protected void onCreate(Bundle savedInstanceState) {
    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

        super.onDestroy();
    }

    private void nextActivity(int opt){
        Intent indent = null;
        switch (opt){
            case SETUP :
                indent = new Intent(this, ConfigActivity.class);
                break;
            case CHALLENGES :
                indent = new Intent(this, ChallengesActivity.class);
                indent.putExtra("type", false);
                break;
            case CHALLENGES_IN_PLAYING:
                indent = new Intent(this, ChallengesActivity.class);
                indent.putExtra("type", true);
                break;
            case SCORE :
                break;
            case GPSCHALLENGES :
                break;
            case QRCODE_R :
            case QRCODE_W :
                indent = new Intent(this, QRCodeActivity.class);
                indent.putExtra("type", opt);
                break;

        }
        if (indent != null)
            startActivity(indent);

    }//private void nextActivity(int opt){
    private class MyBroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                int opt = intent.getIntExtra("parameter", -1);
                if (opt != -1){
                    nextActivity(opt);
                }//if (opt != -1){
            }//if (intent != null){

        }//public void onReceive(Context context, Intent intent) {
    }//private class MyBroadcastReceive extends BroadcastReceiver {
}//public class MainActivity extends AppCompatActivity {