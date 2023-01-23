package gui;

import graphics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
//    private final double xmin = -10.;
//    private final double xmax = 10.;
//    private final double ymin = -7.;
//    private final double ymax = 7.;


    private Dimension minSize = new Dimension(600,400);
    private GraphicsPanel mainPanel;
    private JPanel controlPanel;
    private GroupLayout gl;
    private  GroupLayout glcp;

    private JLabel xmin, ymin, xmax, ymax, tmin, tmax;
    private JSpinner xmins, ymins, xmaxs, ymaxs, tmins, tmaxs;
    private SpinnerNumberModel nmxmins, nmymins, nmxmaxs, nmymaxs, nmtmins, nmtmaxs;
    private JPanel clr1, clr2;
    private JLabel clr1n, clr2n;
    private JCheckBox ch1, ch2;

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


        clr1 = new JPanel();
        clr2 = new JPanel();
        clr1.setBackground(Color.BLUE);
        clr2.setBackground(Color.RED);

        ch1 = new JCheckBox("", true);
        ch2 = new JCheckBox("", true);

        clr1n = new JLabel("Цвет явн. функ.");
        clr2n = new JLabel("Цвет парам. функ.");

        xmin = new JLabel();
        ymin = new JLabel();
        xmax = new JLabel();
        ymax = new JLabel();
        tmin = new JLabel();
        tmax = new JLabel();
        xmin.setText("xmin");
        ymin.setText("ymin");
        xmax.setText("xmax");
        ymax.setText("ymax");
        tmin.setText("tmin");
        tmax.setText("tmax");

        nmxmins = new SpinnerNumberModel(-10., -100., 4.9, 0.1);
        nmxmaxs = new SpinnerNumberModel(10., -4.9, 100., 0.1);
        nmymins = new SpinnerNumberModel(-7., -100., 4.9, 0.1);
        nmymaxs = new SpinnerNumberModel(7., -4.9, 100., 0.1);
        nmtmins = new SpinnerNumberModel(-100., -1000., -5., 1);
        nmtmaxs = new SpinnerNumberModel(100., 5., 1000., 1);
        xmins = new JSpinner(nmxmins);
        ymins = new JSpinner(nmymins);
        xmaxs = new JSpinner(nmxmaxs);
        ymaxs = new JSpinner(nmymaxs);
        tmins = new JSpinner(nmtmins);
        tmaxs = new JSpinner(nmtmaxs);


        Converter converter = new Converter((Double) xmins.getValue(), (Double) xmaxs.getValue(), (Double) ymins.getValue(), (Double) ymaxs.getValue(),
                0, 0);

        CartesianPainter cartesianPainter = new CartesianPainter(converter);

        FunctionPainter functionPainter = new FunctionPainter(converter, clr1.getBackground());

        ParametricFunctionPainter parametricFunctionPainter = new ParametricFunctionPainter(converter, clr2.getBackground());

        mainPanel = new GraphicsPanel(cartesianPainter);

        mainPanel.addPainter(functionPainter);
        mainPanel.addPainter(parametricFunctionPainter);

        mainPanel.setBackground(Color.white);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                converter.setWidth(mainPanel.getWidth());
                converter.setHeight(mainPanel.getHeight());
                mainPanel.repaint();
            }
        });


        ch1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ch1.isSelected()) {
                    mainPanel.addPainter(functionPainter);
                } else mainPanel.removePainter(functionPainter);
            }
        });
        ch2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ch2.isSelected()) {
                    mainPanel.addPainter(parametricFunctionPainter);
                } else mainPanel.removePainter(parametricFunctionPainter);
            }
        });



        clr1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(MainWindow.this, "Выберите цвет явн. функ.", clr1.getBackground());
                if (newColor != null) {
                    clr1.setBackground(newColor);
                    functionPainter.setColor(newColor);
                    mainPanel.repaint();
                }
            }
        });
        clr2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var newColor = JColorChooser.showDialog(
                        MainWindow.this, "Выберите цвет парам. функ.", clr2.getBackground());
                if (newColor != null) {
                    clr2.setBackground(newColor);
                    parametricFunctionPainter.setColor(newColor);
                    mainPanel.repaint();
                }
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

        glcp.setHorizontalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(xmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(xmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ymin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(ymins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(tmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(tmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                )
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(xmax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(xmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ymax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(ymaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(tmax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(tmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                )
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ch1)
                                .addGap(8)
                                .addComponent(clr1, 45, 45, 45)
                                .addGap(8)
                                .addComponent(clr1n, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addComponent(ch2)
                                .addGap(8)
                                .addComponent(clr2, 45, 45, 45)
                                .addGap(8)
                                .addComponent(clr2n, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        ))
                .addGap(8)
        );

        glcp.setVerticalGroup(glcp.createSequentialGroup()
                .addGap(8)
                .addGroup(glcp.createParallelGroup()
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(xmin, GroupLayout.Alignment.CENTER)
                                        .addComponent(xmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                )
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ymin, GroupLayout.Alignment.CENTER)
                                        .addComponent(ymins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                )
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(tmin, GroupLayout.Alignment.CENTER)
                                        .addComponent(tmins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                ))
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(xmax, GroupLayout.Alignment.CENTER)
                                        .addComponent(xmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                )
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ymax, GroupLayout.Alignment.CENTER)
                                        .addComponent(ymaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                )
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(tmax, GroupLayout.Alignment.CENTER)
                                        .addComponent(tmaxs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                ))
                        .addGap(8)
                        .addGroup(glcp.createSequentialGroup()
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ch1)
                                        .addComponent(clr1, 20, 20, 20)
                                        .addComponent(clr1n, GroupLayout.Alignment.CENTER)
                                )
                                .addGroup(glcp.createParallelGroup()
                                        .addComponent(ch2)
                                        .addComponent(clr2, 20, 20, 20)
                                        .addComponent(clr2n, GroupLayout.Alignment.CENTER)
                                ))
                )
                .addGap(8)
        );
    }
}
