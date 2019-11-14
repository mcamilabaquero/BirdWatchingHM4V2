package com.example.birdwatchinghm4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    //Part A

    Button buttonSearch;
    EditText editTextZipCode2;
    TextView textViewBirdName;
    TextView textViewWatcherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    //Part B

        buttonSearch = findViewById(R.id.buttonSearch);

        editTextZipCode2 = findViewById(R.id.editTextZipCode2);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewWatcherName = findViewById(R.id.textViewWatcherName);

        buttonSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (view == buttonSearch) {

            String searchZipCode = editTextZipCode2.getText().toString();

            myRef.orderByChild("ZipCode").equalTo(searchZipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    //String

                    DB foundDB = dataSnapshot.getValue(DB.class);
                    String searchBirdName = foundDB.BirdName;
                    String searchWatcherName = foundDB.WatcherName;

                    textViewBirdName.setText(searchBirdName);
                    textViewWatcherName.setText(searchWatcherName);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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

        if(item.getItemId() == R.id.itemMain) {

            Intent landingIntent = new Intent(this, MainActivity.class);
            startActivity(landingIntent);

        } else if(item.getItemId() == R.id.itemSearchScreen) {

            Toast.makeText(this, "You are already in the Search page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    }
