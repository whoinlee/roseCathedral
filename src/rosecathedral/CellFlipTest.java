package rosecathedral;

import processing.core.PApplet;
import processing.core.PImage;

public class CellFlipTest extends PApplet {

	private static final long serialVersionUID = 1L;
	
	PImage bkgImage;
	Cell oneCell;
	
	int stageW = 400;
	int stageH = 500;
	int cellUnit = 40;
	int cellLineColor = color(85, 254, 3);	//green
	int colNum, rowNum;
	int count = 0;
	
	public void setup() {
		size(stageW+1, stageH+1, P3D); //OPENGL:smoothed, P3D: need smooth() option?
		//noStroke();
		//noFill();
		
		bkgImage = loadImage("rose.jpg");
		
		//background(0);
		//image(bkgImage, 0, 0);

		colNum = ceil(stageW/cellUnit);
		rowNum = ceil(stageH/cellUnit);
		//-- Cell (PApplet p, int xloc, int yloc, int cellSize, int pOutlineColor, int pColorL, int pColorB, int pColorR, int pColorT) 
		oneCell = new Cell(this, 5*cellUnit, 5*cellUnit, cellUnit, cellLineColor, 255, 255, 255, -1);
	}

	public void draw() {
		//background(255, 204, 0);
		count++;
		background(0); //why???? is this necessary??
		image(bkgImage, 0, 0);
		//lights();
		if (mousePressed) {
			float speed = random(1)/10;
			//println("speed:" + speed);
			oneCell.setFlipSpeed(speed);
			//println("flipping (" + i + ", " + j + "):" + grid[i][j].ang0);
			oneCell.startFlipping();
		}
		
		oneCell.display();
		redraw();
	}
}