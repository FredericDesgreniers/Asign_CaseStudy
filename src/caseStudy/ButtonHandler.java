package caseStudy;

import caseStudy.animation.Bike;
import caseStudy.animation.LenzLaw;
import caseStudy.animation.Pendulum;
import caseStudy.animation.ResistorsInParallel;
import caseStudy.animation.Series;
import caseStudy.animation.Spring;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javax.swing.JOptionPane;

public class ButtonHandler implements EventHandler,IConstants{
    
    private CaseStudy caseStudy;
    
    public ButtonHandler(CaseStudy caseStudy){
        this.caseStudy = caseStudy;
    }
    
    @Override
    public void handle(Event event) {
        String name = "";
        if(event.getSource() instanceof Button){
            Button b=(Button)event.getSource();
            name = b.getText();
        }
      
        else if(event.getSource() instanceof MenuItem){
            MenuItem m = (MenuItem)event.getSource();
            name = m.getText();
        }
      
        System.out.println(name);
        switch(name)
        {
            case MED_PLAY:caseStudy.getCurrentAnimation().start();break;
            case MED_RESET:caseStudy.getCurrentAnimation().reset();break;
            case MED_DONE:caseStudy.getCurrentAnimation().done();break;
            case MED_HELP: JOptionPane.showMessageDialog(null,caseStudy.getCurrentAnimation().getHelp(),HELP_STR,JOptionPane.INFORMATION_MESSAGE);break;
                  
            case AN_CALC1:caseStudy.setCurrentAnimation(new Series(AN_CALC1));break;
            case AN_CALC2:caseStudy.setCurrentAnimation(new Bike(AN_CALC2));break;
            case AN_WAVES1:caseStudy.setCurrentAnimation(new Spring(AN_WAVES1));break;
            case AN_WAVES2:caseStudy.setCurrentAnimation(new Pendulum(AN_WAVES2));break;
            case AN_EM1:caseStudy.setCurrentAnimation(new LenzLaw(AN_EM1));break;
            case AN_EM2:caseStudy.setCurrentAnimation(new ResistorsInParallel(AN_EM2));break;
            case EXIT_STR:System.exit(ZERO);break;
        }
    } 
}
