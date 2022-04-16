package TennisTounament;

import java.util.ArrayList;

public class GameDataCollector {
    private Player winner;
    private Player[] players;
    private ArrayList<String> points;

    public GameDataCollector() {
        /**
         * initialises a arraylist
         */
        this.points = new ArrayList<>() ;
    }

    public void setWinner(Player winner) {
        /**
         * sets a new value for the winner attribute
         */
        this.winner = winner;
    }

    public void setPlayers(Player[] players) {
        /**
         * sets a new value for the players attribute
         */
        this.players = players;
    }

    public void setPoints(String toAdd) {
        /**
         * adds a point to the points attribute
         */
        points.add(toAdd);
    }

    public ArrayList<String> getPoints() {
        /**
         * returns a array list of strings. these strings correspond to each point a player scores
         */
        return points;
    }

    public Player getWinner() {
        /**
         * returns a the winner as a Player object
         */
        return winner;
    }
}

