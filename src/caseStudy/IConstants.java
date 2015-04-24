package caseStudy;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public interface IConstants {
    //FRAME
    public static final double DIM_X=600;
    public static final double DIM_Y=600;
    
    //button constants
    public static final String MED_PLAY= "PLAY";
    public static final String MED_RESET="RESET";
    public static final String MED_DONE= "DONE";
    public static final String MED_HELP= "HELP";
    
    public static final String AN_CALC1="SERIES";
    public static final String AN_CALC2="BIKE PROFIT";
    public static final String AN_EM1="LENZ LAW";
    public static final String AN_EM2="RESISTOR IN ||";
    public static final String AN_WAVES1="SPRING";
    public static final String AN_WAVES2="PENDULUM";
    
    //ADD YOUR STUFF HERE, try to keep dome fomatting
    public static final int ZERO=0;
    public static final int TIME_MILLIS_CONVERSION=1000;
    public static String DECIMALFORMAT_2DEC="##.00";
    public static String DECIMALFORMAT_2DEC$="##.00$";
    //Pendulum
    public static final double GRAVACC = 9.80665;
    //
    
    //SPRING
    public static final double PI = Math.PI;
    public static final Font FONT_ARIAL_20=new Font("Arial",20);

    //series
    public static final int SERIES_MAXITERATIONS=100;
    public static final int SERIES_OPACITY_IN=0;
    public static final int SERIES_OPACITY_FN=1;
    public static final int SERIES_TIME_SUM_EX=500;
    public static final int SERIES_VALUECIRCLE_RMIN=20;
    public static final double SERIES_A_DEF=1;
    public static final double SERIES_R_DEF=2;
    public static final double SERIES_INFINITY_IN=-1;
    public static final double SERIES_SUM_RADIUS_IN=50;
    public static final double SERIES_SUM_X=500;
    public static final double SERIES_SUM_Y=100;
    public static final double SERIES_VALUE_LABEL_X=480;
    public static final double SERIES_VALUE_LABEL_Y=90;
    public static final double SERIES_SCALE_DEF=5;
    public static final double SERIES_VALUE_Y=100;
    public static final double SERIES_VALUET_Y=90;
    public static final double SERIES_VALUET_TY=-10;
    public static final double SERIES_VALUERT_Y=30;
    public static final double SERIES_VALUERT_X=50;
    public static final double SERIES_VALUEAT_X=50;
    public static final double SERIES_VALUECIRCLE_XI=200;
    public static final double SERIES_VALUECIRCLE_XF=500;
    public static final double SERIES_MAX_SUM_R=500;
    public static final double SERIES_INFINITE_RANGE[]={-1,1};
    
    public static final String SERIES_VALUET_COLOR="white";
    public static final String SERIES_LABEL_STYLE="circleLabel";
    public static final String SERIES_LABEL_FORMULA_T="Geometric series: AR^n";
    public static final String SERIES_LABEL_A_T="  set A ";
    public static final String SERIES_LABEL_R_T="  set R ";
    public static final String SERIES_TEXT_INFINITE="infinity";
    
    //BIKE
    public static final double BIKE_STARTCOST_DEF=700000;
    public static final double BIKE_STARTCOST_X=100;
    public static final double BIKE_BIKECOST_DEF=110;
    public static final double BIKE_BIKECOST_x=100;
    public static final double BIKE_BIKECOST_y=30;
    public static final double BIKE_CONSTANT=200;
    public static final double BIKE_MAXBIKES=70000;
    public static final double BIKE_SCALE_Y=50000;
    public static final double BIKE_GRAPH_X=100;
    public static final double BIKE_GRAPH_Y=200;
    public static final double BIKE_LABEL_AXISY_Y=220;
    public static final double BIKE_LABEL_AXISX_Y=180;
    public static final double BIKE_LABEL_AXISX_XOFFSET=20;
    public static final double BIKE_LABEL_MAXPROFIT_Y=55;
    
    public static final int STARTPRICE_IN=-1;
    public static final String BIKE_LABEL_INITIALCOST_T="  Initial costs ";
    public static final String BIKE_LABEL_BIKECOST_T="  Cost per bike ";
    public static final String BIKE_TEXT_PRICE="Price: ";
   
   //style
    public static final Paint PAINT_BLACK=Paint.valueOf("black");
    public static final Paint PAINT_RED=Paint.valueOf("red");
    
    
}
