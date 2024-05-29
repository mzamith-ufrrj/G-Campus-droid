package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
        String opt = null;
        Intent indent = getIntent();
        if (indent != null) {
            opt = indent.getStringExtra("game").toUpperCase();
            if (opt.compareTo("FORCA") == 0){
                GameManagerGCampus.getInstance().getHangman(this);
                //1 - obter novos do servidor
                //2 - listar os gravados e não resolvidos no telefone
                //3 - se resolvido apagar o desafio do servidor


                /*
                HangFragment frag = new HangFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.challengePlay_fragmentView, frag)
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();

                */
                return;
            }
        }//if (indent != null) {
    }//protected void onCreate(Bundle savedInstanceState) {

    /*
    * Callback called by http connection
     */
    public void  showHangman(String s, int t){

        Bundle b = new Bundle();
        switch (t){
            case WEB:
                b.putString("TYPE", "WEB");
                b.putString("PARAM", s);

                break;
            case FILE:
                b.putString("TYPE", "FILE");
                b.putString("PARAM", s);
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