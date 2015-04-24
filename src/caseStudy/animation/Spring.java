package caseStudy.animation; 

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class Spring extends AnimationBase implements IConstants{
    
    //initializing various variables for later use
    double mass;
    double period;
    double springConstant;
    double amplitude;
    TextField[] fields = new TextField[TEXTFIELD_AMT];
    ImageView spring;
    ImageView hangingMass;
    
    //constructor
    public Spring(String name){
        
        super(name);
        //adds the ui elements
        setGUI();
    }
    
    //calculates the period so that the loop only calculates what is necessary
    public double calculatePeriod(){
        //formula -> 2*pi*sqrt(mass/constant)
        return TWO*IConstants.PI*Math.sqrt(getMass()/getSpringConstant());
    }
    
    //Adds the correct KeyFrames to the timeline for both animated objects
    public void calculateKeyframes(){
        //fetches the values for the 3 variables from the text fields
        try{
            setMass(Math.abs(Double.parseDouble(fields[0].getText())));
            setSpringConstant(Math.abs(Double.parseDouble(fields[1].getText())));
            setAmplitude(Math.abs(Double.parseDouble(fields[2].getText())));
        }
        catch(Exception e){System.out.println("invalid values, please enter a valid number and try again");}
        
        //calculates the period and creates an array of KeyFrames of theproper length
        period = calculatePeriod();
        KeyFrame[] frames = new KeyFrame[(int)(period*TIME_MILLIS_CONVERSION/TWO)];
        
        //calculates the proper values and adds them to the KeyFrames timeline
        for(int i = ZERO ; i < frames.length ; i++){
            //creates an easy to use value to animate with -> sin(time*sqrt(constant/mass))
            double stretchPercent = PERCENT_STRETCH_CORRECTION + STRETCH_DAMPER*(ONE + Math.sin(((double)i*FRAMERATE_MILLIS/TIME_MILLIS_CONVERSION)*Math.sqrt(springConstant/mass)));
            //Creates KeyValues for the spring's stretch and position
            KeyValue stretchSpringVal = new KeyValue(spring.scaleYProperty(), stretchPercent);
            KeyValue positionSpringVal = new KeyValue(spring.yProperty(), -(spring.getFitHeight() - stretchPercent*spring.getFitHeight())/TWO);
            //Creates KeyValues for the hangingMass's position
            KeyValue positionMassVal = new KeyValue(hangingMass.yProperty(), (stretchPercent*spring.getFitHeight()-spring.getFitHeight()));
            //Creates KeyFrames and adds them to the array
            frames[i] = new KeyFrame(Duration.millis(FRAMERATE_MILLIS*i), stretchSpringVal, positionSpringVal, positionMassVal);
            timeline.getKeyFrames().add(frames[i]);
        }
        //adds the KeyFrames to the timeline
        timeline.getKeyFrames().addAll(frames);
    }
    
    //starts to play the animation by calling the calculateKeyFrames method and playing the timeline
    @Override
    public void start(){
        done();
        calculateKeyframes();
        timeline.play();
    }
    
    //stops the timeline and makes a new one
    @Override
    public void done(){
        timeline.stop();
        timeline = new Timeline();
    }
    
    //clears the contents of the pane and fills it again with the defaults
    @Override
    public void reset(){
        done();
        getChildren().clear();
        setGUI();
    }
    
    //creates and adds all visual elements 
    public void setGUI(){
        //creates and defines all the labels
        Label[] labels = new Label[fields.length];
        labels[0] = new Label("Mass : ");
        labels[1] = new Label("Spring constant : ");
        labels[2] = new Label("Amplitude : ");
        
        for(int i = ZERO ; i < labels.length ; i++){
            //adds the labels to the view
            getChildren().add(labels[i]);
            labels[i].setLayoutY(i*LABEL_SPACING);
            //adds the fields to the view
            fields[i] = new TextField("1.00");
            getChildren().add(fields[i]);
            fields[i].setLayoutY(i*LABEL_SPACING);
            fields[i].setLayoutX(FIELDS_X_DISPLACEMENT);
        //0 -> massField
        //1 -> constantield
        //2 -> amplitudeField
        }
        
        //timeline.setAutoReverse(true);
        
        //Creates the spring graphic and sets initial the parameters
        Image springGraphic = new Image(this.getClass().getResourceAsStream("/res/SpringCaseStudy.png"));
        spring = new ImageView(springGraphic);
        spring.setFitHeight(IMAGEVIEW_HEIGHT);
        spring.setPreserveRatio(true);
        spring.setSmooth(true);
        spring.setLayoutX(IMAGE_X_DISPLACEMENT);
        getChildren().add(spring);
        
        //Creates the hanging mass graphic and sets the initial parameters
        Image massGraphic = new Image(this.getClass().getResourceAsStream("/res/MassCaseStudy.png"));
        hangingMass = new ImageView(massGraphic);
        hangingMass.setFitHeight(IMAGEVIEW_HEIGHT);
        hangingMass.setPreserveRatio(true);
        hangingMass.setSmooth(true);
        hangingMass.setLayoutX(IMAGE_X_DISPLACEMENT);
        hangingMass.setLayoutY(spring.getFitHeight()-30);
        hangingMass.setScaleX(MASS_SCALE_FACTOR);
        hangingMass.setScaleY(MASS_SCALE_FACTOR);
        getChildren().add(hangingMass);
    }
    
    //method that defines what the help window displays
    @Override
    public String getHelp(){
        return "Set the values and press play when you are ready";
    }
    
    //getter and  setter methods for the variables
    public void setPeriod(double period) {
        this.period = period;
    }
    //
    public void setMass(double mass) {
        this.mass = mass;
    }
    //
    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }
    //
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
    //
    public double getPeriod() {
        return period;
    }
    //
    public double getMass() {
        return mass;
    }
    //
    public double getSpringConstant() {
        return springConstant;
    }
    //
    public double getAmplitude() {
        return amplitude;
    }
}
