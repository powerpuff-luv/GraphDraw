package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private Dimension minSize = new Dimension(600,400);
    //private GraphicsPanel mainPanel;
    private GroupLayout gl;
    public MainWindow(){
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gl = new GroupLayout(getContentPane());
        setLayout(gl);
    }
}
