package com.example.hw_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    List<movie> lstMovie;
    FloatingActionButton btnAdd;
    private RecyclerViewAdapter myAdapter;
    long maxid=0;
    DatabaseReference reference;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        lstMovie= new ArrayList<>();
        final RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this,lstMovie);
        btnAdd = findViewById(R.id.fab);
        reference = FirebaseDatabase.getInstance().getReference().child("movies");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lstMovie.clear();

                if(dataSnapshot.exists())
                    maxid= (dataSnapshot.getChildrenCount());

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    movie m = dataSnapshot1.getValue(movie.class);
                    m.setKey(dataSnapshot1.getKey());
                    lstMovie.add(m);
                }
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MenuActivity.this, "Opsss..Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        myAdapter = new RecyclerViewAdapter(this,lstMovie);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

    }

    public void AddMovieMethod(View view) {
        Intent intent = new Intent(this,AddMovieActivity.class);
        intent.putExtra("id",String.valueOf(maxid));
        this.startActivity(intent);
    }

}