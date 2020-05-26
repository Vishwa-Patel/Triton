package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    private Button btnevent,btnuserlogout,btncontact,btnvendor,btncomplaint,btnnot,btnvisitor,btnparking,btnabout,btnpay;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnevent=(Button)findViewById(R.id.btnevent);
        btncontact=(Button)findViewById(R.id.btncontact);
        btnvendor=(Button)findViewById(R.id.btnvendor);
        btncomplaint=(Button)findViewById(R.id.btncomplaint);
        btnnot=(Button)findViewById(R.id.btnnot);
        btnvisitor=(Button)findViewById(R.id.btnvisitor);
        btnparking=(Button)findViewById(R.id.btnparking);
        btnabout=(Button)findViewById(R.id.btnabout);
        btnpay=(Button)findViewById(R.id.btnpay);
        btnuserlogout=(Button)findViewById(R.id.btnuserlogout);

        btnnot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(home.this,notification.class);
                startActivity(i);
            }
        });

        btnvendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home.this,vendor.class);
                startActivity(i);
            }
        });

        btnevent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                openaevent();
            }
        });
        btncontact.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                opencontact();
            }
        });
        btncomplaint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                opencomplaint();
            }
        });
        btnvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openvisitor();
            }
        });
        btnparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home.this,parking.class);
                startActivity(i);
            }
        });

        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home.this,aboutus.class);
                startActivity(i);
            }
        });

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home.this,demoweb.class);
                startActivity(i);
            }
        });
        btnuserlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //logout
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }

    public void openaevent()
    {
        Intent i=new Intent(home.this,event.class);
        startActivity(i);
    }


    public void opencontact()
    {
        Intent i=new Intent(home.this,contact.class);
        startActivity(i);
    }


    public void opencomplaint()
    {
        Intent i=new Intent(home.this,complaint.class);
        startActivity(i);
    }

    public void openvisitor()
    {
        Intent i=new Intent(home.this,visitor.class);
        startActivity(i);
    }

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut(); //logout
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

}
