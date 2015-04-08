package caseStudy;

import caseStudy.gui.MediaPane;
import caseStudy.gui.MenuPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CaseStudy extends Application{
    private MediaPane mediaButtons;
    private MenuPane menuPane;
    @Override
    public void start(Stage primaryStage) throws Exception {        
        mediaButtons=new MediaPane(this);
        menuPane=new MenuPane(this);
        StackPane root=new StackPane();
        
        GridPane grid=new GridPane()
        {
            
        };
        grid.add(mediaButtons, 0, 0);
        grid.add(menuPane, 0, 1);
        root.getChildren().add(grid);
        
        Scene scene=new Scene(root,300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}