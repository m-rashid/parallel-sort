/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author merashid27
 */

import java.util.concurrent.*;


public class parallelPack extends RecursiveAction {
    int start;
    int end;
    int[] input;
    int[] bits;
    int[] output;
    int[] bitSum;
    int threshold;
    
    
    public parallelPack(int start, int end, int[] input, int[] bits, int[] bitSum, int[] output, int threshold){
        this.start = start;
        this.end = end;
        this.input = input;
        this.bits = bits;
        this.bitSum = bitSum;
        this.output = output;
        this.threshold = threshold;
    }
    
    @Override
    public void compute(){
        if((end - start) <= threshold){
            for(int i=0; i<input.length; i++){
                if(bits[i] == 1){
                    output[bitSum[i-1]] = input[i];
                }
            }
        }
        else{
            int mid = (end-start)/2;
            parallelPack left = new parallelPack(start, mid, input, bits, bitSum, output, threshold);
            parallelPack right = new parallelPack(start, mid, input, bits, bitSum, output, threshold);
            left.fork();
            right.compute();
            left.join();
        }
    }
    
}
