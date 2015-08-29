package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Main extends JPanel
{
    JFrame f;
    JPanel p, p1, p2, p3;
    FlowLayout fl;
    GridBagLayout gbl;
    LineBorder lb;
    Color c1, c2, c3, c4;
    public Main ()
    {
        gbl = new GridBagLayout();
        fl  = new FlowLayout(1,1,1);
        c1  = new Color(255,255,240);
        c2  = new Color(240,255,240);
        c3  = new Color(255,240,240);
        c4  = new Color(50, 50, 200);
        lb  = new LineBorder(c4,1,true);
        f  = new JFrame("for VINGRAD :)");
        p  = new JPanel(gbl);
        p1 = new JPanel(fl);
        p2 = new JPanel(fl);
        p3 = new JPanel(fl);
        p1.setBorder(lb);
        p1.setPreferredSize(new Dimension(200, 200));
        p1.setBackground(c1);
        p2.setBorder(lb);
        p2.setPreferredSize(new Dimension(100, 301));
        p2.setBackground(c2);
        p3.setBorder(lb);
        p3.setPreferredSize(new Dimension(200, 100));
        p3.setBackground(c3);
        p.add(p1, new GridBagConstraints(0,0,1,1,1.0,1.0,18,0,new Insets(1,1,0,0),0,0));
        p.add(p2, new GridBagConstraints(1,0,1,2,1.0,1.0,12,0,new Insets(1,1,1,1),0,0));
        p.add(p3, new GridBagConstraints(0,1,1,1,1.0,1.0,10,0,new Insets(1,1,1,0),0,0));
        f.setCursor(new Cursor(12));
        f.setLocation(150,150);
        f.setDefaultCloseOperation(3);
        f.setResizable(false);
        f.getContentPane().add(p);
        f.pack();
        f.setVisible(true);
    }
    public static void main(String args[]) {new Main();}
}