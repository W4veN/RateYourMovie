package com.example.hw_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class MovieActivity extends AppCompatActivity {

    private TextView tvtitle, tvdescription, tvcategory, value;
    private ImageView img;
    DatabaseReference reference;
    StorageReference mStorageRef;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtdesc);
        tvcategory = (TextView) findViewById(R.id.txtcategory);
        img = (ImageView) findViewById(R.id.imgmoviethumbnail);
        reference = FirebaseDatabase.getInstance().getReference().child("movies");
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        value = (TextView) findViewById(R.id.value);

        //Receive data
        Intent intent = getIntent();
        final String Title = intent.getExtras().getString("MovieTitle");
        String Description = intent.getExtras().getString("Description");
        String Category = intent.getExtras().getString("Category");
        String image = intent.getExtras().getString("Thumbnail");
        final String key = intent.getExtras().getString("Key");
        int rate = intent.getExtras().getInt("Rate");

        mStorageRef = FirebaseStorage.getInstance().getReference("Images");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float myRating, boolean fromUser) {
                reference.child(key).child("rate").setValue(myRating);
                value.setText(" " + myRating);
            }
        });

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        tvcategory.setText(Category);
        Picasso.get().load(image).into(img);
        ratingBar.setRating(rate);
    }
}

