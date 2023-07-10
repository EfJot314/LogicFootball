package org.football;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.round;

public class MouseController {

    private final JFrame frame;


    public MouseController(JFrame frame){
        this.frame = frame;
    }


    public int[] getMouseBoardPosition(Board board){
        //zbieranie danych boiska
        float unit = board.getUnit();
        float dx = board.getDx();
        float dy = board.getDy();

        //zbieranie pozycji myszy
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        Point windowPoint = this.frame.getLocation();
        float xm = (float) (mousePoint.getX() - windowPoint.getX());
        float ym = (float) (mousePoint.getY() - windowPoint.getY());

        //znajdowanie pozycji myszy na boisku
        int x = round((xm - dx) / unit);
        int y = round((ym - dy) / unit);

        return new int[]{x,y};
    }


}
