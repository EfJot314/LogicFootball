package org.football;

import javax.swing.*;

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
        //tworze boisko
        Board board = new Board(8, 10, 25, this.frame);

        this.frame.add(board);

        //wyswietlam
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }


}
