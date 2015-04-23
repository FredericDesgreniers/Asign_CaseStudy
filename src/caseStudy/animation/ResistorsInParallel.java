package caseStudy.animation;

import caseStudy.AnimationBase;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;

public class ResistorsInParallel extends AnimationBase{
    
    public double resistors[] = new double[3];
    public double voltage;
    public AnimationBase animation;
    public TextField fields[] = new TextField[4];
    public Label labels[]= new Label[4];
    public Rectangle rectangles[] = new Rectangle[12];
    public Circle resistorsSource[] = new Circle[3];
    public int currentlyColoredShape=0;
    public resistanceLabel[] new Label[3];




    public ResistorsInParallel(String name) {
    
        super(name);
        
        this.labels[0] = new Label("Resistor 1: ");
        this.labels[1] = new Label("Resistor 2: ");
        this.labels[2] = new Label("Resistor 3: ");
        this.labels[3] = new Label("Voltage: ");
        this.resistanceLabel[0] = new Label("0 Ohms");
        this.resistanceLabel[1] = new Label("0 Ohms");
        this.resistanceLabel[2] = new Label("0 Ohms");
        
        getChildren.addAll(resistanceLabel);
        
        for(int i=0; i<4; i++){
        getChildren().add(labels[i]);
        getChildren().add(fields[i]);
        
        rectangles[0].setFill(Color.BLUE);
        
        }
    }
    
    public void start(){
    
    	this.timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>(){
    	
    		@Override
    		publid void handle(ActionEvent event){
    			nextShapeColored();
    			
    			if(currentlyColoredShape==3){
    				resistanceLabel[0] = new Label(resistors[0]+" Ohms");
    			}
    			
    			if(currentlyColoredShape==7){
    				resistanceLabel[1] = new Label(resistors[1]+" Ohms");
    			}
    			
    			if(currentlyColoredShape==10){
    				resistanceLabel[2] = new Label(resistors[2]+" Ohms");
    			}
    }

    
    public void calculateKeyFrames(){
    
    	setResistor(0, Double.parseDouble(fields[0].getText()));
    	setResistor(1, Double.parseDouble(fields[1].getText()));
    	setResistor(2, Double.parseDouble(fields[2].getText()));
    	setVoltage(Double.parseDouble(fields[3].getText()));
    	
    }
    
    
    
    public void nextShapeColored(){
    	
    	if(currentlyColoredShape>=12){
    	rectangles[0].setFill(Color.BLUE);
    	currentlyColoredShape=0;
    	}
    	
    	else{
    	rectangles[currentlyColoredShape].setFill(null);
    	rectangles[currentlyColoredShape+1].setFill(null);
    	currentlyColoredShape++;
    	}
    }
    
    
    
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
    return this.resistors[i];
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
