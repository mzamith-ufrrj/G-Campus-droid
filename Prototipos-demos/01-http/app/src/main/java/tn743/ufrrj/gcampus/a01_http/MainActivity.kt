package tn743.ufrrj.gcampus.a01_http

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mProgressBarAsync: ProgressBar

    private lateinit var mOutput:TextView
    private lateinit var mCOMM:Button
    private lateinit var mHASH:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProgressBarAsync = findViewById(R.id.idProgressBar)

        mOutput = findViewById(R.id.idOutput)
        mCOMM = findViewById(R.id.idCOMM)
        mHASH = findViewById(R.id.idhash)




        mCOMM.setOnClickListener{ onCOMMEvent() }
        mHASH.setOnClickListener{ onHASHEvent() }
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
    private fun onHASHEvent(){
        //mOutput.text = md5()
    }
    private fun onCOMMEvent(){
        Toast.makeText(applicationContext, "Working...", Toast.LENGTH_SHORT).show()
        mOutput.text = "Still working..."
        mProgressBarAsync.visibility = View.VISIBLE
        DoAsync().execute()

    }

    inner class DoAsync():AsyncTask<Void, Void, String>(){
/*
        The three types used by an asynchronous task are the following:
            -- Params, the type of the parameters sent to the task upon execution.
            -- Progress, the type of the progress units published during the background computation.
            -- Result, the type of the result of the background computation.
                Params: (in your case String) is the parameter that the AsyncTask takes. You have to pass this when you call execute method
                Progress: (in your case Void) is the type of progress. Void means you are not using it. If it is say, Integer, you could've used values like 10, 20, 30... and use these to show a progress bar on screen.
                Result: (in your case String) is what the AsyncTask returns as the result. You are returning a String. You can return any Object you want.

 */
        //class DoAsync():AsyncTask<Void, Void, String>(){
        override fun doInBackground(vararg params: Void?): String? {
            Log.d(TAG, "doInBackground")
            // Versão GET
/*
            var  http_get = HttpCOMM()
            val myURL = "http://192.168.1.14/~rural-droid/get_page.php"
            //var map : HashMap<String, Int> = HashMap<String, Int> ()
            http_get.mSTURL = myURL
            http_get.mParams!!.clear()
            http_get.mParams!!.put("namegetfname", "Marcelo+Panaro+de+Moraes")
            http_get.mParams!!.put("namegetlname", "Zamith");
            http_get.sendGET()
            return http_get.mResponseData;
    */
            //Versão post
            var  http_post = HttpCOMM()
            val myURL = "http://192.168.1.14/~rural-droid/post_page.php"
            //var map : HashMap<String, Int> = HashMap<String, Int> ()
            http_post.mSTURL = myURL
            http_post.mParams!!.clear()
            http_post.mParams!!.put("namepostfname", "Marcelo+Panaro+de+Moraes")
            http_post.mParams!!.put("namepostlname", "Zamith")
            http_post.sendPOST()
            return http_post.mResponseData;

}

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            result?.let { mOutput.text = it }
            //mOutput.text = result
            mProgressBarAsync.visibility = View.INVISIBLE

        }
    }
}//class MainActivity : AppCompatActivity() {

