/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private SimpleDictionary streamDictionary;
    private boolean userTurn = false;
    private String wordFragment = "";
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();

        try {
            streamDictionary = new SimpleDictionary(getAssets().open("words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        wordFragment ="";
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        System.out.println("Computer's turn.");
        if(streamDictionary.isWord(wordFragment) & (wordFragment.length() >= 4)){
            TextView statusLabel = (TextView)findViewById(R.id.gameStatus);
            statusLabel.setText("Computer Wins.");
        }
        else if(wordFragment.length() == 0){
            String aBetterWord = streamDictionary.getAnyWordStartingWith(wordFragment);
            wordFragment += aBetterWord.substring(aBetterWord.length());

        }
        else if(streamDictionary.getAnyWordStartingWith(wordFragment) != null){
            String betterWord = streamDictionary.getAnyWordStartingWith(wordFragment);
            //System.out.println("got here");
            wordFragment += betterWord.charAt(wordFragment.length());
           // System.out.println(wordFragment+ "better");
        }

        else {
            TextView statusLabel = (TextView)findViewById(R.id.gameStatus);
            statusLabel.setText("Computer Challenged Your Word And Won!");
            userTurn = false;
            return ;
        }
        TextView ghostLabel = (TextView)findViewById(R.id.ghostText);
        ghostLabel.setText(wordFragment);
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for user key presses.
     * @param keyCode
     * @param event
     * @return whether the key stroke was handled.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        char pressedKey = (char)event.getUnicodeChar();
        if(Character.isLetter(pressedKey)){
            wordFragment += pressedKey;
            userTurn = false;
            computerTurn();
        }


        TextView ghostLabel = (TextView)findViewById(R.id.ghostText);
        ghostLabel.setText(wordFragment);
        return super.onKeyUp(keyCode, event);
    }


    public void challengeHandler(View view){
        if((wordFragment.length()>= 4) & streamDictionary.isWord(wordFragment)){
            TextView statusLabel = (TextView)findViewById(R.id.gameStatus);
            statusLabel.setText("User Wins.");
        }

        else if(streamDictionary.getAnyWordStartingWith(wordFragment) != null){
            TextView statusLabel = (TextView)findViewById(R.id.gameStatus);
            statusLabel.setText("User Wins. Possible word is: " + streamDictionary.getAnyWordStartingWith(wordFragment));
        }

        else{
            TextView statusLabel = (TextView)findViewById(R.id.gameStatus);
            statusLabel.setText("User Wins.");
        }
    }
}
