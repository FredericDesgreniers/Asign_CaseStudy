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
public class MediaPane extends VBox implements IConstants{
    private CaseStudy caseStudy;
    private Button playBtn,doneBtn,resetBtn,helpBtn;
    public MediaPane(CaseStudy caseStudy)
    {
        this.setSpacing(15);
        this.setPrefSize(IConstants.DIM_X/HALF, IConstants.DIM_Y/HALF);
        this.caseStudy=caseStudy;
        playBtn=new Button(IConstants.MED_PLAY);
        doneBtn=new Button(IConstants.MED_DONE);
        resetBtn=new Button(IConstants.MED_RESET);
        helpBtn=new Button(IConstants.MED_HELP);
        for(Button b :  new Button[]{playBtn,doneBtn,resetBtn,helpBtn})
        {
            b.setOnAction(caseStudy.getEventHandler());
            b.getStyleClass().add(STYLE_MEDIABTN);
            
        }
        
        this.getChildren().addAll(playBtn,doneBtn,resetBtn,helpBtn);
    }
}
