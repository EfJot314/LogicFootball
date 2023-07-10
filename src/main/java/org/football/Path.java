package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Path{

    private final List<float[]> path;

    public Path(float nX, float nY){
        this.path = new ArrayList<>();
        //pierwszy punkt na srodku
        this.addToPath(new float[]{nX/2, nY/2});
    }

    public void addToPath(float[] position){
        this.path.add(position);
    }

    public Iterator<float[]> getIterator(){
        return this.path.iterator();
    }
}
