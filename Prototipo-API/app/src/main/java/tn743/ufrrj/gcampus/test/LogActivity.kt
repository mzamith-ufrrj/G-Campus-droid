package tn743.ufrrj.gcampus.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_log)
        val intent = getIntent()
        val i: Int = intent.getIntExtra("type", -1)
        Toast.makeText(applicationContext, "Opção de log foi: " + i.toString(), Toast.LENGTH_LONG).show();

        val configFragment = ConfigFragment()
        //mainfrag.setArguments(bundle)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.main_fragmentView, configFragment)
            .commit()

    }//override fun onCreate(savedInstanceState: Bundle?) {
}//class LogActivity : AppCompatActivity() {