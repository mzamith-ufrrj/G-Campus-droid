package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.util.Log;
import tn743.ufrrj.gcampus.j_g_campus_test.R;

public class GuessWorldFragment extends Fragment {
    private static final String TAG = "GuessWorldFragment";
    private Button mBtnSend = null;
    private Button mBtnCancel= null;
    public GuessWorldFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_top));
    }

    private void onBtnClick(View v){
        //
        if (v.getId() == R.id.btnSend){
            Log.d(TAG, "Send answer");
            return;
        }//if (v.getId() == R.id.btnSend){
        if (v.getId() == R.id.btnCancel){
            Log.d(TAG, "Send cancel");
            return;
        }//if (v.getId() == R.id.btnSend){


    }//private void onBtnClick(View v){
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_guess_world, container, false);
        mBtnSend = view.findViewById(R.id.btnSend);
        mBtnCancel = view.findViewById(R.id.btnCancel);
        mBtnSend.setOnClickListener(v -> onBtnClick(v));
        mBtnCancel.setOnClickListener(v -> onBtnClick(v));

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = view.getLayoutParams();
        float h = ((float) metrics.heightPixels) * 0.30f;
        //float w = ((float) metrics.widthPixels) * 0.8f;

        params.height =  (int) h;
        //params.width =   (int) w;

        view.setLayoutParams(params);
        return view;
    }
    /*
    @Override
    public void onDetach() {
        super.onDetach();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    */


}//public class GuessWorldFragment extends Fragment {