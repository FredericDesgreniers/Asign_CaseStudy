/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import java.awt.Color;
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
    public void setStaticGUI()
    {
        Label formula=new Label("Geometric series: AR^n");
        formula.setLayoutX(250);
        formula.setLayoutY(15);
        formula.setFont(new Font("Arial",20));
        Label aLabel=new Label("  set A ");
        aLabel.setTextFill(Paint.valueOf("black"));
        Label rLabel=new Label("  set R ");
        rLabel.setLayoutY(30);
        rLabel.setTextFill(Paint.valueOf("black"));
        
        getChildren().addAll(valueAText,valueRText,rLabel,aLabel,circleSum,formula);
    }
    public void done()
    {
        timeline.stop();
        getChildren().clear();
        setStaticGUI();
    }
    public void start()
    {
        done();
        timeline=new Timeline();
        double a=getValueA();
        double r=getValueR();
        radius=0;
        infinity=IConstants.SERIES_INFINITY_IN;
        double scale=IConstants.SERIES_SCALE_DEF;
        
        Label radiusL=new Label(String.valueOf(radius));
        radiusL.getStyleClass().add(IConstants.SERIES_LABEL_STYLE);
        radiusL.setLayoutX(IConstants.SERIES_VALUE_LABEL_X);
        radiusL.setLayoutY(IConstants.SERIES_VALUE_LABEL_Y);
        getChildren().add(radiusL);
        int iMax=0;
        for(int i=0;i<IConstants.SERIES_MAXITERATIONS;i++)
        {
            
            double value=(a*Math.pow(r, i));

            System.out.println(value);
            Circle c=new Circle();
            c.setFill(Paint.valueOf("red"));
            c.setOpacity(IConstants.SERIES_OPACITY_IN);
            c.setRadius((value/scale)>20?value/scale:20);
            c.setLayoutY(IConstants.SERIES_VALUE_Y);
            DecimalFormat f = new DecimalFormat("##");
            Label t=new Label(f.format(value));
            
            t.setLayoutY(IConstants.SERIES_VALUET_Y);
            t.setTranslateX(IConstants.SERIES_VALUET_TY);
            t.setTextFill(Paint.valueOf(IConstants.SERIES_VALUET_COLOR));
            t.setVisible(false);
            this.getChildren().addAll(c,t);
            KeyValue valuescXI=new KeyValue(c.centerXProperty(),200);
            KeyValue valuescXF=new KeyValue(c.centerXProperty(),500);
             KeyValue valuescOI=new KeyValue(c.opacityProperty(),0);
            KeyValue valuescOF=new KeyValue(c.opacityProperty(),1);
            
            KeyValue valuetXI=new KeyValue(t.layoutXProperty(),200);
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
            if(infinity>0)
            {
                iMax=i;
                break;
            }
            if(Double.parseDouble(f.format(value))<=0)
            {
                iMax=i;
                break;
            }
        }
        KeyFrame kf=new KeyFrame(Duration.millis((iMax+1)*1000+1000),new KeyValue(circleSum.radiusProperty(),80));
        timeline.getKeyFrames().add(kf);
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
