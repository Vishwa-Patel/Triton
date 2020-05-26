package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class adminhome extends AppCompatActivity {

    private Button adbtnpay,adbtnevent,adbtncontact,adbtnvendor,adbtncomplaint,adbtnnot,adbtnvisitor,adbtnparking,adbtnabout,btnadlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        adbtnevent=(Button)findViewById(R.id.adbtnevent);
        adbtncontact=(Button)findViewById(R.id.adbtncontact);
        adbtnvendor=(Button)findViewById(R.id.adbtnvendor);
        adbtncomplaint=(Button)findViewById(R.id.adbtncomplaint);
        adbtnnot=(Button)findViewById(R.id.adbtnnot);
        adbtnvisitor=(Button)findViewById(R.id.adbtnvisitor);
        adbtnparking=(Button)findViewById(R.id.adbtnparking);
        adbtnabout=(Button)findViewById(R.id.adbtnabout);
        btnadlogout=(Button)findViewById(R.id.btnadlogout);
        adbtnpay=(Button)findViewById(R.id.adbtnpay);

        adbtnevent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(adminhome.this,adminevent.class);
                startActivity(i);
            }
        });

        adbtncontact.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(adminhome.this,contact.class);
                startActivity(i);
            }
        });

        adbtncomplaint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(adminhome.this,admincomplaint.class);
                startActivity(i);
            }
        });

        adbtnvendor.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(adminhome.this,vendor.class);
                startActivity(i);
            }
        });

        btnadlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //logout
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        adbtnnot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(adminhome.this,adminnotify.class);
                startActivity(i);
            }
        });

        adbtnvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(adminhome.this,adminvisitordetail.class);
                startActivity(i);
            }
        });

        adbtnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(adminhome.this,aboutus.class);
                startActivity(i);
            }
        });

        adbtnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(adminhome.this,demoweb.class);
                startActivity(i);
            }
        });
        adbtnparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(adminhome.this,parking.class);
                startActivity(i);
            }
        });

    }

    /*
    public void openaevent()
    {
        Intent i=new Intent(adminhome.this,adminevent.class);
        startActivity(i);
    }


    public void opencontact()
    {
        Intent i=new Intent(adminhome.this,contact.class);
        startActivity(i);
    }


    public void opencomplaint()
    {
        Intent i=new Intent(adminhome.this,admincomplaint.class);
        startActivity(i);
    }

    public void logout()
    {
        FirebaseAuth.getInstance().signOut(); //logout
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
        */
}
