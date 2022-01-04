package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    int[] gameSet = { 2,2,2,2,2,2,2,2,2};

    int[][] winPosition = {{0,1,2} , {3,4,5} , {6,7,8}  , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};

    boolean gameActive = true;



// 0 = yellow , 1 = red;
    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameSet[tappedCounter] == 2 && gameActive) {
            gameSet[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.img_2);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.img_3);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(1000);

            for (int[] winningPosition : winPosition) {
                if (gameSet[winningPosition[0]] == gameSet[winningPosition[1]] && gameSet[winningPosition[1]] == gameSet[winningPosition[2]] && gameSet[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = " ";
                    if (activePlayer == 1) {
                        winner = "Yellow has won 😎";
                    } else {
                        winner = "Red has won 😎";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.resetButton);
                    TextView winnerText = (TextView) findViewById(R.id.textView);
                    winnerText.setText(winner);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }

            }
        }

    }
    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.resetButton);
        TextView winnerText = (TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gameSet, 2);
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}