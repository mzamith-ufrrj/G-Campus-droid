package tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import tn743.ufrrj.gcampus.j_g_campus_test.R;

public class ChallengesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mTxtChallenge;
    public Button mBtnChallenge;
    private Context mContext;
    public ChallengesViewHolder(@NonNull View itemView,  Context c) {
        super(itemView);
        mContext = c;
        mTxtChallenge = (TextView) itemView.findViewById(R.id.challenge_name);
        mBtnChallenge = (Button) itemView.findViewById(R.id.challenge_button);
        mBtnChallenge.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = getAdapterPosition();
        Log.d( "ChallengesViewHolder", "onClick " + Integer.toString(i) );
        Intent intent = new Intent("selected-challenge");
        intent.putExtra("parameter", i);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }
}
