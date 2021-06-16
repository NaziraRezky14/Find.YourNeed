package com.example.findyourneed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadingPage1 extends AppCompatActivity {

    Button lanjut1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page1);

        lanjut1 = (Button) findViewById(R.id.lanjut1);

        lanjut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), LoadingPage2.class);
                startActivity(intent);
            }
        });
    }
}