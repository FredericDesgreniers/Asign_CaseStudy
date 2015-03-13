/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caseStudy.gui;

import caseStudy.CaseStudy;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author cstuser
 */
public class MenuPane extends Pane{
    private CaseStudy caseStudy;
    private Button calc1Btn,calc2Btn,waves1Btn,waves2Btn,em1Btn,em2Btn;
    
    public MenuPane(CaseStudy caseStudy)
    {
        this.caseStudy=caseStudy;
        calc1Btn=new Button("Calculus 1");
        calc2Btn=new Button("Calculus 2");
        waves1Btn=new Button("Waves 1");
        waves2Btn=new Button("Waves 2");
        em1Btn=new Button("Em 1");
        em1Btn=new Button("Em 2");
        
        
    }
    
}
