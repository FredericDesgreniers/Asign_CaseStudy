package caseStudy;

import caseStudy.animation.Spring;
import caseStudy.gui.MediaPane;
import caseStudy.gui.MenuPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CaseStudy extends Application{
    private MediaPane mediaButtons;
    private MenuPane menuPane;
    public AnimationBase currentAnimation;
    public EventHandler handler;
    @Override
    public void start(Stage primaryStage) throws Exception {  
        handler=new ButtonHandler(this);
        mediaButtons=new MediaPane(this);
        menuPane=new MenuPane(this);
        currentAnimation=new Spring();
        
        StackPane root=new StackPane();
        
        GridPane grid=new GridPane()
        {
            
        };
        grid.add(mediaButtons, 1, 1);
        grid.add(menuPane, 0, 1);
        grid.add(currentAnimation, 0, 0);
        root.getChildren().add(grid);
        
        Scene scene=new Scene(root,IConstants.DIM_X,IConstants.DIM_Y);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
}