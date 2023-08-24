package com.learning.controller;

import java.util.Random;

public class HiLoController {
	
	
	
	 private int correct;
	  private int guess;
	  
	  public void setGuess(int userGuess) {
	    this.guess = userGuess;
	  }
	  
	  public void reset() {
	    this.correct = new Random().nextInt(0, 100);
	  }
	  
	  public int judge() {
	    return Integer.compare(guess, correct);
	  }
	  
	  public String feedback() {
		  
		  if(judge() == 0) {
			  return "Correct";
		  }
		  else {
			  if(judge() ==1 ) {
				  return "Guess lower";
			  }
			  else {
				  return "Guess higher";
			  }
		  }
	  }
	
	  


}
