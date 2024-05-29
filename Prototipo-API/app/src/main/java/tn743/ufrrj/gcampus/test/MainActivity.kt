package tn743.ufrrj.gcampus.test

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var mRVFunctions: RecyclerView
    private lateinit var mMessageReceiver: MyBroadcastReceive
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //mRVFunctions = this.findViewById (R.id.rvFunctions)
        mRVFunctions = findViewById(R.id.rvFunctions)
        var funcs = Function.createFunctionList()
        var adapter:FunctionsAdapter = FunctionsAdapter(funcs, applicationContext)
        mRVFunctions.adapter = adapter
        mRVFunctions.layoutManager = LinearLayoutManager(this)

        mMessageReceiver = MyBroadcastReceive()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter("selected-test"))

    }//override fun onCreate(savedInstanceState: Bundle?) {

    private fun nextActivity(index:Int){
        var width:Int = 0
        var height:Int = 0

        var msg:String = "(" + width.toString() + "," + height.toString() + ")";
        Log.d("TAG", msg)
        val activity = applicationContext as Activity
        val displayMetrics = DisplayMetrics()
        val windowManager = activity.getWindowManager()
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        val s = windowManager.currentWindowMetrics.toString()
        Log.d(TAG, s)


        //var next:Intent = Intent(this, LogActivity::class.java)
        //next.putExtra("type", index)
        //startActivity(next)

    }

    inner private class MyBroadcastReceive: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                var index:Int = it.getIntExtra("parameter", -1)
                if (index == -1) {
                    Toast.makeText(applicationContext, "Opção inválida!", Toast.LENGTH_LONG).show();
                    return;
                }
                nextActivity(index);
            }



        }//override fun onReceive(context: Context?, intent: Intent?) {

    }//inner private class MyBroadcastReceive: BroadcastReceiver(){

}//class MainActivity : AppCompatActivity() {