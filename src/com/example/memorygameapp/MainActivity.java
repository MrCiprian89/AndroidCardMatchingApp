package com.example.memorygameapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
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
	private String[] cardSuit = {"c","d","h","s"};
	private String[] cardNumber = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 //create a reference to my Controls an store buttons in array
		
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
			
		
	//LOOP to add eventlisteners to each button		
	for( int i = 15 ; i >= 0; i -- ) {
		final int cardIndex = i;
			imageButtons[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//call cardPicked when the button is clicked, sending its array index and
					//the string the card generated
					selectCardButton(cardIndex);
				} //method onClick ends
			});		
		}
		
	}

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

	 private void selectCardButton(int cardIndex) {
		 
			 //setup highlight of selected
			 //myImageButton0.setBackgroundResource(R.drawable.card_7s);
			// tsetImageResource(R.drawable.card_10c);
		 
		// this.imageButtons[cardIndex].setImageResource(R.drawable.card_10c);
		 this.imageButtons[cardIndex].setImageResource(getResources().getIdentifier("card_"  + cardNumber[0] + cardSuit[1], "drawable", getPackageName())); 
         }
		  
	 };
	
	//myImageButton0.setImageDrawable(getResource()getDrawable(R.drawable.cardback))
	
	
