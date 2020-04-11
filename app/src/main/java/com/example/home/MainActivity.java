package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView sms;
    Button record , stop;
    DatabaseReference reff;
    String myvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        record=findViewById(R.id.record);
        stop=findViewById(R.id.stop);
        sms=findViewById(R.id.sms);
        reff= FirebaseDatabase.getInstance().getReference().child("recorder");
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.child("home1").setValue("1");
                sms.setText("inserted");

            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myvalue=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                sms.setText("failed");
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+myvalue, Toast.LENGTH_SHORT).show();
                //reff.removeValue();
                //Toast
            }
        });

    }
}
