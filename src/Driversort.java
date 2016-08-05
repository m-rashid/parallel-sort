
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author merashid27
 */
public class Driversort {
    
    static int arraySizeMin = 1000000;
    static int arraySizeMax = 5000000;
    static int arraySizeIncr = 1000000 ;
    static int[] array;
    static int[] copy;
    static int[] copy2;
    static int arraySizeCurrent;
    
    static int threshold;
    
    static long startTime;
    
    static float T2;
    static float T1;
    
    static int threads;
    
     public static void start(){
        startTime = System.currentTimeMillis();
    }
    
    public static float stop(){
        return (System.currentTimeMillis() - startTime) / 1000.0f;
    }
    
    public static void reset(){
        startTime = 0;
    }

    public static int randInt(int min, int max){
        
        Random rand = new Random();
        int random = rand.nextInt((max-min))+min;
        return random;
    }
    
    
    public static void main(String[] args){
        
        /*
        arraySizeCurrent = arraySizeMin;
        array = new int[arraySizeMax];
        
        for(int i=0; i<arraySizeMax; i++){
            array[i] = randInt(0, arraySizeMax*10);
        }
        
        for(int i=0; i<5; i++){
            copy = new int[arraySizeCurrent];
            copy2 = new int[arraySizeCurrent];
            System.arraycopy(array, 0, copy, 0, arraySizeCurrent);
            System.arraycopy(array, 0, copy2, 0, arraySizeCurrent);
            
            reset();
            MergesortParallel sort = new MergesortParallel(array, 0, array.length, 1000);
            System.gc();
            ForkJoinPool pool = new ForkJoinPool();
            start();
            pool.invoke(sort);
            T2 = stop();
            System.out.println("The parallel sort took "+T2+" seconds" );
            System.out.println("Array size: "+arraySizeCurrent);
            reset();
            
            start();
            Arrays.sort(copy2);
            T1 = stop();
            
            System.out.println("The sequential sort took: "+T1+" seconds");
            System.out.println("Speed up: "+T1/T2);
            System.out.println(" ");
           // System.out.println("Number of threads: "+threads);
            reset();
            arraySizeCurrent += arraySizeIncr;
            
        }
        */
        
        
        arraySizeCurrent = arraySizeMin;
        array = new int[arraySizeMax];
        
        for(int i=0; i<arraySizeMax; i++){
            array[i] = randInt(0, arraySizeMax*10);
        }
        
        for(int i = 0; i<arraySizeCurrent; i++){
            array = new int[arraySizeCurrent];
            copy = new int[arraySizeCurrent];
            copy2 = new int[arraySizeCurrent];
            System.arraycopy(array, 0, copy, 0, arraySizeCurrent);
            System.arraycopy(array, 0, copy2, 0, arraySizeCurrent);
            reset();
            start();
            Arrays.sort(copy2);
            T1 = stop();
                
            
            //array[i] = ThreadLocalRandom.current().nextInt(arraySizeMin, arraySizeMax);
            //array[i] = randInt(arraySizeMin, arraySizeMax);
            
            for(int t=2; t<=1024; t*=2){
                threads = t;
                threshold = arraySizeCurrent/t;
            
                for(int j = 0; j<5; j++){
                    reset();
                    MergesortParallel sort = new MergesortParallel(copy, 0, array.length, threshold);
                    System.gc();
                    ForkJoinPool pool = new ForkJoinPool();
                    start();
                    pool.invoke(sort);
                    T2 = stop();
                    System.out.println("The run took "+T2 );
                    System.out.println("Number of threads: "+threads);
                    break;

                }
            }
            arraySizeCurrent += arraySizeIncr;
        }
        
        
        
        
    }
    
}
