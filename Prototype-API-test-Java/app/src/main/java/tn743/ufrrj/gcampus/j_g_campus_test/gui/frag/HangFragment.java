package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.GameManagerGCampus;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.HangmanLogic;

public class HangFragment extends Fragment {
    private static String TAG =  "HangFragment";
    private LinearLayout mHangmanLayout = null;

    private TextView[] mLettersTextView = null;
    private HangmanLogic mHangmanLogic = null;

    private int mIDBtnRecoveryLetters = 0;
    private int mIDBtnGuessAnswer = 0;
    private int mIDBtnListCharacter = 0;

    private MyBroadcastReceive mReceive = null;

    public HangFragment() {
        // Required empty public constructor
        //Example of letters aquiring
        //BagResources.getInstance().setAndInsertAdd("MARELO");
        //BagResources.getInstance().setAndInsertAdd("JULIANA");


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        //GameManagerGCampus.getInstance().loadChallengesInProgress();
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_top));
        mReceive = new MyBroadcastReceive();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReceive, new IntentFilter("guest-word-to-answer"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_hang, container, false);
        mHangmanLayout = view.findViewById(R.id.llhangman);




        String stype = getArguments().getString("TYPE");

        String output = new String ("");
        if (stype.compareTo("WEB")==0){
            mHangmanLogic = GameManagerGCampus.getInstance().getLastHangman();
            output = "WEB: FILENAME:" + mHangmanLogic.getGameID() + " <---";

        }else{
            int index = getArguments().getInt("FILEINDEX");
            mHangmanLogic = GameManagerGCampus.getInstance().getHangmanInProgress(index);
            output = "FILEINDEX (" + Integer.toString(index) + ") : FILENAME:" + mHangmanLogic.getGameID() + " <---";

        }
//TO DEBUG
        Log.d(TAG, output);



        int id = 0;
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int hp = metrics.heightPixels;
        int wp = metrics.widthPixels;
        LinearLayout.LayoutParams params = null;
        //String txt = new String("MARCELO");
        //Insert hangman theme
        String txt = mHangmanLogic.getTheme();
        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //params.width = (int) (wp /txt.length());
        //params.height =  (int) (hp / 11.5);
        TextView text  = new TextView(getContext());
        text.setId(id++);
        text.setText(txt);
        text.setGravity(Gravity.CENTER);
        //btn.setOnClickListener(v -> btnOnClickWord(v));
        //text.setBackgroundColor(Color.rgb(255, 0, 0  ));
        text.setBackgroundColor(getResources().getColor(R.color.gray25));

        row.addView(text, params);
        mHangmanLayout.addView(row);

            String answer = mHangmanLogic.getMask();
            char[] word = answer.toCharArray();

            row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            //mTextViewWords = new TextView[mAnswer.length()];
            mLettersTextView = new TextView[word.length];
            for (int i = 0; i < word.length; i++) {
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.width = (int) (wp / word.length);
                params.height = (int) (hp / 11.5);
                //params.gravity = Gravity.CENTER;
                text = new TextView(getContext());
                //Button btn = new Button(getContext());
                //btn.setId(i);
                text.setId(id++);
                txt = "";
                txt = txt.copyValueOf(word, i, 1);
                text.setText(txt);
                text.setGravity(Gravity.CENTER);
                //btn.setOnClickListener(v -> btnOnClickWord(v));
                text.setBackgroundColor(getResources().getColor(R.color.gray25));
                mLettersTextView[i] = text;
                row.addView(text, params);
            }
            mHangmanLayout.addView(row);

        Button btn = new Button(getContext());

        mIDBtnRecoveryLetters = id++;

        btn.setId(mIDBtnRecoveryLetters);
        btn.setText("Recupear letras!");
        btn.setOnClickListener(v -> btnOnClickWord(v));
        btn.setBackgroundColor(getResources().getColor(R.color.blue_ufrrj));
        btn.setTextColor(getResources().getColor(R.color.white));
        mHangmanLayout.addView(btn);

        btn = new Button(getContext());
        mIDBtnGuessAnswer = id++;
        btn.setId(mIDBtnGuessAnswer);
        btn.setText("Chutar resposta!");
        btn.setOnClickListener(v -> btnOnClickWord(v));
        btn.setBackgroundColor(getResources().getColor(R.color.blue_ufrrj));
        btn.setTextColor(getResources().getColor(R.color.white));
        mHangmanLayout.addView(btn);

        btn = new Button(getContext());
        mIDBtnListCharacter = id++;
        btn.setId(mIDBtnListCharacter);
        btn.setText("#42#");
        btn.setOnClickListener(v -> btnOnClickWord(v));
        btn.setBackgroundColor(getResources().getColor(R.color.blue_ufrrj));
        btn.setTextColor(getResources().getColor(R.color.white));
        mHangmanLayout.addView(btn);
        ImageView iView = new ImageView(getContext());
        iView.setId(id+2);
        iView.setImageBitmap(setImage());
        mHangmanLayout.addView(iView);



        changeColorTextView();
        //MARCELO
        // Inflate the layout for this fragment
        return view;
    }//public View onCreateView

    private Bitmap setImage(){
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(180, 180, conf); // this creates a MUTABLE bitmap
        return bmp;
    }
    @Override
    public void onDestroy(){
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReceive);
        super.onDestroy();
    }


    private void changeColorTextView(){
            String mask = mHangmanLogic.getMask();
            if (mask != null){
                for (int j = 0; j < mask.length(); j++){
                    TextView text = mLettersTextView[j];
                    String s = mask.substring(j, j+1);
                    if (s.compareTo("#") !=0 ){
                        text.setText(s);
                        text.setBackgroundColor(getResources().getColor(R.color.green));
                    }
                }
            }else
                Toast.makeText(getContext(), "ERROR in MASK string ", Toast.LENGTH_SHORT).show();
    }//private void changeColorTextView(){

    private void guessAnswer(String s){
        if (mHangmanLogic.guessAnswer(s, TAG)){
            getWord();
            changeColorTextView();

        }else
          Toast.makeText(getContext(), "Resposta errada. Perdeu ponto!", Toast.LENGTH_SHORT).show();


    }//private void guessAnswer(String s){
    private void getWord(){
        //for (int i = 0; i < mGroupsTextView.size(); i++){
        //TextView[] textviews = mGroupsTextView.get(i);
        String mask = mHangmanLogic.getMask();
        for (int j = 0; j < mask.length(); j++){
            TextView text = mLettersTextView[j];
            String s = mask.substring(j, j+1);
            if (s.compareTo("#") !=0 ){
                text.setText(s);
                //text.setBackgroundColor(getResources().getColor(R.color.green));
            }//if (s.compareTo("#") !=0 ){
        }//for (int j = 0; j < mask.length(); j++){
        //}//for (int i = 0; i < mGroupsTextView.size(); i++){
    }//private void getWords(){

    private void btnOnClickWord(View v){
        int id = v.getId();
        if (id == mIDBtnRecoveryLetters){
            Toast.makeText(getContext(), "Recuperar letras", Toast.LENGTH_SHORT).show();
            boolean ret = mHangmanLogic.recoveryLetters();
            if (ret){
                getWord();
                changeColorTextView();
            }//if (ret){
            else{
                Toast.makeText(getContext(), "NÃO EXISTE MAIS LETRAS DISPONÍVEIS!!!", Toast.LENGTH_SHORT).show();
            }

            return;
        }
        if (id == mIDBtnListCharacter){
            LogFragment log = new LogFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.challengePlay_fragmentView, log)
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();
            return;
        }
        if (id == mIDBtnGuessAnswer){
            int index = getActivity().getSupportFragmentManager().getFragments().size();
            Log.d(TAG, Integer.toString(index));

            GuessWordFragment guess = new GuessWordFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.challengePlay_fragmentView, guess)
                    .addToBackStack(null)
                    .setReorderingAllowed(true)
                    .commit();

            //.add(R.id.challengePlay_fragmentView, guess, "guess")
            //.setReorderingAllowed(true)
            //.commit();
        }
    }//private void btnOnClickWord(View v){

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        GameManagerGCampus.getInstance().setChallengesInProgress();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /*
     *
     */
    private class MyBroadcastReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                String msg = intent.getStringExtra("answer");
                Log.d(TAG, "onReceive \t" + msg);
                guessAnswer(msg.trim());
            }//if (intent != null){
        }//public void onReceive(Context context, Intent intent) {
    }//private class MyBroadcastReceive extends BroadcastReceiver{

}//    public View onCreateView