package caseStudy.animation;

import caseStudy.AnimationBase;
import javafx.animation.Animation;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class LenzLaw extends AnimationBase{
    
    private double charge;
    private double electricField;
    private double initialVelocity;
    private double magneticField;
    private double acceleration;
    private Circle object;
    private TextField chargeField;
    private TextField electricFieldField;
    private TextField initialVelocityField;
    private TextField magneticFieldField;
    private Label chargeLabel;
    private Label electricFieldLabel;
    private Label initialVelocityLabel;
    private Label magneticFieldLabel;
    private Label forceLabel;
    private int timeCounter=0;
    


    public LenzLaw(String name) {
        
        super(name);
        
        this.chargeLabel = new Label("Charge: ");
        this.electricFieldLabel = new Label("Electric Field(N/C): ");
        this.initialVelocityLabel = new Label("Initial Velocity(m/s): ");
        this.magneticFieldLabel = new Label("Magnetic Field(T): ");
        this.forceLabel = new Label("Force = 0 N");
        
        this.chargeField = new TextField("1");
        this.electricFieldField = new TextField("10");
        this.initialVelocityField = new TextField("0");
        this.magneticFieldField = new TextField("10");
        
        this.chargeField.setLayoutX(100);
        this.chargeField.setLayoutY(0);
        this.electricFieldField.setLayoutX(100);
        this.electricFieldField.setLayoutY(40);
        this.initialVelocityField.setLayoutX(100);
        this.initialVelocityField.setLayoutY(80);
        this.magneticFieldField.setLayoutX(100);
        this.magneticFieldField.setLayoutY(120);
        this.electricFieldLabel.setLayoutY(40);
        this.initialVelocityLabel.setLayoutY(80);
        this.magneticFieldLabel.setLayoutY(120);
        this.forceLabel.setLayoutX(300);
        this.forceLabel.setLayoutY(10);
        
        this.getChildren().addAll(chargeLabel, forceLabel, electricFieldLabel, magneticFieldLabel, initialVelocityLabel, chargeField, electricFieldField, magneticFieldField, initialVelocityField);
        
        object = new Circle(300, 100, 35);
        
        this.getChildren().add(object);
    }
    
    private void calculateKeyFrames(){
    	
    	calculateAcceleration();
        
        this.timeline = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>(){
    	
    		public void handle(ActionEvent event){
    			object.setLayoutX(getPosition(0.005*timeCounter));
                        timeCounter++;
                }
        }));
    	
    }
    
    
    private void calculateAcceleration(){
    	this.acceleration = 1*this.getForce()+ 0.001;
    }
    
    private double getPosition(double time){
        calculateAcceleration();
        return (initialVelocity*time + 0.5*acceleration*time*time);
    }
    
    
    public void start(){
        setValues();
        forceLabel.setText("Force = "+ (double)getForce()+" N");
    	calculateKeyFrames();
        timeline.setCycleCount(Animation.INDEFINITE);
    	timeline.play();
    }
    
    
    public void done(){
        timeline.stop();
    }
    
    
    public void reset(){
        this.timeCounter=0;
        this.chargeField.setText("1");
        this.electricFieldField.setText("10");
        this.magneticFieldField.setText("10");
        this.initialVelocityField.setText("0");
        this.forceLabel.setText("Force = 0 N");
        object.setLayoutX(0);
    }
    
    public String getHelp(){
        return "This shows a mass with a certain charge that acts under a magnetic and electric field.\n There may also be an initial velocity, which "
                + "changes the force on the mass.";
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
    
    public void setValues(){
        setCharge(Double.parseDouble(chargeField.getText()));
    	setElectricField(Double.parseDouble(electricFieldField.getText()));
    	setMagneticField(Double.parseDouble(magneticFieldField.getText()));
    	setInitialVelocity(Double.parseDouble(initialVelocityField.getText()));
    }
    
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
