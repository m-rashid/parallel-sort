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
import java.util.Arrays;


public class QuicksortParallel extends RecursiveAction{
    
    private int[] arr;
    private int start;
    private int end;
    private int threshold;
    
    
   public QuicksortParallel(int[] arr, int start, int end, int threshold){
       this.arr = arr;
       this.start = start;
       this.end = end;
       this.threshold = threshold;
       
   }
   
   
   @Override
   public void compute(){
       
       if((end-start)<=threshold){
           Arrays.sort(arr, start, end);
       }
       
       else{
                      
       }
   }
   
   public void sort(int[] arr, int start, int end){
       
       
       
   }
    
    
}
