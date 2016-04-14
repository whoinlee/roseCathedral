package rosecathedral;

import processing.core.PApplet;
import processing.core.PConstants;

public class Cell2 {
	
	PApplet parent;
	
	int x, y;
	int w, h;
	int outlineColor;
	int outlineAlpha = 150;			//-- was 150, 100
	
	//-- triangle colors
	int colorL, colorB, colorR, colorT;
	
	//-- flipping related
	float flipSpeed = (float) 0.1;	//-- was 0.6
	float currentAngle = 0;

	boolean isFlippable = true;
	boolean isFlipping = false;
	boolean isFlipEndL = false;
	boolean isFlipEndB = false;
	
	
	///////////////
	// REFERENCE //
	///////////////
	// color c = img.pixels[loc];  	//-- Grab the color
	// float z = (mouseX / float(width)) * brightness(img.pixels[loc]) - 20.0;
	// Before accessing this array, the data must be loaded with the loadPixels() function
	// After the array data has been modified, the updatePixels() function must be run.
	//
	// Getting the color of a single pixel with get(x, y) is easy, but not as fast as grabbing the data directly from pixels[]
	// get(x, y) == pixels[y*width+x]
	///////////////
	
	Cell2 (PApplet p, int xloc, int yloc, int cellSize, int pOutlineColor, int pColorL, int pColorB, int pColorR, int pColorT, boolean pFlippable) 
	{
		parent = p;
		
		x = xloc;
		y = yloc;
		w = h = cellSize;
		
		outlineColor = pOutlineColor;
		colorL = pColorL;
		colorB = pColorB;
		colorR = pColorR;
		colorT = pColorT;
		isFlippable = pFlippable;
		
		isFlipping = false;
		isFlipEndL = false;
		isFlipEndB = false;
	}
	
	void init() {
		if (isFlippable) {
			isFlipping = true;
			//isFlipEndL = false;
			//isFlipEndB = false;
		}
	}
	
	void reset() {
		
	}
	
	void flip() {
		//--triangles
		if (isFlipping) {
			//-- while being flipped
			isFlipEndL = false;
			isFlipEndB = false;
			parent.pushMatrix();
			parent.translate(x+w/2, y+h/2, 0);	//-- to the center of the cell
			//
			//-------------------//
			//-- left triangle --//
			//-------------------//
			//-- flips to the right
			if (colorL != -1) {
				//-- while being flipped, keeps the original color
				parent.fill(colorL);
				parent.rotateY(currentAngle);
				parent.triangle(-w/2, -h/2, 0, 0, -w/2, h/2);
				
				//TEST
//				if (currentAngle >= PConstants.PI) {
//					//-- on being flipped, sets the left triangle's back color to "orange"
//					if (colorL != parent.color(255, 255, 255)) {
//						//-- if not white
//						parent.fill(parent.color(246, 153, 0));	//-- set to orange
//					}
//					
//				} else {
//					//-- while being flipped, keeps the original color
//					parent.fill(colorL);
//				}

				//-- Rotates a shape around the y-axis by "sin(parent.radians(currentAngle))*30"
				//parent.rotateY(parent.sin(parent.radians(currentAngle))*30);
				//triangle(x1, y1, x2, y2, x3, y3);
			}
			
			//---------------------//
			//-- bottom triangle --//
			//---------------------//
			//-- flips to the top
			if (colorB != -1) {
				if (currentAngle >= PConstants.PI) {
					parent.fill(parent.color(255, 228, 0));		//-- yellow
				} else {
					parent.fill(colorB);
				}
				parent.rotateX(currentAngle);
				parent.triangle(-w/2, h/2, 0, 0, w/2, h/2);
				
				//-- Rotates a shape around the x-axis, was 20
				//parent.rotateX(parent.sin(parent.radians(currentAngle))*25);
			}
			
			//-- update currentAngle, then reevaluate
			currentAngle += flipSpeed;
			if (currentAngle < PConstants.PI) {
				//parent.println("angle is less than 180");
			} else {	//????????
			//} else if (currentAngle >= 2 * parent.PI) {	//????????
				isFlipping = false;
				isFlipEndL = true;
				isFlipEndB = true;
				//parent.println("flipped!! " + currentAngle);
			}
			
			parent.popMatrix();	//?????

		} else {
			//-- if is not flipping
			if (colorL != -1) {
				if (isFlipEndL) {
					//parent.fill(parent.color(246, 153, 0));			//orange
					parent.fill(colorL);
					parent.triangle(x+w/2, y+h/2, x+w, y+h, x+w, y);	//flipped right triangle
				} else {
					parent.fill(colorL);
					parent.triangle(x, y, x+w/2, y+h/2, x, y+h);		//left triangle
				}
			}
			if (colorB != -1) {
				if (isFlipEndB) {
					parent.fill(parent.color(255, 228, 0));	//yellow
					parent.triangle(x, y, x+w/2, y+h/2, x+w, y);		//flipped top triangle
				} else {
					parent.fill(colorB);
					parent.triangle(x, y+h, x+w/2, y+h/2, x+w, y+h);	//bottom triangle
				}
			}
		}

		//--------------------//
		//-- right triangle --//
		//--------------------//
		if (colorR != -1 && !isFlipEndB) {//???isFlipEndB? not isFlipEndL???
			parent.fill(colorR);
			parent.triangle(x+w/2, y+h/2, x+w, y+h, x+w, y);			//right triangle
		}
		
		//--------------------//
		//-- top triangle 	--//
		//--------------------//
		if (colorT != -1) {
			parent.fill(colorT);
			parent.triangle(x, y, x+w/2, y+h/2, x+w, y);				//top triangle
		}
		
		//--------------------//
		//-- outlines		--//
		//--------------------//
		//-- top
		parent.line(x, y, x+w, y);
		parent.stroke(outlineColor, outlineAlpha);
		//-- right
		parent.line(x+w, y, x+w, y+h);
		parent.stroke(outlineColor, outlineAlpha);
		//-- bottom
		parent.line(x+w, y+h, x, y+h);
		parent.stroke(outlineColor, outlineAlpha);
		//-- left
		parent.line(x, y+h, x, y);
		parent.stroke(outlineColor, outlineAlpha);
		//-- diagonal 1, LT to RB
		parent.line(x, y, x+w, y+h);
		parent.stroke(outlineColor, outlineAlpha);
		//-- diagonal 2, RT to LB
		parent.line(x+w, y, x, y+h);
		parent.stroke(outlineColor, outlineAlpha);
		
		parent.noStroke();	//making a clean outline, why???, even if the top is not drawn
	}
	
	void setSpeed(float pFlipSpeed) {
		flipSpeed = pFlipSpeed;
	}
}