/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
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
        valueAText=new TextField(1+"");
        valueRText=new TextField(2+"");
        circleSum=new Circle();
        circleSum.setRadius(50);
        circleSum.setLayoutX(500);
        circleSum.setLayoutY(100);
        

        this.getChildren().add(circleSum);
    }
    
    public void start()
    {
        double a=getValueA();
        double r=getValueR();
        radius=0;
        infinity=-1;
        int scale=5;
        
        Label radiusL=new Label(radius+"");
        radiusL.getStyleClass().add("circleLabel");
        radiusL.setLayoutX(480);
        radiusL.setLayoutY(90);
        
        radiusL.setTextFill(Paint.valueOf("red"));
        getChildren().add(radiusL);
        for(int i=0;i<100;i++)
        {
            
            double value=(a*Math.pow(r, i));
            if(value==0)break;
            System.out.println(value);
            Circle c=new Circle(value/10);
            c.setOpacity(0);
            c.setRadius(20);
            c.setLayoutY(100);
            DecimalFormat f = new DecimalFormat("##.00");
            Label t=new Label(f.format(value));
            t.setLayoutY(90);
            t.setTranslateX(-10);
            t.setTextFill(Paint.valueOf("white"));
            t.setVisible(false);
            this.getChildren().addAll(c,t);
            KeyValue valuescXI=new KeyValue(c.centerXProperty(),0);
            KeyValue valuescXF=new KeyValue(c.centerXProperty(),500);
             KeyValue valuescOI=new KeyValue(c.opacityProperty(),0);
            KeyValue valuescOF=new KeyValue(c.opacityProperty(),1);
            
            KeyValue valuetXI=new KeyValue(t.layoutXProperty(),0);
            KeyValue valuetXF=new KeyValue(t.layoutXProperty(),500);
            
            KeyValue circleI=new KeyValue(circleSum.radiusProperty(),radius/scale);
            if(radius<500)
            {
                radius+=value;
            }else
            {
                infinity=radius/scale;
                
            }
            KeyValue circleF=new KeyValue(circleSum.radiusProperty(),radius/scale);
            KeyFrame kf0=new KeyFrame(Duration.millis(i*1000),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    t.setVisible(true);
                }
            },valuescOI,valuescXI,valuetXI);
            KeyFrame kf1=new KeyFrame(Duration.millis((i+1)*1000),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {

                    getChildren().removeAll(c,t);
                }
            },valuescXF,valuescOF,valuetXF,circleI );
            KeyFrame kf2=new KeyFrame(Duration.millis((i+1)*1000+500),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                                        DecimalFormat f = new DecimalFormat("##.00");
                    radiusL.setText(infinity==circleSum.getRadius()?"infinity":f.format(circleSum.getRadius()*scale));
                }
            },circleF);
            
            timeline.getKeyFrames().addAll(kf0,kf1,kf2);
            if(infinity>0)break;
        }
        
        
        timeline.play();
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
        valueAText.setText(a+"");
    }
    public void setValueR(int r)
    {
        valueAText.setText(r+"");
    }
}
