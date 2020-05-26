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

public class adminevent extends AppCompatActivity {
    TextView bookingdetails,eventdetails;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminevent);
        eventdetails=(TextView)findViewById(R.id.eventdetails);

        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();


        CollectionReference collectionReference=fstore.collection("event");
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                String data="";
                int i=1;
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    String name=documentSnapshot.getString("Name");
                    String time=documentSnapshot.getString("Time");
                    String apt=documentSnapshot.getString("Apartmentno");
                    String date=documentSnapshot.getString("Date");

                    data +=" "+i+")"+" Event name: "+name+"\n Apartment no: "+apt+"\n Date: "+date+"\n Time: "+time+"\n\n";
                    i++;
                }
                eventdetails.setText(data);
            }
        });

        /*
        userid= fauth.getCurrentUser().getUid();
        final DocumentReference documentReference= fstore.collection("Userdetail").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                username.setText(documentSnapshot.getString("Username"));
             //   contactno.setText(documentSnapshot.getString("ContactNo"));

            }
        });  */



    }
}
