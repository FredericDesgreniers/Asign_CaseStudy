package caseStudy.animation; 

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public final class Spring extends AnimationBase implements IConstants{
    
    double mass;
    double period;
    double springConstant;
    double amplitude;
    ImageView spring;
    ImageView hangingMass;
    
    public Spring(String name){
        super(name);
        TextField[] fields = new TextField[3];
        for(int i = 0 ; i < 3 ; i++){
            fields[i] = new TextField("3.00");
            getChildren().add(fields[i]);
        //0 -> massField
        //1 -> constantield
        //2 -> amplitudeField
        }
        
        setMass(Double.parseDouble(fields[0].getText()));
        setSpringConstant(Double.parseDouble(fields[1].getText()));
        setAmplitude(Double.parseDouble(fields[2].getText()));
        period = calculatePeriod();
        timeline.setAutoReverse(true);
        
        Image springGraphic = new Image(this.getClass().getResourceAsStream("/res/SpringCaseStudy.png"));
        spring = new ImageView(springGraphic);
        spring.setFitHeight(100);
        spring.setPreserveRatio(true);
        spring.setSmooth(true);
        spring.setLayoutX(0);
        getChildren().add(spring);
        
        Image massGraphic = new Image(this.getClass().getResourceAsStream("/res/MassCaseStudy.png"));
        hangingMass = new ImageView(massGraphic);
        hangingMass.setFitHeight(100);
        hangingMass.setPreserveRatio(true);
        hangingMass.setSmooth(true);
        hangingMass.setLayoutX(0);
        hangingMass.setScaleX(0.7);
        hangingMass.setScaleY(0.7);
        getChildren().add(hangingMass);
    }
    
    public double calculatePeriod(){
        return 2*IConstants.PI*Math.sqrt(getMass()/getSpringConstant());
    }
    
    public void calculateKeyframes(){
        int i = 0;
        KeyFrame[] frames = new KeyFrame[(int)(calculatePeriod()*1/2*1000)];
        while(i < frames.length){
            double stretchPercent = 1+Math.cos(((float)i*33.0f/1000.0f)*Math.sqrt(springConstant/mass));
            //spring.setFitHeight((stretchPercent*0.6+20)*spring.getFitHeight());
            KeyValue stretchVal = new KeyValue(spring.scaleYProperty(), (stretchPercent*0.6 + 0.4));
            KeyValue positionVal = new KeyValue(spring.yProperty(), -(spring.getFitHeight() - (stretchPercent*0.6 + 0.4)*spring.getFitHeight())/2);
            frames[i] = new KeyFrame(Duration.millis(33*i), stretchVal, positionVal);
            //hangingMass.setCenterY(30.00 + (DIM_Y/2)*stretchPercent);
            timeline.getKeyFrames().add(frames[i]);
            i++;
        }
        timeline.getKeyFrames().addAll(frames);
    }
    
    public void calculateKeyframes2(){
        double stretchPercent = 0.00;
        //adds the keyframes to the timeline
        int tempI=-1;
        List<KeyFrame> frames=new ArrayList();
        for(int i = 1 ; i<100 ; i++){
            tempI=i;
            //calculate percent extension
            //stretchPercent = 1+Math.cos((i*33/1000)*Math.sqrt(springConstant/mass));
            //spring
            //spring.setFitHeight((stretchPercent*0.6+20)*spring.getFitHeight()); //this should be adding a keyframe
            //mass
            //hangingMass.setCenterY(30.00 + (DIM_Y/2)*stretchPercent); //CHANGE FINAL POSITIONS
            KeyFrame kf=new KeyFrame(Duration.millis(100+i*100),new KeyValue(spring.xProperty(),100+i*5 ));
            System.out.println(i);
            frames.add(kf);
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
