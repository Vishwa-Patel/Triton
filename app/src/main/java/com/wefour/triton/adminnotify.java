package com.wefour.triton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.os.Bundle;
import android.text.format.DateUtils;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class adminnotify extends AppCompatActivity {

    EditText adnotify;
    Button btnnotifyadd;
    public static final String TAG = "TAG";
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    DocumentReference documentReference;
    String userid;
    String notify;
    public static int id=1;
    // public static final String DATE_FORMAT_2 = "dd-MMM-yyyy";
   // public static final String DATE_FORMAT_1 = "hh:mm a";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminnotify);

        adnotify=(EditText)findViewById(R.id.adnotify);
        btnnotifyadd=(Button)findViewById(R.id.btnnotifyadd);
        mAuth= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userid= mAuth.getCurrentUser().getUid();
       // documentReference= fstore.collection("Userdetails").document(userid);

        btnnotifyadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date today = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String dateToStr = format.format(today);

                String notify= adnotify.getText().toString();
                String uid=Integer.toString(id);

                documentReference = fstore.collection("notification").document(uid);

                Map<String,Object> user=new HashMap<>();
                user.put("Notice",notify);
                user.put("Date",dateToStr);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"OnSucess: notice is registered..."+userid);
                        id++;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"OnFailure: ..."+e.toString());
                    }
                });

                Toast.makeText(getApplicationContext(),"Your Notice is registered ...!!!",Toast.LENGTH_LONG).show();

                //  Intent i = new Intent(complaint.this, MainActivity.class);
                //  startActivity(i);

            }

        });

        }

   /* public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_2);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }  */


}
