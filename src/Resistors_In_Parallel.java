/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mathieu
 */

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;

public class Resistors_In_Parallel extends AnimationBase{
    
    public double resistors[] = new double[3];
    public double voltage;
    public AnimationBase animation;
    public TextField resistorField[]= new TextField[3];
    public TextField voltageField;
    public Rectangle rectangles[] = new Rectangle[12];
    public Circle resistorsSource[] = new Circle[3];

    
    /*
    *
    *Accessors Methods
    *
    */
public double getTotalResistance(){
    return (double)(1/(1/resistors[0]+1/resistors[1]+1/resistors[2]));
}

public double getCurrentinResistor(int i){
    return voltage/resistors[i];
}

public double getInitialCurrent(){
    return getCurrentinResistor(0)+getCurrentinResistor(1)+getCurrentinResistor(2);
}

public double getVoltage(){
    return this.voltage;
}

public double getResistor(int i){
    return resistors[i];
}

/*
*
*Modifiers methods
*
*/
public void setVoltage(double voltage){
    this.voltage = voltage;
}

public void setResistor(int i, double resistance){
    this.resistors[i] = resistance;
}


}
