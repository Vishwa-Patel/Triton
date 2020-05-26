package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class vendor extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnvendoruser;
    Spinner vspinner;
    String str[]={"Select Vendor Type","Milkman","Plumber","Electricians","Cable TV Operator","Vegetable Vendors"};
    String cat="";
    TextView vendordetails;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        // **************spinner code************************************
         vspinner=(Spinner) findViewById (R.id.vspinner);
         btnvendoruser=(Button)findViewById(R.id.btnvendoruser);
         //vendordetails=(TextView)findViewById(R.id.vendordetails);

         vspinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the Str list

         ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,str);
         aa.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item );
         vspinner.setAdapter(aa);

         btnvendoruser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent i=new Intent(vendor.this,vendordetail.class);
                 i.putExtra("Category",cat);
                 startActivity(i);

             }
         });

    }
    @Override
    public void onItemSelected (AdapterView<?> arg0, View arg1 , int pos, long id) {

        //int pos show's selected position nd first by default take first position
        if (str[pos] == "Milkman") {
            //Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Milkman";
        }
        else if(str[pos] == "Plumber")
        {
            //Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Plumber";
        }
        else if(str[pos] == "Electricians")
        {
            //Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Electricians";
        }
        else if(str[pos] == "Cable TV Operator")
        {
           // Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Cabletv";
        }
        else if(str[pos] == "Vegetable Vendors")
        {
            //Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Vegetable";
        }

        /* fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        CollectionReference collectionReference=fstore.collection(cat);
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                String data="";
                int i=1;
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    String u=documentSnapshot.getString("Name");
                    String d=documentSnapshot.getString("Type");
                    String c=documentSnapshot.getString("Contactno");
                    String a=documentSnapshot.getString("Address");

                    data +=" "+i+")"+" Name : "+u+"\n Type : "+d+"\n Contact No : "+c+"\n Address : "+a+"\n\n\n";
                    i++;

                }
                vendordetails.setText(data);

            }
        });  */
    }
    @Override
    public void onNothingSelected ( AdapterView<?> arg0 ) {
        Toast. makeText ( getApplicationContext (),"hello", Toast . LENGTH_LONG ). show ();

    }
}
