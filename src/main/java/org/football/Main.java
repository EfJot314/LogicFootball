package org.football;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        //tworze frame i panel
        JFrame frame = new JFrame("Logic Football Menu");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(300, 300);

        //tworze elementy
        JLabel title = new JLabel("Logic Football");
        JButton startButton = new JButton("Start game");
        JButton exitButton = new JButton("Exit");

        //layout
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        //konfiguruje elementy
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game game = new Game(new Player(Color.RED), new Player(Color.BLUE));
                game.runGame();
                frame.setVisible(false);
            }
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        //dodaje elementy
        panel.add(title);
        panel.add(startButton);
        panel.add(exitButton);

        //wyswietlanie
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}