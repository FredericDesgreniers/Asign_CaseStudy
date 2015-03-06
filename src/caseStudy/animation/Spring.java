package caseStudy.animation; 

import caseStudy.AnimationBase;
import caseStudy.IConstants;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public final class Spring extends AnimationBase{
    
    double period;
    double mass;
    double springConstant;
    double amplitude;
    
    public Spring(){
        
        TextField[] fields = new TextField[3];
        for(int i = 0 ; i <= 3 ; i++){
            fields[i] = new TextField("3.00");
        //0 -> massField
        //1 -> constantield
        //2 -> amplitudeField
        }
        
        setMass(Double.parseDouble(fields[0].getText()));
        setSpringConstant(Double.parseDouble(fields[1].getText()));
        setAmplitude(Double.parseDouble(fields[2].getText()));
        period = calculatePeriod();
        
        //Image springGraphic = new Image();
    }
    
    public double calculatePeriod(){
        return 2*IConstants.PI*Math.sqrt(getMass()/getSpringConstant());
    }
    
    public void calculateKeyfames(){
        
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setSpringConstant(double springConstant) {
        this.springConstant = springConstant;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getPeriod() {
        return period;
    }

    public double getMass() {
        return mass;
    }

    public double getSpringConstant() {
        return springConstant;
    }

    public double getAmplitude() {
        return amplitude;
    }
    
    
}
