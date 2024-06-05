package tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress;

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
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallenges.ChallengeFun;
import tn743.ufrrj.gcampus.j_g_campus_test.menuchallengesinprogress.FunChallengeProgressViewHolder;

public class FunChallengeProgressAdapter extends RecyclerView.Adapter<FunChallengeProgressViewHolder> {

    private List<FunChallengeProgress> mList;


    public FunChallengeProgressAdapter(List<FunChallengeProgress> list){
        mList = list;
    }
    @NonNull
    @Override
    public FunChallengeProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.challengesprogress, parent, false);
        FunChallengeProgressViewHolder viewHolder = new FunChallengeProgressViewHolder(view, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunChallengeProgressViewHolder holder, int position) {
        FunChallengeProgress item = mList.get(position);
        TextView textView = holder.mChallenge;
        String challenge = new String(item.getChallengeType() + "<" + item.getGameID() + "><" + Integer.toString(item.getIndex()) + ">");
        textView.setText(challenge);

        textView = holder.mTheme;
        textView.setText(item.getTheme());


        Button button = holder.mBTNTest;
        button.setText(item.isOnline() ? "Testar" : "Bloqueado");
        button.setEnabled(item.isOnline());
    }

    @Override
    public int getItemCount() { return mList.size(); }
}
