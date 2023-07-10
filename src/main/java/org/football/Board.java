package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Board extends JComponent{

    public final float nX;
    public final float nY;

    private float unit;

    private final JFrame frame;

    public Board(int x, int y, float unit, JFrame frame){
        super();
        this.nX = x;
        this.nY = y+2;
        this.unit = unit;
        this.frame = frame;
    }

    public void setUnit(float unit){
        this.unit = unit;
    }


    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        float width = this.frame.getWidth();
        float height = this.frame.getHeight();

        float dx = (width - this.nX*this.unit) / 2;
        float dy = (height - this.nY*this.unit) / 2;

        //pionowe linie
        for(int i=0;i<=this.nX;i++){
            g2.draw(new Line2D.Float(i*unit+dx, dy+unit, i*unit+dx, dy+this.nY*unit-unit));
        }

        //poziome linie
        for(int i=1;i<this.nY;i++){
            g2.draw(new Line2D.Float(dx, i*unit+dy, dx+this.nX*unit, i*unit+dy));
        }

        //bramki
        g2.draw(new Line2D.Float(dx+(this.nX/2-1)*unit, dy, dx+(this.nX/2+1)*unit, dy));
        g2.draw(new Line2D.Float(dx+(this.nX/2-1)*unit, dy+this.nY*unit, dx+(this.nX/2+1)*unit, dy+this.nY*unit));

        g2.draw(new Line2D.Float(dx+(this.nX/2-1)*unit, dy, dx+(this.nX/2-1)*unit, dy+unit));
        g2.draw(new Line2D.Float(dx+(this.nX/2-1)*unit, dy+this.nY*unit, dx+(this.nX/2-1)*unit, dy+this.nY*unit-unit));

        g2.draw(new Line2D.Float(dx+(this.nX/2+1)*unit, dy, dx+(this.nX/2+1)*unit, dy+unit));
        g2.draw(new Line2D.Float(dx+(this.nX/2+1)*unit, dy+this.nY*unit, dx+(this.nX/2+1)*unit, dy+this.nY*unit-unit));

        g2.draw(new Line2D.Float(dx+(this.nX/2)*unit, dy, dx+(this.nX/2)*unit, dy+unit));
        g2.draw(new Line2D.Float(dx+(this.nX/2)*unit, dy+this.nY*unit, dx+(this.nX/2)*unit, dy+this.nY*unit-unit));

    }

}
