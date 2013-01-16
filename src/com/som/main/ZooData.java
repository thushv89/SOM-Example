package com.som.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ZooData {

	double[][] animalData;
	String[] animalNames;

	public void parseData(){

		int dataLength = 19;
		int attrLenght = 17;
		animalNames = new String[dataLength];
		animalData = new double[dataLength][attrLenght];
		try {
			//use buffering, reading one line at a time
			//FileReader always assumes default encoding is OK!
			File aFile = new File("zoo.txt");
			BufferedReader input =  new BufferedReader(new FileReader(aFile));
			try {
				String line = null; //not declared within while loop
				int i=0;
				/*
				 * readLine is a bit quirky :
				 * it returns the content of a line MINUS the newline.
				 * it returns null only for the END of the stream.
				 * it returns an empty String if two newlines appear in a row.
				 */
				while (( line = input.readLine()) != null){
					String text = line;
					if(text!=null && text.length()>0){
						String[] tokens = text.split(",");
						animalNames[i]=tokens[0];
						for(int j=1;j<tokens.length;j++){
							animalData[i][j-1]=Integer.parseInt(tokens[j]);  
						}
					}
					i++;
				}
			}
			finally {
				input.close();
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}

	}

	public double[][] getAnimalData(){
		return animalData;
	}
	public String[] getAnimalNames(){
		return animalNames;
	}
}
