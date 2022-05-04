package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:red 1:yellow 2:empty
    int activePlayer=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,5,8},{2,4,6},{3,4,5},{6,7,8}};

    public void dropIn(View view){
        ImageView image = (ImageView) view;
        int tappedTag = Integer.parseInt(image.getTag().toString());

        if(gameState[tappedTag] == 2) {
            image.setTranslationY(-1500);
            image.animate().translationYBy(1500).rotation(3600).setDuration(300);
            gameState[tappedTag] = activePlayer;

            if (activePlayer == 0) {
                image.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPosition) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&  (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && (gameState[winningPosition[0]] != 2) ) {
                    String winner;
                    if (activePlayer == 1)
                        winner = "red";
                    else
                        winner = "yellow";
                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}