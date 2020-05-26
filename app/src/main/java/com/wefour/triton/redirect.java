package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class redirect extends AppCompatActivity {

    FirebaseFirestore fstore;
    FirebaseAuth fauth;
    String userid,ac;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        userid= fauth.getCurrentUser().getUid();

        Toast.makeText(getApplicationContext(),"uid:"+userid,Toast.LENGTH_LONG).show();

        documentReference= fstore.collection("Userdetails").document(userid);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                String type = documentSnapshot.getString("Account");
                ac=type;
                Toast.makeText(getApplicationContext(),"type:"+ac,Toast.LENGTH_LONG).show();

                if(ac=="Admin")
                {
                    openadminhome();
                }
                else if(ac=="User"){
                    openuserhome();
                }
                else
                {
                   // Toast.makeText(getApplicationContext(),"User Account doesn't exist...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void openadminhome()
    {
        Intent i=new Intent(redirect.this,adminhome.class);
        startActivity(i);
    }

    void openuserhome()
    {
        Intent i=new Intent(redirect.this,home.class);
        startActivity(i);
    }


}
