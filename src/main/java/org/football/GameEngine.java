package org.football;

import java.util.Random;

import static java.lang.Math.abs;

public class GameEngine {

    private final Player[] players;
    private int currentPlayerId;

    public GameEngine(Player[] players){
        this.players = players;
        //losuje kto rozpoczyna
        Random rnd = new Random();
        this.currentPlayerId = abs(rnd.nextInt() % 2);

    }

    public Player getCurrentPlayer(){
        return this.players[this.currentPlayerId];
    }

    public void changePlayer(){
        this.currentPlayerId = (this.currentPlayerId + 1) % 2;
    }


}
