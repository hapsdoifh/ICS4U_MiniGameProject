
package GamePackage;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.math.*;

public class Level{
    protected String[] text;
    public int difficulty;
    protected int checkIndex;
    protected int time; //in seconds
    protected int Mistakes;
    protected ArrayList<String> Words = new ArrayList<String>();

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
    public void clear(){
        checkIndex = 0;
        time = 0; //in seconds
        Mistakes = 0;
        text = null;
    }
    public void DoLevel(javax.swing.JTextArea Dest, javax.swing.JTextField Source){
        int rangeBot = Words.size()/30*(difficulty-1);
        int rangeTop = Words.size()/30*(difficulty);
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
                break;
            }
            else if(text[checkIndex].charAt(i) != In.charAt(i)){
                mistake ++;
            }
        }
        checkIndex++;
        Mistakes += mistake;
        Source.setText("");
        if(checkIndex >= (int)(10+difficulty*1.5)){
            difficulty ++;
            return true;//round over
        }else{
            return false;
        }
    }
}