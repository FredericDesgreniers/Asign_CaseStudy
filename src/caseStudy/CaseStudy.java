package caseStudy;

import caseStudy.animation.BlankAnimation;
import caseStudy.gui.MediaPane;
import caseStudy.gui.MenuPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import caseStudy.CaseStudy;

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
        this.stage=primaryStage;
        setCurrentAnimation(null);
       stage.setResizable(false);
        stage.show();
        
    }
    public void setCurrentAnimation(AnimationBase ab){
        currentAnimation=ab;
        GridPane grid=new GridPane();
       
        mediaButtons=new MediaPane(this);
        mediaButtons.getStyleClass().add("pane");
        menuPane=new MenuPane(this);

        final Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("EXIT");
        exitItem.setOnAction(handler);
        fileMenu.getItems().add(exitItem);
        
        final Menu calcMenu = new Menu("Cal");
        MenuItem seriesItem = new MenuItem(IConstants.AN_CALC1);
        seriesItem.setOnAction(handler);
        MenuItem bikeItem = new MenuItem(IConstants.AN_CALC2);
        bikeItem.setOnAction(handler);
        calcMenu.getItems().addAll(seriesItem, bikeItem);
        
        final Menu wavesMenu = new Menu("Waves");
        MenuItem springItem = new MenuItem(IConstants.AN_WAVES1);
        springItem.setOnAction(handler);
        MenuItem pendulumItem = new MenuItem(IConstants.AN_WAVES2);
        pendulumItem.setOnAction(handler);
        wavesMenu.getItems().addAll(springItem, pendulumItem);
        
        final Menu emMenu = new Menu("E&M");
        MenuItem lenzItem = new MenuItem(IConstants.AN_EM1);
        lenzItem.setOnAction(handler);
        MenuItem resistorItem = new MenuItem(IConstants.AN_EM2);
        resistorItem.setOnAction(handler);
        emMenu.getItems().addAll(lenzItem, resistorItem);
                
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, calcMenu, wavesMenu, emMenu);
        grid.add(menuBar, 0, 0);
        
        grid.add(ab==null?new BlankAnimation(""):ab, 0, 1);
        grid.add(menuPane, 0, 2);
        grid.add(mediaButtons, 1, 2);
        scene=new Scene(grid,IConstants.DIM_X,IConstants.DIM_Y);
        scene.getStylesheets().add(this.getClass().getResource("/res/style1.css").toString());
        grid.getStyleClass().add("grid");
        stage.setScene(scene);
        stage.setTitle(ab==null?"Animation Home":ab.getName());

    }
}