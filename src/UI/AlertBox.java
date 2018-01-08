/*
    Programming Assignment 3 - CIT 285
    Develop a multimidia simulation of a slot machine. 
    
    @Created by Olga Gavrylchenko, 11/15/2017
 */

package UI;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;
import java.text.DecimalFormat;
import java.util.Optional;


public class AlertBox implements SlotMachine_Iface{

    public boolean resetDialog(){
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ARE YOU SURE YOU WOULD LIKE TO START AGAIN?");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeaderText("CRAZY MONSTERS");
        alert.initOwner(null);
        
        //create buttons
        ButtonType btYes = new ButtonType("Yes");
        ButtonType btNo = new ButtonType("No");
        alert.getButtonTypes().setAll(btNo, btYes);
        
        alert.getDialogPane().getStylesheets().add("UI/MainStyleClass.css");
        Optional<ButtonType> result = alert.showAndWait(); //thread 
        
        if(result.get() == btYes){

             return true;
            
        }else{
            alert.close(); //close dialog window  
            return false;  
        } 
    }
    
    public void errorDialog(String content){
        Alert alert = new Alert(Alert.AlertType.ERROR, content);
        alert.setHeaderText("ERROR: ");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        alert.showAndWait(); //thread 
    }
    
    public void warningDialog(MediaPlayer media){
        media.play();
        DialogPane pane = new DialogPane();
       
        Alert alert = new Alert(Alert.AlertType.NONE);
        
        pane.setStyle("-fx-background-image: url(resources/GameOverBox.png); -fx-background-repeat: no-repeat; -fx-alignment: top-center;");
        pane.setMinHeight(ALERT_BOX_HEIGHT);
        pane.setMinWidth(ALERT_BOX_WIDTH);
        pane.setMaxHeight(ALERT_BOX_HEIGHT);
        pane.setMaxWidth(ALERT_BOX_WIDTH);
        alert.setDialogPane(pane);
        
        alert.getDialogPane().getStylesheets().add("UI/MainStyleClass.css");
        alert.initStyle(StageStyle.UNDECORATED);
        
        ButtonType btYes = new ButtonType("QUIT A GAME");
        ButtonType btNo = new ButtonType("CONTINUE PLAYING");
        alert.getButtonTypes().setAll(btNo, btYes);
        Optional<ButtonType> result = alert.showAndWait(); //thread 
        
        if(result.get() == btYes){
            
             Platform.exit(); 
        }else{  
            media.stop();
            alert.close(); //close dialog window
        }
    }
    
    public void congratulationDialog(double num, MediaPlayer media){
        media.play();
        DecimalFormat pattern = new DecimalFormat("00.00");
        String content ="$"+pattern.format(num);
        
        DialogPane pane = new DialogPane();
        BorderPane subPane = new BorderPane();
        subPane.setMinHeight(ALERT_BOX_HEIGHT-200);
        subPane.setMinWidth(ALERT_BOX_WIDTH);
        subPane.setMaxHeight(ALERT_BOX_HEIGHT-200);
        subPane.setMaxWidth(ALERT_BOX_WIDTH);
        
        HBox hBox = new HBox();
        TextField tfResult = new TextField();
        tfResult.setMinSize(LABEL_WIDTH_LARGE, LABEL_HEIGHT);
        tfResult.setFont(MAIN_FONT_XXLARGE);
        
        tfResult.setText(content);
        tfResult.setAlignment(Pos.BOTTOM_CENTER);
        tfResult.setEditable(false);
        tfResult.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        
        
        hBox.setMinHeight(ALERT_BOX_HEIGHT-200);
        hBox.getChildren().add(tfResult);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        
        subPane.setCenter(hBox);
        
        pane.getChildren().add(subPane);
        
        Alert alert = new Alert(Alert.AlertType.NONE, content);
        
        
        pane.setStyle("-fx-background-image: url(resources/CongratulationBox.png); -fx-background-repeat: no-repeat; -fx-alignment: top-center;");
        pane.setMinHeight(ALERT_BOX_HEIGHT);
        pane.setMinWidth(ALERT_BOX_WIDTH);
        pane.setMaxHeight(ALERT_BOX_HEIGHT);
        pane.setMaxWidth(ALERT_BOX_WIDTH);
        alert.setDialogPane(pane);
        
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(null);
        ButtonType btYes = new ButtonType("GET MONEY AND QUIT");
        ButtonType btNo = new ButtonType("CONTINUE PLAYING");
        alert.getButtonTypes().setAll(btNo, btYes);
        
        alert.getDialogPane().getStylesheets().add("UI/MainStyleClass.css");
        
        alert.setOnCloseRequest(e->alert.close());
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait(); //thread 
        
        if(result.get() == btYes){
            media.stop();
             quitDialog();
             alert.close();
            
        }else{  
            media.stop();
            alert.close(); //close dialog window
        } 
          
    }
    
    public void quitDialog(){
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ARE YOU SURE YOU WOULD LIKE TO QUIT A PROGRAM?");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setHeaderText("CRAZY MONSTERS");
        alert.initOwner(null);
        
        //create buttons
        ButtonType btYes = new ButtonType("Yes");
        ButtonType btNo = new ButtonType("No");
        alert.getButtonTypes().setAll(btNo, btYes);
        
        alert.getDialogPane().getStylesheets().add("UI/MainStyleClass.css");
        Optional<ButtonType> result = alert.showAndWait(); //thread 
        
        if(result.get() == btYes){

             Platform.exit();
            
        }else{  
            alert.close(); //close dialog window
        } 
    }//quitDialog

}//AlertBox
