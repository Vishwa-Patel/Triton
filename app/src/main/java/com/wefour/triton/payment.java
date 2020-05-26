//package com.wefour.triton;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class payment extends AppCompatActivity {
//    EditText orderid, custid;
//    Button btnpay;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payment);
//
//        //initOrderId();
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        btnpay = (Button) findViewById(R.id.btnpay);
//        orderid = (EditText) findViewById(R.id.orderid);
//        custid = (EditText) findViewById(R.id.custid);
//
//        btnpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(payment.this, checksum.class);
//                intent.putExtra("orderid", orderid.getText().toString());
//                intent.putExtra("custid", custid.getText().toString());
//                startActivity(intent);
//            }
//        });
//        if (ContextCompat.checkSelfPermission(payment.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(payment.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
//        }
//
//    }
//}
