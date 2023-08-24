package com.learning.controller;
import com.learning.odometer.*;
import com.learning.Odometer.*;
import com.learning.exception.UnsupportedActionException;


public class OdometerController {
	
	private Odometer o;
	private static OdometerController inst;
	
	public static OdometerController get() {
	    if (inst == null) {
	      inst = new OdometerController();
	    }
	    return inst;
	  }
	  
	
	public void init(int size) {
		o = new Odometer(size);	
	}
	
	public int decrement() {
		o.decrementReading();
		return o.getReading();
	}
	
	public void reset() {
		o.reset();
	}
	
	public int getReading() {
		return o.getReading();
	}
	
	public int increment(){
		o.incrementReading();
		return o.getReading();
		
	}

	
	 public void performAction(String action) throws UnsupportedActionException{
		    switch(action) {
		    case "prev":
		      decrement();
		      break;
		    case "next":
		      increment();
		      break;
		    case "reset":
		      reset();
		      break;
		    default:
		      throw new UnsupportedActionException("This action not supported");
		    }
		  }

	

}
