package com.example.hw_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class AddMovieActivity extends AppCompatActivity {

    movie mov;
    Button btn;
    TextView title;
    TextView desc;
    TextView category;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        btn = findViewById(R.id.buttonadd);
        title = findViewById(R.id.addTitleId);
        desc=findViewById(R.id.addDescriptionId);
        category=findViewById(R.id.addCategoryId);
        Intent intent = getIntent();
        reference = FirebaseDatabase.getInstance().getReference().child("movies");
        mov = new movie();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mov.setCategory(category.getText().toString().trim());
                mov.setDescription(desc.getText().toString().trim());
                mov.setTitle(title.getText().toString().trim());
                final String titleString = title.getText().toString();
                mov.setThumbnail("https://firebasestorage.googleapis.com/v0/b/hwproject-36b65.appspot.com/o/krists-luhaers-AtPWnYNDJnM-unsplash.jpg?alt=media&token=c7dcd96e-a760-499d-a4fa-26ef724505e0");
                reference.child(UUID.randomUUID().toString()).setValue(mov);
                String movieID=mov.getKey();

                Context context = getApplicationContext();
                CharSequence text = "Video was added successfully!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}

