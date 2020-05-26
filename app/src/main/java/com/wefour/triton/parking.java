package com.wefour.triton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parking extends AppCompatActivity {


    ImageView imageViewarea1,imageViewarea_2,imageViewarea_3,imageViewarea_4,imageViewarea_5;
    TextView lblvalue,lblarea2,lblarea3,lblarea4,lblarea5;
    DatabaseReference dref;
    String areaone,areatwo,areathree,areafour,areafive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);


        lblvalue = (TextView) findViewById(R.id.lblvalue);
        lblarea2 = (TextView) findViewById(R.id.lblarea2);
        lblarea3 = (TextView) findViewById(R.id.lblarea3);
        lblarea4 = (TextView) findViewById(R.id.lblarea4);
        lblarea5 = (TextView) findViewById(R.id.lblarea5);
        imageViewarea1 = (ImageView) findViewById(R.id.imageViewarea1);
        imageViewarea_2 = (ImageView) findViewById(R.id.imageViewarea_2);
        imageViewarea_3 = (ImageView) findViewById(R.id.imageViewarea_3);
        imageViewarea_4 = (ImageView) findViewById(R.id.imageViewarea_4);
        imageViewarea_5 = (ImageView) findViewById(R.id.imageViewarea_5);
        dref= FirebaseDatabase.getInstance().getReference();

        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                areaone=dataSnapshot.child("namemekyarakhahe1").getValue().toString();
                areatwo = dataSnapshot.child("namemekyarakhahe2").getValue().toString();
                areathree = dataSnapshot.child("namemekyarakhahe3").getValue().toString();
                areafour = dataSnapshot.child("namemekyarakhahe4").getValue().toString();
                areafive = dataSnapshot.child("namemekyarakhahe5").getValue().toString();

                if(areaone.equals("true")){
                    imageViewarea1.setImageResource(R.drawable.empty1);
                }
                else if(areaone.equals("false")){
                    imageViewarea1.setImageResource(R.drawable.car4);
                }

                if(areatwo.equals("true")){
                    imageViewarea_2.setImageResource(R.drawable.empty1);
                }
                else if(areatwo.equals("false")){
                    imageViewarea_2.setImageResource(R.drawable.ncar3);
                }

                if(areathree.equals("true")){
                    imageViewarea_3.setImageResource(R.drawable.empty1);
                }
                else if(areathree.equals("false")){
                    imageViewarea_3.setImageResource(R.drawable.ncar1);
                }

                if(areafour.equals("true")){
                    imageViewarea_4.setImageResource(R.drawable.empty1);
                }
                else if(areafour.equals("false")){
                    imageViewarea_4.setImageResource(R.drawable.ncar2);
                }

                if(areafive.equals("true")){
                    imageViewarea_5.setImageResource(R.drawable.empty1);
                }
                else if(areafive.equals("false")){
                    imageViewarea_5.setImageResource(R.drawable.ncar6);
                }


//                lblvalue.setText(status);
//                lblarea2.setText(areatwo);
//                lblarea3.setText(areathree);
//                lblarea4.setText(areafour);
//                lblarea5.setText(areathree);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
