/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseStudy.animation;

import caseStudy.AnimationBase;
import caseStudy.IConstants;
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
public class Bike extends AnimationBase implements IConstants{
    int startCost=ZERO;
    int bikeCost=ZERO;
    TextField startCostField;
    TextField bikeCostField;
    TextField priceField;
    Line[] lines; //that moment when you don't think about javafx chart when making your plan and now you're stuck with a line array
    public Bike(String name) {
        super(name);

        setupGui();
    }
    
    public void setupGui()
    {
        startCostField=new TextField(String.valueOf(BIKE_STARTCOST_DEF));
        startCostField.setLayoutX(BIKE_STARTCOST_X);
        bikeCostField=new TextField(String.valueOf(BIKE_BIKECOST_DEF));
        bikeCostField.setLayoutY(BIKE_BIKECOST_y);
        bikeCostField.setLayoutX(BIKE_BIKECOST_x);
        setStaticGUI();
    }
    public String getHelp()
    {
        return "This animation will draw a graph of the profit for each price and output the best price once ofund. You get to specify the initial startup cost and the price per bike for manifacturing";
    }
    public void reset()
    {
        done();
        setupGui();
    }
    public void setStaticGUI()
    {
         Label scLabel=new Label(BIKE_LABEL_INITIALCOST_T);
        scLabel.setTextFill(PAINT_BLACK);
        Label bcLabel=new Label(BIKE_LABEL_BIKECOST_T);
        bcLabel.setLayoutY(BIKE_BIKECOST_y);
        bcLabel.setTextFill(PAINT_BLACK);
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
        return BIKE_MAXBIKES-BIKE_CONSTANT*price;
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
        int price=ZERO;
        double lastY=ZERO;
        double maxY=ZERO;
        double scaleY=BIKE_SCALE_Y;
        boolean yFinish=false;
        int startPrice=STARTPRICE_IN;
        while(true){
            double profit=getProfit(price);
            if(profit>=ZERO)
            {
                if(startPrice<ZERO)
                startPrice=price;
                wasPos=true;
                Line line=new Line(BIKE_GRAPH_X+price,BIKE_GRAPH_Y-lastY,BIKE_GRAPH_X+price+1,BIKE_GRAPH_Y-profit/scaleY);
                line.getStyleClass().add("graphLine");
                profits.add(line);
                if(BIKE_GRAPH_Y-lastY>BIKE_GRAPH_Y-profit/scaleY)
                    maxY=BIKE_GRAPH_Y-lastY;
                lastY=profit/scaleY;
            }else if(wasPos==true){
                break;
            }
            price++;
        }
        Line axisXLine=new Line(profits.get(ZERO).getStartX(),BIKE_GRAPH_Y,profits.get(ZERO).getStartX(),BIKE_GRAPH_Y);
        axisXLine.getStyleClass().add("graphAxis");
        Line axisYLine=new Line(profits.get(ZERO).getStartX(),BIKE_GRAPH_Y,profits.get(ZERO).getStartX(),BIKE_GRAPH_Y);
        axisYLine.getStyleClass().add("graphAxis");
        Label axisYLabel=new Label(String.valueOf(ZERO));
        axisYLabel.setLayoutX(profits.get(ZERO).getStartX());
        axisYLabel.setLayoutY(BIKE_LABEL_AXISY_Y);
        Label axisXLabel=new Label(String.valueOf(ZERO));
        axisXLabel.setLayoutX(profits.get(ZERO).getStartX()+BIKE_LABEL_AXISX_XOFFSET);
        axisXLabel.setLayoutY(BIKE_LABEL_AXISX_Y);
        
        Label maxProfitLabel=new Label();
        maxProfitLabel.setLayoutY(BIKE_LABEL_MAXPROFIT_Y);
        
        getChildren().addAll(profits);
        getChildren().addAll(axisXLine,axisYLine,axisYLabel,axisXLabel,maxProfitLabel);
        for(int i=ZERO;i<profits.size();i++)
        {
            int currentPrice=i+startPrice;
            double currentProfit=(BIKE_GRAPH_Y-profits.get(i).getEndY())*scaleY;
            Line currentLine=profits.get(i);
            Line nextLine=(i+1<profits.size())?profits.get(i+1):null;
               
            currentLine.setVisible(false);
            KeyValue KVendX=new KeyValue(currentLine.endXProperty(),currentLine.getEndX());
            KeyValue KVendY=new KeyValue(currentLine.endYProperty(),currentLine.getEndY());
            KeyValue KVaxisXLabel=new KeyValue(axisXLabel.layoutXProperty(),currentLine.getEndX()+BIKE_LABEL_AXISX_XOFFSET);
            KeyValue KVaxisYline=null;
            KeyValue KVaxisYLabel=null;
            if(maxY==currentLine.getEndY() )
            {
                yFinish=true;
            }
            if(!yFinish)
            {
                KVaxisYline=new KeyValue(axisYLine.endYProperty(),currentLine.getEndY());
                KVaxisYLabel=new KeyValue(axisYLabel.layoutYProperty(),currentLine.getEndY()-BIKE_LABEL_AXISX_XOFFSET);
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
                    DecimalFormat f = new DecimalFormat(DECIMALFORMAT_2DEC$);
                    String profitT=f.format(currentProfit);
                    axisYLabel.setText("Profit: "+profitT);
                    
                    f = new DecimalFormat(DECIMALFORMAT_2DEC$);
                    String priceT=f.format(currentPrice);
                    axisXLabel.setText(BIKE_TEXT_PRICE+priceT);
                    
                    maxProfitLabel.setText("Maximum profit of "+profitT+" at a price of "+priceT);
                    
                }
                    
            },lineX,KVaxisYline,KVaxisYLabel,KVaxisXLabel);
            }
            else 
                axisFrame=new KeyFrame(Duration.millis(100*i),new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    DecimalFormat f = new DecimalFormat(BIKE_TEXT_PRICE+(DECIMALFORMAT_2DEC$));
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
