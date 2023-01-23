package gui;

import graphics.Converter;
import graphics.FunctionPainter;
import graphics.GraphicsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private Dimension minSize = new Dimension(600,400);
    private GraphicsPanel mainPanel;
    private GroupLayout gl;
    public MainWindow(){
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gl = new GroupLayout(getContentPane());
        setLayout(gl);

        Converter converter = new Converter(-7,7,-7,7,0,0);

        FunctionPainter functionPainter = new FunctionPainter(converter);

        mainPanel = new GraphicsPanel(functionPainter);
        mainPanel.setBackground(Color.white);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                converter.setWidth(mainPanel.getWidth());
                converter.setHeight(mainPanel.getHeight());
                mainPanel.repaint();
            }
        });

        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(mainPanel));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(mainPanel));
    }
}
