package tn743.ufrrj.gcampus.j_g_campus_test.gui.frag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.BagResources;


public class LogFragment extends Fragment {

    private EditText mLog = null;
    public LogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.fade));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_top));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log, container, false);
        mLog = view.findViewById(R.id.mEdtLog);


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = view.getLayoutParams();
        float h = ((float) metrics.heightPixels) * 0.8f;
        //float w = ((float) metrics.widthPixels) * 0.8f;

        params.height =  (int) h;
        //params.width =   (int) w;

        view.setLayoutParams(params);
        String text = BagResources.getInstance().getAllCharacter();

        mLog.setText(text);
        mLog.setEnabled(false);
        return view;
    }

    /*
    private class MyFrame extends FrameLayout {
        public MyFrame(@NonNull Context context) {
            super(context);
        }
        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int w = canvas.getWidth();
            int h  = canvas.getHeight();
            if (mNLines == 0){
                double r = Math.ceil((double)(h) / (double) (CHAR_HEIGHT));
                mNLines = (int) r;
            }

            //Do some drawing
            Paint paint = new Paint();
            paint.setColor(getColor(R.color.yellow_ufrrj));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(40);

            boolean flag = true;
            int index = 0;
            while (flag){


                int index2 = mLog.size() - index;
                if (index2 <= 0)
                    flag = false;

                if (flag){
                    String line = Integer.toString(index2) + ":" + mLog.get(index2-1);
                    canvas.drawText(line, 8, (index+1)*CHAR_HEIGHT, paint);
                }

                if (index < mNLines)
                    index++;
                else
                    flag = false;

            }//while (flag){



        }// public void onDraw(Canvas canvas) {
    }//private class MyFrame extends FrameLayout {

     */
}//public class LogFragment extends Fragment {