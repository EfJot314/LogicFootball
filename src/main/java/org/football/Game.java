package org.football;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Game {

    private final JFrame frame;
    private final JPanel panel;

    public Game(){
        this.frame = new JFrame("Logic football");
        this.frame.setSize(500, 500);
        this.panel = new JPanel();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(panel);
        this.frame.setSize(1000, 500);


    }

    public void runGame(){
        //tworze kontroler myszy
        MouseController mouse = new MouseController(this.frame);

        //tworze boisko
        Board board = new Board(8, 10, 25, this.frame, mouse);

        this.frame.add(board);

        //listener ruchu myszki
        this.frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                board.updateBoard();
            }
        });

        //listener klikniecia myszki
        this.frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                board.updatePath();
            }
        });



        //wyswietlam
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }


}
