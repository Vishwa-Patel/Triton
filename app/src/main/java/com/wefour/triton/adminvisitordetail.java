package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class adminvisitordetail extends AppCompatActivity {

    TextView titleadvisitor,advisitor;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminvisitordetail);

        advisitor=(TextView)findViewById(R.id.advisitor);
        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        CollectionReference collectionReference=fstore.collection("visitor");
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                String data="";
                int i=1;
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    String name=documentSnapshot.getString("Name");
                    String comp=documentSnapshot.getString("Contactno");
                    String apt= documentSnapshot.getString("Apartmentno");
                    String rel= documentSnapshot.getString("Relation");
                    String enter= documentSnapshot.getString("Entertime");
                    String exit= documentSnapshot.getString("Exittime");
                    String date= documentSnapshot.getString("Date");

                    data +=" "+i+")"+" Name: "+name+"\n Apartment no: "+apt+"\n Contactno: "+comp+"\n Relation: "+rel+"\n Date: "+date+"\n Enter time: "+enter+"\n Exit time: "+exit+"\n\n";
                    i++;
                }
                advisitor.setText(data);
            }
        });

    }
}
