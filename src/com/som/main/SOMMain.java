package com.som.main;

import com.som.core.SOM;
import com.som.core.SOMHelper;

public class SOMMain {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] inputArr = {{0,0,0,0,1,1},{0,0,1,1,0,0},{1,1,0,0,0,0},{1,1,0,0,0,0},{0,0,1,1,0,0}
								,{0,0,0,0,1,1},{0,0,1,1,0,0},{1,1,0,0,0,0},{1,1,0,0,0,0}};
		double[][] inputArr1 = {{0,0,0,0,0,1},{0,0,0,0,1,1},{1,1,0,0,0,0},{1,0,0,0,0,0}};

		ZooData zoo = new ZooData();
		zoo.parseData();
		double[][] aniData = zoo.getAnimalData();
		String[] aniNames = zoo.getAnimalNames();
		
		SOM som = new SOM();
		SOMHelper helper = new SOMHelper();
		
		som.initNeurons(helper);
		for(int i=0;i<600;i++){
			som.runEpoch(helper,aniData);
		}
		
		for(int i=0;i<aniNames.length;i++){
			helper.getWinningNeuron(aniData[i],som.getNeuronLayer(),aniNames[i]);
		}
	}

}
