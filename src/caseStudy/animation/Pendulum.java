package caseStudy.animation;

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Pendulum extends AnimationBase{

    double period;
    double stringLength;
    double maxAngle;
    double gravAcc;
    TextField[] fields = new TextField[3];
    ImageView pendulum;
    
    Rectangle shape = new Rectangle(400,-150,100,300);
    
    public Pendulum(String name) {
        
        super(name);
        Label[] labels = new Label[fields.length];
        labels[0] = new Label("String length : ");
        labels[1] = new Label("Max/Inital Angle : ");
        labels[2] = new Label("Gravity acceleration : ");
        
        fields[0] = new TextField("2");
        fields[1] = new TextField("40");
        fields[2] = new TextField("-9.8");
        
        for(int i = 0 ; i < fields.length ; i++){
            getChildren().add(labels[i]);
            labels[i].setLayoutY(i*40);
            
            getChildren().add(fields[i]);
            fields[i].setLayoutY(i*40);
            fields[i].setLayoutX(120);
        //0 -> legthField
        //1 -> angleField
        //2 -> accelerationField
        }
        
        getChildren().add(shape);
    }
    
    //calculates the period so that the loop only calculates what is necessary
    public double calculatePeriod(){
        return 2*IConstants.PI*Math.sqrt(getStringLength()/getGravAcc());
    }
    
    //Adds the correct KeyFrames to the timeline for both animated objects
    public void calculateKeyframes(){
        setStringLength(Double.parseDouble(fields[0].getText()));
        setMaxAngle(Double.parseDouble(fields[1].getText()));
        setGravAcc(Double.parseDouble(fields[2].getText()));
        setPeriod(calculatePeriod());
        
        KeyFrame[] frames = new KeyFrame[(int)(period*1/2*1000)];
        
        for(int i = 0 ; i < frames.length ; i++){
            //creates an easy to use value to animate with
            double angle = getMaxAngle()*Math.cos(2*IConstants.PI*getStringLength()/getGravAcc());
            //Creates KeyValues for the angle
            KeyValue angleVal = new KeyValue(shape.rotateProperty(), i*3);
            //Creates KeyFrames and adds them to the array
            frames[i] = new KeyFrame(Duration.millis(33*i), angleVal);
            timeline.getKeyFrames().add(frames[i]);
        }
        timeline.getKeyFrames().addAll(frames);
    }
    
    @Override
    public void start(){
        
        calculateKeyframes();
        timeline.play();
    }
    
    public void setPeriod(double period) {
        this.period = period;
    }

    public void setStringLength(double stringLength) {
        this.stringLength = stringLength;
    }

    public void setMaxAngle(double maxAngle) {
        this.maxAngle = maxAngle;
    }

    public void setGravAcc(double gravAcc) {
        this.gravAcc = gravAcc;
    }

    public void setFields(TextField[] fields) {
        this.fields = fields;
    }

    public void setPendulum(ImageView pendulum) {
        this.pendulum = pendulum;
    }

    public double getPeriod() {
        return period;
    }

    public double getStringLength() {
        return stringLength;
    }

    public double getMaxAngle() {
        return maxAngle;
    }

    public double getGravAcc() {
        return gravAcc;
    }

    public TextField[] getFields() {
        return fields;
    }

    public ImageView getPendulum() {
        return pendulum;
    }
}