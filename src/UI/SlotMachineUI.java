/*
    Programming Assignment 3 - CIT 285
    Develop a multimidia simulation of a slot machine. 
    
    @Created by Olga Gavrylchenko, 11/15/2017
 */

package UI;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import java.text.DecimalFormat;

public class SlotMachineUI implements SlotMachine_Iface{
    private final BorderPane mainBorderPane;
    
    private HBox resultHBox, timerHBox, slotHBox;
    
    private HBox wheelOneHBox, wheelTwoHBox, wheelThreeHBox;
    private VBox imgOneVBox, imgTwoVBox, imgThreeVBox;
    
    private Button bStop, bStart, bReset;
    
    private TextField tfCredits, tfBet, tfWon;
    private Label lCredits, lBet, lWon;
    
    private String labelArr[] = {"CREDITS", "GAME'S CYCLES", "WINNER PAID"};
   // private double houseValue[] = {1000.00, 10000.00, 500.00, 100.0, 15000.00, 250.00, 150.00, 25000.00, 50000.00, 750.00};
    
    ImageView imageView1, imageView2, imageView3;
   
    
    public SlotMachineUI(){
       this.mainBorderPane = new BorderPane();
       
       this.timerHBox = new HBox();
       this.slotHBox = new HBox();
       this.resultHBox = new HBox();
       
       initializeForm();
       styleForm();
       
       this.mainBorderPane.setStyle("-fx-background-image: url(resources/BackgroundNew.png); -fx-background-repeat: none;");
       
       this.mainBorderPane.setTop(timerHBox);
       this.mainBorderPane.setCenter(slotHBox);
       this.mainBorderPane.setBottom(resultHBox);
    }
    
    private void initializeForm(){
        
        //initialize slot machine hbox
        initializeSlotHBox();

        initializeResultHBox();
    }
    
    private void styleForm(){
        //style HBox containers
       this.timerHBox.setMinHeight(TOP_HEIGHT+1.0);
       this.timerHBox.setMaxHeight(TOP_HEIGHT+1.0);
       
        this.resultHBox.setMinHeight(BOTTOM_HEIGHT);
        this.resultHBox.setMaxHeight(BOTTOM_HEIGHT);
        this.resultHBox.setMinWidth(BOTTOM_WIDTH);
        this.resultHBox.setMaxWidth(BOTTOM_WIDTH);
    }
    
    private void initializeSlotHBox(){
        
        //slot machine
        
        this.slotHBox.setMinHeight(SLOT_HEIGHT);
        this.slotHBox.setMaxHeight(SLOT_HEIGHT);
        this.slotHBox.setMinWidth(SLOT_WIDTH);
        this.slotHBox.setMaxWidth(SLOT_WIDTH);
        
        
        this.wheelOneHBox = new HBox();
        this.wheelTwoHBox = new HBox();
        this.wheelThreeHBox = new HBox();
        
        this.wheelOneHBox.setStyle("-fx-background-image: url(resources/WheelNew.png); -fx-background-repeat: no-repeat; -fx-alignment: top-center;");
        this.wheelOneHBox.setMinSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        this.wheelOneHBox.setMaxSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        
        this.wheelTwoHBox.setStyle("-fx-background-image: url(resources/WheelNew.png); -fx-background-repeat: no-repeat; -fx-alignment: top-center;");
        this.wheelTwoHBox.setMinSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        this.wheelTwoHBox.setMaxSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        
        this.wheelThreeHBox.setStyle("-fx-background-image: url(resources/WheelNew.png); -fx-background-repeat: no-repeat; -fx-alignment: top-center;");
        this.wheelThreeHBox.setMinSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        this.wheelThreeHBox.setMaxSize(WHEEL_WIDTH, WHEEL_HEIGHT);
        
        
        
        this.imgOneVBox = new VBox();
        this.imgTwoVBox = new VBox();
        this.imgThreeVBox = new VBox();
        
        this.imgOneVBox.setMinSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.imgThreeVBox.setMinSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.imgTwoVBox.setMinSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.imgOneVBox.setMaxSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.imgThreeVBox.setMaxSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        this.imgTwoVBox.setMaxSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        
       
        
        setDefaultImages();
        
       this.imgOneVBox.setTranslateX(-25);
       this.imgOneVBox.setTranslateY(25); 
       this.imgTwoVBox.setTranslateX(-25);
       this.imgTwoVBox.setTranslateY(25);
       this.imgThreeVBox.setTranslateX(-25);
       this.imgThreeVBox.setTranslateY(25);
       
       
       this.wheelOneHBox.setAlignment(Pos.CENTER);
       this.wheelTwoHBox.setAlignment(Pos.CENTER);
       this.wheelThreeHBox.setAlignment(Pos.CENTER);
       
       this.wheelTwoHBox.setAlignment(Pos.CENTER);
       this.wheelThreeHBox.setAlignment(Pos.CENTER);
       
       this.wheelOneHBox.getChildren().add(this.imgOneVBox);
       this.wheelTwoHBox.getChildren().add(this.imgTwoVBox);
       this.wheelThreeHBox.getChildren().add(this.imgThreeVBox);
       
       
        
        this.slotHBox.setAlignment(Pos.CENTER);
        this.slotHBox.getChildren().addAll(this.wheelOneHBox, this.wheelTwoHBox, this.wheelThreeHBox);
        
    }
    
    private void initializeResultHBox(){
        //result
        GridPane bottomGridPane = new GridPane();
        GridPane innerGridPane = new GridPane();
        innerGridPane.setHgap(45);
        innerGridPane.setVgap(10);
        innerGridPane.setAlignment(Pos.CENTER);
        //innerGridPane.setPadding(new Insets(5, 2, 2, 2));
        innerGridPane.setMinWidth(SCREEN_WIDTH);
        
        
        this.bStop = new Button("STOP");
        this.bStart = new Button("SPIN");
        this.bReset = new Button("RESET");
        
        this.bStop.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.bStart.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.bReset.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.bStop.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.bStart.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.bReset.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        
       this.bStop.setId("bstop");
       this.bStart.setId("bstart");
       this.bReset.setId("breset");
       
       this.bStop.setFont(MAIN_FONT_XLARGE);
       this.bStart.setFont(MAIN_FONT_XLARGE);
       this.bReset.setFont(MAIN_FONT_XLARGE);
        
        innerGridPane.add(bReset, 0, 1);
        innerGridPane.add(bStop, 1, 1);
        innerGridPane.add(bStart, 2, 1);
        
        
        Separator firstS = new Separator(Orientation.HORIZONTAL);
        firstS.setStyle("-fx-border-width: 2px; -fx-background-color: yellow;");
        innerGridPane.add(firstS, 0, 0);
       // firstS.setMinWidth(SCREEN_WIDTH);
       GridPane.setConstraints(firstS, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER);
        
        HBox buttonInnerHBox = new HBox();
        buttonInnerHBox.getChildren().add(innerGridPane);
        
        //text fields
        HBox innerHBox = new HBox();
        this.tfCredits= new TextField();
        this.lCredits = new Label(this.labelArr[0]);
        this.tfCredits.setEditable(false);
        this.lCredits.setFont(MAIN_FONT_MEDIUM);
        this.lCredits.setTextFill(Color.WHITE);
        this.lCredits.setTextAlignment(TextAlignment.CENTER);
        this.lCredits.setAlignment(Pos.CENTER);  
        this.tfCredits.setMinSize(LABEL_WIDTH_LARGE,  TEXTFIELD_HEIGHT);
        this.tfCredits.setMaxSize(LABEL_WIDTH_LARGE,  TEXTFIELD_HEIGHT);
        this.lCredits.setMinSize(LABEL_WIDTH_LARGE,  LABEL_HEIGHT);
        bottomGridPane.add(tfCredits, 0, 0);
        bottomGridPane.add(lCredits, 0, 1);
        
        
        this.tfBet= new TextField();
        this.lBet = new Label(this.labelArr[1]);
        this.tfBet.setEditable(false);
        this.lBet.setFont(MAIN_FONT_MEDIUM);
        this.lBet.setTextFill(Color.WHITE);
        this.lBet.setTextAlignment(TextAlignment.CENTER);
        this.lBet.setAlignment(Pos.CENTER);
        this.tfBet.setMinSize(LABEL_WIDTH_SMALL,  TEXTFIELD_HEIGHT);
        this.tfBet.setMaxSize(LABEL_WIDTH_SMALL,  TEXTFIELD_HEIGHT);
        this.lBet.setMinSize(LABEL_WIDTH_SMALL,  LABEL_HEIGHT);
        bottomGridPane.add(tfBet, 1, 0);
        bottomGridPane.add(this.lBet, 1, 1);
        
        this.tfWon= new TextField();
        this.lWon = new Label(this.labelArr[2]);
        this.tfWon.setEditable(false);
        this.lWon.setFont(MAIN_FONT_MEDIUM);
        this.lWon.setTextFill(Color.WHITE);
        this.lWon.setTextAlignment(TextAlignment.CENTER);
        this.lWon.setAlignment(Pos.CENTER);
        this.tfWon.setMinSize(LABEL_WIDTH_LARGE,  TEXTFIELD_HEIGHT);
        this.tfWon.setMaxSize(LABEL_WIDTH_LARGE,  TEXTFIELD_HEIGHT);
        this.lWon.setMinSize(LABEL_WIDTH_LARGE,  LABEL_HEIGHT);
        bottomGridPane.add(this.tfWon, 2, 0);
        bottomGridPane.add(this.lWon, 2, 1);
        
        
        bottomGridPane.setHgap(60);
        bottomGridPane.setAlignment(Pos.CENTER);
        // bottomGridPane.setPadding(new Insets(5, 10, 5, 10));
        bottomGridPane.setMinWidth(SCREEN_WIDTH);
        innerHBox.getChildren().add(bottomGridPane);
        
        resetFields();
        
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(innerHBox, buttonInnerHBox);
        
        
        this.resultHBox.getChildren().addAll( mainVBox );
        
    }
    
    public BorderPane getMainPane(){
        return this.mainBorderPane;
    }
    
    public Button getBStop(){
        return this.bStop;
    }
    
    public Button getBStart(){
        return this.bStart;
    }
    
    public Button getBReset(){
        return this.bReset;
    }
    
    public VBox getImgOneVBox(){
        return this.imgOneVBox;
    }
    
    public VBox getImgTwoVBox(){
        return this.imgTwoVBox;
    }
    
    public VBox getImgThreeVBox(){
        return this.imgThreeVBox;
    }
    
    public ImageView getImageViewOne(){
        return this.imageView1;
    }
    
    public ImageView getImageViewTwo(){
        return this.imageView2;
    }
    
    public ImageView getImageViewThree(){
        return this.imageView3;
    }
    
    public void setUserCredits(double value){
        DecimalFormat pattern = new DecimalFormat("00.00");
        this.tfCredits.setText(pattern.format(value));
        
        this.tfCredits.setAlignment(Pos.CENTER);
        this.tfCredits.setFont(MAIN_FONT_LARGE);  
    }
    
    public void setUserBets(int value){
        this.tfBet.setText(String.valueOf(value));
        this.tfBet.setAlignment(Pos.CENTER);
        this.tfBet.setFont(MAIN_FONT_LARGE);
    }
    
    public void setHouseValue(double value){
        DecimalFormat pattern = new DecimalFormat("00.00");
        this.tfWon.setText(pattern.format(value));
        
        this.tfWon.setAlignment(Pos.CENTER);
        this.tfWon.setFont(MAIN_FONT_LARGE);  
    }
   
    public TextField getUserCredits(){
        return this.tfCredits;
    }
    
    public TextField getUserBets(){
        return this.tfBet;
    }
    
    public TextField getHouseValue(){
        return this.tfWon;
    }  
     
    public void resetFields(){
        setUserCredits(1000.00);
        setUserBets(0);
        setHouseValue(1000.00);
        setDefaultImages();
    }
    
    private void setDefaultImages(){
        this.imgOneVBox.getChildren().clear();
        this.imgTwoVBox.getChildren().clear();
        this.imgThreeVBox.getChildren().clear();
        
        Image  image1 = new Image("resources/MonsterFiveST.png", true); 
        Image  image2 = new Image("resources/MonsterOneST.png", true); 
        Image  image3 = new Image("resources/MonsterTwoST.png", true); 
        
        for(int i=0; i<3; i++){
            this.imgOneVBox.getChildren().add(new ImageView(image2));
            this.imgTwoVBox.getChildren().add(new ImageView(image1));
            this.imgThreeVBox.getChildren().add(new ImageView(image3));
        }
    }
}//SlotMachineUI

