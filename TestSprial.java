package com.mypackage.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author catakamc
 * This program accepts input.txt
 *
 */
public class TestSprial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Declaring File scanner and reader
		Scanner fileScanner = null;
		FileReader reader = null;
		ArrayList<String> list = new ArrayList<String> ();		
		
		try {			
			//Reading file path
			reader =new FileReader("input.txt");
			
			//Reading file reader in a scanner
			fileScanner = new Scanner(reader);	
						
			while(fileScanner.hasNextLine()){
				list.add(fileScanner.nextLine().trim());
			}
			
			//Validate the list to fit into 2 dimensional array, which will be used as data structure.
			if(checkValidListToPrintSpiral(list)) {
				System.out.println("The given strings in the file input cannot be applied for printing in Spriral formatted string");
				System.exit(0);
			}
							
			int rowSize= list.size();
			int colSize = list.get(0).length();
			
			char[][] charArray = new char[rowSize][colSize];
			
			//Creating a two dimensional array of given list.
			for(int i=0; i<list.size(); i++){
				char[] rowCharArray = list.get(i).toCharArray();
				for(int j=0; j<rowCharArray.length; j++){
					charArray[i][j]=rowCharArray[j];
				}
			}
				
			//Calling the print char array method
			String finalString = printCharArrayForward(charArray, 0, rowSize, 0, colSize);
			
			//Printing output 
			System.out.println("!!! Final Spiral formatted String !!!! :   "+finalString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(reader!=null) reader.close();
				if(fileScanner != null) fileScanner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/*
	 * Method to parse the array in forward which accepts cArray two dimensional, startRow, endRow, stCol, endCol
	 * 
	 * @return String
	 */
	private static String printCharArrayForward(char[][] cArray, int stRow, int endRow, int stCol, int endCol){
			
		StringBuilder output = new StringBuilder();		
			for(int i=stRow; i<endRow; i++){					
				//If row next char 
				if(i==stRow) {
					for(int j=stCol; j<endCol; j++) {
						output.append(cArray[i][j]);
					}
				}				
				else if(i==endRow-1) {
					for(int j=endCol-1; j>=stCol; j--) {
						output.append(cArray[i][j]);
					}
					String outputString = printCharArrayBackward(cArray, stRow+1, endRow-1, stCol);					
					output.append(outputString);
					String outputString2 = printCharArrayForward(cArray, stRow+1, endRow-1, stCol+1, endCol-1);
					output.append(outputString2);
					
				}
				else {
					output.append(cArray[i][endCol-1]);
				}
			}		
		return output.toString();
	}
	
	
	/*
	 * Method to parse the array in backward which accepts cArray two dimensional, startRow, endRow, indexCol
	 * 
	 * @return String
	 */
	private static String printCharArrayBackward(char[][] cArray, int stRow, int endRow, int indexCol){		
		
		StringBuilder output = new StringBuilder();
		
			for(int i=endRow-1; i>=stRow; i--){					
				output.append(cArray[i][indexCol]);
			}
		return output.toString();
	}
	
	/*
	 * Method to validate the list to whether it can created as two dimensional array, which accepts ArrayList list
	 * 
	 * @return boolean
	 */
	
	private static boolean checkValidListToPrintSpiral(ArrayList<String> list) {			
		int firstRowCharLength=0;
		if(list != null && !list.isEmpty()) {
			firstRowCharLength = list.get(0).length();
		}
		for(String str : list){
			if(firstRowCharLength != str.length()) {				
				return true;		
			}	
		}
		return false;
	}
	
	
}
