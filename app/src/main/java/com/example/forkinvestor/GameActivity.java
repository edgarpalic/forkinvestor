package com.example.forkinvestor;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    /////////////////////////// TEXT VIEW ///////////////////////////

    TextView countdownTextView, pointsTextView, woodCostTextView, woodInvestmentTextView, ironCostTextView, ironInvestmentTextView, silverCostTextView, silverInvestmentTextView, goldCostTextView, goldInvestmentTextView, systemTextView, clickTextView;

    /////////////////////////// BUTTONS ////////////////////////////

    Button workButton, buyWoodButton, buyIronButton, buySilverButton, buyGoldButton;

    ////////////////////////// VARIABLES ///////////////////////////

    public int totalMoney = 0;
    public int clickMoney = 1;
    public int timeCount = 60;

    public int woodBuyCost = 10;
    public int woodInvestAmount = 0;
    public int ironBuyCost = 100;
    public int ironInvestAmount = 0;
    public int silverBuyCost = 500;
    public int silverInvestAmount = 0;
    public int goldBuyCost = 1000;
    public int goldInvestAmount = 0;

    Context context = this;
    CountDownTimer cdTimer;
    MediaPlayer bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.bounce);

        countdownTextView = findViewById(R.id.countdownTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        woodCostTextView = findViewById(R.id.woodCostTextView);
        woodInvestmentTextView = findViewById(R.id.woodInvestmentTextView);
        ironCostTextView = findViewById(R.id.ironCostTextView);
        ironInvestmentTextView = findViewById(R.id.ironInvestmentTextView);
        silverCostTextView = findViewById(R.id.silverCostTextView);
        silverInvestmentTextView = findViewById(R.id.silverInvestmentTextView);
        goldCostTextView = findViewById(R.id.goldCostTextView);
        goldInvestmentTextView = findViewById(R.id.goldInvestmentTextView);
        systemTextView = findViewById(R.id.systemTextView);
        clickTextView = findViewById(R.id.clickTextView);

        workButton = findViewById(R.id.workButton);
        buyWoodButton = findViewById(R.id.buyWoodButton);
        buyIronButton = findViewById(R.id.buyIronButton);
        buySilverButton = findViewById(R.id.buySilverButton);
        buyGoldButton = findViewById(R.id.buyGoldButton);

        countdownTextView.setText(Integer.toString(timeCount));

        final MediaPlayer[] buySound = {MediaPlayer.create(this, R.raw.ching)};
        bgMusic = MediaPlayer.create(GameActivity.this, R.raw.bgmusic);

        colorCode();
        uiMoneyDisplay();
        countDown();

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalMoney += clickMoney;
                uiMoneyDisplay();
                colorCode();
                systemTextView.setText("You earned " + clickMoney + " money!");
            }
        });

        buyWoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMoney >= woodBuyCost){
                    buyWoodButton.startAnimation(myAnim);
                    totalMoney -= woodBuyCost;
                    woodInvestAmount += 1;
                    woodBuyCost *= 2;
                    clickMoney += 1;
                    systemTextView.setText("Wooden fork purchased!");
                    uiMoneyDisplay();
                    colorCode();

                        if (buySound[0].isPlaying()) {
                            buySound[0].stop();
                            buySound[0].release();
                            buySound[0] = MediaPlayer.create(context, R.raw.ching);
                        } buySound[0].start();

                }else{
                    systemTextView.setText("You cannot affork it!");
                }
            }
        });

        buyIronButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMoney >= ironBuyCost){
                    buyIronButton.startAnimation(myAnim);
                    totalMoney -= ironBuyCost;
                    ironInvestAmount += 1;
                    ironBuyCost *= 2;
                    clickMoney += 10;
                    systemTextView.setText("Iron fork purchased!");
                    uiMoneyDisplay();
                    colorCode();

                    if (buySound[0].isPlaying()) {
                        buySound[0].stop();
                        buySound[0].release();
                        buySound[0] = MediaPlayer.create(context, R.raw.ching);
                    } buySound[0].start();

                }else{
                    systemTextView.setText("You cannot affork it!");
                }
            }
        });

        buySilverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMoney >= silverBuyCost){
                    buySilverButton.startAnimation(myAnim);
                    totalMoney -= silverBuyCost;
                    silverInvestAmount += 1;
                    silverBuyCost *= 2;
                    clickMoney += 50;
                    systemTextView.setText("Silver fork purchased!");
                    buySound[0].start();
                    uiMoneyDisplay();
                    colorCode();

                    if (buySound[0].isPlaying()) {
                        buySound[0].stop();
                        buySound[0].release();
                        buySound[0] = MediaPlayer.create(context, R.raw.ching);
                    } buySound[0].start();

                }else{
                    systemTextView.setText("You cannot affork it!");
                }
            }
        });

        buyGoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalMoney >= goldBuyCost){
                    buyGoldButton.startAnimation(myAnim);
                    totalMoney -= goldBuyCost;
                    goldInvestAmount += 1;
                    goldBuyCost *= 2;
                    clickMoney += 100;
                    systemTextView.setText("Golden fork purchased!");
                    buySound[0].start();
                    uiMoneyDisplay();
                    colorCode();

                    if (buySound[0].isPlaying()) {
                        buySound[0].stop();
                        buySound[0].release();
                        buySound[0] = MediaPlayer.create(context, R.raw.ching);
                    } buySound[0].start();

                }else{
                    systemTextView.setText("You cannot affork it!");
                }
            }
        });

    }

    public void colorCode(){
        if(totalMoney >= woodBuyCost){
            buyWoodButton.setBackgroundColor(Color.GREEN);
        }else{
            buyWoodButton.setBackgroundColor(Color.WHITE);
        }

        if(totalMoney >= ironBuyCost){
            buyIronButton.setBackgroundColor(Color.GREEN);
        }else{
            buyIronButton.setBackgroundColor(Color.WHITE);
        }

        if(totalMoney >= silverBuyCost){
            buySilverButton.setBackgroundColor(Color.GREEN);
        }else{
            buySilverButton.setBackgroundColor(Color.WHITE);
        }

        if(totalMoney >= goldBuyCost){
            buyGoldButton.setBackgroundColor(Color.GREEN);
        }else{
            buyGoldButton.setBackgroundColor(Color.WHITE);
        }
    }

    public void uiMoneyDisplay() {

        pointsTextView.setText("Money: $" + totalMoney);
        woodCostTextView.setText("Cost: $" + woodBuyCost);
        ironCostTextView.setText("Cost: $" + ironBuyCost);
        silverCostTextView.setText("Cost: $" + silverBuyCost);
        goldCostTextView.setText("Cost: $" + goldBuyCost);
        clickTextView.setText("$" + (clickMoney));

        if(woodInvestAmount >= 1){
            woodInvestmentTextView.setText("Amount: " + woodInvestAmount);
        }else{
            woodInvestmentTextView.setText("Amount: 0");
        }

        if(ironInvestAmount >= 1){
            ironInvestmentTextView.setText("Amount: " + ironInvestAmount);
        }else{
            ironInvestmentTextView.setText("Amount: 0");
        }

        if(silverInvestAmount >= 1){
            silverInvestmentTextView.setText("Amount: " + silverInvestAmount);
        }else{
            silverInvestmentTextView.setText("Amount: 0");
        }

        if(goldInvestAmount >= 1){
            goldInvestmentTextView.setText("Amount: " + goldInvestAmount);
        }else{
            goldInvestmentTextView.setText("Amount: 0");
        }
    }

    //a simple countdown method
    public void countDown(){
        cdTimer = new CountDownTimer(timeCount * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(String.valueOf(timeCount));
                timeCount--;
            }

            @Override
            public void onFinish() {
                String finalScore = String.valueOf(totalMoney);
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("finalScore", finalScore);
                startActivity(intent);
            }
        }.start();
    }

    //had to add a countdown timer cancel method when player pushes the hardware back button because it kept counting down and running the onFinish method.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            onStop();
            cdTimer.cancel();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        bgMusic.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
            bgMusic.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
            bgMusic.stop();
    }

}
