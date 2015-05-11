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

public class CaseStudy extends Application implements IConstants{
    private MediaPane mediaButtons;
    private MenuPane menuPane;
    private AnimationBase currentAnimation;
    private EventHandler handler;
    private Scene scene;
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {  
        handler=new ButtonHandler(this);
        this.stage=primaryStage;
        setCurrentAnimation(null);
       stage.setResizable(false);
        stage.show();
        
    }
    public AnimationBase getCurrentAnimation()
    {
        return currentAnimation;
    }
    public EventHandler getEventHandler()
    {
        return handler;
    }
    public void setCurrentAnimation(AnimationBase ab){
        currentAnimation=ab;
        GridPane grid=new GridPane();
       
        mediaButtons=new MediaPane(this);
        mediaButtons.getStyleClass().add(STYLE_PANE);
        menuPane=new MenuPane(this);

        final Menu fileMenu = new Menu(FILE_STR);
        MenuItem exitItem = new MenuItem(EXIT_STR);
        exitItem.setOnAction(handler);
        fileMenu.getItems().add(exitItem);
        
        final Menu calcMenu = new Menu(AN_CALC);
        MenuItem seriesItem = new MenuItem(AN_CALC1);
        seriesItem.setOnAction(handler);
        MenuItem bikeItem = new MenuItem(AN_CALC2);
        bikeItem.setOnAction(handler);
        calcMenu.getItems().addAll(seriesItem, bikeItem);
        
        final Menu wavesMenu = new Menu(AN_WAVES);
        MenuItem springItem = new MenuItem(AN_WAVES1);
        springItem.setOnAction(handler);
        MenuItem pendulumItem = new MenuItem(AN_WAVES2);
        pendulumItem.setOnAction(handler);
        wavesMenu.getItems().addAll(springItem, pendulumItem);
        
        final Menu emMenu = new Menu(AN_EM);
        MenuItem lenzItem = new MenuItem(AN_EM1);
        lenzItem.setOnAction(handler);
        MenuItem resistorItem = new MenuItem(AN_EM2);
        resistorItem.setOnAction(handler);
        emMenu.getItems().addAll(lenzItem, resistorItem);
                
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, calcMenu, wavesMenu, emMenu);
        grid.add(menuBar, 0, 0);
        
        grid.add(ab==null?new BlankAnimation(""):ab, 0, 1);
        grid.add(menuPane, 0, 2);
        grid.add(mediaButtons, 1, 2);
        scene=new Scene(grid,DIM_X,DIM_Y);
        scene.getStylesheets().add(this.getClass().getResource(STYLE1_PATH).toString());
        grid.getStyleClass().add(STYLE_GRID);
        stage.setScene(scene);
        stage.setTitle(ab==null?TITLE_DEFAULT:ab.getName());

    }
}