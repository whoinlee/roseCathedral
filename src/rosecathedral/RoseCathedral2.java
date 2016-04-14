package rosecathedral;

import processing.core.PApplet;
import processing.core.PImage;	//need?

public class RoseCathedral2 extends PApplet {
	
	private static final long serialVersionUID = 1L;

	PImage bkgImage;
	
	int stageW = 720;			//-- 720x960
	int stageH = 960;	
	int cellUnit = 40;
	int colNum, rowNum;
	
	int color0, color1, color2, color3, color4, color5, color6, color7, color8;
	int black = 0;															//75
	int lightGreen = color(85, 255, 0);		//old:color(85, 254, 3);		//56
	int orange = color(255, 153, 0);		//old:color(246, 153, 0)		//52
	int yellow = color(255, 238, 0);		//color(255, 228, 0)			//39
	int scarlet = color(249, 34, 0);		//old:color(255, 85, 0)			//16
	int lightBlue = color(0, 170, 255);		//old:color(0, 162, 255)		//13
	int darkGreen = color(0, 85, 0);		//old:color(0, 98, 45);			//10
	int green = color(0, 170, 85);			//old:color(50, 166, 5)			//10
	int darkBlue = color(0, 85, 170);		//old:color(0, 87, 158), color(0, 85, 255)			//9
	int darkOrange = color(255, 85, 0);		//old:color(255, 134, 0)		//8
	
	
	/*
	lightGreen = color(85, 254, 0);		//old:color(85, 254, 3);	
	orange = color(246, 153, 0);		
	yellow = color(255, 228, 0);	
	//
	darkOrange = color(255, 85, 0);		//old:color(255, 134, 0)
	scarlet = color(249, 38, 0);		//old:color(255, 85, 0)	
	green = color(50, 166, 0);			//old:color(50, 166, 5)
	//
	darkGreen = color(0, 98, 45);
	lightBlue = color(0, 153, 255);		//old:color(0, 162, 255)
	darkBlue = color(0, 84, 219);			//old:color(0, 87, 158)
	*/
	
	
	int[] colorPool;
	Cell2[][] cellGrid;			//-- 2 dimensional Array for Cell instances
	int[][][] colorArr;			//-- 3 dimensional Array for Cell color value data
	
	
	public void setup() {
		
		//-- set stage and image size
		size(stageW+1, stageH+1, P3D);
		bkgImage = loadImage("rose720x960.jpg");			//-- 720x960
		
		//-- set initial cell color data
		colNum = ceil(stageW/cellUnit);
		rowNum = ceil(stageH/cellUnit);
		cellGrid = new Cell2[rowNum][colNum];
		colorArr = new int[rowNum][colNum][4];
		setColor();											//-- set initial static cell colors ===> randomize
		
		//-- set cells
		int cellLineColor = lightGreen;
		boolean flippable;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				flippable = (random(5)<=2)? true:false;		//-- 40% flippable
				cellGrid[i][j] = new Cell2(this, j*cellUnit, i*cellUnit, cellUnit, cellLineColor, colorArr[i][j][0], colorArr[i][j][1], colorArr[i][j][2], colorArr[i][j][3], flippable);
			}
		}
	}
	
	private void setColor() {	
		//-- initialize
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				//-- white
				colorArr[i][j][0] = 255;
				colorArr[i][j][1] = 255;
				colorArr[i][j][2] = 255;
				colorArr[i][j][3] = 255;
			}
		}
		
		colorPool = new int[9];
		colorPool[0] = color0 = black;
		colorPool[1] = color1 = lightGreen;
		colorPool[2] = color2 = orange;
		colorPool[3] = color3 = yellow;
		colorPool[4] = color4 = scarlet;
		colorPool[5] = color5 = lightBlue;
		colorPool[6] = color6 = green;
		colorPool[7] = color7 = darkBlue;
		colorPool[8] = color8 = darkOrange;
		
		//-- manual(hard-coded) setting
		colorArr[0][1][0]  = yellow;	
		//
		colorArr[1][3][3]  = lightGreen;		
		colorArr[1][7][3]  = lightGreen;	
		colorArr[1][10][3] = orange;	
		colorArr[1][11][1] = orange;
		colorArr[1][12][0] = lightGreen;
		colorArr[1][16][1] = -1;		//open
		//
		colorArr[2][0][2]  = black;						
		colorArr[2][10][0] = yellow;
		colorArr[2][15][3] = lightGreen;
		colorArr[2][16][1] = -1;
		//
		colorArr[3][1][3]  = black;
		colorArr[3][6][0]  = yellow;
		colorArr[3][8][1]  = black;
		colorArr[3][12][0] = -1;
		colorArr[3][12][1] = orange;
		colorArr[3][13][0] = -1;
		colorArr[3][13][2] = -1;
		colorArr[3][15][0] = -1;
		colorArr[3][15][3] = -1;
		colorArr[3][16][1] = -1;
		colorArr[3][17][0] = -1;
		//
		colorArr[4][1][3]  = black;
		colorArr[4][9][1]  = -1;
		colorArr[4][9][2]  = -1;
		colorArr[4][13][0] = -1;
		colorArr[4][14][0] = -1;
		colorArr[4][14][2] = -1;
		colorArr[4][15][1] = -1;
		colorArr[4][15][3] = lightGreen;
		colorArr[4][16][1] = -1;
		//
		colorArr[5][6][1]  = yellow;
		colorArr[5][9][0]  = -1;
		colorArr[5][10][1] = -1;
		colorArr[5][10][2] = black;
		colorArr[5][11][3] = -1;
		colorArr[5][12][1] = orange;
		colorArr[5][13][0] = -1;
		colorArr[5][13][1] = -1;
		colorArr[5][14][1] = -1;
		colorArr[5][16][1] = -1;
		//
		colorArr[6][3][1]  = -1;
		colorArr[6][3][3]  = -1;
		colorArr[6][4][1]  = black;
		colorArr[6][5][0]  = -1;
		colorArr[6][7][2]  = black;
		colorArr[6][9][2]  = -1;
		colorArr[6][9][3]  = -1;
		colorArr[6][10][0] = -1;
		colorArr[6][10][2] = -1;
		colorArr[6][10][3] = -1;
		colorArr[6][11][1] = -1;
		colorArr[6][11][3] = -1;
		colorArr[6][12][1] = -1;
		colorArr[6][12][3] = -1;
		colorArr[6][13][0] = -1;
		colorArr[6][13][3] = -1;
		colorArr[6][14][0] = -1;
		colorArr[6][14][3] = -1;
		colorArr[6][15][0] = -1;
		colorArr[6][15][2] = -1;
		colorArr[6][15][3] = -1;
		colorArr[6][16][0] = -1;
		colorArr[6][17][0] = -1;
		//
		colorArr[7][3][2]  = -1;
		colorArr[7][4][1]  = -1;
		colorArr[7][7][2]  = -1;
		colorArr[7][8][3]  = -1;
		colorArr[7][9][3]  = -1;
		colorArr[7][10][1] = -1;
		colorArr[7][10][2] = -1;
		colorArr[7][10][3] = black;
		colorArr[7][11][0] = -1;
		colorArr[7][11][1] = -1;
		colorArr[7][11][2] = -1;
		colorArr[7][12][1] = -1;
		colorArr[7][12][2] = lightGreen;
		colorArr[7][13][1] = -1;
		colorArr[7][13][2] = -1;
		colorArr[7][13][3] = -1;
		colorArr[7][14][0] = -1;
		colorArr[7][14][1] = -1;
		colorArr[7][14][3] = -1;
		colorArr[7][15][1] = -1;
		colorArr[7][16][1] = -1;
		colorArr[7][17][0] = -1;
		//
		colorArr[8][1][1]  = lightGreen;
		colorArr[8][4][2]  = -1;
		colorArr[8][8][1]  = -1;
		colorArr[8][8][3]  = -1;
		colorArr[8][9][0]  = -1;
		colorArr[8][9][1]  = -1;
		colorArr[8][9][2]  = -1;
		colorArr[8][9][3]  = -1;
		colorArr[8][10][0] = -1;
		colorArr[8][10][1] = -1;
		colorArr[8][10][2] = -1;
		colorArr[8][10][3] = -1;
		colorArr[8][11][0] = -1;
		colorArr[8][11][1] = yellow;
		colorArr[8][11][2] = -1;
		colorArr[8][12][0] = -1;
		colorArr[8][13][0] = -1;
		colorArr[8][13][1] = -1;
		colorArr[8][13][3] = -1;
		colorArr[8][14][0] = lightGreen;
		colorArr[8][14][1] = -1;
		colorArr[8][14][2] = -1;
		colorArr[8][15][1] = -1;
		colorArr[8][15][3] = -1;
		colorArr[8][16][0] = -1;
		colorArr[8][16][1] = -1;
		//
		colorArr[9][5][3]  = -1;
		colorArr[9][6][1]  = -1;
		colorArr[9][6][3]  = yellow;
		colorArr[9][8][0]  = lightGreen;
		colorArr[9][10][2] = -1;
		colorArr[9][11][1] = darkOrange;
		colorArr[9][11][3] = -1;
		colorArr[9][12][0] = -1;
		colorArr[9][12][1] = -1;
		colorArr[9][12][2] = -1;
		colorArr[9][12][3] = -1;
		colorArr[9][13][0] = -1;
		colorArr[9][13][1] = -1;
		colorArr[9][13][2] = -1;
		colorArr[9][13][3] = -1;
		colorArr[9][14][0] = -1;
		colorArr[9][14][1] = -1;
		colorArr[9][14][2] = -1;
		colorArr[9][14][3] = -1;
		colorArr[9][15][1] = -1;
		colorArr[9][15][2] = -1;
		colorArr[9][15][3] = -1;
		colorArr[9][16][1] = -1;
		colorArr[9][17][2] = -1;
		//
		colorArr[10][1][2]  = lightGreen;
		colorArr[10][4][3]  = -1;
		colorArr[10][5][1]  = -1;
		colorArr[10][7][0]  = -1;
		colorArr[10][8][2]  = -1;
		colorArr[10][8][3]  = -1;
		colorArr[10][9][1]  = -1;
		colorArr[10][9][3]  = orange;
		colorArr[10][10][0] = -1;
		colorArr[10][10][1] = -1;
		colorArr[10][10][2] = black;
		colorArr[10][11][1] = -1;
		colorArr[10][11][2] = -1;
		colorArr[10][12][1] = -1;
		colorArr[10][12][2] = -1;
		colorArr[10][12][3] = -1;
		colorArr[10][13][0] = -1;
		colorArr[10][13][1] = -1;
		colorArr[10][13][2] = -1;
		colorArr[10][13][3] = -1;
		colorArr[10][14][0] = -1;
		colorArr[10][14][1] = -1;
		colorArr[10][14][2] = -1;
		colorArr[10][14][3] = -1;
		colorArr[10][15][0] = -1;
		colorArr[10][15][1] = -1;
		colorArr[10][15][2] = -1;
		colorArr[10][15][3] = -1;
		colorArr[10][16][0] = -1;
		colorArr[10][16][1] = -1;
		colorArr[10][16][2] = -1;
		//
		colorArr[11][5][0]  = orange;
		colorArr[11][6][1]  = -1;	
		colorArr[11][7][1]  = orange;	
		colorArr[11][8][0]  = -1;
		colorArr[11][8][2]  = orange;	
		colorArr[11][8][3]  = -1;
		colorArr[11][9][1]  = black;
		colorArr[11][9][2]  = -1;
		colorArr[11][9][3]  = -1;
		colorArr[11][10][0] = -1;
		colorArr[11][10][1] = -1;
		colorArr[11][10][2] = -1;
		colorArr[11][11][0] = -1;
		colorArr[11][11][1] = -1;
		colorArr[11][11][3] = darkOrange;
		colorArr[11][12][0] = -1;
		colorArr[11][12][1] = -1;
		colorArr[11][12][2] = lightGreen;
		colorArr[11][12][3] = black;
		colorArr[11][13][0] = -1;
		colorArr[11][13][1] = darkOrange;
		colorArr[11][13][2] = -1;
		colorArr[11][13][3] = -1;
		colorArr[11][14][0] = -1;
		colorArr[11][14][1] = -1;
		colorArr[11][14][2] = -1;
		colorArr[11][15][0] = -1;
		colorArr[11][15][1] = -1;
		colorArr[11][15][2] = -1;
		colorArr[11][15][3] = -1;
		colorArr[11][16][0] = -1;
		colorArr[11][16][1] = -1;
		colorArr[11][16][2] = -1;
		colorArr[11][16][3] = -1;
		colorArr[11][17][0] = -1;
		colorArr[11][17][1] = -1;
		//
		colorArr[12][2][1]  = black;
		colorArr[12][4][0]  = -1;
		colorArr[12][5][1]  = orange;
		colorArr[12][7][0]  = orange;
		colorArr[12][7][3]  = -1;
		colorArr[12][8][0]  = -1;
		colorArr[12][8][2]  = darkBlue;
		colorArr[12][9][0]  = -1;
		colorArr[12][9][1]  = black;
		colorArr[12][10][0] = -1;
		colorArr[12][10][1] = black;
		colorArr[12][10][2] = -1;
		colorArr[12][10][3] = -1;
		colorArr[12][11][0] = -1;
		colorArr[12][11][1] = lightGreen;
		colorArr[12][11][2] = -1;
		colorArr[12][11][3] = -1;
		colorArr[12][12][0] = -1;
		colorArr[12][12][1] = darkOrange;
		colorArr[12][12][2] = -1;	
		colorArr[12][12][3] = -1;
		colorArr[12][13][0] = -1;
		colorArr[12][13][1] = -1;
		colorArr[12][13][2] = -1;
		colorArr[12][13][3] = -1;
		colorArr[12][14][0] = -1;
		colorArr[12][14][1] = -1;
		colorArr[12][14][2] = -1;
		colorArr[12][14][3] = -1;
		colorArr[12][15][0] = -1;
		colorArr[12][15][1] = -1;
		colorArr[12][15][3] = lightGreen;
		colorArr[12][16][0] = -1;
		colorArr[12][16][1] = -1;
		colorArr[12][16][3] = -1;
		colorArr[12][17][0] = -1;
		//
		colorArr[13][4][2]  = orange;
		colorArr[13][6][1]  = yellow;
		colorArr[13][7][3]  = -1;
		colorArr[13][8][0]  = -1;
		colorArr[13][8][2]  = -1;
		colorArr[13][8][3]  = orange;
		colorArr[13][9][0]  = -1;
		colorArr[13][9][1]  = yellow;
		colorArr[13][9][2]  = -1;
		colorArr[13][9][3]  = lightGreen;
		colorArr[13][10][0] = -1;
		colorArr[13][10][1] = black;
		colorArr[13][10][2] = -1;
		colorArr[13][10][3] = -1;
		colorArr[13][11][0] = -1;
		colorArr[13][11][1] = -1;
		colorArr[13][11][2] = -1;
		colorArr[13][11][3] = -1;
		colorArr[13][12][0] = -1;
		colorArr[13][12][1] = lightGreen;
		colorArr[13][12][2] = -1;	
		colorArr[13][12][3] = -1;
		colorArr[13][13][0] = -1;
		colorArr[13][13][1] = -1;
		colorArr[13][13][2] = darkOrange;
		colorArr[13][13][3] = -1;
		colorArr[13][14][0] = -1;
		colorArr[13][14][1] = -1;
		colorArr[13][14][2] = -1;
		colorArr[13][14][3] = -1;
		colorArr[13][15][0] = -1;
		colorArr[13][15][1] = orange;
		colorArr[13][15][2] = -1;
		colorArr[13][15][3] = -1;
		colorArr[13][16][0] = -1;
		colorArr[13][16][2] = -1;
		colorArr[13][16][3] = -1;
		//
		colorArr[14][5][3]  = lightGreen;
		colorArr[14][7][3]  = darkOrange;
		colorArr[14][8][1]  = yellow;
		colorArr[14][8][2]  = -1;
		colorArr[14][8][3]  = -1;
		colorArr[14][9][0]  = orange;
		colorArr[14][9][1]  = black;
		colorArr[14][9][2]  = -1;
		colorArr[14][9][3]  = black;
		colorArr[14][10][0] = -1;
		colorArr[14][10][1] = yellow;
		colorArr[14][10][2] = -1;
		colorArr[14][10][3] = -1;	
		colorArr[14][11][0] = black;
		colorArr[14][11][1] = -1;
		colorArr[14][11][2] = -1;
		colorArr[14][11][3] = -1;
		colorArr[14][12][0] = -1;
		colorArr[14][12][1] = -1;
		colorArr[14][12][2] = -1;	
		colorArr[14][12][3] = -1;
		colorArr[14][13][0] = -1;
		colorArr[14][13][1] = darkOrange;
		colorArr[14][13][2] = -1;
		colorArr[14][13][3] = -1;
		colorArr[14][14][0] = -1;
		colorArr[14][14][1] = -1;
		colorArr[14][14][2] = -1;
		colorArr[14][14][3] = yellow;
		colorArr[14][15][0] = -1;
		colorArr[14][15][1] = -1;
		colorArr[14][15][2] = -1;
		colorArr[14][15][3] = -1;
		colorArr[14][16][0] = -1;
		colorArr[14][16][1] = -1;
		colorArr[14][16][2] = -1;
		colorArr[14][16][3] = -1;
		colorArr[14][17][0] = -1;
		colorArr[14][17][3] = -1;
		//
		colorArr[15][2][1]  = lightGreen;
		colorArr[15][3][0]  = orange;
		colorArr[15][3][2]  = orange;
		colorArr[15][5][1]  = -1;
		colorArr[15][6][2]  = -1;
		colorArr[15][7][0]  = yellow;
		colorArr[15][7][2]  = lightGreen;
		colorArr[15][7][3]  = black;
		colorArr[15][8][0]  = black;
		colorArr[15][8][1]  = darkOrange;
		colorArr[15][8][2]  = orange;
		colorArr[15][8][3]  = -1;
		colorArr[15][9][0]  = darkOrange;	
		colorArr[15][9][1]  = lightBlue;	
		colorArr[15][9][2]  = darkBlue;
		colorArr[15][9][3]  = black;
		colorArr[15][10][0] = green;	
		colorArr[15][10][1] = black;
		colorArr[15][10][2] = -1;
		colorArr[15][10][3] = -1;
		colorArr[15][11][0] = darkBlue;
		colorArr[15][11][1] = -1;
		colorArr[15][11][2] = -1;
		colorArr[15][11][3] = black;
		colorArr[15][12][0] = -1;
		colorArr[15][12][1] = -1;
		colorArr[15][12][2] = -1;	
		colorArr[15][12][3] = -1;
		colorArr[15][13][0] = black;
		colorArr[15][13][1] = -1;
		colorArr[15][13][2] = -1;
		colorArr[15][13][3] = -1;
		colorArr[15][14][0] = -1;
		colorArr[15][14][1] = -1;
		colorArr[15][14][2] = -1;
		colorArr[15][14][3] = -1;
		colorArr[15][15][0] = -1;
		colorArr[15][15][1] = lightGreen;	
		colorArr[15][15][2] = -1;
		colorArr[15][15][3] = -1;
		colorArr[15][16][0] = -1;
		colorArr[15][16][1] = -1;
		colorArr[15][16][2] = -1;
		colorArr[15][16][3] = -1;
		colorArr[15][17][2] = -1;
		//
		colorArr[16][1][3] = lightGreen;		
		colorArr[16][2][2] = lightGreen;		
		colorArr[16][4][3] = lightGreen;		
		colorArr[16][5][2] = lightGreen;		
		colorArr[16][6][1] = yellow;	
		colorArr[16][6][3] = green;
		colorArr[16][7][0] = black;
		colorArr[16][7][1] = black;
		colorArr[16][7][2] = lightBlue;
		colorArr[16][8][0] = darkBlue;		
		colorArr[16][8][1] = black;
		colorArr[16][8][2] = darkOrange;	
		colorArr[16][8][3] = lightGreen;		
		colorArr[16][9][0] = orange;	
		colorArr[16][9][1] = yellow;	
		colorArr[16][9][2] = black;
		colorArr[16][9][3] = yellow;	
		colorArr[16][10][0] = lightBlue;
		colorArr[16][10][1] = black;
		colorArr[16][10][2] = lightBlue;
		colorArr[16][10][3] = green;
		colorArr[16][11][0] = orange;	
		colorArr[16][11][1] = yellow;	
		colorArr[16][11][2] = black;
		colorArr[16][11][3] = black;
		colorArr[16][12][0] = -1;
		colorArr[16][12][1] = green;
		colorArr[16][12][2] = -1;	
		colorArr[16][12][3] = -1;
		colorArr[16][13][0] = -1;
		colorArr[16][13][1] = -1;
		colorArr[16][13][2] = -1;
		colorArr[16][13][3] = darkOrange;
		colorArr[16][14][0] = -1;
		colorArr[16][14][1] = -1;
		colorArr[16][14][2] = -1;
		colorArr[16][14][3] = -1;
		colorArr[16][15][0] = -1;
		colorArr[16][15][1] = -1;
		colorArr[16][15][2] = -1;
		colorArr[16][15][3] = -1;
		colorArr[16][16][0] = -1;
		colorArr[16][16][1] = lightGreen;
		colorArr[16][16][2] = -1;
		colorArr[16][16][3] = -1;
		colorArr[16][17][0] = -1;
		colorArr[16][17][1] = -1;
		colorArr[16][17][2] = -1;
		colorArr[16][17][3] = -1;
		///
		colorArr[17][2][1] = black;
		colorArr[17][4][3] = lightGreen;	
		colorArr[17][5][0] = black;
		colorArr[17][5][1] = lightGreen;	
		colorArr[17][5][2] = lightGreen;	
		colorArr[17][6][1] = lightGreen;	
		colorArr[17][6][2] = black;
		colorArr[17][7][0] = lightGreen;	
		colorArr[17][7][1] = lightBlue;	
		colorArr[17][7][2] = black;
		colorArr[17][7][3] = darkBlue;		
		colorArr[17][8][0] = lightBlue;
		colorArr[17][8][1] = lightGreen;		
		colorArr[17][8][2] = green;	
		colorArr[17][8][3] = darkOrange;
		colorArr[17][9][0] = orange;
		colorArr[17][9][1] = black;
		colorArr[17][9][2] = darkOrange;	
		colorArr[17][9][3] = lightBlue;	
		colorArr[17][10][0] = lightBlue;
		colorArr[17][10][1] = lightGreen;	
		colorArr[17][10][2] = -1;
		colorArr[17][10][3] = yellow;	
		colorArr[17][11][0] = black;
		colorArr[17][11][1] = darkBlue;	
		colorArr[17][11][2] = green;
		colorArr[17][11][3] = -1;
		colorArr[17][12][0] = black;
		colorArr[17][12][1] = -1;
		colorArr[17][12][2] = -1;	
		colorArr[17][12][3] = -1;
		colorArr[17][13][0] = -1;
		colorArr[17][13][1] = darkOrange;	
		colorArr[17][13][2] = -1;
		colorArr[17][13][3] = -1;
		colorArr[17][14][0] = -1;
		colorArr[17][14][1] = -1;
		colorArr[17][14][2] = -1;
		colorArr[17][14][3] = -1;
		colorArr[17][15][0] = -1;
		colorArr[17][15][1] = -1;
		colorArr[17][15][2] = -1;
		colorArr[17][16][0] = -1;
		colorArr[17][16][1] = -1;
		colorArr[17][16][2] = -1;
		colorArr[17][16][3] = -1;
		colorArr[17][17][0] = -1;
		colorArr[17][17][2] = -1;
		//
		colorArr[18][0][1] = yellow;	
		colorArr[18][0][3] = orange;	
		colorArr[18][1][1] = yellow;	
		colorArr[18][2][1] = yellow;	
		colorArr[18][3][1] = black;
		colorArr[18][3][2] = black;
		colorArr[18][4][1] = lightGreen;		
		colorArr[18][4][3] = orange;	
		colorArr[18][5][0] = yellow;	
		colorArr[18][5][1] = lightGreen;		
		colorArr[18][5][2] = lightGreen;		
		colorArr[18][6][0] = -1;
		colorArr[18][6][1] = yellow;	
		colorArr[18][6][2] = -1;
		colorArr[18][6][3] = yellow;	
		colorArr[18][7][0] = -1;
		colorArr[18][7][1] = black;
		colorArr[18][7][2] = darkOrange;
		colorArr[18][7][3] = black;
		colorArr[18][8][0] = lightBlue;	
		colorArr[18][8][1] = orange;	
		colorArr[18][8][2] = yellow;	
		colorArr[18][8][3] = orange;	
		colorArr[18][9][0] = lightBlue;
		colorArr[18][9][1] = black;
		colorArr[18][9][2] = lightBlue;
		colorArr[18][9][3] = black;
		colorArr[18][10][0] = orange;	
		colorArr[18][10][1] = lightBlue;
		colorArr[18][10][2] = black;
		colorArr[18][10][3] = darkBlue;	
		colorArr[18][11][0] = darkOrange;
		colorArr[18][11][1] = orange;	
		colorArr[18][11][2] = -1;
		colorArr[18][11][3] = black;
		colorArr[18][12][0] = -1;
		colorArr[18][12][1] = -1;
		colorArr[18][12][2] = orange;
		colorArr[18][12][3] = -1;
		colorArr[18][13][0] = orange;
		colorArr[18][13][1] = -1;
		colorArr[18][13][2] = orange;
		colorArr[18][13][3] = -1;
		colorArr[18][14][0] = -1;
		colorArr[18][14][1] = -1;
		colorArr[18][14][2] = -1;
		colorArr[18][14][3] = -1;
		colorArr[18][15][0] = -1;
		colorArr[18][15][1] = -1;
		colorArr[18][15][2] = orange;
		colorArr[18][15][3] = -1;
		colorArr[18][16][0] = -1;
		colorArr[18][16][1] = -1;
		colorArr[18][16][2] = -1;
		colorArr[18][16][3] = -1;
		//
		colorArr[19][0][1] = lightGreen;		
		colorArr[19][2][1] = yellow;	
		colorArr[19][2][2] = lightGreen;		
		colorArr[19][3][0] = lightGreen;		
		colorArr[19][3][1] = orange;	
		colorArr[19][3][2] = orange;	
		colorArr[19][4][2] = yellow;	
		colorArr[19][5][1] = lightGreen;		
		colorArr[19][5][2] = black;
		colorArr[19][6][0] = orange;	
		colorArr[19][6][1] = yellow;	
		colorArr[19][6][2] = black;
		colorArr[19][6][3] = green;
		colorArr[19][7][1] = green;	
		colorArr[19][7][2] = black;
		colorArr[19][7][3] = darkOrange;	
		colorArr[19][8][0] = darkBlue;		
		colorArr[19][8][1] = orange;
		colorArr[19][8][2] = yellow;
		colorArr[19][8][3] = black;
		colorArr[19][9][0] = black;
		colorArr[19][9][1] = orange;
		colorArr[19][9][2] = green;	
		colorArr[19][9][3] = black;
		colorArr[19][10][0] = orange;
		colorArr[19][10][1] = black;
		colorArr[19][10][2] = lightBlue;	
		colorArr[19][10][3] = yellow;	
		colorArr[19][11][0] = orange;	
		colorArr[19][11][1] = black;
		colorArr[19][11][2] = -1;
		colorArr[19][11][3] = black;
		colorArr[19][12][0] = -1;
		colorArr[19][12][1] = -1;
		colorArr[19][12][2] = -1;
		colorArr[19][12][3] = -1;
		colorArr[19][13][0] = -1;
		colorArr[19][13][1] = -1;
		colorArr[19][13][2] = -1;
		colorArr[19][13][3] = -1;
		colorArr[19][14][0] = lightGreen;	
		colorArr[19][14][1] = -1;
		colorArr[19][14][2] = -1;
		colorArr[19][14][3] = -1;
		colorArr[19][15][0] = -1;
		colorArr[19][15][1] = -1;
		colorArr[19][15][2] = -1;
		colorArr[19][15][3] = -1;
		colorArr[19][16][0] = -1;
		colorArr[19][16][2] = -1;
		colorArr[19][17][1] = -1;
		colorArr[19][17][3] = -1;
		///
		colorArr[20][1][1] = lightGreen;	
		colorArr[20][2][1] = lightGreen;	
		colorArr[20][2][3] = lightGreen;	
		colorArr[20][3][3] = black;
		colorArr[20][4][2] = darkOrange;
		colorArr[20][5][2] = lightGreen;	
		colorArr[20][6][0] = orange;
		colorArr[20][6][1] = lightGreen;	
		colorArr[20][6][2] = black;
		colorArr[20][7][1] = orange;
		colorArr[20][7][2] = lightGreen;	
		colorArr[20][8][1] = black;
		colorArr[20][8][3] = darkOrange;
		colorArr[20][9][0] = black;
		colorArr[20][9][1] = lightGreen;
		colorArr[20][9][2] = darkBlue;
		colorArr[20][9][3] = green;
		colorArr[20][10][0] = orange;
		colorArr[20][10][1] = orange;
		colorArr[20][10][2] = -1;
		colorArr[20][10][3] = yellow;
		colorArr[20][11][0] = orange;
		colorArr[20][11][1] = orange;
		colorArr[20][11][2] = -1;
		colorArr[20][11][3] = -1;
		colorArr[20][12][0] = lightGreen;
		colorArr[20][12][1] = -1;
		colorArr[20][12][2] = -1;
		colorArr[20][12][3] = black;
		colorArr[20][13][0] = -1;
		colorArr[20][13][1] = -1;
		colorArr[20][13][2] = -1;
		colorArr[20][13][3] = -1;
		colorArr[20][14][0] = -1;
		colorArr[20][14][1] = -1;
		colorArr[20][14][2] = -1;
		colorArr[20][14][3] = -1;
		colorArr[20][15][0] = -1;
		colorArr[20][15][1] = -1;
		colorArr[20][15][2] = -1;
		colorArr[20][15][3] = -1;
		colorArr[20][16][0] = -1;
		colorArr[20][16][2] = -1;
		colorArr[20][16][3] = -1;
		//
		colorArr[21][1][1] = lightGreen;
		colorArr[21][3][0] = orange;
		colorArr[21][3][1] = orange;
		colorArr[21][3][3] = lightGreen;
		colorArr[21][5][1] = black;
		colorArr[21][5][3] = darkOrange;
		colorArr[21][6][1] = lightGreen;
		colorArr[21][7][0] = lightGreen;
		colorArr[21][7][3] = lightGreen;
		colorArr[21][8][0] = orange;
		colorArr[21][8][1] = yellow;
		colorArr[21][8][2] = lightGreen;
		colorArr[21][8][3] = orange;
		colorArr[21][9][0] = black;
		colorArr[21][9][1] = black;
		colorArr[21][9][2] = orange;
		colorArr[21][9][3] = darkOrange;
		colorArr[21][10][0] = -1;
		colorArr[21][10][1] = yellow;
		colorArr[21][10][2] = lightGreen;
		colorArr[21][10][3] = -1;
		colorArr[21][11][0] = -1;
		colorArr[21][11][1] = -1;
		colorArr[21][11][2] = orange;
		colorArr[21][11][3] = -1;
		colorArr[21][12][0] = -1;
		colorArr[21][12][1] = -1;
		colorArr[21][12][2] = -1;
		colorArr[21][12][3] = -1;
		colorArr[21][13][0] = yellow;
		colorArr[21][13][1] = -1;
		colorArr[21][13][3] = -1;
		colorArr[21][14][0] = -1;
		colorArr[21][14][1] = -1;
		colorArr[21][14][2] = -1;
		colorArr[21][14][3] = -1;
		colorArr[21][15][0] = lightGreen;
		colorArr[21][15][1] = -1;
		colorArr[21][15][3] = darkBlue;
		colorArr[21][16][0] = -1;
		colorArr[21][17][0] = lightGreen;
		colorArr[21][17][3] = -1;
		///
		colorArr[22][2][1] = orange;
		colorArr[22][3][2] = black;
		colorArr[22][5][2] = black;
		colorArr[22][6][1] = black;
		colorArr[22][7][1] = lightGreen;
		colorArr[22][7][3] = black;
		colorArr[22][8][0] = lightGreen;
		colorArr[22][8][1] = lightGreen;
		colorArr[22][8][2] = black;
		colorArr[22][8][3] = yellow;
		colorArr[22][9][0] = orange;
		colorArr[22][9][1] = black;
		colorArr[22][9][2] = black;
		colorArr[22][10][0] = yellow;
		colorArr[22][10][2] = black;
		colorArr[22][11][0] = -1;
		colorArr[22][11][1] = -1;
		colorArr[22][11][2] = -1;
		colorArr[22][11][3] = -1;
		colorArr[22][12][0] = -1;
		colorArr[22][12][1] = -1;
		colorArr[22][12][2] = -1;
		colorArr[22][12][3] = -1;
		colorArr[22][13][0] = -1;
		colorArr[22][13][2] = lightGreen;
		colorArr[22][13][3] = -1;
		colorArr[22][14][1] = -1;
		colorArr[22][14][3] = -1;
		colorArr[22][15][0] = -1;
		colorArr[22][15][2] = lightGreen;
		colorArr[22][16][3] = -1;
		colorArr[22][17][1] = -1;
		colorArr[22][17][3] = -1;
		//
		colorArr[23][0][1] = yellow;
		colorArr[23][2][2] = orange;
		colorArr[23][3][2] = orange;
		colorArr[23][5][0] = orange;
		colorArr[23][6][3] = orange;
		colorArr[23][7][1] = lightGreen;	
		colorArr[23][7][2] = black;
		colorArr[23][7][3] = lightGreen;
		colorArr[23][8][1] = lightGreen;	
		colorArr[23][8][2] = yellow;
		colorArr[23][9][1] = black;
		colorArr[23][9][2] = yellow;
		colorArr[23][10][1] = black;
		colorArr[23][10][3] = yellow;
		colorArr[23][11][0] = black;
		colorArr[23][11][1] = -1;
		colorArr[23][11][2] = -1;
		colorArr[23][12][2] = -1;
		colorArr[23][12][3] = -1;
		colorArr[23][13][1] = -1;
		colorArr[23][13][3] = -1;
		colorArr[23][14][1] = -1;
		colorArr[23][15][3] = -1;
		colorArr[23][17][0] = -1;
	}
	
	public void draw() {
		//the background color is used to clear the display window at the beginning of each frame. 
		//necessary!! for 3D flipping. ?? to white??
		background(0);
		image(bkgImage, 0, 0);
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				//if (mousePressed && (j>4) && (random(0, 10)<2)) {
				if (mousePressed && (random(0, 10)<2)) {
					cellGrid[i][j].init();
					//println("flipping (" + i + ", " + j + "):" + cellGrid[i][j].currentAngle);
				}
				
				cellGrid[i][j].flip();
			}
		}
	}
	
//	public void resize(int w, int h) {
//		println("resizing");
//	}
	
}