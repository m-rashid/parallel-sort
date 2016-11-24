/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author merashid27
 */

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.Random;

public class parallelMap extends RecursiveAction{
    
    int start, end;
    int[] arr;
    int threshold;
    int bits[];
    int pivot;
    
    public parallelMap(int[] arr, int pivot, int start, int end, int threshold, int[] bits){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this. threshold = threshold;
        this.bits = bits;
        this.pivot = pivot;
    }
    
    @Override
    public void compute(){
        
        if((end - start) <= threshold){
            for(int i=0; i<arr.length; i++){
                if(arr[i]>pivot){
                    bits[i] = 1;
                }
                else{
                    bits[i] = 0;
                }
            }
        }
        else{
            int mid = (start+end)/2;
            parallelMap left = new parallelMap(arr, pivot, start, mid, threshold, bits);
            parallelMap right = new parallelMap(arr, pivot, mid, end, threshold, bits);
            left.fork();
            right.compute();
            left.join();
        }
        
    }
    
}
