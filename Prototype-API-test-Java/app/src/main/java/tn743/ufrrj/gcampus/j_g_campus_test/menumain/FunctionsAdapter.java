package tn743.ufrrj.gcampus.j_g_campus_test.menumain;

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

public class FunctionsAdapter extends RecyclerView.Adapter<FunctionViewHolder> {
    private List<Function> mFunctions;

    public FunctionsAdapter(List<Function> funcs) {

        mFunctions = funcs;

    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View functionView = inflater.inflate(R.layout.functions, parent, false);

        // Return a new holder instance
        FunctionViewHolder viewHolder = new FunctionViewHolder(functionView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder holder, int position) {
        Function item = mFunctions.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.mTestText;
        textView.setText(item.getName());
        Button button = holder.mBTNTest;
        button.setText(item.isOnline() ? "Testar" : "Bloqueado");
        button.setEnabled(item.isOnline());
    }

    @Override
    public int getItemCount() { return mFunctions.size();}
}//public class FunctionsAdapter extends RecyclerView.Adapter<FunctionViewHolder> {
