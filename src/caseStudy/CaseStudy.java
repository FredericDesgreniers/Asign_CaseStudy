package caseStudy;

import caseStudy.gui.MediaPane;
import javafx.application.Application;
import javafx.stage.Stage;

public class CaseStudy extends Application{
    private MediaPane mediaButtons;
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.print("potat");
        
        mediaButtons=new MediaPane(this);
    }
}