import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.Random;

public class mergesortParallel extends RecursiveAction {
    private int[] arr;
    private int start, end;
    private static int threshold;
    
    static long startTime = 0;
    

    public mergesortParallel(int[] arr, int start, int end, int threshold) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
    }
    
    private static void start(){
        startTime = System.currentTimeMillis();
    }
    
    private static float stop(){
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }

    @Override
    protected void compute() {
            if (end - start <= threshold) {
                    // sequential sort
                    Arrays.sort(arr, start, end);
                    return;
            }

            // Sort halves in parallel
            int mid = start + (end-start) / 2;
            invokeAll(
                    new mergesortParallel(arr, start, mid, threshold),
                    new mergesortParallel(arr, mid, end, threshold) );

            // sequential merge
            sequentialMerge(mid);
    }

    private void sequentialMerge(int mid) {
         //implement the merge
        
        int[] temp = new int[(end-start)];
        
        int i = 0;
        int h = start;
        int j = mid;
        
        
        while(h<mid && j<end){
           
            
            if(arr[h]<=arr[j]){
                temp[i] = arr[h];
                //temp = Arrays.copyOf(arr, h);
                h++;
            }
            else{
                temp[i] = arr[j];
                j++;
            }
            i++;
        }
        
        if(h<mid){
                /*
                for(k=j ; k<=end; k++){
                    
                        temp[i] = arr[k];
                    
                        i++;
                    
                }
                */
            System.arraycopy(arr, h, temp, i, mid-h);
        }
        
        else if(j<end){
            /*
            for(k=h; k<=mid; k++){
                temp[i] = arr[k];
                i++;
            }
            */
            
            System.arraycopy(arr, j, temp, i, end-j);
        }
        
        /*
        for(k=start; k<=end; k++){
            
            arr[k] = temp[k];
            }
        */
        
        System.arraycopy(temp, 0, arr, start, temp.length);
        
        

    }
 
     public static void main(String[] args){
         
         int[] array = new int[2000000];
         
         Random rand = new Random();
         for(int i = 0; i<array.length; i++){
             array[i] = rand.nextInt();
         }
         
       // int[] array = {4,1,2,7,8,6,12,11, 22, 23, 56, 78, 66, 19, 15, 45,28,32,49,52, 90,21,104,88,44}; //20 elements
        
        float time = stop();
        
        
        
        mergesortParallel sort = new mergesortParallel(array, 0, array.length, 500000);
        
        
        System.gc();
        
        ForkJoinPool pool = new ForkJoinPool();
        start();
        
        pool.invoke(sort);
        
        time = stop();
        
        /*
        for(int i : array){
            System.out.println(i);
        }
        */
        
        System.out.println("The run took "+time );
        System.out.println("Number of threads: "+array.length/threshold);       
                
    }
//
}
