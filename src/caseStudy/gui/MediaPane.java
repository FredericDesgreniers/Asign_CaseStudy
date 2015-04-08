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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author cstuser
 */
public class MediaPane extends FlowPane{
    private CaseStudy caseStudy;
    private Button playBtn,doneBtn,resetBtn,helpBtn;
    public MediaPane(CaseStudy caseStudy)
    {
        this.setPrefSize(IConstants.DIM_X/2, IConstants.DIM_Y/2);
        this.caseStudy=caseStudy;
        playBtn=new Button("Play");
        playBtn.setOnAction(caseStudy.handler);
        doneBtn=new Button("Done");
        doneBtn.setOnAction(caseStudy.handler);
        resetBtn=new Button("Reset");
        resetBtn.setOnAction(caseStudy.handler);
        helpBtn=new Button("Help");
        helpBtn.setOnAction(caseStudy.handler);
        
        
        this.getChildren().addAll(playBtn,doneBtn,resetBtn,helpBtn);
    }
}
