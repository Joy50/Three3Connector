package com.joy50.three3connector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int active_player = 0;
    int [] state = {2,2,2,2,2,2,2,2,2};
    int [] [] winning_combinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsactive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DropIn(View view) {
        ImageView counter = (ImageView)view;
        counter.setTranslationY(-1000);
        //counter.setImageResource(R.drawable.red);
        int tapped_state = Integer.parseInt(counter.getTag().toString());
        if(state[tapped_state]==2) {
            state[tapped_state] = active_player;
            if (active_player == 0) {
                counter.setImageResource(R.drawable.red);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.yllow);
                active_player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(60f).setDuration(300);
            for (int[] winning_combo : winning_combinations){
                if (state[winning_combo[0]] == state[winning_combo[1]] &&
                        state[winning_combo[1]] == state[winning_combo[2]] &&
                        state[winning_combo[0]] !=2){
                    gameIsactive = false;
                    LinearLayout layout = (LinearLayout)findViewById(R.id.popup);
                    String winner = "Yellow";
                    TextView text = (TextView)findViewById(R.id.textView2);
                    if(state[winning_combo[0]] == 0){
                        winner = "Red";
                    }
                    text.setText(winner + " Has won!");
                    layout.setVisibility(view.VISIBLE);
                }
            }
        }
    }

    public void PlayAgain(View view) {
        LinearLayout layout = findViewById(R.id.popup);
        layout.setVisibility(view.INVISIBLE);
        active_player = 0;
        for (int i=0 ; i<state.length ; i++){
            state[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.grid);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}
