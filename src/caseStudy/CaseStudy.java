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
    public Scene scene;
    public Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {  
        handler=new ButtonHandler(this);
        currentAnimation=new Spring();

        this.stage=primaryStage;
        setCurrentAnimation(null);
        
    }
    public void setCurrentAnimation(AnimationBase ab)
    {
        GridPane grid=new GridPane();
        mediaButtons=new MediaPane(this);
        menuPane=new MenuPane(this);
        grid.add(mediaButtons, 1, 1);
        grid.add(menuPane, 0, 1);
        if(ab!=null)
        grid.add(ab, 0, 0);
        scene=new Scene(grid,IConstants.DIM_X,IConstants.DIM_Y);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}