package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.gui.frag.HangFragment;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.GameManagerGCampus;

public class ChallengePlayActivity extends AppCompatActivity {
    public static final String TAG = "ChallengePlayActivity";

    public static final int WEB = 0;
    public static final int FILE = 1;

    private Handler mHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_challenge_play);
        String opt = null,
              opt2 = null;

        Intent indent = getIntent();
        if (indent != null) {
            opt = indent.getStringExtra("type");
            opt2 = indent.getStringExtra("game");
            if (opt.compareTo("WEB")==0){
                if (opt2.compareTo("Forca") == 0)
                    GameManagerGCampus.getInstance().getHangman(this);

            }else if (opt.compareTo("FILE")==0){
                showHangman(FILE);
                Log.d(TAG, "LOAD FROM FILE");
            }



        }//if (indent != null) {
    }//protected void onCreate(Bundle savedInstanceState) {

    /*
    * Callback called by http connection
     */
    public void  showHangman(int t){

        Bundle b = new Bundle();
        switch (t){
            case WEB:
                b.putString("TYPE", "WEB");
                //b.putString("PARAM", s);

                break;
            case FILE:
                b.putString("TYPE", "FILE");
                //b.putString("PARAM", s);
                break;

        }


        HangFragment frag = new HangFragment();
        frag.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.challengePlay_fragmentView, frag)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
            finish();
        }


        /*
        if (fm.getBackStackEntryCount() > 0) {
            Log.d(TAG, "popping backstack");
            fm.popBackStack();
        } else {
            Log.d(TAG, "nothing on backstack, calling super");
            super.onBackPressed();
        }

         */
        /*
        super.onBackPressed();
        //FragmentManager fm = getSupportFragmentManager();
        //fm.popBackStack();
        getSupportFragmentManager().popBackStack();

         */
        //finish();
    }
}//public class ChallengePlayActivity extends AppCompatActivity {