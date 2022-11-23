
package GamePackage;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.math.*;
import java.time.LocalTime;

public class Level{
    private String[] text;
    private int difficulty;
    private int checkIndex;
    private int time; //in seconds
    private int Mistakes;
    private ArrayList<String> Words = new ArrayList<String>();
    private int totalChar = 0;
    
    public Level(String Filename){
        difficulty = 1;
        checkIndex = 0;
        Mistakes = 0;
        
        try{
            File tempFile = new File("src\\GamePackage\\RandomWords.txt");
            Scanner myScan = new Scanner(tempFile);
            int i = 0;
            while (myScan.hasNext()){
                Words.add( myScan.nextLine());
                
            }
        }
        catch(Exception e){}
    }
    
    //calculates the user's approximate words per minute
    public String calcWPM(double start, double end) {
        double elapsedTime = end - start;
        double seconds = elapsedTime / 1000000000.0; //total time taken to type out phrase
        int wpm = (int) ((((double) totalChar / 4) / seconds)*60);
        totalChar = 0;
        return Integer.toString(wpm);
    }
    
    public int getMistakes(){
        return Mistakes;
    }
    public int getSentenceLength(){
        return text.length;
    }
   
    
    public void clear(){
        checkIndex = 0;
        time = 0; //in seconds
        Mistakes = 0;
        text = null;
    }
    public void DoLevel(javax.swing.JTextArea Dest, javax.swing.JTextField Source){

        int rangeBot = Words.size()/20*(difficulty>=20?19:difficulty-1);
        int rangeTop = Words.size()/20*(difficulty>=20?20:difficulty);
        int L = (int)(10+difficulty*1.5);
        text = new String[L];
        String output = "";
        for (int i = 0; i<L;i++){
            text[i] = Words.get((int)(Math.random()*(rangeTop-rangeBot))+rangeBot);
            output += text[i];
            output += " ";
        }
        Dest.setText(output);
        
    }
    public boolean checkMistake(String In,javax.swing.JTextField Source){
        int mistake = 0;
        for(int i = 0; i<text[checkIndex].length();i++){
            if(i>=In.length()){
                mistake += text[checkIndex].length() - In.length();
                break;
            }
            else if(text[checkIndex].charAt(i) != In.charAt(i)){
                mistake++;
            }
        }
        
        checkIndex++;
        Mistakes += mistake;
        totalChar += In.length();
        Source.setText("");
        if(checkIndex >= (int)(10+difficulty*1.5)){
            difficulty ++;
            return true;//round over
        }else{
            return false;
        }
    }
}