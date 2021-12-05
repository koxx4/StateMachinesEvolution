package org.koxx4.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GraphArea extends Canvas {

    public GraphArea() {
        super();
        this.setPreferredSize(new Dimension(200,400));
        this.setMinimumSize(new Dimension(200,200));
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double rectangle = new Rectangle2D.Double(30,30, 200,200);
        g2d.setBackground(Color.BLUE);
        g2d.fill(rectangle);
    }

}
