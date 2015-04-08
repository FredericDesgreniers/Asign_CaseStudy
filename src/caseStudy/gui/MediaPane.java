/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caseStudy.gui;

import caseStudy.CaseStudy;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author cstuser
 */
public class MediaPane extends StackPane{
    private CaseStudy caseStudy;
    private Button playBtn,doneBtn,resetBtn,helpBtn;
    public MediaPane(CaseStudy caseStudy)
    {
        this.caseStudy=caseStudy;
        playBtn=new Button("Play");
        doneBtn=new Button("Done");
        resetBtn=new Button("Reset");
        helpBtn=new Button("Help");
        this.getChildren().addAll(playBtn,doneBtn,resetBtn,helpBtn);
    }
}
