package test;

import processing.core.PApplet;
import processing.core.PImage;
import generativedesign.*;
//import processing.pdf.*;
import java.util.Calendar;

public class ColorPickerTest extends PApplet {

	private static final long serialVersionUID = 1L;
	
	boolean savePDF = false;

	PImage img;
	int[] colors;
	int[] filteredColors;
	int[] filteredRowColors;

	String sortMode = null;
	
	int stageW = 720;			//-- 720x960
	int stageH = 960;
//	int stageW = 600;			//-- 720x960
//	int stageH = 600;

	int cellUnit = 40;
	int colCount = floor (stageW / cellUnit);
	int rowCount = floor (stageH / cellUnit);
	
	boolean isFiltered = false;
	boolean isFilteredSorting = false;
	

	public void setup(){
	  size(stageW, stageH);
//	  colorMode(HSB, 360, 100, 100, 100);
	  noStroke();
	  noCursor();
	  img = loadImage("rose720x960.jpg");
//	  img = loadImage("pic1.jpg");

	  //-- get colors from image
	  int i = 0; 
	  colors = new int[colCount*rowCount];
	  for (int gridY=0; gridY<rowCount; gridY++) {
	    for (int gridX=0; gridX<colCount; gridX++) {
	      int px = (int) (gridX * cellUnit);
	      int py = (int) (gridY * cellUnit);
	      colors[i] = img.get(px, py);
	      i++;
	    }
	  }
	}

	public void draw(){
//	  if (savePDF) {
//	    beginRecord(PDF, timestamp()+".pdf");
//	    colorMode(HSB, 360, 100, 100, 100);
//	    noStroke();
//	  }

	  // sort colors
		if (!isFilteredSorting) {
		  if (sortMode != null) {
			  colors = GenerativeDesign.sortColors(this, colors, sortMode);
		  } else {
			  //-- reset color values
			  int i = 0; 
			  for (int gridY=0; gridY<rowCount; gridY++) {
			    for (int gridX=0; gridX<colCount; gridX++) {
			      int px = (int) (gridX * cellUnit);
			      int py = (int) (gridY * cellUnit);
			      colors[i] = img.get(px, py);
			      i++;
			    }
			  }
		  }
		}
	  
	  if (isFiltered) {
		  clear();
		  int fCount = 3;
//		  int step = round(colCount/(fCount-1));
		  filteredColors = new int[fCount*rowCount];
		  filteredRowColors = new int[colCount];
		  //-- filtering  
		  int j = 0;	//index for colors array
		  int k = 0;	//index for filteredColors array
		  int middleIndex = floor(colCount/2);
		  for (int r=0; r<rowCount; r++) {
		    for (int c=0; c<colCount; c++) {
		      filteredRowColors[c] = colors[j];
		      j++;
		    }
		    //-- filter filterdRowColors
		    filteredRowColors = sort(filteredRowColors);
//		    for (int m=0; m<fCount; m++) {
//		    	int nextIndex = m*step;
//		    	if (m==(fCount-1)) nextIndex = colCount-1;
//		    	filteredColors[k] = filteredRowColors[nextIndex];
//		    	k++;
//		    }
		    filteredColors[k++] = filteredRowColors[0];
		    filteredColors[k++] = filteredRowColors[1];
//		    filteredColors[k++] = filteredRowColors[middleIndex];
//		    filteredColors[k++] = filteredRowColors[colCount-2];
		    filteredColors[k++] = filteredRowColors[colCount-1];
		  }
		  
		  if (isFilteredSorting && (sortMode != null)) {
			  filteredColors = GenerativeDesign.sortColors(this, filteredColors, sortMode);
		  } 
		  
		  int l = 0;
		  for (int r=0; r<rowCount; r++) {
		    for (int c=0; c<fCount; c++) {
			     fill(filteredColors[l]);
			     rect(c*cellUnit, r*cellUnit, cellUnit, cellUnit);
			     l++;
		    }
		  }
	  } else {
		  // draw grid
		  int i = 0;
		  for (int gridY=0; gridY<rowCount; gridY++) {
		    for (int gridX=0; gridX<colCount; gridX++) {
		      fill(colors[i]);
		      rect(gridX*cellUnit, gridY*cellUnit, cellUnit, cellUnit);
		      i++;
		    }
		  }
	  }

//	  if (savePDF) {
//	    savePDF = false;
//	    endRecord();
//	  }
	}

	public void keyReleased(){
//	  if (key=='c' || key=='C') GenerativeDesign.saveASE(this, colors, timestamp()+".ase");
	  if (key=='s' || key=='S') saveFrame(timestamp()+"_##.png");
//	  if (key=='p' || key=='P') savePDF = true;
	  
//	  if (key == '0') img = loadImage("rose720x960.jpg");
//	  if (key == '1') img = loadImage("pic1.jpg");
//	  if (key == '2') img = loadImage("pic2.jpg"); 
//	  if (key == '3') img = loadImage("pic3.jpg"); 
	  
	  if (key == '0') isFiltered = false;
	  if (key == '1') isFiltered = true;
	  
	  if (key == '2') isFilteredSorting = true;
	  if (key == '3') isFilteredSorting = false;
	  
	  if (key == '4') {
		  sortMode = null;
//		  isFiltered = false;
	  }
	  if (key == '5') {
		  sortMode = GenerativeDesign.HUE;
//		  isFiltered = false;
	  }
	  if (key == '6') {
		  sortMode = GenerativeDesign.SATURATION;
//		  isFiltered = false;
	  }
	  if (key == '7') {
		  sortMode = GenerativeDesign.BRIGHTNESS;
//		  isFiltered = false;
	  }
	  if (key == '8') {
		  sortMode = GenerativeDesign.GRAYSCALE;
//		  isFiltered = false;
	  }
	  if (key == '9') {
		  isFiltered = false;
		  //-- trace color values
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