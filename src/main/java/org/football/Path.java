package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

public class Path{

    private final List<int[]> path;

    public Path(float nX, float nY){
        this.path = new ArrayList<>();
        //pierwszy punkt na srodku
        this.path.add(new int[]{(int)(nX/2), (int)(nY/2)});
    }


    public boolean canBeAddedToPath(int[] position, Board board){
        //sprawdzam czy nie wychodze poza plansze
        if(board.isOut(position)) return false;

        //sprawdzam czy nie ma za duzej odleglosci miedzy nimi (porownuje do 1.5 z powodu ew przyblizen)
        int[] lastPosition = this.path.get(this.path.size()-1);
        if(abs(lastPosition[0] - position[0]) >= 1.5 || abs(lastPosition[1] - position[1]) >= 1.5) return false;

        //sprawdzam czy nie ide po bokach
        if(board.isOnBorder(lastPosition) && board.isOnBorder(position) && (lastPosition[0] == position[0] || lastPosition[1] == position[1])) return false;

        //sprawdzam czy nie ide po tym co juz przeszedlem
        Iterator<int[]> iterator = this.getIterator();
        int[] p1 = iterator.next();
        while(iterator.hasNext()){
            int[] p2 = iterator.next();
            if(p1[0] == lastPosition[0] && p1[1] == lastPosition[1] && p2[0] == position[0] && p2[1] == position[1]) return false;
            if(p1[0] == position[0] && p1[1] == position[1] && p2[0] == lastPosition[0] && p2[1] == lastPosition[1]) return false;
            p1 = p2;
        }

        //corner case - "scinanie" rogu bramki
        if(lastPosition[0] != board.nX/2 && position[0] != board.nX/2 && (position[1] == 0 || position[1] == board.nY)) return false;
        if(position[0] != board.nX/2 && lastPosition[0] != board.nX/2 && (lastPosition[1] == 0 || lastPosition[1] == board.nY)) return false;

        return true;
    }

    public void addToPath(int[] position){
        this.path.add(position);
    }

    public int[] getLastPosition(){
        return this.path.get(this.path.size()-1);
    }

    public Iterator<int[]> getIterator(){
        return this.path.iterator();
    }
}
