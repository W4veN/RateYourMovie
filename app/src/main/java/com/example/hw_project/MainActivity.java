package com.example.hw_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnim,bottomAnim;
    ImageView movieImage;
    TextView checkMyMovieApptextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // fullscreen - no bar

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        movieImage = findViewById(R.id.movieImageView);
        movieImage.setAnimation(topAnim);

        checkMyMovieApptextview=findViewById(R.id.check_my_movie_text);
        checkMyMovieApptextview.setAnimation(bottomAnim);
    }

    public void goToAppMethod(View view) {
        Intent goToMenuIntent;
        goToMenuIntent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(goToMenuIntent);
    }
}