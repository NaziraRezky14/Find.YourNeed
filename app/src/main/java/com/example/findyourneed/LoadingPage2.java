package com.example.findyourneed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadingPage2 extends AppCompatActivity {

    Button lanjut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page2);

        lanjut2 = (Button) findViewById(R.id.lanjut2);

        lanjut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), LoadingPage3.class);
                startActivity(intent);
            }
        });
    }
}