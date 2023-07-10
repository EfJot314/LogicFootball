package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class Board extends JComponent{

    public final float nX;
    public final float nY;

    private float unit;

    private final JFrame frame;

    private Path path;


    public Board(int x, int y, float unit, JFrame frame){
        super();
        this.nX = x;
        this.nY = y+2;
        this.unit = unit;
        this.frame = frame;
        this.path = new Path(this.nX, this.nY);
    }

    public void setUnit(float unit){
        this.unit = unit;
    }

    public float getDx(){
        return (this.frame.getWidth() - this.nX*this.unit) / 2;
    }

    public float getDy(){
        return (this.frame.getHeight() - this.nY*this.unit) / 2;
    }


    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        float dx = this.getDx();
        float dy = this.getDy();

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

        //sciezka
        Iterator<float[]> pathPoints = this.path.getIterator();
        float[] p1 = pathPoints.next();
        //kropka na poczatku
        int r = (int)(unit/8);
        g.fillOval((int)(dx+p1[0]*unit)-r, (int)(dy+p1[1]*unit)-r, 2*r, 2*r);
        //linie
        g2.setStroke(new BasicStroke(2));
        while(pathPoints.hasNext()){
            float[] p2 = pathPoints.next();
            g2.draw(new Line2D.Float(dx+p1[0]*unit, dy+p1[1]*unit, dx+p2[0]*unit, dy+p2[1]*unit));
            p1 = p2;
        }
        //kropka na koncu
        g.fillOval((int)(dx+p1[0]*unit)-r, (int)(dy+p1[1]*unit)-r, 2*r, 2*r);


    }

}
