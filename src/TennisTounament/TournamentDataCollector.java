package TennisTounament;

import java.util.ArrayList;

public class TournamentDataCollector {
    private int counter;
    private Player winner;
    private ArrayList<GameDataCollector> tournamentGames;

    public TournamentDataCollector(int count) {
        this.counter = count;
        tournamentGames = new ArrayList<>();
    }

    public ArrayList<GameDataCollector> getTournamentGames() {
        return tournamentGames;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void addTournamentGames(GameDataCollector game) {
        this.tournamentGames.add(game);
    }

    public Player getWinner() {
        return winner;
    }
}
