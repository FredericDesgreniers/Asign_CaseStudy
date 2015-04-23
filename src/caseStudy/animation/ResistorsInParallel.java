package caseStudy.animation;

import caseStudy.AnimationBase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ResistorsInParallel extends AnimationBase{
    
    public double resistors[] = new double[3];
    public double voltage;
    public TextField fields[] = new TextField[4];
    public Label labels[]= new Label[4];
    public Rectangle rectangles[] = new Rectangle[12];
    public Rectangle batteryRectangles[] = new Rectangle[2];
    public Circle resistorsSource[] = new Circle[3];
    public int currentlyColoredShape=0;
    public Label totalResistanceLabel = new Label("Total Resistance = 0 Ohms");
    public Label currentInResistor[] = new Label[3];
    public Label resistanceNumberLabel[] = new Label[3];

    


    public ResistorsInParallel(String name) {
    
        super(name);
        
        voltage = 0;
        resistors[0] = 0;
        resistors[1] = 0;
        resistors[2] = 0;
        
        this.labels[0] = new Label("Resistor 1: ");
        this.labels[1] = new Label("Resistor 2: ");
        this.labels[2] = new Label("Resistor 3: ");
        this.labels[3] = new Label("Voltage: ");
        this.currentInResistor[0] = new Label("Current in Resistor 1 = ");
        this.currentInResistor[1] = new Label("Current in Resistor 2 = ");
        this.currentInResistor[2] = new Label("Current in Resistor 3 = ");
        
        for(int i=0; i<4; i++){
        labels[i].setLayoutY(i*40);
        getChildren().add(labels[i]);
        fields[i] = new TextField("1");
        fields[i].setLayoutX(100);
        fields[i].setLayoutY(i*40);
        getChildren().add(fields[i]);
        }
        
        getChildren().add(totalResistanceLabel);
        totalResistanceLabel.setLayoutX(350);
        totalResistanceLabel.setLayoutY(0);
        
        getChildren().addAll(currentInResistor);
        currentInResistor[0].setLayoutX(350);
        currentInResistor[1].setLayoutX(350);
        currentInResistor[2].setLayoutX(350);
        currentInResistor[0].setLayoutY(10);
        currentInResistor[1].setLayoutY(20);
        currentInResistor[2].setLayoutY(30);
        
        resistorsSource[0] = new Circle(352, 95, 15);
        resistorsSource[1] = new Circle(427, 95, 15);
        resistorsSource[2] = new Circle(578, 95, 15);
        getChildren().addAll(resistorsSource);
        
        
        batteryRectangles[0] = new Rectangle(500, 131, 3, 40);
        batteryRectangles[1] = new Rectangle(490, 120, 3, 62);
        rectangles[0] = new Rectangle(500, 150, 75, 5);
        rectangles[1] = new Rectangle(425, 150, 65, 5);
        rectangles[2] = new Rectangle(425, 112, 5, 40);
        rectangles[3] = new Rectangle(425, 79, 5, 33);
        rectangles[4] = new Rectangle(425, 46, 5, 33);
        rectangles[5] = new Rectangle(350, 150, 75, 5);
        rectangles[6] = new Rectangle(350, 45, 5, 105);
        rectangles[7] = new Rectangle(350, 45, 80, 5);
        rectangles[8] = new Rectangle(425, 45, 150, 5);
        rectangles[9] = new Rectangle(575, 45, 5, 110);
        
        this.getChildren().add(batteryRectangles[0]);
        this.getChildren().add(batteryRectangles[1]);
        
        for(int i=0; i<10; i++){
        this.getChildren().add(rectangles[i]);
        }
        
        resistanceNumberLabel[0] = new Label("1");
        resistanceNumberLabel[0].setLayoutX(350);
        resistanceNumberLabel[0].setLayoutY(90);
        resistanceNumberLabel[0].setTextFill(Color.WHITE);
        resistanceNumberLabel[1] = new Label("2");
        resistanceNumberLabel[1].setLayoutX(425);
        resistanceNumberLabel[1].setLayoutY(90);
        resistanceNumberLabel[1].setTextFill(Color.WHITE);
        resistanceNumberLabel[2] = new Label("3");
        resistanceNumberLabel[2].setLayoutX(575);
        resistanceNumberLabel[2].setLayoutY(90);
        resistanceNumberLabel[2].setTextFill(Color.WHITE);
        getChildren().addAll(resistanceNumberLabel);
        
    }
    
    public void start(){
        
        setValues();
        
        totalResistanceLabel.setText("Total Resistance = "+getTotalResistance()+" Ohms");
        currentInResistor[0].setText("Current in Resistor 1 = "+getCurrentinResistor(0)+" A");
        currentInResistor[1].setText("Current in Resistor 2 = "+getCurrentinResistor(1)+" A");
        currentInResistor[2].setText("Current in Resistor 3 = "+getCurrentinResistor(2)+" A");
        
        
    	this.timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
    	
    		public void handle(ActionEvent event){
    			nextShapeColored();
                }
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void done(){
        timeline.stop();
    }
    public void reset(){
        setValues();
        currentlyColoredShape = 0;
        currentInResistor[0].setText("Current in Resistor 1 = 0 A");
        currentInResistor[1].setText("Current in Resistor 2 = 0 A");
        currentInResistor[2].setText("Current in Resistor 3 = 0 A");
        totalResistanceLabel.setText("Total Resistance = 0 Ohms");
        
        for(int i=0; i<rectangles.length; i++){
            rectangles[i].setFill(Color.BLACK);
        }
        
    }
    
    public String getHelp(){
        return "This animation shows the current flowing into the circuit, and passing through\n the resistors which are placed in parellel. From the input values on the left side,\n "
                + "the values above the circuit will change, which are the Total resistance of the system,\n and the current in each resistor. Values of the resistors could be between 1 and 10, and so\n is the value for the voltage"
                + "in order for this application to work clearly.";
    }
    
    

    
    public void setValues(){
    
    	setResistor(0, Double.parseDouble(fields[0].getText()));
    	setResistor(1, Double.parseDouble(fields[1].getText()));
    	setResistor(2, Double.parseDouble(fields[2].getText()));
    	setVoltage(Double.parseDouble(fields[3].getText()));
    	
    }
    
    
    
    public void nextShapeColored(){
        System.out.println("Done");
        
        if(currentlyColoredShape==4){
            rectangles[4].setFill(Color.BLACK);
            rectangles[7].setFill(Color.BLACK);
            currentlyColoredShape=7;
        }
        
    	if(currentlyColoredShape>=9){
        rectangles[9].setFill(Color.BLACK);
    	rectangles[0].setFill(Color.BLUE);
    	currentlyColoredShape=0;
    	}
    	
        else if(currentlyColoredShape>=1 && currentlyColoredShape<4){
            rectangles[currentlyColoredShape].setFill(Color.BLACK);
            rectangles[currentlyColoredShape+3].setFill(Color.BLACK);
            currentlyColoredShape++;
            rectangles[currentlyColoredShape].setFill(Color.BLUE);
            rectangles[currentlyColoredShape+3].setFill(Color.BLUE);
        }
        
    	else{
    	rectangles[currentlyColoredShape].setFill(Color.BLACK);
        currentlyColoredShape++;
    	rectangles[currentlyColoredShape].setFill(Color.BLUE);
    	}
    }
    
    
    
    /*
    *
    *Accessors Methods
    *
    */
public double getTotalResistance(){
    return (double)(1/(1/resistors[0]+1/(resistors[1]+resistors[2])));
}

public double getCurrentinResistor(int i){
    return voltage/resistors[i];
}

public double getInitialCurrent(){
    return getCurrentinResistor(0)+getCurrentinResistor(1)+getCurrentinResistor(2);
}

public double getVoltage(){
    setValues();
    return this.voltage;
}

public double getResistor(int i){
    setValues();
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
