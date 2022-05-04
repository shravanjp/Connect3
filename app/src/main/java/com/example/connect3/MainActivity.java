package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0:red 1:yellow 2:empty
    int activePlayer = 0;
    boolean gameActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,5,8},{2,4,6},{3,4,5},{6,7,8}};

    public void dropIn(View view){
        if( gameActive ) {
            ImageView image = (ImageView) view;
            int tappedTag = Integer.parseInt(image.getTag().toString());

            if (gameState[tappedTag] == 2) {
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
                    if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && (gameState[winningPosition[0]] != 2)) {
                        String winner;
                        if (activePlayer == 1)
                            winner = "Red";
                        else
                            winner = "Yellow";

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);

                        winnerTextView.setText(winner + " has won the game");
                        winnerTextView.setVisibility(View.VISIBLE);
                        playAgainBtn.setVisibility(View.VISIBLE);
                        gameActive = false;
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainBtn.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout boardGrid = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.boardGrid);
        for(int i = 0; i < boardGrid.getChildCount(); i++) {
            ImageView child = (ImageView) boardGrid.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}