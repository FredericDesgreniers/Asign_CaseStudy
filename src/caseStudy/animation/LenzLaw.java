package caseStudy.animation;

import caseStudy.AnimationBase;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class LenzLaw extends AnimationBase{
    
    public double charge;
    public double electricField;
    public double initialVelocity;
    public double magneticField;
    public double acceleration;
    public Circle object;
    public TextField chargeField;
    public TextField electricFieldField;
    public TextField initialVelocityField;
    public TextField magneticFieldField;
    public Label chargeLabel;
    public Label electricFieldLabel;
    public Label initialVelocityLabel;
    public Label magneticFieldLabel;
    public Label forceLabel;
    


    public LenzLaw(String name) {
        
        super(name);
        
        this.chargeLabel = new Label("Charge: ");
        this.electricFieldLabel = new Label("Electric Field(N/C): ");
        this.initialVelocityLabel = new Label("Initial Veocity(m/s): ");
        this.magneticFieldLabel = new Label("Magnetic Field(T): ");
        
        this.chargeField = new TextField("1");
        this.electricFieldField = new TextField("0");
        this.initialVelocityField = new TextField("0");
        this.magneticFieldField = new TextField("0");
        
        this.chargeField.setLayoutX(100);
        this.chargeField.setLayoutY(0);
        this.electricFieldField.setLayoutX(100);
        this.electricFieldField.setLayoutY(40);
        this.initialVelocityField.setLayoutX(100);
        this.initialVelocityField.setLayoutY(80);
        this.magneticFieldField.setLayoutX(100);
        this.magneticFieldField.setLayoutY(120);
        
        this.getChildren().addAll(chargeLabel, electricFieldLabel, magneticFieldLabel, initialVelocityLabel, chargeField, electricFieldField, magneticFieldField, initialVelocityField);
        
        object = new Circle(400, 100, 50);
        
        this.getChildren().add(object);
    }
    
    public void calculateKeyFrames(){
    
    	setCharge(Double.parseDouble(chargeField.getText()));
    	setElectricField(Double.parseDouble(electricFieldField.getText()));
    	setMagneticField(Double.parseDouble(magneticFieldField.getText()));
    	setInitialVelocity(Double.parseDouble(initialVelocityField.getText()));
    	
    	calculateAcceleration();
    	
    	KeyFrame[] frames = new KeyFrame[(int)(500/acceleration)];
    	
    	for(int i = 0; i<frames.length; i++){
    		
    		KeyValue keyValue = new KeyValue(object.centerXProperty(), i*3);
    		
    		frames[i] = new KeyFrame(Duration.millis(33*i), keyValue);
    		timeline.getKeyFrames().add(frames[i]);
    	}
    	
    	timeline.getKeyFrames().addAll(frames);
    	
    	forceLabel = new Label("Force: "+ getForce());
    	getChildren().add(forceLabel);
    	
    }
    
    
    public void calculateAcceleration(){
    	this.acceleration = 1*this.getForce()*0.001+0.001;
    }
    
    
    public void start(){
    	
    	calculateKeyFrames();
    	timeline.play();
    }
    
    
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
    
    public double getAcceleration(){
    	return this.acceleration;
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
