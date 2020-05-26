package com.wefour.triton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class event extends AppCompatActivity {

    public static final String TAG = "TAG";
    private Button btnevent;
    private EditText edtevent_date, edtevent_name, edtevent_time, edtevent_houseno;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
    private String userid;
    private DocumentReference documentrefernce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        btnevent=(Button)findViewById(R.id.btnevent);
        edtevent_date=(EditText)findViewById(R.id.edtevent_date);
        edtevent_name=(EditText)findViewById(R.id.edtevent_name);
        edtevent_time=(EditText)findViewById(R.id.edtevent_time);
        edtevent_houseno=(EditText)findViewById(R.id.edtevent_houseno);

        mAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date,name,time,home;

                name=edtevent_name.getText().toString();
                date=edtevent_date.getText().toString();
                time=edtevent_time.getText().toString();
                home=edtevent_houseno.getText().toString();
                /*Validation code*/

                if (TextUtils.isEmpty(date)) {
                    edtevent_date.setError("Date is required...");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    edtevent_name.setError("Event Name is required...");
                    return;
                }
                if (TextUtils.isEmpty(time)) {
                    edtevent_time.setError("Time is required...");
                    return;
                }
                if (TextUtils.isEmpty(home)) {
                    edtevent_houseno.setError("House No is required...");
                    return;
                }
                userid=mAuth.getCurrentUser().getUid();
                documentrefernce=fstore.collection("event").document(userid);

                Map<String,Object> user = new HashMap<>();
                user.put("Name",name);
                user.put("Apartmentno",home);
                user.put("Date",date);
                user.put("Time",time);

                documentrefernce.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"OnSucess: details are registered..."+userid);
                        Intent i=new Intent(event.this,home.class);
                        startActivity(i);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"OnFailure: ..."+e.toString());
                    }
                });

                Toast.makeText(getApplicationContext(),"Details are registered successfully ...!!!",Toast.LENGTH_LONG).show();

            }
        });
    }
}
