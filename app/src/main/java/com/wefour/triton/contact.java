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

public class contact extends AppCompatActivity {
    TextView username,contactno;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        username=(TextView)findViewById(R.id.username);

        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();


        CollectionReference collectionReference=fstore.collection("Userdetails");
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                String data="";
                int i=1;
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    String u=documentSnapshot.getString("Username");
                    String c=documentSnapshot.getString("ContactNo");
                    String em=documentSnapshot.getString("E-mail");
                    String apt=documentSnapshot.getString("ApartmentNo");

                    data +=" "+i+")"+" Username: "+u+"\n\n Apartment no: "+apt+"\n\n Contact No: "+c+"\n\n E-mail: "+em+"\n\n\n";
                    i++;

                }
                username.setText(data);
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
