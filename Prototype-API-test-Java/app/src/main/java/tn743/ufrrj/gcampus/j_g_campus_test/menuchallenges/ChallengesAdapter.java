package tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn743.ufrrj.gcampus.j_g_campus_test.R;



public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesViewHolder>{
    private List<ChallengeFun> mOptions;

    public ChallengesAdapter(List<ChallengeFun> opts) {

        mOptions = opts;

    }
    @NonNull
    @Override
    public ChallengesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.challenges, parent, false);
        ChallengesViewHolder viewHolder = new ChallengesViewHolder(view, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengesViewHolder holder, int position) {
        ChallengeFun item = mOptions.get(position);
        TextView textView = holder.mTxtChallenge;
        textView.setText(item.getName());
        Button button = holder.mBtnChallenge;
        button.setText(item.isOnline() ? "Testar" : "Bloqueado");
        button.setEnabled(item.isOnline());
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }
}
