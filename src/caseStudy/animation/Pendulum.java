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

public class Pendulum extends AnimationBase implements IConstants{
    
    //initializes a few variables
    double period;
    double stringLength;
    double maxAngle;
    double gravAcc;
    TextField[] fields = new TextField[TEXTFIELD_AMT];
    ImageView pendulum;
    
    //contructor
    public Pendulum(String name) {
        super(name);
        //sets all the gui elements
        setGUI();
    }
    
    //calculates the period so that the loop only calculates what is necessary
    public double calculatePeriod(){
        //formula -> 2*pi*sqrt(length/acceleration)
        return TWO*PI*Math.sqrt(getStringLength()/Math.abs(getGravAcc()));
    }
    
    //Adds the correct KeyFrames to the timeline for both animated objects
    public void calculateKeyframes(){
        try{
            setStringLength(Double.parseDouble(fields[0].getText()));
            setMaxAngle(Double.parseDouble(fields[1].getText()));
            setGravAcc(Double.parseDouble(fields[2].getText()));
        }
        catch(Exception e){System.out.println("invalid values, please enter valid numbers and try again");}
        
        //calculates the period and creates an array of KeyFrames of theproper length
        setPeriod(calculatePeriod());
        KeyFrame[] frames = new KeyFrame[(int)(period*TIME_MILLIS_CONVERSION/TWO)];
        
        //calculates the proper values and adds them to the KeyFrames timeline
        for(int i = ZERO ; i < frames.length ; i++){
            //creates an easy to use value to animate with
            double angle = getMaxAngle()*Math.cos((double)i*FRAMERATE_MILLIS/TIME_MILLIS_CONVERSION*Math.sqrt((TWO*PI*getGravAcc()/getStringLength())));
            //Creates KeyValues for the angle
            KeyValue angleVal = new KeyValue(pendulum.rotateProperty(), angle);
            //Creates KeyFrames and adds them to the array
            frames[i] = new KeyFrame(Duration.millis(FRAMERATE_MILLIS*i), angleVal);
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
    
    //creates and adds all visual elements 
    public void setGUI(){
        //creates and defines all the labels
        Label[] labels = new Label[fields.length];
        labels[0] = new Label("String length : ");
        labels[1] = new Label("Max/Inital Angle : ");
        labels[2] = new Label("Gravity acceleration : ");
        
        //gives default values to the TextFields
        fields[0] = new TextField("2");
        fields[1] = new TextField("40");
        fields[2] = new TextField(GRAVACC + "");
        
        for(int i = ZERO ; i < fields.length ; i++){
            //adds the labels to the view
            getChildren().add(labels[i]);
            labels[i].setLayoutY(i*LABEL_SPACING);
            //adds the fields to the view
            getChildren().add(fields[i]);
            fields[i].setLayoutY(i*LABEL_SPACING);
            fields[i].setLayoutX(FIELDS2_X_DISPLACEMENT);
        //0 -> legthField
        //1 -> angleField
        //2 -> accelerationField
        }
        
        //Creates the spring graphic and sets initial the parameters
        Image springGraphic = new Image(this.getClass().getResourceAsStream("/res/PendulumCaseStudy.png"));
        pendulum = new ImageView(springGraphic);
        pendulum.setPreserveRatio(true);
        pendulum.setSmooth(true);
        pendulum.setLayoutX(IMAGE2_X_DISPLACEMENT);
        pendulum.setLayoutY(IMAGE2_Y_DISPLACEMENT);
        getChildren().add(pendulum);
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
    public void setStringLength(double stringLength) {
        this.stringLength = stringLength;
    }
    //
    public void setMaxAngle(double maxAngle) {
        this.maxAngle = maxAngle;
    }
    //
    public void setGravAcc(double gravAcc) {
        this.gravAcc = gravAcc;
    }
    //
    public void setFields(TextField[] fields) {
        this.fields = fields;
    }
    //
    public void setPendulum(ImageView pendulum) {
        this.pendulum = pendulum;
    }
    //
    public double getPeriod() {
        return period;
    }
    //
    public double getStringLength() {
        return stringLength;
    }
    //
    public double getMaxAngle() {
        return maxAngle;
    }
    //
    public double getGravAcc() {
        return gravAcc;
    }
    //
    public TextField[] getFields() {
        return fields;
    }
    //
    public ImageView getPendulum() {
        return pendulum;
    }
}