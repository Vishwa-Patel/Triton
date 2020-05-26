package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class vendordetail extends AppCompatActivity {

    TextView txtvendordetail,vendordetailnew;
    Button btnvendorback,btnvendorhome;

    String cat="";
    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendordetail);

        txtvendordetail=(TextView)findViewById(R.id.txtvendordetail);
        vendordetailnew=(TextView)findViewById(R.id.vendordetailnew);
        //btnvendorback=(Button)findViewById(R.id.btnvendorback);
       // btnvendorhome=(Button)findViewById(R.id.btnvendorhome);

        Intent i= getIntent();
        cat=i.getStringExtra("Category");

        if(cat.equals("Cabletv"))
            txtvendordetail.setText("Cable Tv Operators");
        else if(cat.equals("Vegetable"))
            txtvendordetail.setText("Vegetable Vendors");
        else
            txtvendordetail.setText(cat);

        fauth = FirebaseAuth.getInstance();
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
                vendordetailnew.setText(data);

            }
        });



    }
}
