/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caseStudy.gui;

import caseStudy.CaseStudy;
import caseStudy.IConstants;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author cstuser
 */
public class MenuPane extends VBox implements IConstants{
    private CaseStudy caseStudy;
    private Button[] buttons;
    
    public MenuPane(CaseStudy caseStudy)
    {
        this.getStyleClass().add(STYLE_MENUPANE);
        this.setPrefSize(DIM_X/HALF, DIM_Y/HALF);
        this.setMaxWidth(DIM_X/HALF);
        this.caseStudy=caseStudy;
        buttons=new Button[6];
        buttons[0]=new Button(AN_CALC1);
        buttons[1]=new Button(AN_CALC2);
        buttons[2]=new Button(AN_WAVES1);
        buttons[3]=new Button(AN_WAVES2);
        buttons[4]=new Button(AN_EM1);
        buttons[5]=new Button(AN_EM2);
        
        for(Button b:buttons)
        {
              b.setOnAction(caseStudy.getEventHandler());
              b.getStyleClass().add(STYLE_MENUBTN);
              b.setPrefWidth(DIM_X/HALF);
              if(caseStudy.getCurrentAnimation()!=null)
              if(b.getText().equals(caseStudy.getCurrentAnimation().getName()))
              {
                  b.getStyleClass().add(STYLE_MENUACTIVEBTN);
              }
        }
        
        this.getChildren().addAll(buttons);
        
    }
    
}
