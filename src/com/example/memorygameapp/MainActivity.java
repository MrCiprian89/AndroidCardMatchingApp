package com.example.memorygameapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	//Game Objects
	private ImageButton myImageButton0,myImageButton1,myImageButton2,myImageButton3,myImageButton4,myImageButton5,myImageButton6,myImageButton7,myImageButton8,myImageButton9
	,myImageButton10,myImageButton11,myImageButton12,myImageButton13,myImageButton14,myImageButton15;
	private ImageButton[] imageButtons =  { myImageButton0,myImageButton1,myImageButton2,myImageButton3,myImageButton4,myImageButton5,myImageButton6,myImageButton7,myImageButton8,myImageButton9
											,myImageButton10,myImageButton11,myImageButton12,myImageButton13,myImageButton14,myImageButton15};;
	private EditText timerLabel;
	private EditText scoreLabel;
	private TextView dsplayMessage;
	final Handler handler = new Handler();
	//stores the unique value pairs of cards
	HashMap<Integer, String> grid = new HashMap<Integer, String>();

	//stores the index of the cards face up. if none up start as - number
	int card1 = -1;
	int card2 = -1;
	//keeps time after game starts
	public CountDownTimer cd;
	//flag for when game starts and timer starts
	private Boolean gameStarted;
	//flag for a correct match
	boolean correctAnswer = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 //create a reference to Controls an store buttons in array	
		this.timerLabel = (EditText) findViewById(R.id.editText2);
		this.scoreLabel = (EditText) findViewById(R.id.editText1);
		this.dsplayMessage = (TextView) findViewById(R.id.displayMessageTextView);
		
		imageButtons[0] = this.myImageButton0 = (ImageButton) findViewById(R.id.ImageButton01);
		imageButtons[1] = this.myImageButton1 = (ImageButton) findViewById(R.id.ImageButton02);
		imageButtons[2] = this.myImageButton2 = (ImageButton) findViewById(R.id.ImageButton03);
		imageButtons[3] = this.myImageButton3 = (ImageButton) findViewById(R.id.ImageButton04);
		imageButtons[4] = this.myImageButton4 = (ImageButton) findViewById(R.id.ImageButton05);
		imageButtons[5] = this.myImageButton5 = (ImageButton) findViewById(R.id.ImageButton06);
		imageButtons[6] = this.myImageButton6 = (ImageButton) findViewById(R.id.ImageButton07);
		imageButtons[7] = this.myImageButton7 = (ImageButton) findViewById(R.id.ImageButton08);
		imageButtons[8] = this.myImageButton8 = (ImageButton) findViewById(R.id.ImageButton09);
		imageButtons[9] = this.myImageButton9 = (ImageButton) findViewById(R.id.ImageButton10);
		imageButtons[10] = this.myImageButton10 = (ImageButton) findViewById(R.id.ImageButton11);
		imageButtons[11] = this.myImageButton11 = (ImageButton) findViewById(R.id.ImageButton12);
		imageButtons[12] = this.myImageButton12 = (ImageButton) findViewById(R.id.ImageButton13);
		imageButtons[13] = this.myImageButton13= (ImageButton) findViewById(R.id.ImageButton14);
		imageButtons[14] = this.myImageButton14 = (ImageButton) findViewById(R.id.ImageButton15);
		imageButtons[15] = this.myImageButton15 = (ImageButton) findViewById(R.id.ImageButton16);
		
	//LOOP to add event listeners to each button		
	for( int i = 15 ; i >= 0; i -- ) {
		imageButtons[i].setBackgroundColor(android.R.drawable.btn_default);
		final int cardIndex = i;
			imageButtons[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//call cardPicked when the button is clicked, sending its array index and
					//the string the card generated
					selectCardButton(cardIndex);
				} //method onClick ends
			});	//END	setOnClickListener
		}//END for
	

	
	//Initialize the game
	Iterator<String> iterator = pickEightCards().iterator();
	int key=0;
	//LOOP through and store the random card values
	while (iterator.hasNext()) {
		String value = (String) iterator.next();
		grid.put(key, value);
		key++;
	}
	gameStarted = false;
	
}//END onCreate()
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//EVENT LISTENER ########################################################################
	/*
	 * Method starts the timer at beginning of game and then flips cards face up
	 * @param take an integer that keeps track of the index of the card that was clicked
	 */
	 private void selectCardButton(int cardIndex) {
		 
		 //RED's code to start a timer and only run it once 
		 if (gameStarted == false){
			 gameStarted = true;
		 
		 //creates a timer at 30 seconds and starts counting down after the first click
		 cd = new CountDownTimer(30000, 1000) {

				//onTick update the timerTextView with how many seconds are remaining
			     public void onTick(long millisUntilFinished) {
			    	 timerLabel .setText("" + millisUntilFinished / 1000);
			     }

				@Override
				public void onFinish() {
					//when the timer runs out
					
				}
			}.start(); //starts timer on creation
		 
		 }	//END if
		 
		 String cardString = grid.get(cardIndex);
		

		 
		//Store face up card values
		if(card1 == cardIndex){
			card1 = -1;
			this.imageButtons[cardIndex].setImageResource(getResources().getIdentifier("cardback", "drawable" ,getPackageName()));
			imageButtons[cardIndex].setBackgroundColor(android.R.drawable.btn_default);
		}
		else if(card1 < 0){
			 //setup highlight of selected
			 imageButtons[cardIndex].setBackgroundColor(Color.rgb(255, 255, 51));
			card1 = cardIndex;
			this.imageButtons[cardIndex].setImageResource(getResources().getIdentifier(cardString, "drawable" ,getPackageName())); 
		}
		else if(card1 >= 0){		
			this.imageButtons[cardIndex].setImageResource(getResources().getIdentifier(cardString, "drawable" ,getPackageName())); 
			card2 = cardIndex;
			if(checkMatch(card1, card2))
			{
				this.imageButtons[card1].setVisibility(View.INVISIBLE);
				this.imageButtons[cardIndex].setVisibility(View.INVISIBLE);
				card1 = -1;
				card2 = -1;
			}
			else{
				final int index = cardIndex;
				handler.postDelayed(new Runnable() {
				    @Override
				    public void run() {
				        // flip card back over after 5 seconds
				    	imageButtons[index].setImageResource(getResources().getIdentifier("cardback", "drawable" ,getPackageName()));
				    }
				}, 500);
				
					}//END else

		}		
		
    }//END selectCardButton()
	 
	 
	 //PRIVATE UTILITY METHODS
	  
	// pick eight cards out of a 52 card deck and shuffle 2 copies of each into a 16 member ArrayList
		private static ArrayList<String> pickEightCards() {
			Random random = new Random();
			ArrayList<String> deckOfCards = new ArrayList<String>();
			String[] cardSuit={"c","d","h","s"};
			ArrayList<String> cardSelected = new ArrayList<String>();
			String card;
			
			// create ArrayList of cards
			for (String suit : cardSuit) 
				for (int index = 1; index < 14; index++) 
					deckOfCards.add("card_"+index+suit);

			// remove eight random cards and put them into another list
			for (int index = 0; index < 8; index++) {
				card = deckOfCards.remove(random.nextInt(deckOfCards.size()));
				// add the same random card to the new list twice
				cardSelected.add(card);
				cardSelected.add(card);
			} // end for
			
			// shuffle the card list
			Collections.shuffle(cardSelected);
			
			return cardSelected;
		} // end pickEightCards method
		
		private Boolean checkMatch(int cardValue1, int cardValue2){
			String s1 =  grid.get(cardValue1);
			String s2 =  grid.get(cardValue2);
			
			if (s1.equals(s2)) {
				dsplayMessage.setText("Right!");
				correctAnswer = true;
			} // end if
			else {
				card2 = -1;
				correctAnswer = false;
				dsplayMessage.setText("Wrong! Pick again...");
			} // end else
			return correctAnswer;
		}//END checkMatch()
		
		
		  
}; // END MainActivity
	
	//myImageButton0.setImageDrawable(getResource()getDrawable(R.drawable.cardback))
	
	
