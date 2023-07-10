package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class Board extends JComponent{

    public final float nX;
    public final float nY;

    private float unit;

    private final JFrame frame;

    private final Path path;
    private final MouseController mouse;
    private final GameEngine engine;
    private boolean showNewPath;
    private final Timer timer;


    public Board(int x, int y, float unit, JFrame frame, MouseController mouse, GameEngine engine){
        super();
        this.nX = x;
        this.nY = y+2;
        this.unit = unit;
        this.frame = frame;
        this.path = new Path(this.nX, this.nY);
        this.mouse = mouse;
        this.engine = engine;
        this.showNewPath = true;

        this.timer = new Timer(1000/4 , new ActionListener() {
            int timerPeriods = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                showNewPath = !showNewPath;
                updateBoard();
                timerPeriods += 1;
                //koniec migania i dzialania wtedy podejmowane
                if(timerPeriods > 4){
                    timerPeriods = 0;
                    showNewPath = true;
                    path.clearNewPath();
                    engine.changePlayer();
                    ((Timer)e.getSource()).stop();
                }
            }
        });

    }

    public void setUnit(float unit){
        this.unit = unit;
        this.updateBoard();
    }

    public float getUnit(){
        return this.unit;
    }

    public float getDx(){
        return (this.frame.getWidth() - this.nX*this.unit) / 2;
    }

    public float getDy(){
        return (this.frame.getHeight() - this.nY*this.unit) / 2;
    }

    public void updateBoard(){
        this.frame.validate();
        this.frame.repaint();
    }

    public void updatePath(){
        int[] mousePosition = this.mouse.getMouseBoardPosition(this);

        if(!this.timer.isRunning() && this.path.canBeAddedToPath(mousePosition, this)){
            this.path.addToPath(mousePosition);

            if(this.isChange()){
                //miganie nowej sciezki
                this.timer.start();
            }

            this.updateBoard();
        }


    }

    public boolean isOnBorder(int[] position){
        if(position[0] == 0 || position[0] == this.nX) return true;
        if(position[1] == 0 || position[1] == this.nY) return true;
        return ((position[1] == 1 || position[1] == this.nY-1) && (position[0] <= this.nX/2-1 || position[0] >= this.nX/2+1));
    }

    public boolean isOut(int[] position){
        //boki
        if(position[0] < 0 || position[0] > this.nX) return true;

        //przy bramkach
        if(position[1] < 0 || position[1] > this.nY) return true;
        return ((position[1] < 1 || position[1] > this.nY-1) && (position[0] < this.nX/2-1 || position[0] > this.nX/2+1));

    }

    private boolean isChange(){
        int[] lastPosition = this.path.getLastPosition();
        //odbicia od bokow
        if(this.isOnBorder(lastPosition)) return false;
        //odbicia od sciezki
        Iterator<int[]> iterator = this.path.getIterator();
        while(iterator.hasNext()){
            int[] position = iterator.next();
            if(position != lastPosition && position[0] == lastPosition[0] && position[1] == lastPosition[1]) return false;
        }
        //jak sie nie odbijam to zmiana tury
        return true;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        float dx = this.getDx();
        float dy = this.getDy();

        int r = (int)(unit/8);

        //myszka (kursor-wskaznik)
        int[] mousePosition = this.mouse.getMouseBoardPosition(this);
        if(!this.isOut(mousePosition)){
            g2.setColor(Color.GRAY);
            g2.fillOval((int)(dx+mousePosition[0]*unit)-2*r, (int)(dy+mousePosition[1]*unit)-2*r, 4*r, 4*r);
        }


        //pionowe linie
        g2.setColor(Color.BLACK);
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
        Iterator<int[]> pathPoints = this.path.getIterator();
        int[] p1 = pathPoints.next();
        //kropka na poczatku
        g.fillOval((int)(dx+p1[0]*unit)-r, (int)(dy+p1[1]*unit)-r, 2*r, 2*r);
        //linie
        g2.setStroke(new BasicStroke(2.5f));
        while(pathPoints.hasNext()){
            int[] p2 = pathPoints.next();
            g2.draw(new Line2D.Float(dx+p1[0]*unit, dy+p1[1]*unit, dx+p2[0]*unit, dy+p2[1]*unit));
            p1 = p2;
        }
        //kropka na koncu
        g2.setColor(this.engine.getCurrentPlayer().color);
        g2.fillOval((int)(dx+p1[0]*unit)-r, (int)(dy+p1[1]*unit)-r, 2*r, 2*r);

        //nowa sciezka
        if(this.showNewPath){
            pathPoints = this.path.getNewPathIterator();
            if(pathPoints.hasNext()){
                g2.setColor(this.engine.getCurrentPlayer().color);
                p1 = pathPoints.next();
                while(pathPoints.hasNext()){
                    int[] p2 = pathPoints.next();
                    g2.draw(new Line2D.Float(dx+p1[0]*unit, dy+p1[1]*unit, dx+p2[0]*unit, dy+p2[1]*unit));
                    p1 = p2;
                }
            }
        }





    }

}
