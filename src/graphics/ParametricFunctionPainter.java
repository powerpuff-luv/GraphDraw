package graphics;

import java.awt.*;

public class ParametricFunctionPainter implements Painter{

    private final int numOfPoints = 1000;
    private final double tLowerBound = -100;
    private final double tUpperBound = 100;
    private double t;
    private double tIncrement;
    private double epsilon;
    private Color clr = Color.red;
    private Converter cnv;
    public ParametricFunctionPainter(Converter cnv){
        this.cnv = cnv;
    }

    public void paint(Graphics g, int width, int height){
        g.setColor(clr);
        t = tLowerBound;
        tIncrement = (tUpperBound - tLowerBound) / numOfPoints;
        epsilon = tIncrement / 2;
        double xPrev = -2 * Math.cos(t) + 3 * Math.cos(Math.abs(2 * t / 3));
        double yPrev = -2 * Math.sin(t) + 3 * Math.sin(Math.abs(2 * t / 3));
        while (tUpperBound - t > epsilon){
            t += tIncrement;
            double x = -2 * Math.cos(t) + 3 * Math.cos(Math.abs(2 * t / 3));
            double y = -2 * Math.sin(t) + 3 * Math.sin(Math.abs(2 * t / 3));
            g.drawLine(cnv.xCrt2Scr(xPrev), cnv.yCrt2Scr(yPrev), cnv.xCrt2Scr(x), cnv.yCrt2Scr(y));
            xPrev = x;
            yPrev = y;
        }
    }
}

