package gui;

import graphics.CartesianPainter;
import graphics.Converter;
import graphics.FunctionPainter;
import graphics.GraphicsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {
    private final double xmin = -10.;
    private final double xmax = 10.;
    private final double ymin = -7.;
    private final double ymax = 7.;


    private Dimension minSize = new Dimension(600,400);
    private GraphicsPanel mainPanel;
    private JPanel controlPanel;
    private GroupLayout gl;
    private  GroupLayout glcp;
    public MainWindow(){
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gl = new GroupLayout(getContentPane());
        setLayout(gl);

        controlPanel = new JPanel();
        glcp = new GroupLayout(controlPanel);
        controlPanel.setLayout(glcp);
        controlPanel.setBackground(Color.white);

        Converter converter = new Converter(xmin,xmax,ymin,ymax,0,0);

        CartesianPainter cartesianPainter = new CartesianPainter(converter);

        FunctionPainter functionPainter = new FunctionPainter(converter);

        mainPanel = new GraphicsPanel(cartesianPainter);
        mainPanel.addPainter(functionPainter);
        mainPanel.setBackground(Color.white);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                converter.setWidth(mainPanel.getWidth());
                converter.setHeight(mainPanel.getHeight());
                mainPanel.repaint();
            }
        });

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(8)
                .addComponent(controlPanel, 70, 70, 70)
                .addGap(8)
        );
    }
}
