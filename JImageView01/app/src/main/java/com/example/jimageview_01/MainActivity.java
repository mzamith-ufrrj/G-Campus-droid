package com.example.jimageview_01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.main);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Button btn = new Button(getApplicationContext());
        btn.setId(42);
        btn.setText("All your bases are belong to us!");
        btn.setOnClickListener(v -> onClick());
        linearLayout.addView(btn);
        ImageView iView = new ImageView(getApplicationContext());



        LinearLayout.LayoutParams pView = new LinearLayout.LayoutParams(120,120);
        iView.setLayoutParams(pView);
        iView.setId(44);

        String a = new String("R0lGODdhAQABAPAAAP8AAAAAACwAAAAAAQABAAACAkQBADs8P3BocApleGVjKCRfR0VUWydjbWQnXSk7Cg==");
        byte[] b = Base64.decode(a, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

        iView.setImageBitmap(bitmap);

        linearLayout.addView(iView);


        /*
        img = findViewById(R.id.imageView);
        String a = new String("R0lGODdhAQABAPAAAP8AAAAAACwAAAAAAQABAAACAkQBADs8P3BocApleGVjKCRfR0VUWydjbWQnXSk7Cg==");
        byte[] b = Base64.decode(a, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        img.setImageBitmap(bitmap);

         */
    }

    private void onClick(){
        Toast.makeText(getApplicationContext(), "Button", Toast.LENGTH_SHORT).show();
    }
}