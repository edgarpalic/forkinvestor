package com.example.forkinvestor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    ArrayList<UserScore> userList = new ArrayList<>();
   ListComp listSort = new ListComp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        ImageView imageView = findViewById(R.id.imageView);
        ListView listView = findViewById(R.id.listView);
        final Button startButton = findViewById(R.id.startButton);
        imageView.setImageResource(R.drawable.forklogo);

        ScoreListAdapter adapter = new ScoreListAdapter(this, R.layout.list_item, userList);
        listView.setAdapter(adapter);

        getData();
        Collections.sort(userList, listSort);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.startAnimation(myAnim);
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("scoreboard");
        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    String name = childSnapshot.child("userName").getValue(String.class);
                    String score = childSnapshot.child("userScore").getValue(String.class);

                    UserScore user = new UserScore(name, score);
                    userList.add(user);
                    Collections.sort(userList, listSort);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}
