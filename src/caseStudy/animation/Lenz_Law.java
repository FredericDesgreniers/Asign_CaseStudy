package caseStudy.animation;

import caseStudy.AnimationBase;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;

public class Lenz_Law extends AnimationBase{
    
    public double charge;
    public double electricField;
    public double initialVelocity;
    public double magneticField;
    public AnimationBase animation;
    public Circle object;
    public Circle electricFieldSource;
    public Circle magneticFieldSource;
    public TextField chargeField;
    public TextField electricFieldField;
    public TextField initialVelocityField;
    public TextField magneticFieldField;
    
    
    /*
    *
    *Accessor methods
    *
    */
    public double getForce(){
        return this.charge*(this.electricField+this.initialVelocity+this.magneticField);
    }
    
    public double getCharge(){
        return this.charge;
    }
    
    public double getElectricField(){
        return this.electricField;
    }
    
    public double getinitialVelocity(){
        return this.initialVelocity;
    }
    
    public double getMagneticField(){
        return this.magneticField;
    }
    
    public double getPosition(){
        int i = 5;
        return i;
    }
    
    public double getDistanceLeft(){
        int i = 5;
        return i;
    }
    
    
    
    /*
    *
    *Modifiers methods
    *
    */
    public void setCharge(double charge){
        this.charge = charge;
    }
    
    public void setElectricField(double EF){
        this.electricField = EF;
    }
    
    public void setInitialVelocity(double initialVelocity){
        this.initialVelocity = initialVelocity;
    }
    
    public void setMagneticField(double MF){
        this.magneticField = MF;
    }
    
}
