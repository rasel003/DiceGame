package com.olivine.dicegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random rng = new Random();


    TextView tvPlayerTurn, tvPlayerOneScore, tvPlayerTwoScore;

    int rollNumber, playerOneScore, playerTwoScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollNumber = playerOneScore = playerTwoScore = 0;

        tvPlayerOneScore = findViewById(R.id.tvPlayerOneScore);
        tvPlayerTwoScore = findViewById(R.id.tvPlayerTwoScore);
        imageViewDice = findViewById(R.id.image_view_dice);
        tvPlayerTurn = findViewById(R.id.tvPlayerTurn);

        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private void rollDice() {

        int randomNumber = rng.nextInt(6) + 1;

        if( rollNumber%2==0 ){
            tvPlayerTurn.setText("Player One Turn");
            playerOneScore += randomNumber;
            tvPlayerOneScore.setText(String.valueOf(playerOneScore));
        }else{
            tvPlayerTurn.setText("Player Two Turn");
            playerTwoScore += randomNumber;
            tvPlayerTwoScore.setText(String.valueOf(playerTwoScore));
        }
        rollNumber++;

        switch (randomNumber) {
            case 1:
                imageViewDice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dice6);
                break;
        }

        if(playerOneScore>20){
            gameOver("Player One Won");
        }
        if(playerTwoScore > 20 ){
            gameOver("Player Two Won");
        }
    }
    void gameOver(String player){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(player).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        rollNumber = playerOneScore = playerTwoScore = 0;
                        tvPlayerOneScore.setText("0");
                        tvPlayerTwoScore.setText("0");
                        tvPlayerTurn.setText("Player One Turn");
                        imageViewDice.setImageResource(R.drawable.dice1);
                    }
                });
        builder.setTitle("Game Over");
        builder.create();
        builder.show();
    }
}