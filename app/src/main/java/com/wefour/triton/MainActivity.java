package com.wefour.triton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    Button btnreg;
    Button signinbtn;
    FirebaseFirestore fstore;
    private EditText emailid,pass;
    String userid,ac;
    DocumentReference documentReference;
    // Spinner spin;
    //String str[]={"Select Account Type","Admin","User"};
    //String cat,userid,type;
    //private  static final Pattern PASSWORD_PATTERN= Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$])(?=\\S+$).{6,}$");

    private FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnreg=(Button)findViewById(R.id.btnreg);
        signinbtn=(Button)findViewById(R.id.signinbtn);
        emailid=(EditText) findViewById(R.id.emailid);
        pass=(EditText) findViewById(R.id.pass);


        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        btnreg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i=new Intent(MainActivity.this,register.class);
                startActivity(i);
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                final String mail=emailid.getText().toString();
                final String password=pass.getText().toString();

                if (TextUtils.isEmpty(mail)) {
                    emailid.setError("email is required...");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    emailid.setError("Email-id should be valid...");
                    return;

                }

                if (TextUtils.isEmpty(password)) {
                    pass.setError("Password is required...");
                    return;
                }

                /* if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    pass.setError("Please enter valid password...");
                    return;
                } */


                if(mail.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fauth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                openahome();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Username or Password is incorrect !!!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
        /***************spinner code************************************
        spin = ( Spinner ) findViewById (R.id.spinner4);
        spin . setOnItemSelectedListener (this);
        ArrayAdapter aa = new ArrayAdapter ( this ,android.R.layout.simple_spinner_item,str);
        aa.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item );
        spin . setAdapter (aa); */

    }

    /*
    @Override
    public void onItemSelected (AdapterView<?> arg0, View arg1 , int pos, long id) {

        //int pos show's selected position nd first by default take first position
        if (str[pos] == "Admin") {
            // Toast.makeText(getApplicationContext(), str[pos], Toast.LENGTH_LONG).show();
            cat="Admin";
        }
        else if(str[pos] == "User")
        {
            cat="User";
        }
    }
    @Override
    public void onNothingSelected ( AdapterView<?> arg0 ) {
        //Toast. makeText ( getApplicationContext (),"hello", Toast . LENGTH_LONG ). show ();

    } */

    public void openahome()
    {
        //Toast.makeText(getApplicationContext(), "in open a home", Toast.LENGTH_SHORT).show();
        userid= fauth.getCurrentUser().getUid();

       // Toast.makeText(getApplicationContext(),"uid:"+userid,Toast.LENGTH_LONG).show();

        documentReference= fstore.collection("Userdetails").document(userid);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                String type = documentSnapshot.getString("Account");
                ac=type;
                //Toast.makeText(getApplicationContext(),"type:"+ac,Toast.LENGTH_LONG).show();

                if(ac.equals("Admin"))
                {
                    Intent i=new Intent(MainActivity.this,adminhome.class);
                    startActivity(i);
                }
                else if(ac.equals("User"))
                {
                    Intent i=new Intent(MainActivity.this,home.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"User Account doesn't exist...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
