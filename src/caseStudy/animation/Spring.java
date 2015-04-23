package caseStudy.animation; 

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class Spring extends AnimationBase implements IConstants{
    
    double mass;
    double period;
    double springConstant;
    double amplitude;
    TextField[] fields = new TextField[3];
    ImageView spring;
    ImageView hangingMass;
    
    public Spring(String name){
        
        super(name);
        
        Label[] labels = new Label[3];
        labels[0] = new Label("Mass : ");
        labels[1] = new Label("Spring constant : ");
        labels[2] = new Label("Amplitude : ");
        
        for(int i = 0 ; i < fields.length ; i++){
            getChildren().add(labels[i]);
            labels[i].setLayoutY(i*40);
            fields[i] = new TextField("1.00");
            getChildren().add(fields[i]);
            fields[i].setLayoutY(i*40);
            fields[i].setLayoutX(100);
        //0 -> massField
        //1 -> constantield
        //2 -> amplitudeField
        }
        
        //timeline.setAutoReverse(true);
        
        //Creates the spring graphic and sets initial the parameters
        Image springGraphic = new Image(this.getClass().getResourceAsStream("/res/SpringCaseStudy.png"));
        spring = new ImageView(springGraphic);
        spring.setFitHeight(100);
        spring.setPreserveRatio(true);
        spring.setSmooth(true);
        spring.setLayoutX(400);
        getChildren().add(spring);
        
        //Creates the hanging mass graphic and sets the initial parameters
        Image massGraphic = new Image(this.getClass().getResourceAsStream("/res/MassCaseStudy.png"));
        hangingMass = new ImageView(massGraphic);
        hangingMass.setFitHeight(100);
        hangingMass.setPreserveRatio(true);
        hangingMass.setSmooth(true);
        hangingMass.setLayoutX(400);
        hangingMass.setLayoutY(spring.getFitHeight()-30);
        hangingMass.setScaleX(0.7);
        hangingMass.setScaleY(0.7);
        getChildren().add(hangingMass);
    }
    
    //calculates the period so that the loop only calculates what is necessary
    public double calculatePeriod(){
        return 2*IConstants.PI*Math.sqrt(getMass()/getSpringConstant());
    }
    
    //Adds the correct KeyFrames to the timeline for both animated objects
    public void calculateKeyframes(){
        setMass(Double.parseDouble(fields[0].getText()));
        setSpringConstant(Double.parseDouble(fields[1].getText()));
        setAmplitude(Double.parseDouble(fields[2].getText()));
        
        period = calculatePeriod();
        
        KeyFrame[] frames = new KeyFrame[(int)(period*1/2*1000)];
        
        for(int i = 0 ; i < frames.length ; i++){
            //creates an easy to use value to animate with
            double stretchPercent = 1+Math.sin(((float)i*33.0f/1000.0f)*Math.sqrt(springConstant/mass));
            //Creates KeyValues for the spring's stretch and position
            KeyValue stretchSpringVal = new KeyValue(spring.scaleYProperty(), (stretchPercent*0.6 + 0.2));
            KeyValue positionSpringVal = new KeyValue(spring.yProperty(), -(spring.getFitHeight() - (stretchPercent*0.6 + 0.2)*spring.getFitHeight())/2);
            //Creates KeyValues for the hangingMass's position
            KeyValue positionMassVal = new KeyValue(hangingMass.yProperty(), ((stretchPercent*0.6 + 0.2)*spring.getFitHeight()-spring.getFitHeight()));
            //Creates KeyFrames and adds them to the array
            frames[i] = new KeyFrame(Duration.millis(33*i), stretchSpringVal, positionSpringVal, positionMassVal);
            timeline.getKeyFrames().add(frames[i]);
        }
        timeline.getKeyFrames().addAll(frames);
    }
    
    public void start(){
        
        
        calculateKeyframes();
        timeline.play();
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getPeriod() {
        return period;
    }

    public double getMass() {
        return mass;
    }

    public double getSpringConstant() {
        return springConstant;
    }

    public double getAmplitude() {
        return amplitude;
    }
}
