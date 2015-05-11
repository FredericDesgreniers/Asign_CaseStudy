package caseStudy;

import javafx.animation.Timeline;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public abstract class AnimationBase extends Pane implements IConstants{
    
    protected Timeline timeline = new Timeline();
    private String name;
    public AnimationBase(String name)
    {
        this.setPrefSize(DIM_X, DIM_Y_HALF);
        this.name=name;
    }
    public void start()
    {
        timeline.play();
    }
     public void done()
    {
        timeline.stop();
    }
    public void reset()
    {
        timeline.stop();
    }
    public Timeline getTimeline()
    {
        return timeline;
    }
    public String getName()
    {
        return name;
    }
    public String getHelp()
    {
        return DEFAULT_HELP;
    }
}
