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
import java.util.concurrent.RecursiveTask;
import java.util.Random;

public class parallelSum extends RecursiveTask<Integer>{
    
    int start, end, threshold;
    int[] arr;
   // int ans = 0;
    
    parallelSum(int [] arr, int start, int end){
        this.start = start;
        this.end = end;
        this.arr = arr;
    }
    
    protected Integer compute(){
        if((end - start)<= threshold){
            int ans = 0;
            for(int i=start; i<end; i++){
                ans += arr[i];
                return ans;
            }
        }
        
        int mid = (start+end)/2;
        parallelSum left = new parallelSum(arr, start, mid);
        parallelSum right = new parallelSum(arr, mid, end);
            
        left.fork();
        int rightAns = right.compute();
        int leftAns = left.join();
        return leftAns + rightAns;
        
    }
    
}
