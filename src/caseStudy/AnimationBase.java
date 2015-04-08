package caseStudy;

import javafx.animation.Timeline;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public abstract class AnimationBase extends Pane{
    
    public Timeline timeline = new Timeline();
    
    public AnimationBase()
    {
        this.setPrefSize(IConstants.DIM_X, IConstants.DIM_Y/2);
    }
    public void start()
    {
        timeline.play();
    }
    public void reset()
    {
        timeline.stop();
    }
}
