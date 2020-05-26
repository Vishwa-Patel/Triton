package com.wefour.triton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button btnsubmit;
    private EditText txtuname, txtapt, txtcontact, txtemail, txtpass1, txtpass2;
    private RadioButton male, female;
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userid;
    DocumentReference documentrefernce;

    private  static final Pattern PASSWORD_PATTERN= Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$])(?=\\S+$).{6,}$");
    private static final Pattern PHONE_PATTERN=Pattern.compile("^"+
            "(?=.[0-9])"+
            "(?=\\S+$)"+".{10}"+"$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnsubmit = (Button) findViewById(R.id.submit);
        txtapt = (EditText) findViewById(R.id.txtapt);
        txtuname= (EditText) findViewById(R.id.txtuser);
        txtcontact = (EditText) findViewById(R.id.txtcontact);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpass1 = (EditText) findViewById(R.id.txtpass);
        txtpass2 = (EditText) findViewById((R.id.txtconfpass));
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        mAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        //databaseReference = FirebaseDatabase.getInstance().getReference("dregister");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, apt, email, contact, pass, gender,cnpass;
                uname = txtuname.getText().toString();
                apt = txtapt.getText().toString().trim();
                contact = txtcontact.getText().toString();
                email = txtemail.getText().toString().trim();
                pass = txtpass1.getText().toString().trim();
                cnpass=txtpass2.getText().toString().trim();
                if ( male. isChecked ()) {
                    gender="Male";
                    //Toast.makeText (getApplicationContext(),"male",Toast . LENGTH_LONG ). show ();
                } else{
                    gender="Female";
                    //Toast.makeText(getApplicationContext (),"female",Toast.LENGTH_LONG ). show ();
                }

                if (TextUtils.isEmpty(uname)) {
                    txtuname.setError("Username is required...");
                    return;
                }
                if (TextUtils.isEmpty(apt)) {
                    txtapt.setError("Apartment number is required...");
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    txtcontact.setError("Contact No is required...");
                    return;
                }

                if (!PHONE_PATTERN.matcher(contact).matches()) {
                    txtcontact.setError("Please enter valid phone number....");
                    return;
                }


                if (TextUtils.isEmpty(email)) {
                    txtemail.setError("Email id is required...");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtemail.setError("Please enter valid Email id...");
                    return;

                }
                if (TextUtils.isEmpty(pass)) {
                    txtpass1.setError("password is required...");
                    return;
                }
                if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                    txtpass1.setError("Please enter valid password...");
                    return;
                }
                if (TextUtils.isEmpty(cnpass)) {
                    txtpass2.setError("Please re-enter the password...");
                    return;
                }
                if (!pass.equals(cnpass))
                {
                    txtpass2.setError("The passwords doesn't match...");
                    return;
                }

                CreateUserAccount(uname,apt,contact,email,pass,gender);


            }

        });
    }

    private void CreateUserAccount(final String uname, final String apt, final String contact, final String email, final String pass,final String gen) {


        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            String type="User";
                            userid = mAuth.getCurrentUser().getUid();
                            documentrefernce = fstore.collection("Userdetails").document(userid);

                            Map<String,Object> user=new HashMap<>();
                            user.put("Account",type);
                            user.put("Gender",gen);
                            user.put("Password",pass);
                            user.put("ContactNo",contact);
                            user.put("E-mail",email);
                            user.put("ApartmentNo",apt);
                            user.put("Username",uname);

                            documentrefernce.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"OnSucess: user profile is created..."+userid);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"OnFailure: ..."+e.toString());
                                }
                            });

                            Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_LONG).show();


                            Intent i = new Intent(register.this, MainActivity.class);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Account creation failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

   /* public void openmain() {
        Intent i = new Intent(register.this, MainActivity.class);
        startActivity(i);
    } */
}


