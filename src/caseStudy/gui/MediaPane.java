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
import javafx.scene.layout.VBox;

/**
 *
 * @author cstuser
 */
public class MediaPane extends VBox{
    private CaseStudy caseStudy;
    private Button playBtn,doneBtn,resetBtn,helpBtn;
    public MediaPane(CaseStudy caseStudy)
    {
        this.setSpacing(15);
        this.setPrefSize(IConstants.DIM_X/2, IConstants.DIM_Y/2);
        this.caseStudy=caseStudy;
        playBtn=new Button(IConstants.MED_PLAY);
        playBtn.setOnAction(caseStudy.handler);
        playBtn.getStyleClass().add("mediaBtn");
        doneBtn=new Button(IConstants.MED_DONE);
        doneBtn.setOnAction(caseStudy.handler);
        doneBtn.getStyleClass().add("mediaBtn");
        resetBtn=new Button(IConstants.MED_RESET);
        resetBtn.setOnAction(caseStudy.handler);
        resetBtn.getStyleClass().add("mediaBtn");
        helpBtn=new Button(IConstants.MED_HELP);
        helpBtn.setOnAction(caseStudy.handler);
        helpBtn.getStyleClass().add("mediaBtn");
        
        
        this.getChildren().addAll(playBtn,doneBtn,resetBtn,helpBtn);
    }
}
