package tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import tn743.ufrrj.gcampus.j_g_campus_test.R;

public class FunChallengeProgressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mChallenge;
    public TextView mTheme;
    public Button mBTNTest;
    public Context mContext;

    public FunChallengeProgressViewHolder(View view, Context context) {
        super(view);
        mContext = context;
        mChallenge = (TextView) view.findViewById(R.id.challenge_progress_name);
        mTheme = (TextView) view.findViewById(R.id.challenge_progress_theme);
        mBTNTest = (Button) view.findViewById(R.id.challenge_progress_button);
        mBTNTest.setOnClickListener(this);

    }//public ViewHolder(View itemView) {

    @Override
    public void onClick(View v) {

    }
}
