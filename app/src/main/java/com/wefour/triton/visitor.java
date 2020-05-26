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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class visitor extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button btnvisitor;
    EditText edtvisitor_name,edtvisitor_contactno,edtvisitor_residentno,edtvisitor_relation,edtvisitor_entrytime,edtvisitor_exittime;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userid;
    DocumentReference documentrefernce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        btnvisitor=(Button)findViewById(R.id.btnevent);

        edtvisitor_name =(EditText)findViewById(R.id.edtvisitor_name);
        edtvisitor_contactno =(EditText)findViewById(R.id.edtvisitor_contactno);
        edtvisitor_residentno =(EditText)findViewById(R.id.edtvisitor_residentno);
        edtvisitor_relation =(EditText)findViewById(R.id.edtvisitor_relation);
        edtvisitor_entrytime=(EditText)findViewById(R.id.edtvisitor_entrytime);
        edtvisitor_exittime=(EditText)findViewById(R.id.edtvisitor_exittime);



        mAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        btnvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String dateToStr = format.format(today);

                String name,apt,contact,relation,enter,exit;

                name=edtvisitor_name.getText().toString();
                apt=edtvisitor_residentno.getText().toString();
                contact=edtvisitor_contactno.getText().toString();
                relation=edtvisitor_relation.getText().toString();
                enter=edtvisitor_entrytime.getText().toString();
                exit=edtvisitor_exittime.getText().toString();
                /*validation */
                if (TextUtils.isEmpty(name)) {
                    edtvisitor_name.setError("Visitor Name is required...");
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    edtvisitor_contactno.setError("Contact No is required...");
                    return;
                }
                if (TextUtils.isEmpty(apt)) {
                    edtvisitor_residentno.setError("Resident No is required...");
                    return;
                }
                if (TextUtils.isEmpty(relation)) {
                    edtvisitor_relation.setError("Relation is required...");
                    return;
                }
                if (TextUtils.isEmpty(enter)) {
                    edtvisitor_entrytime.setError("Entry time is required...");
                    return;
                }

                if (TextUtils.isEmpty(exit)) {
                    edtvisitor_exittime.setError("Exit time is required...");
                    return;
                }
                userid=mAuth.getCurrentUser().getUid();
                documentrefernce=fstore.collection("visitor").document(userid);

                Map<String,Object> user = new HashMap<>();
                user.put("Name",name);
                user.put("Apartmentno",apt);
                user.put("Contactno",contact);
                user.put("Relation",relation);
                user.put("Date",dateToStr);
                user.put("Entertime",enter);
                user.put("Exittime",exit);

                documentrefernce.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"OnSucess: details are registered..."+userid);
                        Intent i=new Intent(visitor.this,home.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"OnFailure: ..."+e.toString());
                    }
                });

                Toast.makeText(getApplicationContext(),"Details are recorded successfully ...!!!",Toast.LENGTH_LONG).show();

            }
        });


    }
}
