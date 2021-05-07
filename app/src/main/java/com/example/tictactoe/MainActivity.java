package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchPlayer();
        Button newGameButton = findViewById(R.id.newGame);
        newGameButton.setText("New Game");
    }


    public void onClick(View clicked) {
        Button button = findViewById(clicked.getId());
        if(button.getText().length() == 0) {
            String player = determinePlayer();
            button.setText(player);
            switchPlayer();
        }
    }

    protected String determinePlayer() {
        TextView turnText = findViewById(R.id.turnText);
        if(turnText.getText().toString().contains("X")) {
            return "X";
        }

        else{
            return "O";
        }
    }

    protected void switchPlayer() {
        TextView turnText = findViewById(R.id.turnText);
        if(determinePlayer().equals("O") || turnText.getText().length() == 0) {
            turnText.setText("Player X's Turn");
        }

        else if(determinePlayer().equals("X")) {
            turnText.setText("Player O's Turn");
        }
    }

    // Will try to refactor this method to involve a loop to find the button ids
    // for cleaner, shorter code
    public void newGame(View view) {
        ArrayList<Button> buttonList = new ArrayList<>();

        buttonList.add(findViewById(R.id.ticTac1));
        buttonList.add(findViewById(R.id.ticTac2));
        buttonList.add(findViewById(R.id.ticTac3));
        buttonList.add(findViewById(R.id.ticTac4));
        buttonList.add(findViewById(R.id.ticTac5));
        buttonList.add(findViewById(R.id.ticTac6));
        buttonList.add(findViewById(R.id.ticTac7));
        buttonList.add(findViewById(R.id.ticTac8));
        buttonList.add(findViewById(R.id.ticTac9));

        for (Button button: buttonList) {
            button.setText("");
        }

        TextView turnText = findViewById(R.id.turnText);
        turnText.setText("Player X's Turn");
    }
}