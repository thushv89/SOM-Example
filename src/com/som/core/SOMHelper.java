package com.som.core;

public class SOMHelper {

	public double[][][] initNeurons(int rowsX,int rowsY, int dimensions) {
		double[][][] neuronLayer = new double[rowsX][rowsY][dimensions];
		for (int i=0; i<rowsX;i++){
			for (int j=0; j<rowsY;j++){
				for(int k=0;k<dimensions;k++){
					//neuronLayer[i][j][k]=Math.random();
					neuronLayer[i][j][k]=0.4;
				}
			}
		}
		return neuronLayer;
	} 

	public double[] getWinningNeuron(double[] pattern,double[][][] neurons,String toPrint){
		int winner_x=0,winner_y=0;
		
		//initialize variables to a higher value 
		double currDistance = 1000;
		double prevDistance = 1000;
		
		//get the winning neuron
		for (int j=0;j<SOMConstants.NEURON_ROWS_X;j++){
			for (int k=0;k<SOMConstants.NEURON_ROWS_Y;k++){
				currDistance = calcEucDist(pattern, neurons[j][k]);
				if(currDistance<prevDistance){
					winner_x = j;
					winner_y = k;
					prevDistance = currDistance;
				}
				
			}
		}
		System.out.println(toPrint +" - "+winner_x + "," + winner_y);
		return new double[]{winner_x,winner_y};
	}
	
	
	public double calcEucDist(double[] pattern,double[] neuron) {
		double distance =0.0;
		for (int i=0;i<pattern.length;i++){
			distance += Math.pow(pattern[i]-neuron[i], 2);
		}
		return Math.sqrt(distance);
	}


	public double[] substract(double[] array1,double[] array2) {
		double[] array3 = new double[array1.length];
		for (int i=0;i<array1.length;i++){
			array3[i] = array1[i]-array2[i];
		}	
		return array3;
	}
	public double[] add(double[] array1,double[] array2) {
		double[] array3 = new double[array1.length];
		for (int i=0;i<array1.length;i++){
			array3[i] = array1[i]+array2[i];
		}
		return array3;
	}
	
	public double[] multiplyArrayByConst(double constant,double[] array) {
		for (int i=0;i<array.length;i++) {
			array[i]=array[i]*constant;
		}
		return array;
	}
	
	public double calcNeighFunc(int sourceX,int sourceY,int neuronX, int neuronY) {
		double value = Math.exp((-1/(2*Math.pow(SOMConstants.NEIGHBORHOOD_RADIUS,2))) *(Math.pow(sourceX-neuronX, 2)+Math.pow(sourceY-neuronY,2)));
		
		if(Math.sqrt(Math.pow(sourceX-neuronX, 2)+Math.pow(sourceY-neuronY, 2))>SOMConstants.NEIGHBORHOOD_RADIUS){
			value =0;
		}
		//System.out.println("Neighborhood "+sourceX+","+sourceY+" "+neuronX+","+neuronY+"\t"+value);
		return value;
	}
}
