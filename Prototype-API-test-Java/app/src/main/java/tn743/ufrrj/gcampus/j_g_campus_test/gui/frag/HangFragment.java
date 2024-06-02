package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.util.Vector;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.GameManagerGCampus;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.HangmanLogic;

public class HangFragment extends Fragment {
    private static String TAG =  "HangFragment";
    private LinearLayout mHangmanLayout = null;

    private Vector<TextView[]> mGroupsTextView = null;
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

        if (stype.compareTo("WEB")==0){
            mHangmanLogic = GameManagerGCampus.getInstance().getLastHangman();

        }else{
            int index = getArguments().getInt("game-logic");

            mHangmanLogic = GameManagerGCampus.getInstance().getHangman(index);

        }


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

        mGroupsTextView = new Vector<>();
        //Inserting the words, considering more the one
        for (int j = 0; j < mHangmanLogic.Words(); j++){
            String answer = mHangmanLogic.getMask(j);
            char[] word = answer.toCharArray();
            //for (int i = 0; i < answer.length(); i++)
            //    word[i] = '#';
            row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            //mTextViewWords = new TextView[mAnswer.length()];
            TextView[] vetTextView = new TextView[word.length];
            for (int i = 0; i < word.length; i++){
                params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.width = (int) (wp /word.length);
                params.height =  (int) (hp / 11.5);
                //params.gravity = Gravity.CENTER;
                text  = new TextView(getContext());
                //Button btn = new Button(getContext());
                //btn.setId(i);
                text.setId(id++);
                txt = "";
                txt = txt.copyValueOf(word, i, 1);
                text.setText(txt);
                text.setGravity(Gravity.CENTER);
                //btn.setOnClickListener(v -> btnOnClickWord(v));
                text.setBackgroundColor(getResources().getColor(R.color.gray25));
                vetTextView[i] = text;
                row.addView(text, params);

            }//for (int i = 0; i < word.length; i++){
            mGroupsTextView.add(vetTextView);
            mHangmanLayout.addView(row);
        }//for (int j = 0; j < mHangmanLogic.Words(); j++){
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
        changeColorTextView();
        //MARCELO
        // Inflate the layout for this fragment
        return view;
    }//public View onCreateView

    /*
    Method used to catch button event
     */
    private void changeColorTextView(){
        for (int i = 0; i < mGroupsTextView.size(); i++){
            TextView[] textviews = mGroupsTextView.get(i);
            String mask = mHangmanLogic.getMask(i);
            for (int j = 0; j < mask.length(); j++){
                TextView text = textviews[j];
                String s = mask.substring(j, j+1);
                if (s.compareTo("#") !=0 ){
                    text.setText(s);
                    text.setBackgroundColor(getResources().getColor(R.color.green));
                }
            }

        }//for (int i = 0; i < mGroupsTextView.size(); i++){

    }

    private void guessAnswer(String s){
        if (mHangmanLogic.guessAnswer(s)){
            getWords();
            changeColorTextView();
        }else
            Toast.makeText(getContext(), "Resposta errada. Perdeu ponto!", Toast.LENGTH_SHORT).show();
    }//private void guessAnswer(String s){
    private void getWords(){
        for (int i = 0; i < mGroupsTextView.size(); i++){
            TextView[] textviews = mGroupsTextView.get(i);
            String mask = mHangmanLogic.getMask(i);
            for (int j = 0; j < mask.length(); j++){
                TextView text = textviews[j];
                String s = mask.substring(j, j+1);
                if (s.compareTo("#") !=0 ){
                    text.setText(s);
                    //text.setBackgroundColor(getResources().getColor(R.color.green));
                }//if (s.compareTo("#") !=0 ){
            }//for (int j = 0; j < mask.length(); j++){
        }//for (int i = 0; i < mGroupsTextView.size(); i++){
    }//private void getWords(){
    private void btnOnClickWord(View v){
        int id = v.getId();
        if (id == mIDBtnRecoveryLetters){
            Toast.makeText(getContext(), "Recuperar letras", Toast.LENGTH_SHORT).show();
            boolean ret = mHangmanLogic.recoveryLetters();
            if (ret){
                getWords();
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



            return;
            //Toast.makeText(getContext(), "Chutar resposta", Toast.LENGTH_SHORT).show();

        }

        //mTextViewWords[3].setText("Z");

    }//private void btnOnClickWord(View v){

    @Override
    public void onDestroy() {
        mHangmanLogic.save(getContext().getFilesDir().toString());

        super.onDestroy();
    }

    private class MyBroadcastReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                String msg = intent.getStringExtra("answer");
                Log.d(TAG, msg);
                guessAnswer(msg);
            }//if (intent != null){
        }//public void onReceive(Context context, Intent intent) {
    }//private class MyBroadcastReceive extends BroadcastReceiver{
}//public class HangFragment extends Fragment