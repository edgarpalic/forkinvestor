package com.example.forkinvestor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EndActivity extends AppCompatActivity {

    private TextView theScoreTextView;
    private EditText theUser;
    private UserScore userScore;
    private String name;
    private String score;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("scoreboard");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        theScoreTextView = findViewById(R.id.finalScoreTextView);
        theUser = findViewById(R.id.editText);
        final Button upload = findViewById(R.id.uploadButton);
        final Button back = findViewById(R.id.backButton);

        Bundle extras = getIntent().getExtras();
        final String theScore = extras.getString("finalScore");
        theScoreTextView.setText(theScore);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload.startAnimation(myAnim);
                name = theUser.getText().toString().trim();
                score = theScoreTextView.getText().toString().trim();

                userScore = new UserScore(name, score);

                if(!name.equals("")){
                    userScore.setUserName(name);
                    userScore.setUserScore(score);

                    myRef.push().setValue(userScore);

                    Toast.makeText(getApplicationContext(),"Score uploaded successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"You need a name/gamertag!", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    //hardware back button will send you to first page instead of the previous one.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}
