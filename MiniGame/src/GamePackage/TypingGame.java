/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GamePackage;

import javax.swing.JLabel;

/**
 *
 * @author zuhan
 */
public class TypingGame {
    protected int totalScore = 0;
    protected int LevelCount;
    public Level GameLevel;
    protected boolean RoundState; //false for not started, true for started

    
    public void toggleRound(){
        RoundState = !RoundState;
    }
    public boolean getRoundState(){
        return RoundState;
    }
    public int getScore(){
        return totalScore;
    }
    
    public TypingGame(String Filename){
        GameLevel = new Level(Filename);
        RoundState = false;
        totalScore = 0;
        LevelCount = 0;
    }
    public void startRound(javax.swing.JTextArea Dest, javax.swing.JTextField Source){
        GameLevel.DoLevel(Dest, Source);
    }
    
    
    public boolean CheckWord(javax.swing.JTextField Source, int time){
        boolean reval = GameLevel.checkMistake(Source.getText().substring(0,Source.getText().length()-1),Source);
        if(reval){ //round emded
           toggleRound();     
           //Score Calculation Here
           if(GameLevel.getSentenceLength()*5 > 10*GameLevel.getMistakes()){
                totalScore += GameLevel.getSentenceLength()*((time/5)+1) - 10*GameLevel.getMistakes();             
           }else{
               totalScore +=0;
           }
           
           GameLevel.clear();
           
        }
        
        return reval;
    }
    
    //
    public void displayWPM(double start, double end, JLabel label) {
        label.setText(GameLevel.calcWPM(start, end));
    }
    
}