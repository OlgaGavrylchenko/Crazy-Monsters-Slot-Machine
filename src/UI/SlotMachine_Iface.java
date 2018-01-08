/*
    Programming Assignment 3 - CIT 285
    Develop a multimidia simulation of a slot machine. 
    
    @Created by Olga Gavrylchenko, 11/15/2017
 */

package UI;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public interface SlotMachine_Iface {
    
    double SCREEN_WIDTH = 1000.0;
    double SCREEN_HEIGHT = 1000.0;
    
    double WHEEL_WIDTH = 250.0;
    double WHEEL_HEIGHT = 500.0;
    
    double SLOT_WIDTH = 750.0;
    double SLOT_HEIGHT = 600.0;
    
    double IMAGE_WIDTH = 150.0;
    double IMAGE_HEIGHT = 450.0;
    
    double TOP_HEIGHT = 150.0;
    double MIDDLE_HEIGHT = 300.0;
    double BOTTOM_HEIGHT = 200.0;
    double BOTTOM_WIDTH = 1000.0;
    double MIDDLE_WIDTH = 200.0;
    
    double BUTTON_WIDTH = 200.0;
    double BUTTON_HEIGHT = 80.0;
    
    double LABEL_WIDTH_LARGE = 200.0;
    double LABEL_WIDTH_SMALL = 140.0;
    double TEXTFIELD_HEIGHT = 50;
    double LABEL_HEIGHT = 40.0;
    
    double ALERT_BOX_HEIGHT = 600.0;
    double ALERT_BOX_WIDTH = 600.0;
    
    //FONT
    public Font MAIN_FONT_SMALL = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 14);
    public Font MAIN_FONT_MEDIUM = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
    public Font MAIN_FONT_LARGE = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24);
    public Font MAIN_FONT_XLARGE = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30);
    public Font MAIN_FONT_XXLARGE = Font.font("Bree Serif", FontWeight.BOLD, FontPosture.REGULAR, 72);
    public Font TITLE_FONT_SMALL = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
    public Font TITLE_FONT_MEDIUM = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 22);
    public Font TITLE_FONT_LARGE = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 36);

    
}//SlotMAchine_Iface

