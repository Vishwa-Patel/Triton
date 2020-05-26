package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {

    TextView textview16,textview15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        textview15=(TextView)findViewById(R.id.textView15);
        textview16=(TextView)findViewById(R.id.textView16);
    }
}
