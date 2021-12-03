package com.example.database;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
private EditText editTextTextPersonName, editTextTextPersonName2, editTextTextPersonName3;
private Button insert, delete, update, view;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);

        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        view = findViewById(R.id.view);

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        //button insert
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt =  editTextTextPersonName.getText().toString();
                String contacttxt  =  editTextTextPersonName.getText().toString();
                String dobtxt =  editTextTextPersonName.getText().toString();

                FirebaseApp checkinsertdata = db.insertuserdata(nametxt, contacttxt, dobtxt);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New entry inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New was not entry inserted", Toast.LENGTH_SHORT).show();
            }
        });

//        //button delete
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nametxt =  editTextTextPersonName.getText().toString();
//                String contacttxt  =  editTextTextPersonName.getText().toString();
//                String dobtxt =  editTextTextPersonName.getText().toString();
//
//                Boolean checkdeletedata = DB.insertuserdata(nametxt, contacttxt, dobtxt);
//                if (checkdeletedata == true)
//                    Toast.makeText(MainActivity.this, "New entry deleted", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "New was not entry deleted", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //button update
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nametxt =  editTextTextPersonName.getText().toString();
//                String contacttxt  =  editTextTextPersonName.getText().toString();
//                String dobtxt =  editTextTextPersonName.getText().toString();
//
//                Boolean checkupdatedata = DB.insertuserdata(nametxt, contacttxt, dobtxt);
//                if (checkupdatedata == true)
//                    Toast.makeText(MainActivity.this, "New entry updated", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "New was not entry updated", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //button view
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//          public void onClick(View view) {
//
//                Cursor res = DB.getData();
//                if (res.getCount() == 0) {
//                    Toast.makeText(MainActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("Name : " + res.getString(0) + "\n");
//                    buffer.append("contact : " + res.getString(1) + "\n");
//                    buffer.append("dob : " + res.getString(2) + "\n");
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Entries");
//                builder.setMessage(buffer.toString());
//                builder.show();
//            }
//        });
    }
}