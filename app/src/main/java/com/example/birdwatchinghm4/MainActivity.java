package com.example.birdwatchinghm4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Part A

    Button buttonSubmit;
    EditText editTextZipCode, editTextBirdName, editTextWatcherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Part B

    buttonSubmit = findViewById(R.id.buttonSubmit);

    editTextZipCode = findViewById(R.id.editTextZipCode);
    editTextBirdName = findViewById(R.id.editTextBirdName);
    editTextWatcherName = findViewById(R.id.editTextWatcherName);

    buttonSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (view == buttonSubmit) {

            String createZipCode = editTextZipCode.getText().toString();
            String createBirdName = editTextBirdName.getText().toString();
            String createWatcherName = editTextWatcherName.getText().toString();

            DB createDB = new DB(createZipCode, createBirdName, createWatcherName);

            myRef.push().setValue(createDB);

            editTextZipCode.getText().clear();
            editTextBirdName.getText().clear();
            editTextWatcherName.getText().clear();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigationmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemSearchScreen) {

            Intent landingIntent = new Intent(this, SearchActivity.class);
            startActivity(landingIntent);

        } else if(item.getItemId() == R.id.itemMain) {

            Toast.makeText(this, "You are already in the Main page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}
