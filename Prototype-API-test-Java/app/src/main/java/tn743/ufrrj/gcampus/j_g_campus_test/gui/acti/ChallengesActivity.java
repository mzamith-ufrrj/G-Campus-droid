package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.GameManagerGCampus;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges.ChallengeFun;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges.ChallengesAdapter;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress.FunChallengeProgress;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress.FunChallengeProgressAdapter;

public class ChallengesActivity extends AppCompatActivity {
    private static final String TAG = "ChallengesActivity";
    private MyBroadcastReceive mMessageReceiver = null;
    private RecyclerView mRVChallenges = null;

    private boolean mType = false ;             /*<!brief false = new challenges / true = in progress >*/

    ArrayList<ChallengeFun> mListOpt = null;
    ArrayList<FunChallengeProgress> mListOptInProgress = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle param = getIntent().getExtras();
        if (param != null){
            mType = param.getBoolean("type");
        }
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenges);
        mRVChallenges = findViewById(R.id.rvChasllenges);

        if (!mType){
            mListOpt = null;
            mListOpt = ChallengeFun.createFunctionList();
            ChallengesAdapter adapter = new ChallengesAdapter(mListOpt);
            mRVChallenges.setAdapter(adapter);
            mRVChallenges.setLayoutManager(new LinearLayoutManager(this));
        }else{
            mListOptInProgress = GameManagerGCampus.getInstance().getChallengesInProgress();

            FunChallengeProgressAdapter adapter = new FunChallengeProgressAdapter(mListOptInProgress);
            mRVChallenges.setAdapter(adapter);
            mRVChallenges.setLayoutManager(new LinearLayoutManager(this));
        }
        mMessageReceiver = new MyBroadcastReceive();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("selected-challenge"));
    }
    private void nextActivity(int opt) {
        Intent intent = null;
        intent = new Intent(this, ChallengePlayActivity.class);
        if (!mType) {
            ChallengeFun f = mListOpt.get(opt);
            intent.putExtra("type", "WEB");
            intent.putExtra("game", f.getName());
            //Colocar aqui que é para pegar da web
        }else{
            FunChallengeProgress f = mListOptInProgress.get(opt);
            intent.putExtra("type", "FILE");
            int index = f.getIndex();
            intent.putExtra("game-logic", index);
            //Coolocar aqui que é para pegar do arquivo

        }


        startActivity(intent);

    }//private void nextActivity(int opt) {
    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
    private class MyBroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                int opt = intent.getIntExtra("parameter", -1);
                if (opt != -1){
                    Log.d(TAG, Integer.toString(opt));

                    nextActivity(opt);
                }//if (opt != -1){
            }//if (intent != null){

        }//public void onReceive(Context context, Intent intent) {
    }//private class MyBroadcastReceive extends BroadcastReceiver {
}//public class ChallengesActivity extends AppCompatActivity {