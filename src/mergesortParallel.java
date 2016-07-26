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

public class mergesortParallel extends RecursiveAction{
   
    int threshold;
    
    int start;
    int end;
    int[] arr;
    mergesortParallel left;
    mergesortParallel right;
    
    public mergesortParallel(int[] arr, int start, int end, int threshold){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
        
        
    }
    
    @Override
    public void compute(){
        int middle = start + (end-start)/2;
        
        if(end-start <= threshold){
            Arrays.sort(arr, start, end);    
            return;
        }
        
        invokeAll(
               left = new mergesortParallel(arr, start, middle, threshold),
               right = new mergesortParallel(arr, middle, end, threshold) 
        );
        sequentialMerge(middle);
            
    }
    
    public void sequentialMerge(int middle){
        
        
        
    }
    
    public int size(){
       return end-start;
    }
    
    public mergesortParallel getLarger(){
        if(left.size()>right.size()){
            return left;
        }
        return right;
    }
}
