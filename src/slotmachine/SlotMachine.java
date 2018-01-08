/*
    Programming Assignment 3 - CIT 285
    Develop a multimidia simulation of a slot machine. 
    
    @Created by Olga Gavrylchenko, 11/15/2017
 */
package slotmachine;

import UI.AlertBox;
import UI.SlotMachine_Iface;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SlotMachine extends Application implements SlotMachine_Iface{
    
    private final BorderPane root = new BorderPane();   
    private GameFunctional game;
    private AlertBox alertBox;
    
    @Override
    public void start(Stage primaryStage) {
        
        Label title = new Label("@Created by Olga Gavrylchenko, 11/15/2017");
        title.setFont(MAIN_FONT_SMALL); 
        title.setPrefHeight(30);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefHeight(50);
        hbox.getChildren().add(title);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("UI/MainStyleClass.css");
        
        //instantiate objects
        this.game = new GameFunctional();
        this.alertBox = new AlertBox();
        
        root.setCenter(this.game.getMainPane());
        root.setBottom(hbox);
       
        primaryStage.setMaxWidth(SCREEN_WIDTH+20);
        primaryStage.setMinWidth(SCREEN_WIDTH+20);
        primaryStage.setMinHeight(SCREEN_HEIGHT+10);
        primaryStage.setScene(scene);
        
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e-> {
            e.consume();//stop event propogation
            this.alertBox.quitDialog();
        });
        
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}//SlotMachine

