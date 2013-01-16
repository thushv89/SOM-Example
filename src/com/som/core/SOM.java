package com.som.core;

public class SOM {
	// This is a comment

	double[][][] neurons;
	
	//Initialize neurons
	public void initNeurons(SOMHelper helper){
		neurons = helper.initNeurons(SOMConstants.NEURON_ROWS_X,SOMConstants.NEURON_ROWS_Y,SOMConstants.DIMENSIONS);
	}
	
	public void runEpoch(SOMHelper helper, double[][] input){
		
		//System.out.println("---------------------EPOCH-------------------");
		
		//do for all inputs
		for(int i=0;i<input.length;i++) {
			int winner_x=0,winner_y=0;
			
			double currDistance=1000;
			double prevDistance=1000;
			
			//get the winning neuron
			for (int j=0;j<SOMConstants.NEURON_ROWS_X;j++){
				for (int k=0;k<SOMConstants.NEURON_ROWS_Y;k++){
					currDistance = helper.calcEucDist(input[i], neurons[j][k]);
					if(currDistance<prevDistance){
						winner_x = j;
						winner_y = k;
						prevDistance = currDistance;
					}
					
				}
			}
			
			double[] winningNeuron = neurons[winner_x][winner_y];
			//System.out.println("Winning "+winner_x+","+winner_y);
			
			for(int x=0;x<SOMConstants.NEURON_ROWS_X;x++){
				for(int y=0;y<SOMConstants.NEURON_ROWS_Y;y++){
					neurons[x][y]= helper.add(neurons[x][y],helper.multiplyArrayByConst(SOMConstants.LEARNING_RATE*helper.calcNeighFunc(winner_x, winner_y, x, y),helper.substract(input[i], neurons[x][y])));
				}
			}
		}
		
		/*
		for(int x=0;x<SOMConstants.NEURON_ROWS_X;x++){
			for(int y=0;y<SOMConstants.NEURON_ROWS_Y;y++){
				for(int z=0;z<SOMConstants.DIMENSIONS;z++){
					System.out.print(neurons[x][y][z]+"\t");
				}
				System.out.println("");
			}
			System.out.println("\n");
		}*/
		
		//decay learning rate and the neighborhood radius (Convergence)
		SOMConstants.LEARNING_RATE = .8*SOMConstants.LEARNING_RATE;
		SOMConstants.NEIGHBORHOOD_RADIUS = .8*SOMConstants.NEIGHBORHOOD_RADIUS;
	}
	
	public double[][][] getNeuronLayer(){
		return neurons; 
	}
}
