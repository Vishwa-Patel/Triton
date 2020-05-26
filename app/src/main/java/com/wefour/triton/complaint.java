package com.wefour.triton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class complaint extends AppCompatActivity {

    public static final String TAG = "TAG";
    private Button btncomplaint;
    private EditText edtcomplaint;
    TextView txtcomplaint;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    DocumentReference documentReference;
    String userid;
    public String name,home,n,h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        btncomplaint=(Button)findViewById(R.id.btncomplaint);
        edtcomplaint=(EditText)findViewById(R.id.edtcomplaint);
        txtcomplaint=(TextView)findViewById(R.id.txtcomplaint);

        mAuth= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userid= mAuth.getCurrentUser().getUid();
        documentReference= fstore.collection("Userdetails").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name = documentSnapshot.getString("Username");
                home = documentSnapshot.getString("ApartmentNo");

                btncomplaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String comp= edtcomplaint.getText().toString();
                       // Toast.makeText(getApplicationContext(),"uid:"+userid+" uname:"+name+" home:"+home, Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(),"uid:"+userid+" n:"+n+" h:"+h, Toast.LENGTH_LONG).show();

                        documentReference = fstore.collection("complaint").document(userid);

                        Map<String,Object> user=new HashMap<>();
                        user.put("Username",name);
                        user.put("Apartmentno",home);
                        user.put("complaint",comp);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG,"OnSucess: complaint is registered..."+userid);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG,"OnFailure: ..."+e.toString());
                            }
                        });

                        Toast.makeText(getApplicationContext(),"Your Complaint is registered ...!!!",Toast.LENGTH_LONG).show();


                      //  Intent i = new Intent(complaint.this, MainActivity.class);
                      //  startActivity(i);

                    }

            });

        }

    });


    }


}

