/*
    Programming Assignment 3 - CIT 285
    Develop a multimidia simulation of a slot machine. 
    
    @Created by Olga Gavrylchenko, 11/15/2017
 */
package slotmachine;

import UI.AlertBox;
import UI.SlotMachineUI;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.ArrayList;
import java.nio.file.Paths;

public class GameFunctional implements Media_Iface{
    
    private SlotMachineUI slotMachineUI;
    final int SIZE = 240;
    int i =0;
    
    private Timeline timeLine;
    private ArrayList<Integer> resultNum;
    
    private Media backgroundSound, startSound, errorSound, voiceSound, applauseSound;
    private MediaPlayer gameBackgroundS, gameStarS, errorS, cartoonS, applauseS;
    
    private AlertBox alertBox;
    
    public GameFunctional(){
        
        this.slotMachineUI = new SlotMachineUI(); //maybe use singleton pattern
        this.resultNum = new ArrayList<>();
        this.alertBox = new AlertBox();
        
        //initialize sounds 
       initializeSounds();
       
       //play sound
       this.gameBackgroundS.play(); 
       
       //initialize a start button
        this.slotMachineUI.getBStart().setOnAction((ActionEvent event)->{
            
            if(this.timeLine == null){
                this.cartoonS.stop();
                this.applauseS.stop();
                
                try{
                    int bet = Integer.parseInt(this.slotMachineUI.getUserBets().getText());
                    double amount = Double.parseDouble(this.slotMachineUI.getUserCredits().getText());
                    
                    if(amount > 0){
                        this.slotMachineUI.setUserBets(++bet);
                    
                        if(this.gameBackgroundS.getStatus() == MediaPlayer.Status.PLAYING){
                            this.gameBackgroundS.stop();
                        }
                    
                       this.gameStarS.play();
                       runWheels();
                        
                    }else{//if amoun less than 0
                        this.alertBox.warningDialog(this.cartoonS);
                        this.slotMachineUI.resetFields();
                    }
                    
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });
        
        //initialize stop button
        this.slotMachineUI.getBStop().setOnAction((ActionEvent event)->{
            
            if(this.timeLine != null){
                
                if(this.timeLine.getStatus() == Animation.Status.RUNNING){
                    this.timeLine.stop();
                    this.timeLine = null;
                    
                    if(this.gameStarS.getStatus() == MediaPlayer.Status.PLAYING){
                            this.gameStarS.stop();
                    }
                
                    if(!this.resultNum.isEmpty()){
                        if( (this.resultNum.get(0)==this.resultNum.get(1)) && 
                            (this.resultNum.get(0)==this.resultNum.get(2)) && 
                            (this.resultNum.get(1)==this.resultNum.get(2))      ){
                        
                            this.gameBackgroundS.play();
                               
                            try{
                            
                                double houseValue = Double.parseDouble(this.slotMachineUI.getHouseValue().getText());
                                double userValue = Double.parseDouble(this.slotMachineUI.getUserCredits().getText());
                                double result = userValue + houseValue;
                                
                                this.alertBox.congratulationDialog(result, this.applauseS);
                                this.slotMachineUI.setUserCredits( result);
                                this.slotMachineUI.resetFields();
                                
                            }catch(NumberFormatException e){
                                e.printStackTrace();
                            }
                    }else{
                        try{
                             this.cartoonS.play();
                             this.gameBackgroundS.play();
                            
                            double houseValue = Double.parseDouble(this.slotMachineUI.getHouseValue().getText());
                            double userValue = Double.parseDouble(this.slotMachineUI.getUserCredits().getText());
                        
                            this.slotMachineUI.setUserCredits( (userValue - 1.0) );
                            this.slotMachineUI.setHouseValue( (houseValue + 1.0) );
                        }catch(NumberFormatException e){
                            e.printStackTrace();
                        }
                    }
                 }
                }
            }//if
            
        });
        
        
        this.slotMachineUI.getBReset().setOnAction((ActionEvent event)->{ 
            
            this.errorS.play();
            
            if(this.timeLine != null){//if weels are spinning 
                if(this.timeLine.getStatus() == Animation.Status.RUNNING){
                    this.timeLine.stop();
                    this.timeLine = null;
                    if(this.gameStarS.getStatus() == MediaPlayer.Status.PLAYING){
                            this.gameStarS.stop();
                    } 
                    
                    if(this.alertBox.resetDialog() == true){
                        
                        this.slotMachineUI.resetFields();
                        this.errorS.stop();
                        this.gameBackgroundS.play();
                    }
                }     
            }else{ //if button stop has already pressed 
                 
                 if(this.alertBox.resetDialog() == true){
                     this.slotMachineUI.resetFields();
                     this.errorS.stop();
                     this.gameBackgroundS.play();
               }
            }        
        });
        
    }//constructor
    
    
    
    public BorderPane getMainPane(){
        return this.slotMachineUI.getMainPane();
    }
    
    private int getRandomNum(){
        int min = 1, max = 36;
        int num;
        
        num = min + (int) (Math.random() * (max + 1) ); //because Math.random generate numbersfrom [0, 1.0)
        
        return (num % 3); //get num from [0,2]
    }
    
    
    public ArrayList<Integer> getRandomNumList(){
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i<this.SIZE; i++){
            
            list.add( getRandomNum() );
        }
        
        return list;
    }
    
    private ArrayList<Image> getRandomImage(ArrayList<Integer> arr){
        
        ArrayList<Image> list = new ArrayList<>();
        
        for(int i=0; i<arr.size(); i++){
            
            int num = arr.get(i);
            
            if(num == 0){
                
               list.add(new Image("resources/MonsterFourST.png", true));
                
            }else if(num == 1){
                
               list.add(new Image("resources/MonsterTwoST.png", true));
                
            }else if(num == 2){
                
               list.add(new Image("resources/MonsterOneST.png", true));   
            }
            
        }//for loop
        
        for(int j=0; j<this.SIZE; j++){
              System.out.println(list.get(j).toString());
        }
            
        return list;
    }
    
    
    private void runWheels(){
        
       ArrayList<Integer> numOne = getRandomNumList();
       ArrayList<Integer> numTwo = getRandomNumList();
       ArrayList<Integer> numThree = getRandomNumList();
       
       ArrayList<Image> listOne = getRandomImage(numOne);
       ArrayList<Image> listTwo = getRandomImage(numTwo);
       ArrayList<Image> listThree = getRandomImage(numThree);
              
       VBox wheelOne = this.slotMachineUI.getImgOneVBox();
       VBox wheelTwo = this.slotMachineUI.getImgTwoVBox();
       VBox wheelThree = this.slotMachineUI.getImgThreeVBox();
        
       EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
           
              if(i < this.SIZE-1){
               //in resultNum we will add 3 numbers each frame
               this.resultNum.clear();
               
               
               wheelOne.getChildren().clear();
               wheelTwo.getChildren().clear();
               wheelThree.getChildren().clear(); 
               
               ImageView image11, image12, image13, image21, image22, image23, image31, image32, image33;

                      if(i == 0){
                        image11 = new ImageView(listOne.get(i));
                        image12 = new ImageView();
                        image13 = new ImageView(listOne.get(i+1));
                        
                        image21 = new ImageView(listTwo.get(i));
                        image22 = new ImageView();
                        image23 = new ImageView(listTwo.get(i+1));
                        
                        image31 = new ImageView(listThree.get(i));
                        image32 = new ImageView();
                        image33 = new ImageView(listThree.get(i+1));
                        
                      }else{
                        image11 = new ImageView(listOne.get(i));
                        image12 = new ImageView(listOne.get(i-1));
                        image13 = new ImageView(listOne.get(i+1));
                        
                        image21 = new ImageView(listTwo.get(i));
                        image22 = new ImageView(listTwo.get(i-1));
                        image23 = new ImageView(listTwo.get(i+1));
                        
                        image31 = new ImageView(listThree.get(i));
                        image32 = new ImageView(listThree.get(i-1));
                        image33 = new ImageView(listThree.get(i+1));
                      }

                      //define duration time
                      final Duration durOne = Duration.millis(500);
                      final Duration durTwo = Duration.millis(200);
                      final Duration durThree = Duration.millis(1000);
                      final Duration durFour = Duration.millis(50);
                      final Duration durFive = Duration.millis(150);
                     
                      FadeTransition ft12 = new FadeTransition(durThree, image12);
                      ft12.setFromValue(0.9);
                      ft12.setToValue(0.7);
                      ft12.setCycleCount(1);
                      ft12.setAutoReverse(true);
                      ft12.setInterpolator(Interpolator.EASE_BOTH);
                      
                      FadeTransition ft11 = new FadeTransition(durOne, image11);
                      ft11.setFromValue(1);
                      ft11.setToValue(1);
                      ft11.setCycleCount(1);
                      ft11.setAutoReverse(true);
                      ft11.setInterpolator(Interpolator.EASE_IN);
                      
                      FadeTransition ft13 = new FadeTransition(durThree, image13);
                      ft13.setFromValue(0.9);//0.75
                      ft13.setToValue(0.7);//1
                      ft13.setCycleCount(1);
                      ft13.setAutoReverse(true);
                      ft13.setInterpolator(Interpolator.EASE_OUT);

                      FadeTransition ft22 = new FadeTransition(durThree, image22);
                      ft22.setFromValue(0.9);
                      ft22.setToValue(0.7);
                      ft22.setCycleCount(1);
                      ft22.setAutoReverse(true);
                      ft22.setInterpolator(Interpolator.EASE_BOTH);
                      
                      FadeTransition ft21 = new FadeTransition(durOne, image21);
                      ft21.setFromValue(1);
                      ft21.setToValue(1);
                      ft21.setCycleCount(1);
                      ft21.setAutoReverse(true);
                      ft21.setInterpolator(Interpolator.EASE_IN);
                      
                      FadeTransition ft23 = new FadeTransition(durThree, image23);
                      ft23.setFromValue(0.9);
                      ft23.setToValue(0.7);
                      ft23.setCycleCount(1);
                      ft23.setAutoReverse(true);
                      ft23.setInterpolator(Interpolator.EASE_OUT);

                      
                      FadeTransition ft32 = new FadeTransition(durThree, image32);
                      ft32.setFromValue(0.9);
                      ft32.setToValue(0.7);
                      ft32.setCycleCount(1);
                      ft32.setAutoReverse(true);
                      ft32.setInterpolator(Interpolator.EASE_BOTH);
                      
                      FadeTransition ft31 = new FadeTransition(durOne, image31);
                      ft31.setFromValue(1);
                      ft31.setToValue(1);
                      ft31.setCycleCount(1);
                      ft31.setAutoReverse(true);
                      ft31.setInterpolator(Interpolator.EASE_IN);
                      
                      FadeTransition ft33 = new FadeTransition(durThree, image33);
                      ft33.setFromValue(0.9);
                      ft33.setToValue(0.7);
                      ft33.setCycleCount(1);
                      ft33.setAutoReverse(true);
                      ft33.setInterpolator(Interpolator.EASE_OUT);
                      
                      
                      ParallelTransition pl1 = new ParallelTransition(ft11, ft21, ft31);
                      
                      ParallelTransition pl2 = new ParallelTransition(ft12, ft22, ft32);
                      pl2.setDelay(durFour);
                      
                      ParallelTransition pl3 = new ParallelTransition(ft13, ft23, ft33);
                      pl3.setDelay(durFive);
                     
                      ParallelTransition pl = new ParallelTransition( pl1, pl3, pl2);
                      pl.play();
                           
                    
                      this.resultNum.add(numOne.get(i));
                      this.resultNum.add(numTwo.get(i));
                      this.resultNum.add(numThree.get(i));
                      
                      wheelOne.getChildren().addAll(image12, image11, image13);
                      wheelTwo.getChildren().addAll(image22, image21, image23);
                      wheelThree.getChildren().addAll(image32, image31, image33);
                      
                      if(i == this.SIZE-2){
                          i =0;
                      }else{
                          i++;
                      }
                      
                    }
       };//event handle
       
       this.timeLine = new Timeline();
       KeyFrame keyFrameFast = new KeyFrame(Duration.millis(250), eventHandler);
       this.timeLine.getKeyFrames().add( keyFrameFast ); 
       this.timeLine.setCycleCount(Timeline.INDEFINITE);   
       this.timeLine.play();      
       
    }//runWheels
    
    
    private void initializeSounds(){
        this.backgroundSound = new Media(Paths.get(Media_Iface.GameBackgroundPath).toUri().toString());
        this.startSound = new Media(Paths.get(Media_Iface.GameStartPath).toUri().toString());
        this.errorSound = new Media(Paths.get(Media_Iface.ErrorSoundPath).toUri().toString());
        this.voiceSound = new Media(Paths.get(Media_Iface.CartoonVoicePath).toUri().toString());
        this.applauseSound = new Media(Paths.get(Media_Iface.ApplausePath).toUri().toString());
       
       this.gameBackgroundS = new MediaPlayer(this.backgroundSound);
       this.gameStarS = new MediaPlayer(this.startSound);
       this.errorS = new MediaPlayer(this.errorSound);
       this.cartoonS = new MediaPlayer(this.voiceSound);
       this.applauseS = new MediaPlayer(this.applauseSound);
       
       this.gameBackgroundS.setCycleCount(MediaPlayer.INDEFINITE);
       this.gameStarS.setCycleCount(MediaPlayer.INDEFINITE);
       
       this.gameBackgroundS.setVolume(0.3);
       this.gameStarS.setVolume(0.7);
       this.cartoonS.setVolume(1);
       this.errorS.setVolume(1);
       this.applauseS.setVolume(0.7);
    }
   
    
}//GameFunctional
