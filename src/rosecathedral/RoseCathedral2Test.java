package rosecathedral;

import java.util.Calendar;

import generativedesign.*;
import processing.core.PApplet;
import processing.core.PImage;
//import processing.pdf.*;


public class RoseCathedral2Test extends PApplet {
	
	private static final long serialVersionUID = 1L;

	PImage bkgImage;
	
	int stageW = 720;			//-- 720x960
	int stageH = 960;	
	int cellUnit = 40;
	int colNum, rowNum;
	
	int color0, color1, color2, color3, color4, color5, color6, color7;
	int color8;
	
	int black;			//75 --> 19 -->9
	int lightGreen;		//56 --> 14	-->7
	int orange;			//52 --> 13 -->6
	int yellow;			//39 --> 10 -->5
	int darkOrange;		//24 --> 12 -->6
	int lightBlue;		//13 -->  3 -->2
	int green;			//10 -->  3 -->2
	int darkBlue;		//9  -->  2 -->1
	
	int darkGreen;
	int scarlet;
	int darkScarlet1, darkScarlet2, darkScarlet3;
	int blue;
	int deepBlue;
	int lightPink, pink, darkPink, orangePink, purple;
	int cyan, yellowGreen;
	
	int[] orgPool;
	int[] extraPool;
	int[] colorPool;
	Cell2[][] cellGrid;			//-- 2 dimensional Array for Cell instances
	int[][][] colorArr;			//-- 3 dimensional Array for Cell color value data
	
	//-- for testing
	boolean isTesting = false;
	int[] colors;
	int[] filteredRowColors;
	int colorNum;
	String sortMode = null;
	
	boolean isReset = false;
	boolean isTracing = false;
	
	
	public void setup() {
		
		//-- set stage and image size
		size(stageW+1, stageH+1, P3D);
		noStroke();
		bkgImage = loadImage("rose720x960.jpg");			//-- 720x960
		
		background(0);
		image(bkgImage, 0, 0);
		
		//-- set initial cell color data
		colNum = ceil(stageW/cellUnit);
		rowNum = ceil(stageH/cellUnit);
		cellGrid = new Cell2[rowNum][colNum];
		colorArr = new int[rowNum][colNum][4];
		setColor();						
		
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
		//-- this is wrong
//		float[] blackArr = GenerativeDesign.RGBtoHSB(0, 0, 0);			//75 times
//		float[] greenArr = GenerativeDesign.RGBtoHSB(85, 254, 3);		//56 times
//		float[] orangeArr = GenerativeDesign.RGBtoHSB(246, 153, 0);		//52 times
//		float[] yellowArr = GenerativeDesign.RGBtoHSB(255, 228, 0);		//39 times
//		float[] scarletArr = GenerativeDesign.RGBtoHSB(255, 85, 0);		//16 times
//		float[] lightBlueArr = GenerativeDesign.RGBtoHSB(0, 162, 255);	//13 times
//		float[] darkGreenArr = GenerativeDesign.RGBtoHSB(50, 166, 5);	//10 times
//		float[] blueArr = GenerativeDesign.RGBtoHSB(0, 87, 158);		//9 times
//		float[] lightOrangeArr = GenerativeDesign.RGBtoHSB(255, 134, 0);//8 times
//		black = color(blackArr[0], blackArr[1], blackArr[2]);
//		lightGreen = color(greenArr[0], greenArr[1], greenArr[2]);
//		orange = color(orangeArr[0], orangeArr[1], orangeArr[2]);
//		yellow = color(yellowArr[0], yellowArr[1], yellowArr[2]);
//		scarlet = color(scarletArr[0], scarletArr[1], scarletArr[2]);
//		lightBlue = color(lightBlueArr[0], lightBlueArr[1], lightBlueArr[2]);
//		green = color(darkGreenArr[0], darkGreenArr[1], darkGreenArr[2]);
//		darkBlue = color(blueArr[0], blueArr[1], blueArr[2]);
//		darkOrange = color(lightOrangeArr[0], lightOrangeArr[1], lightOrangeArr[2]);
		
		
		//-- these values are set as RGB values
		black = color(0, 0, 0);	
		//
		lightGreen = color(85, 255, 0);		//old:color(85, 254, 3);
		yellow = color(255, 238, 0);		//color(255, 228, 0)
		//
		orange = color(255, 153, 0);		//old:color(246, 153, 0)
		darkOrange = color(255, 85, 0);		//old:color(255, 134, 0)
		//
		green = color(0, 170, 85);			//old:color(50, 166, 5)
		lightBlue = color(0, 170, 255);		//old:color(0, 162, 255)
		darkBlue = color(0, 85, 170);		//old:color(0, 87, 158), color(0, 85, 153)
		/////////////////////////////////////////
		
		darkGreen = color(0, 85, 17);		//old:color(0, 98, 45);
		scarlet = color(255, 34, 0);		
		darkScarlet1 = color(238, 17, 34);
		darkScarlet2 = color(170, 0, 0);
		darkScarlet3 = color(85, 0, 0);
		blue = color(0, 85, 255);
		deepBlue = color(0, 0, 85);
		lightPink = color(255,170,255);
		pink = color(255,85,255);
		darkPink = color(255,0,170);
		orangePink = color(255,85,170);
		purple = color(170,0,170);
		cyan = color(85,255,170);
		yellowGreen = color(170,255,85);
		/////////////////////////////////////////
			
		
		//-- this line should come after the above color settings
		colorMode(HSB, 255);
		
		
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
		
		if (isTesting) {
//			//-- get colors from image
//			colors = new int[colNum*rowNum];
//			int i = 0; 
//			for (int row=0; row<rowNum; row++) {
//			  for (int col=0; col<colNum; col++) {
//			    int px = (int) (col * cellUnit);
//			    int py = (int) (row * cellUnit);
//			    colors[i] = bkgImage.get(px, py);	//*****//
//			    i++;
//			  }
//			}
//			
//			//-- sort colors
//			colors = GenerativeDesign.sortColors(this, colors, GenerativeDesign.BRIGHTNESS);
//			
//			//-- filter colors
//			int fCount = 3;	//number of selected colors per row
//			colorNum = fCount*rowNum + 9;
//			colorPool = new int[colorNum];
//			filteredRowColors = new int[colNum]; 
//			int j = 0;		//index for colors array
//			int k = 0;		//index for colorTestPool array
//			for (int r=0; r<rowNum; r++) {
//			    for (int c=0; c<colNum; c++) {
//			      filteredRowColors[c] = colors[j];
//			      j++;
//			    }
//			    //-- filter filterdRowColors
//			    filteredRowColors = sort(filteredRowColors);
//			    colorPool[k++] = filteredRowColors[0];
//			    colorPool[k++] = filteredRowColors[1];
//			    colorPool[k++] = filteredRowColors[colNum-1];
//			}
//			//-- for testing
////			int index = 0;
////			for (int r2=0; r2<rowNum; r2++) {
////				println("row " + r2);
////				for (int c2=0; c2<3; c2++) {
////					println("hue[" + index + "]:" + colorPool[index]);
////					index++;
////		    	}
////			}
//			
//			//-- temporarily --//
//			//-----------------//
//			colorPool[k] = color0 = black;
//			colorPool[k+1] = color1 = lightGreen;
//			colorPool[k+2] = color2 = orange;
//			colorPool[k+3] = color3 = yellow;
////			colorPool[k+4] = color4 = scarlet;
//			colorPool[k+4] = color4 = darkOrange;
//			colorPool[k+5] = color5 = lightBlue;
//			colorPool[k+6] = color6 = green;
//			colorPool[k+7] = color7 = darkBlue;
//			
//			//-----------------//
//			
////			for (int r3=0; r3<3; r3++) {
////				println("row extra " + r3);
////				for (int c3=0; c3<3; c3++) {
////					println("hue[" + index + "]:" + colorPool[index]);
////					index++;
////		    	}
////			}
//			
////			colorPool = GenerativeDesign.sortColors(this, colorPool, sortMode);
			colorNum = 40;
			
			colorPool = new int[colorNum];
			orgPool = new int[39];
			//-- original colors by ratio
			for (int i=0; i<9; i++) {
				orgPool[i] = colorPool[i] = black;
			}
			for (int i=9; i<16; i++) {
				orgPool[i] = colorPool[i] = lightGreen;
			}
			for (int i=16; i<22; i++) {
				orgPool[i] = colorPool[i] = orange;
			}
			for (int i=22; i<27; i++) {
				orgPool[i] = colorPool[i] = yellow;
			}
			for (int i=27; i<33; i++) {
				orgPool[i] = colorPool[i] = darkOrange;
			}
			orgPool[33] = colorPool[33] = lightBlue;
			orgPool[34] = colorPool[34] = lightBlue;
			orgPool[35] = colorPool[35] = green;
			orgPool[36] = colorPool[36] = green;			
			orgPool[37] = colorPool[37] = darkBlue;
			orgPool[38] = colorPool[38] = darkGreen;
			orgPool[39] = colorPool[39] = scarlet;
			
			//-- additional reposterized colors selected from the image
			extraPool = new int[21];
			extraPool[0] = yellowGreen;
			extraPool[1] = darkScarlet1;
			extraPool[2] = darkScarlet2;
			extraPool[3] = darkScarlet3;
			extraPool[4] = blue;
			extraPool[5] = deepBlue;
			extraPool[6] = lightPink;
			extraPool[7] = pink;
			extraPool[8] = darkPink;
			extraPool[9] = orangePink;
			extraPool[10] = purple;
			extraPool[11] = cyan;
					
			lightPink = color(255,170,255);
			pink = color(255,85,255);
			darkPink = color(255,0,170);
			orangePink = color(255,85,170);
			purple = color(170,0,170);
			cyan = color(85,255,170);
			yellowGreen = color(170,255,85);
			/*
			int black;			//75 --> 19 -->9
			int lightGreen;		//56 --> 14	-->7
			int orange;			//52 --> 13 -->6
			int yellow;			//39 --> 10 -->5
			int darkOrange;		//24 --> 12 -->6
			//
			int lightBlue;		//13 -->  3 -->2
			int green;			//10 -->  3 -->2
			int darkBlue;		//9  -->  2 -->1
			*/
		} else {
			colorNum = 8;
			colorPool = new int[colorNum];
			colorPool[0] = color0 = black;
			colorPool[1] = color1 = lightGreen;
			colorPool[2] = color2 = orange;
			colorPool[3] = color3 = yellow;
			colorPool[4] = color4 = darkOrange;
			colorPool[5] = color5 = lightBlue;
			colorPool[6] = color6 = green;
			colorPool[7] = color7 = darkBlue;
			
//			colorPool[4] = color4 = scarlet;
			
		}
			
			//-- manual(hard-coded) setting
			colorArr[0][1][0]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			//
			colorArr[1][3][3]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[1][7][3]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[1][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[1][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[1][12][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[1][16][1] = -1;		//open
			//
			colorArr[2][0][2]  = (isTesting)? colorPool[(int) random(colorNum)]: black;						
			colorArr[2][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[2][15][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[2][16][1] = -1;
			//
			colorArr[3][1][3]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[3][6][0]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[3][8][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[3][12][0] = -1;
			colorArr[3][12][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
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
			colorArr[4][15][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[4][16][1] = -1;
			//
			colorArr[5][6][1]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[5][9][0]  = -1;
			colorArr[5][10][1] = -1;
			colorArr[5][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[5][11][3] = -1;
			colorArr[5][12][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[5][13][0] = -1;
			colorArr[5][13][1] = -1;
			colorArr[5][14][1] = -1;
			colorArr[5][16][1] = -1;
			//
			colorArr[6][3][1]  = -1;
			colorArr[6][3][3]  = -1;
			colorArr[6][4][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[6][5][0]  = -1;
			colorArr[6][7][2]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
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
			colorArr[7][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[7][11][0] = -1;
			colorArr[7][11][1] = -1;
			colorArr[7][11][2] = -1;
			colorArr[7][12][1] = -1;
			colorArr[7][12][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
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
			colorArr[8][1][1]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
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
			colorArr[8][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[8][11][2] = -1;
			colorArr[8][12][0] = -1;
			colorArr[8][13][0] = -1;
			colorArr[8][13][1] = -1;
			colorArr[8][13][3] = -1;
			colorArr[8][14][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[8][14][1] = -1;
			colorArr[8][14][2] = -1;
			colorArr[8][15][1] = -1;
			colorArr[8][15][3] = -1;
			colorArr[8][16][0] = -1;
			colorArr[8][16][1] = -1;
			//
			colorArr[9][5][3]  = -1;
			colorArr[9][6][1]  = -1;
			colorArr[9][6][3]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[9][8][0]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[9][10][2] = -1;
			colorArr[9][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
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
			colorArr[10][1][2]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[10][4][3]  = -1;
			colorArr[10][5][1]  = -1;
			colorArr[10][7][0]  = -1;
			colorArr[10][8][2]  = -1;
			colorArr[10][8][3]  = -1;
			colorArr[10][9][1]  = -1;
			colorArr[10][9][3]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[10][10][0] = -1;
			colorArr[10][10][1] = -1;
			colorArr[10][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
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
			colorArr[11][5][0]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[11][6][1]  = -1;	
			colorArr[11][7][1]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[11][8][0]  = -1;
			colorArr[11][8][2]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[11][8][3]  = -1;
			colorArr[11][9][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[11][9][2]  = -1;
			colorArr[11][9][3]  = -1;
			colorArr[11][10][0] = -1;
			colorArr[11][10][1] = -1;
			colorArr[11][10][2] = -1;
			colorArr[11][11][0] = -1;
			colorArr[11][11][1] = -1;
			colorArr[11][11][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[11][12][0] = -1;
			colorArr[11][12][1] = -1;
			colorArr[11][12][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[11][12][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[11][13][0] = -1;
			colorArr[11][13][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
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
			colorArr[12][2][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[12][4][0]  = -1;
			colorArr[12][5][1]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[12][7][0]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[12][7][3]  = -1;
			colorArr[12][8][0]  = -1;
			colorArr[12][8][2]  = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;
			colorArr[12][9][0]  = -1;
			colorArr[12][9][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[12][10][0] = -1;
			colorArr[12][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[12][10][2] = -1;
			colorArr[12][10][3] = -1;
			colorArr[12][11][0] = -1;
			colorArr[12][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[12][11][2] = -1;
			colorArr[12][11][3] = -1;
			colorArr[12][12][0] = -1;
			colorArr[12][12][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
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
			colorArr[12][15][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[12][16][0] = -1;
			colorArr[12][16][1] = -1;
			colorArr[12][16][3] = -1;
			colorArr[12][17][0] = -1;
			//
			colorArr[13][4][2]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[13][6][1]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[13][7][3]  = -1;
			colorArr[13][8][0]  = -1;
			colorArr[13][8][2]  = -1;
			colorArr[13][8][3]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[13][9][0]  = -1;
			colorArr[13][9][1]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[13][9][2]  = -1;
			colorArr[13][9][3]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[13][10][0] = -1;
			colorArr[13][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[13][10][2] = -1;
			colorArr[13][10][3] = -1;
			colorArr[13][11][0] = -1;
			colorArr[13][11][1] = -1;
			colorArr[13][11][2] = -1;
			colorArr[13][11][3] = -1;
			colorArr[13][12][0] = -1;
			colorArr[13][12][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[13][12][2] = -1;	
			colorArr[13][12][3] = -1;
			colorArr[13][13][0] = -1;
			colorArr[13][13][1] = -1;
			colorArr[13][13][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[13][13][3] = -1;
			colorArr[13][14][0] = -1;
			colorArr[13][14][1] = -1;
			colorArr[13][14][2] = -1;
			colorArr[13][14][3] = -1;
			colorArr[13][15][0] = -1;
			colorArr[13][15][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[13][15][2] = -1;
			colorArr[13][15][3] = -1;
			colorArr[13][16][0] = -1;
			colorArr[13][16][2] = -1;
			colorArr[13][16][3] = -1;
			//
			colorArr[14][5][3]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[14][7][3]  = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[14][8][1]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[14][8][2]  = -1;
			colorArr[14][8][3]  = -1;
			colorArr[14][9][0]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[14][9][1]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[14][9][2]  = -1;
			colorArr[14][9][3]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[14][10][0] = -1;
			colorArr[14][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[14][10][2] = -1;
			colorArr[14][10][3] = -1;	
			colorArr[14][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[14][11][1] = -1;
			colorArr[14][11][2] = -1;
			colorArr[14][11][3] = -1;
			colorArr[14][12][0] = -1;
			colorArr[14][12][1] = -1;
			colorArr[14][12][2] = -1;	
			colorArr[14][12][3] = -1;
			colorArr[14][13][0] = -1;
			colorArr[14][13][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[14][13][2] = -1;
			colorArr[14][13][3] = -1;
			colorArr[14][14][0] = -1;
			colorArr[14][14][1] = -1;
			colorArr[14][14][2] = -1;
			colorArr[14][14][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
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
			colorArr[15][2][1]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[15][3][0]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[15][3][2]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[15][5][1]  = -1;
			colorArr[15][6][2]  = -1;
			colorArr[15][7][0]  = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[15][7][2]  = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[15][7][3]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][8][0]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][8][1]  = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[15][8][2]  = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[15][8][3]  = -1;
			colorArr[15][9][0]  = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
			colorArr[15][9][1]  = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;	
			colorArr[15][9][2]  = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;
			colorArr[15][9][3]  = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: green;	
			colorArr[15][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][10][2] = -1;
			colorArr[15][10][3] = -1;
			colorArr[15][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;
			colorArr[15][11][1] = -1;
			colorArr[15][11][2] = -1;
			colorArr[15][11][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][12][0] = -1;
			colorArr[15][12][1] = -1;
			colorArr[15][12][2] = -1;	
			colorArr[15][12][3] = -1;
			colorArr[15][13][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[15][13][1] = -1;
			colorArr[15][13][2] = -1;
			colorArr[15][13][3] = -1;
			colorArr[15][14][0] = -1;
			colorArr[15][14][1] = -1;
			colorArr[15][14][2] = -1;
			colorArr[15][14][3] = -1;
			colorArr[15][15][0] = -1;
			colorArr[15][15][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[15][15][2] = -1;
			colorArr[15][15][3] = -1;
			colorArr[15][16][0] = -1;
			colorArr[15][16][1] = -1;
			colorArr[15][16][2] = -1;
			colorArr[15][16][3] = -1;
			colorArr[15][17][2] = -1;
			//
			colorArr[16][1][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[16][2][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[16][4][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[16][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[16][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[16][6][3] = (isTesting)? colorPool[(int) random(colorNum)]: green;
			colorArr[16][7][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[16][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;		
			colorArr[16][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
			colorArr[16][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[16][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[16][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[16][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[16][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[16][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[16][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: green;
			colorArr[16][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[16][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[16][11][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][11][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[16][12][0] = -1;
			colorArr[16][12][1] = (isTesting)? colorPool[(int) random(colorNum)]: green;
			colorArr[16][12][2] = -1;	
			colorArr[16][12][3] = -1;
			colorArr[16][13][0] = -1;
			colorArr[16][13][1] = -1;
			colorArr[16][13][2] = -1;
			colorArr[16][13][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[16][14][0] = -1;
			colorArr[16][14][1] = -1;
			colorArr[16][14][2] = -1;
			colorArr[16][14][3] = -1;
			colorArr[16][15][0] = -1;
			colorArr[16][15][1] = -1;
			colorArr[16][15][2] = -1;
			colorArr[16][15][3] = -1;
			colorArr[16][16][0] = -1;
			colorArr[16][16][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[16][16][2] = -1;
			colorArr[16][16][3] = -1;
			colorArr[16][17][0] = -1;
			colorArr[16][17][1] = -1;
			colorArr[16][17][2] = -1;
			colorArr[16][17][3] = -1;
			///
			colorArr[17][2][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][4][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][5][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][5][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][6][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][7][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;	
			colorArr[17][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;		
			colorArr[17][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[17][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[17][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: green;	
			colorArr[17][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[17][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[17][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
			colorArr[17][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;	
			colorArr[17][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[17][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[17][10][2] = -1;
			colorArr[17][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[17][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;	
			colorArr[17][11][2] = (isTesting)? colorPool[(int) random(colorNum)]: green;
			colorArr[17][11][3] = -1;
			colorArr[17][12][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[17][12][1] = -1;
			colorArr[17][12][2] = -1;	
			colorArr[17][12][3] = -1;
			colorArr[17][13][0] = -1;
			colorArr[17][13][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
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
			colorArr[18][0][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][0][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][1][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][2][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][3][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][3][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][4][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[18][4][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][5][0] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][5][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[18][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[18][6][0] = -1;
			colorArr[18][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][6][2] = -1;
			colorArr[18][6][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][7][0] = -1;
			colorArr[18][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[18][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;	
			colorArr[18][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[18][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[18][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[18][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;
			colorArr[18][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;	
			colorArr[18][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[18][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[18][11][2] = -1;
			colorArr[18][11][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[18][12][0] = -1;
			colorArr[18][12][1] = -1;
			colorArr[18][12][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[18][12][3] = -1;
			colorArr[18][13][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[18][13][1] = -1;
			colorArr[18][13][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[18][13][3] = -1;
			colorArr[18][14][0] = -1;
			colorArr[18][14][1] = -1;
			colorArr[18][14][2] = -1;
			colorArr[18][14][3] = -1;
			colorArr[18][15][0] = -1;
			colorArr[18][15][1] = -1;
			colorArr[18][15][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[18][15][3] = -1;
			colorArr[18][16][0] = -1;
			colorArr[18][16][1] = -1;
			colorArr[18][16][2] = -1;
			colorArr[18][16][3] = -1;
			//
			colorArr[19][0][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[19][2][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[19][2][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[19][3][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[19][3][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[19][3][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[19][4][2] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[19][5][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;		
			colorArr[19][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][6][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;	
			colorArr[19][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[19][6][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][6][3] = (isTesting)? colorPool[(int) random(colorNum)]: green;
			colorArr[19][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: green;	
			colorArr[19][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
			colorArr[19][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;		
			colorArr[19][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[19][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[19][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[19][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: green;	
			colorArr[19][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[19][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightBlue;	
			colorArr[19][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;	
			colorArr[19][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;	
			colorArr[19][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][11][2] = -1;
			colorArr[19][11][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[19][12][0] = -1;
			colorArr[19][12][1] = -1;
			colorArr[19][12][2] = -1;
			colorArr[19][12][3] = -1;
			colorArr[19][13][0] = -1;
			colorArr[19][13][1] = -1;
			colorArr[19][13][2] = -1;
			colorArr[19][13][3] = -1;
			colorArr[19][14][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
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
			colorArr[20][1][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][2][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][2][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][3][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[20][4][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[20][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][6][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][6][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[20][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[20][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[20][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[20][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[20][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[20][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;
			colorArr[20][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkGreen;
			colorArr[20][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][10][2] = -1;
			colorArr[20][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[20][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][11][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[20][11][2] = -1;
			colorArr[20][11][3] = -1;
			colorArr[20][12][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[20][12][1] = -1;
			colorArr[20][12][2] = -1;
			colorArr[20][12][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
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
			colorArr[21][1][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][3][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[21][3][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[21][3][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][5][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[21][5][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[21][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][7][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[21][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[21][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[21][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[21][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[21][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[21][9][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkOrange;
			colorArr[21][10][0] = -1;
			colorArr[21][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[21][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][10][3] = -1;
			colorArr[21][11][0] = -1;
			colorArr[21][11][1] = -1;
			colorArr[21][11][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[21][11][3] = -1;
			colorArr[21][12][0] = -1;
			colorArr[21][12][1] = -1;
			colorArr[21][12][2] = -1;
			colorArr[21][12][3] = -1;
			colorArr[21][13][0] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[21][13][1] = -1;
			colorArr[21][13][3] = -1;
			colorArr[21][14][0] = -1;
			colorArr[21][14][1] = -1;
			colorArr[21][14][2] = -1;
			colorArr[21][14][3] = -1;
			colorArr[21][15][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][15][1] = -1;
			colorArr[21][15][3] = (isTesting)? colorPool[(int) random(colorNum)]: darkBlue;
			colorArr[21][16][0] = -1;
			colorArr[21][17][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[21][17][3] = -1;
			///
			colorArr[22][2][1] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[22][3][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][5][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][6][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: darkGreen;
			colorArr[22][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][8][0] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[22][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[22][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][8][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[22][9][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[22][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][10][0] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[22][10][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[22][11][0] = -1;
			colorArr[22][11][1] = -1;
			colorArr[22][11][2] = -1;
			colorArr[22][11][3] = -1;
			colorArr[22][12][0] = -1;
			colorArr[22][12][1] = -1;
			colorArr[22][12][2] = -1;
			colorArr[22][12][3] = -1;
			colorArr[22][13][0] = -1;
			colorArr[22][13][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[22][13][3] = -1;
			colorArr[22][14][1] = -1;
			colorArr[22][14][3] = -1;
			colorArr[22][15][0] = -1;
			colorArr[22][15][2] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[22][16][3] = -1;
			colorArr[22][17][1] = -1;
			colorArr[22][17][3] = -1;
			//
			colorArr[23][0][1] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[23][2][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[23][3][2] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[23][5][0] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[23][6][3] = (isTesting)? colorPool[(int) random(colorNum)]: orange;
			colorArr[23][7][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[23][7][2] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[23][7][3] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;
			colorArr[23][8][1] = (isTesting)? colorPool[(int) random(colorNum)]: lightGreen;	
			colorArr[23][8][2] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[23][9][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[23][9][2] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[23][10][1] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[23][10][3] = (isTesting)? colorPool[(int) random(colorNum)]: yellow;
			colorArr[23][11][0] = (isTesting)? colorPool[(int) random(colorNum)]: black;
			colorArr[23][11][1] = -1;
			colorArr[23][11][2] = -1;
			colorArr[23][12][2] = -1;
			colorArr[23][12][3] = -1;
			colorArr[23][13][1] = -1;
			colorArr[23][13][3] = -1;
			colorArr[23][14][1] = -1;
			colorArr[23][15][3] = -1;
			colorArr[23][17][0] = -1; 

	}//setColor
	
	public void draw() {
		background(0);
		image(bkgImage, 0, 0);
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				//if (mousePressed && (j>4) && (random(0, 10)<2)) {
				if (mousePressed && (random(10)<2)) {
					cellGrid[i][j].init();
					//println("flipping (" + i + ", " + j + "):" + cellGrid[i][j].currentAngle);
				}
				cellGrid[i][j].flip();
			}
		}
		
		//-- draw colorPool for test5
		/*
//		if (sortMode != null) {
//			colorPool = GenerativeDesign.sortColors(this, colorPool, sortMode);
//			int l = 0;
//			for (int r=0; r<rowNum; r++) {
//				float bestSaturation = 0;
//				float bestBrightness = 0;
//				int bestSaturationIndex = 0;
//				int bestBrightnessIndex = 0;
//				for (int c=0; c<3; c++) {
//					int color = colorPool[l];
//					float currentS = saturation(color);
//					float currentB = brightness(color);
//					if (currentS >= bestSaturation) {
//						bestSaturation = currentS;
//						bestSaturationIndex = l;
//					}
//					if (currentB >= bestBrightness) {
//						bestBrightness = currentB;
//						bestBrightnessIndex = l;
//					}
//					fill(colorPool[l]);
//					rect(c*cellUnit, r*cellUnit, cellUnit, cellUnit);
//					l++;
//		    	}
//				fill(colorPool[bestSaturationIndex]);
//				rect(4*cellUnit, r*cellUnit, cellUnit, cellUnit);
////				fill(colorPool[bestBrightnessIndex]);
////				rect(6*cellUnit, r*cellUnit, cellUnit, cellUnit);
//				println(r + " s:" + saturation(colorPool[bestSaturationIndex]) + ", h:" + hue(colorPool[bestSaturationIndex]));
//			}
			*/
			
//			if (isTracing) {
//				int i=0;
//				for (int r=0; r<rowNum; r++) {
//					println("row" + r);
//					for (int c=0; c<3; c++) {
//						int color = colorPool[i];
//						println("b:" + brightness(color) + ", s:" + saturation(color));
//						i++;
//			    	}
//				}
//			}
//		}
	}//draw
	
	public void keyReleased(){
//		if (key=='p' || key=='P') savePDF = true;
		if (key=='s' || key=='S') saveFrame(timestamp()+"_##.png");
		if (key=='r') isReset = true;
		if (key=='t' || key=='T') isTracing = true;

	  
	  if (key == '4') {
		  sortMode = null;
	  }
	  if (key == '5') {
		  sortMode = GenerativeDesign.HUE;
	  }
	  if (key == '6') {
		  sortMode = GenerativeDesign.SATURATION;
	  }
	  if (key == '7') {
		  sortMode = GenerativeDesign.BRIGHTNESS;
	  }
	  if (key == '8') {
		  sortMode = GenerativeDesign.GRAYSCALE;
	  }
//	  if (key == '9') {
//		  isFiltered = false;
//		  //-- trace color values
//		  int index = 0;
//		  for (int i=0; i<rowCount; i++) {
//			  println("\nrow " + i);
//			  for (int j=0; j<colCount; j++) {
//				  if (j<10) {
//					  println("	col 0" + j + ":" + colors[index]);
//				  } else {
//					  println("	col " + j + ":" + colors[index]);
//				  }
//				  index++;
//		    }
//		  }
//	  }
	}
	
	// timestamp
	private String timestamp() {
	  Calendar now = Calendar.getInstance();
	  return String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS", now);
	}
}