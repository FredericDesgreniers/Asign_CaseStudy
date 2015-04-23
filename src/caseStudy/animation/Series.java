/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import java.text.DecimalFormat;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * COMMENTARY ADDED SINCE ALL IS MESSY AND HARD TO UNDERSTAND
 * @author Fred
 */
public class Series extends AnimationBase{

    TextField valueAText;
    TextField valueRText;
    Circle circleSum;
    double radius;
    double infinity;
    public Series(String name) {
        super(name);
        //Creates all the layout stuff, nothing special
        valueAText=new TextField(String.valueOf(IConstants.SERIES_A_DEF));
        valueRText=new TextField(String.valueOf(IConstants.SERIES_R_DEF));
        circleSum=new Circle();
        radius=IConstants.SERIES_SUM_RADIUS_IN;
        circleSum.setRadius(radius);
        circleSum.setLayoutX(IConstants.SERIES_SUM_X);
        circleSum.setLayoutY(IConstants.SERIES_SUM_Y);
        infinity=IConstants.SERIES_INFINITY_IN;
        valueRText.setLayoutY(30);
        valueRText.setLayoutX(50);
        valueAText.setLayoutX(50);
        
        setStaticGUI();
    }
    //When you forget to add sutff to the plan so you cant add 20 variables so you just decide to add a function and make it messy
    public void setStaticGUI()
    {
        Label formula=new Label("Geometric series: AR^n");
        formula.setLayoutX(30);
        formula.setLayoutY(55);
        formula.setFont(new Font("Arial",20));
        Label aLabel=new Label("  set A ");
        aLabel.setTextFill(Paint.valueOf("black"));
        Label rLabel=new Label("  set R ");
        rLabel.setLayoutY(30);
        rLabel.setTextFill(Paint.valueOf("black"));
        
        getChildren().addAll(valueAText,valueRText,rLabel,aLabel,circleSum,formula);
    }
    //calls done to clear children and then readds everything with default values/reinitialises everything
    public void reset()
    {
        done();
        timeline=new Timeline();
        valueAText=new TextField(String.valueOf(IConstants.SERIES_A_DEF));
        valueRText=new TextField(String.valueOf(IConstants.SERIES_R_DEF));
        circleSum=new Circle();
        radius=IConstants.SERIES_SUM_RADIUS_IN;
        circleSum.setRadius(radius);
        circleSum.setLayoutX(IConstants.SERIES_SUM_X);
        circleSum.setLayoutY(IConstants.SERIES_SUM_Y);
        infinity=IConstants.SERIES_INFINITY_IN;
        valueRText.setLayoutY(30);
        valueRText.setLayoutX(50);
        valueAText.setLayoutX(50);
        
        setStaticGUI();
    }
    // stops animation so it's ready to start again
    public void done()
    {
        timeline.stop();
        getChildren().clear();
        setStaticGUI();
    }
    // returns help text for dialog box
    public String getHelp()
    {
        return "This will use the provided value to perform a sum of the equation. The end value will be the infitnite geometrical series sum for the provided euqation of AR^n.";
    }
    public void start()
    {
        done();
        timeline=new Timeline();
        double a=getValueA(); //gets A value
        double r=getValueR(); //gets r value
        radius=0;
        infinity=IConstants.SERIES_INFINITY_IN; //sets infiity var to -1, once it's higher then 0, the sum is considered tending to infinity
        double tendValue=Math.PI;
        double scale=IConstants.SERIES_SCALE_DEF; //scale of the circle, could make it dynamic, but keeping is like this for now
        
        Label radiusL=new Label(String.valueOf(radius)); //created a radius label for the big sum circle that doesnt move
        radiusL.getStyleClass().add(IConstants.SERIES_LABEL_STYLE);
        radiusL.setLayoutX(IConstants.SERIES_VALUE_LABEL_X);
        radiusL.setLayoutY(IConstants.SERIES_VALUE_LABEL_Y);
        getChildren().add(radiusL); //adds radius label
        int iMax=0;
        for(int i=0;i<IConstants.SERIES_MAXITERATIONS;i++)
        {
            
            double value=(a*Math.pow(r, i)); //finds the value at a certain n

           // System.out.println(value);//debug
            Circle c=new Circle(); //creates a small cicle tha trepresents the value
            c.setFill(Paint.valueOf("red"));
            c.setOpacity(IConstants.SERIES_OPACITY_IN);
            c.setRadius(0);
            c.setLayoutY(IConstants.SERIES_VALUE_Y);//starts at 0
            DecimalFormat f = new DecimalFormat("##");//used to format things
            
            Label t=new Label(f.format(value)); //creates label for value circle
            t.setLayoutY(IConstants.SERIES_VALUET_Y);
            t.setTranslateX(IConstants.SERIES_VALUET_TY);
            t.setTextFill(Paint.valueOf(IConstants.SERIES_VALUET_COLOR));
            t.setVisible(false); //will be set visible when small circle appears
            this.getChildren().addAll(c,t); //add both to screen, t being non-visible and c being of radius 0, so invisible
            
            KeyValue valuescXI=new KeyValue(c.centerXProperty(),200);   //keyvalue for small circle X initial
            KeyValue valuescXF=new KeyValue(c.centerXProperty(),500);   //keyvalue for small circle X final
             KeyValue valuescOI=new KeyValue(c.opacityProperty(),0);    //keyvalue for small circle opacity initial
            KeyValue valuescOF=new KeyValue(c.opacityProperty(),1);     //keyvalue for small circle opacity final
            KeyValue valuescRF=new KeyValue(c.radiusProperty(),(value/scale)>20?value/scale:20); //key value for small circle radius -> becomes bigger
            KeyValue valuetXI=new KeyValue(t.layoutXProperty(),200);    //key value for value label X initial
            KeyValue valuetXF=new KeyValue(t.layoutXProperty(),500);    //key value for value label X final
            
            KeyValue circleI=new KeyValue(circleSum.radiusProperty(),radius/scale); //creates a keyvalue for the circle radius initial
            if(radius>500) // once radius is bigger then 500, it tends to a too big number
            {
                if(r>=1 || r<=-1) //iso we check if it tends to infinity
                {
                   infinity=radius/scale;
                    
                }else //if it tends to a real value
                {
                    tendValue=(a/(1-r)); //fiond the sum end result to display latter
                }
                
            }else
            {
                radius+=value; //if its not too big continue adding
            }
            KeyValue circleF=new KeyValue(circleSum.radiusProperty(),radius/scale); //keyvalue for the circle radius final
            KeyFrame kf0=new KeyFrame(Duration.millis(i*1000),new EventHandler<ActionEvent>(){ //first keyframe for the initial values

                @Override
                public void handle(ActionEvent event) {
                    t.setVisible(true); //set value label to true when it's animation starts
                }
            },valuescOI,valuescXI,valuetXI);
            KeyFrame kf1=new KeyFrame(Duration.millis((i+1)*1000),new EventHandler<ActionEvent>(){ //second keyframe for the final value circle values and initial sum circle values

                @Override
                public void handle(ActionEvent event) {

                    getChildren().removeAll(c,t); //remove useless value circle and it's label since they already played the animation
                }
            },valuescXF,valuescOF,valuetXF,circleI ,valuescRF);
            final double tend=tendValue; //need final for stupid innerclasses. 
            KeyFrame kf2=new KeyFrame(Duration.millis((i+1)*1000+500),new EventHandler<ActionEvent>(){ //third keyframe for sum circle final.

                @Override
                public void handle(ActionEvent event) {
                    DecimalFormat f = new DecimalFormat("##.00");
                    radiusL.setText(tend!=Math.PI?f.format(tend):infinity==circleSum.getRadius()?"infinity":f.format(circleSum.getRadius()*scale)); //set new sum value. Shitty code, but no time to clean up
                }
            },circleF);
            
            timeline.getKeyFrames().addAll(kf0,kf1,kf2); //add all keyframes to timeline. 
            if(infinity>0 || tendValue!=Math.PI) //if too big, set maximum i for later
            {
                iMax=i;
                break;
            }
            if(Double.parseDouble(f.format(value))<=0)//see if it's adding 0 and is then finished
            {
                iMax=i;
                break;
            }
        }
        KeyFrame kf=new KeyFrame(Duration.millis((iMax+1)*1000+1000),new KeyValue(circleSum.radiusProperty(),80)); //set sum circle to static final size so it stays on screen and isn't too big or too small for screen
        timeline.getKeyFrames().add(kf);
        timeline.play(); //play the whole damn thing
    }
    
    public double getValue(int n)
    {
        return 0;
    }
    public double getInfitniteValue()
    {
        return 0;
    }
    
    public double getValueA()
    {
        return Double.parseDouble(valueAText.getText());
    }
    public double getValueR()
    {
        return Double.parseDouble(valueRText.getText());
    }
    public void setValueA(int a)
    {
        valueAText.setText(String.valueOf(a));
    }
    public void setValueR(int r)
    {
        valueAText.setText(String.valueOf(r));
    }
}
