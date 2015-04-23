/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.JOptionPane;

/**
 *
 * @author Fred
 */
public class ButtonHandler implements EventHandler{
    private CaseStudy caseStudy;
    public ButtonHandler(CaseStudy caseStudy)
    {
        this.caseStudy=caseStudy;
    }
    @Override
    public void handle(Event event) {
      if(event.getSource() instanceof Button)
      {
          Button b=(Button)event.getSource();
          System.out.println(b.getText());
          switch(b.getText())
          {
              case IConstants.MED_PLAY:caseStudy.currentAnimation.start();break;
              case IConstants.MED_RESET:caseStudy.currentAnimation.reset();break;
              case IConstants.MED_DONE:caseStudy.currentAnimation.done();break;
              case IConstants.MED_HELP: JOptionPane.showMessageDialog(null,caseStudy.currentAnimation.getHelp(),"Help",JOptionPane.INFORMATION_MESSAGE);break;
                  
              case IConstants.AN_CALC1:caseStudy.setCurrentAnimation(new Series(IConstants.AN_CALC1));break;
              case IConstants.AN_CALC2:caseStudy.setCurrentAnimation(new Bike(IConstants.AN_CALC2));break;
              case IConstants.AN_WAVES1:caseStudy.setCurrentAnimation(new Spring(IConstants.AN_WAVES1));break;
              case IConstants.AN_WAVES2:caseStudy.setCurrentAnimation(new Pendulum(IConstants.AN_WAVES2));break;
              case IConstants.AN_EM1:caseStudy.setCurrentAnimation(new LenzLaw(IConstants.AN_EM1));break;
              case IConstants.AN_EM2:caseStudy.setCurrentAnimation(new ResistorsInParallel(IConstants.AN_EM2));break;
          }
          
      }
    }
    
}
