package com.Model;

import javax.swing.*;
import java.awt.*;

public class AbstractView extends JFrame {
    protected void displayJPanel(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(650,450));
        this.pack();
        this.setVisible(true);
    }
}
