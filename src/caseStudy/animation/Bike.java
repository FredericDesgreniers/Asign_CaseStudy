/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 *
 * @author Fred
 */
public class Bike extends AnimationBase{
    int startCost=0;
    int bikeCost=0;
    TextField startCostField;
    TextField bikeCostField;
    TextField priceField;
    Line[] lines;
    public Bike(String name) {
        super(name);
        startCostField=new TextField("700000");
        bikeCostField=new TextField("110");
        bikeCostField.setLayoutY(100);
        getChildren().addAll(startCostField,bikeCostField);
    }
    public void done()
    {
        timeline.stop();
        getChildren().clear();
        getChildren().addAll(startCostField,bikeCostField);
    }
    public double getUnitSales(double price)
    {
        return 70000-200*price;
    }
    public double getCosts(double price)
    {
        return getStartCost()+getBikeCost()*(getUnitSales(price));
    }
    public double getProfit(double price)
    {
        return getUnitSales(price)*price-getCosts(price);
    }
    public void start()
    {
        timeline=new Timeline();

        List<Line> profits=new ArrayList();
        boolean wasPos=false;
        int price=0;
        double lastY=0;
        double maxY=0;
        int scaleY=50000;
        boolean yFinish=false;
        while(true)
        {
            double profit=getProfit(price);
            if(profit>=0)
            {
                wasPos=true;
                Line line=new Line(100+price,200-lastY,100+price+1,200-profit/scaleY);
                profits.add(line);
                if(200-lastY>200-profit/scaleY)
                    maxY=200-lastY;
                lastY=profit/scaleY;
            }else if(wasPos==true)
            {
                break;
            }
            
            price++;
        }
        Line xLine=new Line(profits.get(0).getStartX(),200,profits.get(0).getStartX(),200);
        Line yLine=new Line(profits.get(0).getStartX(),200,profits.get(0).getStartX(),200);
        
        getChildren().addAll(profits);
        getChildren().addAll(xLine,yLine);
        for(int i=0;i<profits.size();i++)
        {
            Line l=profits.get(i);
            Line l2=(i+1<profits.size())?profits.get(i+1):null;
               
            l.setVisible(false);
            KeyValue endX=new KeyValue(l.endXProperty(),l.getEndX());
            KeyValue endY=new KeyValue(l.endYProperty(),l.getEndY());
            
            KeyValue lineY=null;
            if(maxY==l.getEndY() )
            {
                yFinish=true;
            }
            if(!yFinish)lineY=new KeyValue(yLine.endYProperty(),l.getEndY());
            KeyValue lineX=new KeyValue(xLine.endXProperty(),l.getEndX());
            l.setEndX(l.getStartX());
            l.setEndY(l.getStartY());
            KeyFrame graphFrame=new KeyFrame(Duration.millis(100*i),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    if(l2!=null)
                    l2.setVisible(true);
                }
                    
            },endX,endY);
            KeyFrame axisFrame=null;
            if(lineY!=null)
                axisFrame=new KeyFrame(Duration.millis(100*i),lineX,lineY);
            else 
                axisFrame=new KeyFrame(Duration.millis(100*i),lineX);
            
                
            timeline.getKeyFrames().addAll(graphFrame,axisFrame);
        }
        
        
        timeline.play();
    }
    
    
    public double getStartCost()
    {
        return Double.parseDouble(startCostField.getText());
    }
    public double getBikeCost()
    {
        return Double.parseDouble(bikeCostField.getText());
    }
    public double getPrice()
    {
        return Double.parseDouble(priceField.getText());
    }
    
    
}
