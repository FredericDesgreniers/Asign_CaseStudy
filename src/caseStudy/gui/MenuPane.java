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

/**
 *
 * @author cstuser
 */
public class MenuPane extends FlowPane{
    private CaseStudy caseStudy;
    private Button calc1Btn,calc2Btn,waves1Btn,waves2Btn,em1Btn,em2Btn;
    
    public MenuPane(CaseStudy caseStudy)
    {
        this.caseStudy=caseStudy;
        calc1Btn=new Button(IConstants.AN_CALC1);
        calc1Btn.setOnAction(caseStudy.handler);
        calc2Btn=new Button(IConstants.AN_CALC2);
        calc2Btn.setOnAction(caseStudy.handler);
        waves1Btn=new Button(IConstants.AN_WAVES1);
        waves1Btn.setOnAction(caseStudy.handler);
        waves2Btn=new Button(IConstants.AN_WAVES2);
        waves2Btn.setOnAction(caseStudy.handler);
        em1Btn=new Button(IConstants.AN_EM1);
        em1Btn.setOnAction(caseStudy.handler);
        em2Btn=new Button(IConstants.AN_EM2);
        em2Btn.setOnAction(caseStudy.handler);
        
        this.getChildren().addAll(calc1Btn,calc2Btn,waves1Btn,waves2Btn,em1Btn,em2Btn);
        
    }
    
}
