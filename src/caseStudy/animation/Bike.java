/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
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
        startCostField.setLayoutX(100);
        bikeCostField=new TextField("110");
        bikeCostField.setLayoutY(30);
        bikeCostField.setLayoutX(100);
        setStaticGUI();

    }
    public void setStaticGUI()
    {
         Label scLabel=new Label("  Initial costs ");
        scLabel.setTextFill(Paint.valueOf("black"));
        Label bcLabel=new Label("  Cost per bike ");
        bcLabel.setLayoutY(30);
        bcLabel.setTextFill(Paint.valueOf("black"));
        getChildren().addAll(startCostField,bikeCostField,scLabel,bcLabel);
    }
    public void done()
    {
        timeline.stop();
        getChildren().clear();
        setStaticGUI();
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
        done();
        timeline=new Timeline();
        
        List<Line> profits=new ArrayList();
        boolean wasPos=false;
        int price=0;
        double lastY=0;
        double maxY=0;
        int scaleY=50000;
        boolean yFinish=false;
        int startPrice=-1;
        while(true)
        {
            double profit=getProfit(price);
            if(profit>=0)
            {
                if(startPrice<0)
                startPrice=price;
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
        Line axisXLine=new Line(profits.get(0).getStartX(),200,profits.get(0).getStartX(),200);
        Line axisYLine=new Line(profits.get(0).getStartX(),200,profits.get(0).getStartX(),200);
        Label axisYLabel=new Label("0");
        axisYLabel.setLayoutX(profits.get(0).getStartX());
        axisYLabel.setLayoutY(220);
        Label axisXLabel=new Label("0");
        axisXLabel.setLayoutX(profits.get(0).getStartX()+20);
        axisXLabel.setLayoutY(180);
        
        Label maxProfitLabel=new Label("");
        maxProfitLabel.setLayoutY(55);
        
        getChildren().addAll(profits);
        getChildren().addAll(axisXLine,axisYLine,axisYLabel,axisXLabel,maxProfitLabel);
        for(int i=0;i<profits.size();i++)
        {
            int currentPrice=i+startPrice;
            double currentProfit=(200-profits.get(i).getEndY())*scaleY;
            Line currentLine=profits.get(i);
            Line nextLine=(i+1<profits.size())?profits.get(i+1):null;
               
            currentLine.setVisible(false);
            KeyValue KVendX=new KeyValue(currentLine.endXProperty(),currentLine.getEndX());
            KeyValue KVendY=new KeyValue(currentLine.endYProperty(),currentLine.getEndY());
            KeyValue KVaxisXLabel=new KeyValue(axisXLabel.layoutXProperty(),currentLine.getEndX()+20);
            KeyValue KVaxisYline=null;
            KeyValue KVaxisYLabel=null;
            if(maxY==currentLine.getEndY() )
            {
                yFinish=true;
            }
            if(!yFinish)
            {
                KVaxisYline=new KeyValue(axisYLine.endYProperty(),currentLine.getEndY());
                KVaxisYLabel=new KeyValue(axisYLabel.layoutYProperty(),currentLine.getEndY()-20);
            }
            KeyValue lineX=new KeyValue(axisXLine.endXProperty(),currentLine.getEndX());
            currentLine.setEndX(currentLine.getStartX());
            currentLine.setEndY(currentLine.getStartY());
          
            KeyFrame graphFrame=new KeyFrame(Duration.millis(100*i),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    if(nextLine!=null)
                    nextLine.setVisible(true);
                }
                    
            },KVendX,KVendY);
            KeyFrame axisFrame=null;
            if(KVaxisYline!=null)
            {
                axisFrame=new KeyFrame(Duration.millis(100*i),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    DecimalFormat f = new DecimalFormat("##.00$");
                    String profitT=f.format(currentProfit);
                    axisYLabel.setText("Profit: "+profitT);
                    
                    f = new DecimalFormat("##.00$");
                    String priceT=f.format(currentPrice);
                    axisXLabel.setText("Price: "+priceT);
                    
                    maxProfitLabel.setText("Maximum profit of "+profitT+" at a price of "+priceT);
                    
                }
                    
            },lineX,KVaxisYline,KVaxisYLabel,KVaxisXLabel);
            }
            else 
                axisFrame=new KeyFrame(Duration.millis(100*i),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    DecimalFormat f = new DecimalFormat("Price: ##.00$");
                    axisXLabel.setText(f.format(currentPrice));
                }
                    
            },lineX,KVaxisXLabel);
            
                
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
