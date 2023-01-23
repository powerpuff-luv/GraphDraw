package graphics;

import java.awt.*;

public class FunctionPainter implements Painter {
    private Color clr;
    private Converter cnv;

    public FunctionPainter(Converter cnv) {
        this.cnv = cnv;
    }

    public void paint(Graphics g, int width, int height) {
        g.setColor(clr);
        for (int i = 0; i < width - 1; i++) {
            double x1Crt = cnv.xScr2Crt(i);
            double y1Crt = Math.sin(x1Crt) - x1Crt;
            double x2Crt = cnv.xScr2Crt(i + 1);
            double y2Crt = Math.sin(x2Crt) - x2Crt;
            g.drawLine(cnv.xCrt2Scr(x1Crt), cnv.yCrt2Scr(y1Crt), cnv.xCrt2Scr(x2Crt), cnv.yCrt2Scr(y2Crt));
        }
    }
}
