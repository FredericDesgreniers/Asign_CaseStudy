/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caseStudy.gui;

import caseStudy.CaseStudy;
import caseStudy.IConstants;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author cstuser
 */
public class MenuPane extends VBox{
    private CaseStudy caseStudy;
    private Button[] buttons;
    
    public MenuPane(CaseStudy caseStudy)
    {
        this.getStyleClass().add("menuPane");
        this.setPrefSize(IConstants.DIM_X/2, IConstants.DIM_Y/2);
        this.setMaxWidth(IConstants.DIM_X/2);
        this.caseStudy=caseStudy;
        buttons=new Button[6];
        buttons[0]=new Button(IConstants.AN_CALC1);
        buttons[1]=new Button(IConstants.AN_CALC2);
        buttons[2]=new Button(IConstants.AN_WAVES1);
        buttons[3]=new Button(IConstants.AN_WAVES2);
        buttons[4]=new Button(IConstants.AN_EM1);
        buttons[5]=new Button(IConstants.AN_EM2);
        
        for(Button b:buttons)
        {
              b.setOnAction(caseStudy.handler);
              b.getStyleClass().add("menuBtn");
              b.setPrefWidth(IConstants.DIM_X/2);
              if(caseStudy.currentAnimation!=null)
              if(b.getText()==caseStudy.currentAnimation.getName())
              {
                  b.getStyleClass().add("menuBtnActive");
              }
        }
        
        this.getChildren().addAll(buttons);
        
    }
    
}
