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
import java.util.*;

public class mergesortParallel extends RecursiveAction{
   
    int threshold;
    
    int start;
    int end;
    static int[] arr;
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
               new mergesortParallel(arr, start, middle, threshold),
               new mergesortParallel(arr, middle, end, threshold) 
        );
        sequentialMerge(middle);
        
        
            
    }
    
    /*
    public void sequentialMerge(int middle){
        
        int[] temp = new int[arr.length];
        int tempStart = 0;
        int leftStart = start;
        int rightStart = middle;
        int rightEnd = end;
        int leftEnd = middle-1;
        
        while(leftStart<=leftEnd && rightStart<=rightEnd){
            if(arr[leftStart]<=arr[rightStart]){
                temp[tempStart++] = arr[leftStart++];
            }
            else{
                temp[tempStart++] = arr[rightStart++];
            }
        }
        
        while(leftStart<=leftEnd){
            temp[tempStart++] = arr[leftStart++];
        }
        
        while(rightStart<=rightEnd){
            temp[tempStart++] = arr[rightStart++];
        }
        
        /*
        for(int i = arr.length-1 ; i>0; i--, rightEnd--){
            arr[rightEnd]=temp[i];
        }
        
        
        for(int i : temp){
            System.out.println(i);
        }
    }
    */
    
    //copy to temp[]
    
    public void sequentialMerge(int middle){
        int temp[] = new int[arr.length];
        
        //copy everything into temp[]
        for(int i = start; i<=end; i++){
            temp[i] = arr[i];
            
            
        }
        
        
        
        int i = start;
        int j = middle+1;
        int k = start;
        
        //i<=middle && j<=end
        while(true){
            while(i<=middle){
                 arr[k] = temp[i];
               
            k++;
            i++;
            }
            
            while(j<end){
                arr[k] = temp[j];
                //k++;
               j++;
            }
            
            if(temp[i] <= temp[j]){
                arr[k] = temp[i];
               // i++;
            }
            else{
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        
        //whatever remains in the left array
        /*
        while(i<=middle){
            arr[k] = temp[i];
            k++;
            i++;
        }
        */
        /*
        for(int m : temp){
            System.out.println(m);
        }
        */
        
    }
    
    
    
    public static void main(String[] args){
        int[] array = {4,1,2,7,8,6,12,11}; //20 elements
        
        mergesortParallel sort = new mergesortParallel(array, 0, array.length-1, 4);
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(sort);
        
        for(int i : arr){
            System.out.println(i);
        }
                
                
    }
    

}
