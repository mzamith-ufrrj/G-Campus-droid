package tn743.ufrrj.gcampus.test

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.File


/**
 * A simple [Fragment] subclass.
 * Use the [ConfigFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigFragment : Fragment() {
    private val TAG = "ConfigFragment"
    private lateinit var mEdtURL:EditText
    private lateinit var mEdtUser:EditText
    private lateinit var mEdtPasswd:EditText
    private lateinit var mBTNSave:Button
    private lateinit var mBTNMD5:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //arguments?.let {
        //    param1 = it.getString(ARG_PARAM1)
        //    param2 = it.getString(ARG_PARAM2)
        //}
    }//override fun onCreate(savedInstanceState: Bundle?) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_config, container, false)
        mEdtURL = view.findViewById(R.id.edtURL)
        mEdtUser = view.findViewById(R.id.edtUser)
        mEdtPasswd = view.findViewById(R.id.edtPasswd)
        mBTNSave = view.findViewById(R.id.btnSave)
        mBTNMD5 = view.findViewById(R.id.btnMD5)


        mBTNSave.setOnClickListener{  save() }
        mBTNMD5.setOnClickListener{  MD5() }

        load();

        var width:Int = 0
        var height:Int = 0
        val metrics = DisplayMetrics()

        var msg:String = "(" + width.toString() + "," + height.toString() + ")";
        Log.d(TAG, msg)
        return view
    }//override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


    private fun load(){
        var dir: File? = context?.getFilesDir()

        dir.let {
            var file = File(dir, "config.ini")
            if (! file.exists()){
                mEdtURL.setText("http://192.168.1.14/~rural-droid/")
                mEdtUser.setText("rural-droid")
                mEdtPasswd.setText("rural-droid")
            }

        }

    }//private fun load(){
    private fun save(){
        var url:String = mEdtURL.getText().toString()
        var user:String = mEdtUser.getText().toString()
        var passwd:String = mEdtPasswd.getText().toString()
        var textOutput:String = "URL=" + url + "\n" + "USER="+user+"\n"+"PASSWD="+passwd+"\n"

        var dir: File? = context?.getFilesDir()
        dir.let {
            var file = File(dir, "config.ini")
            file.writeText(textOutput)
        }

        var c:Context? = context
        c.let {Toast.makeText(it, "File save: [OK]", Toast.LENGTH_SHORT).show() }
    }//private fun save(){

    private fun MD5(){
        var c:Context? = context
        c.let {Toast.makeText(it, "MD5", Toast.LENGTH_LONG).show() }
    }//private fun save(){

}//class ConfigFragment : Fragment() {