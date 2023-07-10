package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

public class Path{

    private final List<float[]> path;

    public Path(float nX, float nY){
        this.path = new ArrayList<>();
        //pierwszy punkt na srodku
        this.path.add(new float[]{nX/2, nY/2});
    }

    public void addToPath(float[] position){
        float[] lastPosition = this.path.get(this.path.size()-1);
        //sprawdzam czy nie ma za duzej odleglosci miedzy nimi (porownuje do 1.5 z powodu ew przyblizen)
        if(abs(lastPosition[0] - position[0]) >= 1.5 || abs(lastPosition[1] - position[1]) >= 1.5){
            return;
        }
        this.path.add(position);
    }

    public Iterator<float[]> getIterator(){
        return this.path.iterator();
    }
}
