package test;

import processing.core.PApplet;
import processing.core.PImage;
import generativedesign.*;
import processing.pdf.*;
import java.util.Calendar;

public class ColorTest extends PApplet {

	private static final long serialVersionUID = 1L;
	
	boolean savePDF = false;

	PImage img;
	int[] colors;

	String sortMode = null;
	
	int stageW = 720;			//-- 720x960
	int stageH = 960;

	int cellUnit = 40;
	int colCount = floor (stageW / cellUnit);
	int rowCount = floor (stageH / cellUnit);
	

	public void setup(){
	  size(stageW, stageH);
	  colorMode(HSB, 360, 100, 100, 100);
	  noStroke();
	  noCursor();
	  img = loadImage("rose720x960.jpg");
	}

	public void draw(){
	  if (savePDF) {
	    beginRecord(PDF, timestamp()+".pdf");
	    colorMode(HSB, 360, 100, 100, 100);
	    noStroke();
	  }

//	  int tileCount = stageW / max(mouseX, 5);
//	  float rectSize = stageW / tileCount;
//	  int rowCount = (int) floor(stageH % rectSize);
	  int rectSize = cellUnit;
	  int tileCount = colCount;

	  // get colors from image
	  int i = 0; 
	  colors = new int[colCount*rowCount];
	  for (int gridY=0; gridY<rowCount; gridY++) {
	    for (int gridX=0; gridX<colCount; gridX++) {
	      int px = (int) (gridX * rectSize);
	      int py = (int) (gridY * rectSize);
	      colors[i] = img.get(px, py);
	      i++;
	    }
	  }

	  // sort colors
	  if (sortMode != null) colors = GenerativeDesign.sortColors(this, colors, sortMode);
	  

	  // draw grid
	  i = 0;
	  for (int gridY=0; gridY<rowCount; gridY++) {
	    for (int gridX=0; gridX<tileCount; gridX++) {
	      fill(colors[i]);
	      rect(gridX*rectSize, gridY*rectSize, rectSize, rectSize);
	      i++;
	    }
	  }

	  if (savePDF) {
	    savePDF = false;
	    endRecord();
	  }
	}

	public void keyReleased(){
	  if (key=='c' || key=='C') GenerativeDesign.saveASE(this, colors, timestamp()+".ase");
	  if (key=='s' || key=='S') saveFrame(timestamp()+"_##.png");
	  if (key=='p' || key=='P') savePDF = true;
	  
	  if (key == '0') img = loadImage("rose720x960.jpg");
	  if (key == '1') img = loadImage("pic1.jpg");
	  if (key == '2') img = loadImage("pic2.jpg"); 
	  if (key == '3') img = loadImage("pic3.jpg"); 

	  if (key == '4') sortMode = null;
	  if (key == '5') sortMode = GenerativeDesign.HUE;
	  if (key == '6') sortMode = GenerativeDesign.SATURATION;
	  if (key == '7') sortMode = GenerativeDesign.BRIGHTNESS;
	  if (key == '8') sortMode = GenerativeDesign.GRAYSCALE;
	  if (key == '9') {
		  int index = 0;
		  for (int i=0; i<rowCount; i++) {
			  println("\nrow " + i);
			  for (int j=0; j<colCount; j++) {
				  if (j<10) {
					  println("	col 0" + j + ":" + colors[index]);
				  } else {
					  println("	col " + j + ":" + colors[index]);
				  }
				  index++;
		    }
		  }
	  }
	}

	// timestamp
	private String timestamp() {
	  Calendar now = Calendar.getInstance();
	  return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS", now);
	}
}