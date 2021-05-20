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
        ArrayList<Button> buttonList = getButtonList();

        Button button = findViewById(clicked.getId());
        if(button.getText().length() == 0) {
            String player = determinePlayer();
            button.setText(player);
            switchPlayer();
        }

        if(isWon()) {
            for(Button buttonDisable : buttonList) {
                buttonDisable.setClickable(false);
            }
        }

        else if(!isWon() && isTie()) {
            for(Button buttonDisable : buttonList) {
                buttonDisable.setClickable(false);
            }
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
        ArrayList<Button> buttonList = getButtonList();

        for (Button button: buttonList) {
            button.setText("");
        }

        for (Button button: buttonList) {
            button.setClickable(true);
        }

        TextView turnText = findViewById(R.id.turnText);
        turnText.setText("Player X's Turn");
    }

    private ArrayList<Button> getButtonList() {
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
        return buttonList;
    }

    private boolean isWon() {
        String[][] buttonArray = buttonsAsArray();

        for(int row = 0; row < buttonArray.length; row++) {
            if(buttonArray[row][0].equals(buttonArray[row][1]) &&
                    buttonArray[row][0].equals(buttonArray[row][2]) &&
                    !buttonArray[row][0].isEmpty()) {
                setWinner(buttonArray[row][0]);
                return true;
            }
        }

        for(int column = 0; column < buttonArray[0].length; column++) {
            if(buttonArray[0][column].equals(buttonArray[1][column]) &&
                    buttonArray[0][column].equals(buttonArray[2][column]) &&
                    !buttonArray[0][column].isEmpty()) {
                setWinner(buttonArray[0][column]);
                return true;
            }
        }

        if(buttonArray[0][0].equals(buttonArray[1][1]) &&
                buttonArray[1][1].equals(buttonArray[2][2]) &&
                !buttonArray[0][0].isEmpty()) {
            setWinner(buttonArray[0][0]);
            return true;
        }

        if(buttonArray[2][0].equals(buttonArray[1][1]) &&
                buttonArray[1][1].equals(buttonArray[0][2]) &&
                !buttonArray[2][0].isEmpty()) {
            setWinner(buttonArray[2][0]);
            return true;
        }

        return false;
    }

    private boolean isTie() {
        boolean isTieGame = true;
        ArrayList<Button> buttonList = getButtonList();

        for(Button button : buttonList) {
            if(button.getText().toString().isEmpty()) {
                isTieGame = false;
            }
        }

        if(isTieGame) {
            TextView winnerText = findViewById(R.id.turnText);
            winnerText.setText("Game ended in a draw");
        }
        return isTieGame;
    }

    private String[][] buttonsAsArray() {
        ArrayList<Button> buttonList = getButtonList();

        String[][] buttonArray = new String[3][3];
        int index = 0;
        for(int row = 0; row < buttonArray.length; row++) {
            for(int column = 0; column < buttonArray[row].length; column++) {
                buttonArray[row][column] = buttonList.get(index).getText().toString();
                index++;
            }
        }
        return buttonArray;
    }

    private void setWinner(String winner) {
        TextView winnerText = findViewById(R.id.turnText);
        winnerText.setText("Player " + winner + " wins!");
    }
}