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

public class notification extends AppCompatActivity {

    TextView usernotice,titlenotice;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        usernotice=(TextView)findViewById(R.id.usernotice);
        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        CollectionReference collectionReference=fstore.collection("notification");
        collectionReference.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                String data="";
                int i=1;
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    String u=documentSnapshot.getString("Notice");
                    String d=documentSnapshot.getString("Date");

                    data +=" "+i+")"+" Date : "+d+"\n Notice : "+u+"\n\n\n";
                    i++;

                }
                usernotice.setText(data);

            }
        });

    }
}
