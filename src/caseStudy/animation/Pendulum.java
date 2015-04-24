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

    double period;
    double stringLength;
    double maxAngle;
    double gravAcc;
    TextField[] fields = new TextField[3];
    ImageView pendulum;
        
    public Pendulum(String name) {
        
        super(name);
       
        setGUI();
    }
    
    //calculates the period so that the loop only calculates what is necessary
    public double calculatePeriod(){
        return 2*IConstants.PI*Math.sqrt(getStringLength()/Math.abs(getGravAcc()));
    }
    
    //Adds the correct KeyFrames to the timeline for both animated objects
    public void calculateKeyframes(){
        setStringLength(Double.parseDouble(fields[0].getText()));
        setMaxAngle(Math.toRadians(Double.parseDouble(fields[1].getText()))*940/90);
        setGravAcc(Double.parseDouble(fields[2].getText()));
        setPeriod(calculatePeriod());
        KeyFrame[] frames = new KeyFrame[(int)(period*1/2*1000)];
        
        for(int i = 0 ; i < frames.length ; i++){
            //creates an easy to use value to animate with
            double angle = getMaxAngle()*Math.cos((double)i*33/1000)*Math.sqrt((2*IConstants.PI*Math.abs(getGravAcc())/getStringLength()));
            //Creates KeyValues for the angle
            KeyValue angleVal = new KeyValue(pendulum.rotateProperty(), angle);
            //Creates KeyFrames and adds them to the array
            frames[i] = new KeyFrame(Duration.millis(33*i), angleVal);
            timeline.getKeyFrames().add(frames[i]);
        }
        timeline.getKeyFrames().addAll(frames);
    }
    
    @Override
    public void start(){
        done();
        calculateKeyframes();
        timeline.play();
    }
    
    @Override
    public void done(){
        timeline.stop();
        timeline = new Timeline();
    }
    
    @Override
    public void reset(){
        done();
        getChildren().clear();
        setGUI();
    }
    
    public void setGUI(){
        Label[] labels = new Label[fields.length];
        labels[0] = new Label("String length : ");
        labels[1] = new Label("Max/Inital Angle : ");
        labels[2] = new Label("Gravity acceleration : ");
        
        fields[0] = new TextField("2");
        fields[1] = new TextField("40");
        fields[2] = new TextField(GRAVACC + "");
        
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
        
        //Creates the spring graphic and sets initial the parameters
        Image springGraphic = new Image(this.getClass().getResourceAsStream("/res/PendulumCaseStudy.png"));
        pendulum = new ImageView(springGraphic);
        pendulum.setPreserveRatio(true);
        pendulum.setSmooth(true);
        pendulum.setLayoutX(450);
        pendulum.setLayoutY(-150);
        getChildren().add(pendulum);
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