/*import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class mergesortParallel extends RecursiveAction {
	private int[] arr;
    private int start, end;
    private int threshold;

    public mergesortParallel(int[] arr, int start, int end, int threshold) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.threshold = threshold;
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
        
        int[] temp = new int[(end-start)+1];
        
        int i = start;
        int h = start;
        int j = mid+1;
        int k;
        
        while(h<=mid && j<=end){
            if(arr[h]<=arr[j]){
                //temp[i] = arr[h];
                temp = Arrays.copyOf(arr, h);
                h++;
            }
            else{
                temp[i] = arr[j];
                j++;
            }
            i++;
        }
        
        if(h>mid){
            
                for(k=j ; k<=end; k++){
                    
                        temp[i] = arr[k];
                    
                        i++;
                    
                }
            
        }
        
        else{
            for(k=h; k<=mid; k++){
                temp[i] = arr[k];
                i++;
            }
        }
        
        for(k=start; k<=end; k++){
            
            arr[k] = temp[k];
            }
        
        

    }
 /*
     public static void main(String[] args){
        int[] array = {4,1,2,7,8,6,12,11, 22, 23, 56, 78, 66, 19, 15, 45,28,32,49,52}; //20 elements
        
        mergesortParallel sort = new mergesortParallel(array, 0, array.length-1, 4);
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(sort);
        
        for(int i : array){
            System.out.println(i);
        }
                
                
    }
//
}
*/