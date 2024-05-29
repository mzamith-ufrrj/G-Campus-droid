package tn743.ufrrj.gcampus.test;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

public class FunctionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mTestText;
    public Button mBTNTest;
    private Context mContext;
    public FunctionViewHolder(View itemView, Context c) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);
        //itemView.setOnClickListener(this);
        mContext = c;
        mTestText = (TextView) itemView.findViewById(R.id.function_name);
        mBTNTest = (Button) itemView.findViewById(R.id.function_button);
        mBTNTest.setOnClickListener(this);
    }//public ViewHolder(View itemView) {

    @Override
    public void onClick(View v) {
            int i = getAdapterPosition();
            Log.d( "FunctionViewHolder", "onClick " + Integer.toString(i) );
            Intent intent = new Intent("selected-test");
            intent.putExtra("parameter", i);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            //val intent;
            //intent = Intent(this, LogActivity::; class.java)
            //var intent = Intent(this, LogActivity::class.java)
            //intent.putExtra("key", value)
            //startActivity(intent)
    }
}//public class FunctionViewHolder extends RecyclerView.ViewHolder {
