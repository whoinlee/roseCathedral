package rosecathedral;

import processing.core.PApplet;


class Timer {
	
	PApplet parent;
	
	int savedTime;	//-- When Timer started
	int totalTime;	//-- How long Timer should last
	
	
	Timer (PApplet p, int pTotalTime) {
		parent = p;
	    totalTime = pTotalTime;
	  }
	  
	  //-- Starting the timer
	  void start() {
	    //-- When the timer starts it stores the current time in milliseconds.
	    savedTime = parent.millis(); 
	  }
	  
	  //-- The function isFinished() returns true if 5,000 ms have passed. 
	  //-- The work of the timer is farmed out to this method.
	  boolean isFinished() { 
	    //-- Check how much time has passed
	    int passedTime = parent.millis()- savedTime;
	    if (passedTime > totalTime) {
	      return true;
	    } else {
	      return false;
	    }
	  }
	}