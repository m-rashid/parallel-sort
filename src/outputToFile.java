/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author merashid27
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class outputToFile {
    
    private String path;
    private boolean append = false;
    
    public outputToFile(String path){
        this.path = path;
        
    }
    
    public outputToFile(String path, boolean append){
        this.path = path;
        this.append = append;
    }
    
    public void writeToFile( String textLine ) throws IOException {

        FileWriter write = new FileWriter(path,append);
        PrintWriter print = new PrintWriter(write);
        print.printf( "%s" + "%n" , textLine);
        print.close();
    }
    
    
}
