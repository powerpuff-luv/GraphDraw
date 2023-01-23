package graphics;

import math.Function;
import math.ParametricCurve;

import java.awt.*;

public class ParametricFunctionPainter implements Painter{

    private final int numOfPoints = 1000;
    private double tLowerBound = -100;
    private double tUpperBound = 100;
    private double t;
    private double tIncrement;
    private double epsilon;
    private Color clr;
    private Converter cnv;
    private ParametricCurve f;
    public ParametricFunctionPainter(Converter cnv, Color clr, ParametricCurve f, double tmin, double tmax){
        this.f = f;
        this.cnv = cnv;
        this.clr = clr;
        tLowerBound = tmin;
        tUpperBound = tmax;
    }

    public void paint(Graphics g, int width, int height){
        g.setColor(clr);
        t = tLowerBound;
        tIncrement = (tUpperBound - tLowerBound) / numOfPoints;
        epsilon = tIncrement / 2;
        double xPrev = f.invokeX(t);
        double yPrev = f.invokeY(t);
        while (tUpperBound - t > epsilon){
            t += tIncrement;
            double x = f.invokeX(t);
            double y = f.invokeY(t);
            g.drawLine(cnv.xCrt2Scr(xPrev), cnv.yCrt2Scr(yPrev), cnv.xCrt2Scr(x), cnv.yCrt2Scr(y));
            xPrev = x;
            yPrev = y;
        }
    }

    public void setColor(Color clr) {
        this.clr = clr;
    }

    public void setTEdges(Double tmin, Double tmax) {
        tLowerBound = tmin;
        tUpperBound = tmax;
    }
    public void setF(ParametricCurve f) {
        this.f = f;
    }
}

