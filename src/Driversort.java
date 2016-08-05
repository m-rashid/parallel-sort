
import java.util.ArrayList;
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
    
    static ArrayList NoOfThreads = new ArrayList();
    
    static ArrayList runTimes = new ArrayList();
    
     public static void start(){
        startTime = System.nanoTime();
    }
    
    public static float stop(){
        return (System.nanoTime() - startTime)/1000000.0f;
    }

    public static int randInt(int min, int max){
        
        Random rand = new Random();
        int random = rand.nextInt((max-min))+min;
        return random;
    }
    
    public static int randThread(ArrayList list){
        int rand = new Random().nextInt(list.size());
        return (int)list.get(rand);
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
        
        //arraySizeCurrent = arraySizeMin;
        
        array = new int[arraySizeMax];
        
        for(int i=0; i<arraySizeMax; i++){
            array[i] = randInt(0, arraySizeMax);
        }
        
        for(int i=2; i<=4096; i*=2){
            NoOfThreads.add(i);
        }
        
        for(int i = arraySizeMin; i<=arraySizeMax; i+=arraySizeIncr){
            copy = new int[i];
            copy2 = new int[i];
            System.arraycopy(array, 0, copy, 0, i);
            System.arraycopy(array, 0, copy2, 0, i);
            
            //array[i] = ThreadLocalRandom.current().nextInt(arraySizeMin, arraySizeMax);
            //array[i] = randInt(arraySizeMin, arraySizeMax);
            /*
            for(int t=2; t<=1024; t*=2){
                threads = t;
                threshold = i/t;
            */
                
                for(int j = 0; j<5; j++){
                    threads = randThread(NoOfThreads);
                    threshold = i/threads;
                    QuicksortParallel sort = new QuicksortParallel(copy, 0, copy.length-1, threshold);
                    System.gc();
                    ForkJoinPool pool = new ForkJoinPool();
                    start();
                    pool.invoke(sort);
                    T2 = stop();
                    
                    start();
                    Arrays.sort(copy2);
                    T1 = stop();
                    System.out.println("The parallel run took "+T2+"ms" );
                    System.out.println("The sequential run took "+T1+"ms" );
                    System.out.println("Array size: "+i);
                    System.out.println("Number of threads: "+threads);
                    System.out.println("Speed up: "+T1/T2);
                    System.out.println(" ");
                    
                }
            }
           
        
    }
    
}
