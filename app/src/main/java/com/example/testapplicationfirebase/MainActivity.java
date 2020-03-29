package com.example.testapplicationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtID, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.EtID);
        txtName = findViewById(R.id.EtName);
        txtAdd = findViewById(R.id.EtAddress);
        txtConNo = findViewById(R.id.EtConNo);

        btnSave = findViewById(R.id.BtnSave);
        btnShow = findViewById(R.id.BtnShow);
        btnUpdate = findViewById(R.id.BtnUpdate);
        btnDelete = findViewById(R.id.BtnDelete);

        dbRef.child("Std1").setValue(std);
        std = new Student();
    }

    private void clearControls() {
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }


    DatabaseReference getDbRef = FirebaseDatabase.getInstance().getReference().child("Student");
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            if (TextUtils.isEmpty(txtID.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();

            std.setID(txtID.getText().toString().trim());
            std.setName(txtName.getText().toString().trim());
            std.setAddress(txtAdd.getText().toString().trim());
            std.setConNo(txtConNo.getText().toString().trim());

            dbRef.push().setValue(std);
            Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            clearControls();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid Contact Number ", Toast.LENGTH_SHORT).show();
        }

    }






    DatabaseReference readDbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
    raedRef.addListenerForStrigleValueEvent(new ValueEventListener){
        public void onDataChange (DataSnapshot dataSnapshot){
        if (datSnapshot.hasChildren()) {
            txtID.setText(dataSnapshot.child("id").getValue().toString());
            txtName.setText(dataSnapshot.child("name").getValue().toString());
            txtAdd.setText(dataSnapshot.child("address").getValue().toString());
            txtConNo.setText(dataSnapshot.child("conNo").getValue().toString());
        } else
            Toast.makeText(getApplicationContext(), "No source to display ", Toast.LENGTH_SHORT).show();
    }
        public void onCancelled (DatabaseError databaseError){
    }
    }






    DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
     updRef.addListenerForStrigleValueEvent(new ValueEventListener){
        public void onDataChange (DataSnapshot dataSnapshot){
        if (datSnapshot.hasChildren("Std1")) {
            try {

                std.setID(txtID.getText().toString().trim());
                std.setName(txtName.getText().toString().trim());
                std.setAddress(txtAdd.getText().toString().trim());
                std.setConNo(txtConNo.getText().toString().trim());

                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                dbRef.setValue(std);
                clearControls();

                Toast.makeText(getApplicationContext(), "Data updated successfully ", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid Contact Number ", Toast.LENGTH_SHORT).show();
            }
        }
    } }






    DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Student");
     delRef.addListenerForStrigleValueEvent(new ValueEventListener){
        public void onDataChange (DataSnapshot dataSnapshot){
        if (datSnapshot.hasChildren("Std1")) {

            dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
            dbRef.removeValue();
            clearControls();

            Toast.makeText(getApplicationContext(), "Data deleted successfully ", Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(getApplicationContext(), "No source to delete ", Toast.LENGTH_SHORT).show();
    }
        public void onCancelled (DatabaseError databaseError){
    } }

}

