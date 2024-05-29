package tn743.ufrrj.gcampus.j_g_campus_test.gui.acti;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tn743.ufrrj.gcampus.j_g_campus_test.R;
import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMM;
import tn743.ufrrj.gcampus.j_g_campus_test.comm.HttpCOMMRun;
import tn743.ufrrj.gcampus.j_g_campus_test.logic.Configure;

public class ConfigActivity extends AppCompatActivity {
    private final String TAG = "ConfigActivity";
    private EditText mEdtURL = null;
    private EditText mEdtUser = null;
    private EditText mEdtPasswd = null;
    private Button mBTNSave = null;
    private Button mBTNMD5 = null;
    private Button mBTNLogin = null;

    private boolean mIsInMD5 = false;

    private String mMsgKey = null;
    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);
        mEdtURL = findViewById(R.id.edtURL);
        mEdtUser = findViewById(R.id.edtUser);
        mEdtPasswd = findViewById(R.id.edtPasswd);
        mBTNSave = findViewById(R.id.btnSave);
        mBTNMD5 = findViewById(R.id.btnMD5);
        mBTNLogin = findViewById(R.id.btnLogin);
        //Configure.getInstance().setContext(getApplicationContext());
        Configure.getInstance().loadConfig();
        mEdtURL.setText(Configure.getInstance().getMainURL());
        mEdtUser.setText(Configure.getInstance().getUser());
        mEdtPasswd.setText(Configure.getInstance().getPasswd());



        //mBTNSave.setOnClickListener(new onButtonClick());
        mBTNSave.setOnClickListener(v -> saveCondig());
        mBTNMD5.setOnClickListener(v -> MD5());
        mBTNLogin.setOnClickListener(v -> doLogin());

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String string = bundle.getString(mMsgKey);
                receive(string);


            }
        };

        mMsgKey = new String ("FROM-HTTP-THREAD-TO-SETUP");

    }//protected void onCreate(Bundle savedInstanceState) {

    private void saveCondig(){
        Configure.getInstance().setMainURL(mEdtURL.getText().toString());
        Configure.getInstance().setUserPass(mEdtUser.getText().toString(), mEdtPasswd.getText().toString());
        Configure.getInstance().saveCondig();
    }//private void saveCondig(){

    private  void MD5(){
        mIsInMD5 = !mIsInMD5;

        if (mIsInMD5){
            mEdtUser.setText(Configure.getInstance().getMD5User());
            mEdtPasswd.setText(Configure.getInstance().getMD5Passwd());
        }else{
            mEdtUser.setText(Configure.getInstance().getUser());
            mEdtPasswd.setText(Configure.getInstance().getPasswd());

        }
    }//private  void MD5(){
    private void receive(String s){
        String msg = "[ERROR] nenhuma das opções anteriores!";
        if (s.compareTo("EMPTY") == 0){
            msg = new String ("[ERROR] Servidor não encontrado!");
        }else if (s.compareTo("OK") == 0){
            msg = new String ("[OK] autenticação realizada!");
        } else if (s.compareTo("FAILURE") == 0){
            msg = new String ("[ERROR] senha e/ou usuário!");
        }

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }// private void receive(String s){


    private void doLogin(){
        //Passar o link e os parametros do post
        //retorno é imprimir na tela para verificar se funcionou
        //testar a questão de cadastrar no o host como sem https
        String md5user = Configure.getInstance().getMD5User();
        String md5pass = Configure.getInstance().getMD5Passwd();

        String page = getString(R.string.authentication_page);
        String url = new String(mEdtURL.getText().toString() + page);
        HttpCOMM http_post = new HttpCOMM();

        http_post.setURL(url);
        http_post.mParams.clear();
        http_post.mParams.put("user", md5user);
        http_post.mParams.put("passwd", md5pass);

        HttpCOMMRun task = new HttpCOMMRun(http_post);
        task.setHandler(mHandler);
        task.setMsgKey(mMsgKey);
        Thread t = new Thread(task);
        t.start();
    }// private void doLogin(){


}//public class ConfigActivity extends AppCompatActivity {