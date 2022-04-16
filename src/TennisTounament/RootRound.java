package TennisTounament;

public class RootRound{
    protected boolean isRoot;
    protected Player[] players;
    protected int playersAmount;
    protected Player[] thisRoundPlayers;
    protected  TournamentDataCollector dataCollector;


    public RootRound(boolean root, Player[] players, TournamentDataCollector dataCollector) {
        this.dataCollector = dataCollector;
        this.isRoot = root;
        this.players = players;
        this.thisRoundPlayers = new Player[2];
        this.playersAmount = this.players.length;
        if (this.isRoot){
            this.constructorExtension();
            }
        }

    protected void constructorExtension(){
        /**
         * this is a separated from the constructor so when the child class calls this constructor with the super method this wont be used until after the method is complete.
         */
        if (playersAmount <= 2) {
            this.roundGame(this.players);
        }
        else {
            this.generateNextRounds();
        }
    }

    protected void generateNextRounds() {
        /**
         this method is called when the players array is more then 2.
         2 new rounds are created then once the classes are complete the game for this round is played based on the winners of the 2 sub rounds.
         */
        Player[][] playersSplit = splitPlayers();
        new Round(playersSplit[0], this, 0, dataCollector);
        new Round(playersSplit[1], this, 1, dataCollector);
        this.roundGame(this.thisRoundPlayers);
    }

    protected Player[][] splitPlayers() {
        /**
         *         //splits the players array in half  for each new round in the method above
         */
        int playersSize = this.players.length;
        Player[] subPlayers1 = new Player[playersSize/2];
        Player[] subPlayers2 = new Player[playersSize/2];
        for (int i = 0; i < (playersSize / 2); i++) {
            subPlayers1[i] = this.players[i];
            subPlayers2[i] = this.players[i + (playersSize / 2)];
        }
        return new Player[][]{subPlayers1, subPlayers2};
    }

    protected void roundGame(Player[] twoPlayersArr){
        /**
         * this is where the game object will be created and used to calculate the output of this round.
         */
        Game game = new Game(twoPlayersArr[0], twoPlayersArr[1]);
        this.dataCollector.addTournamentGames(game.getData());
        this.setParentWinner(game.getWinner());

    }
    protected void setWinner(int pos,Player winner) {
        /**
         *          sets the thisRoundPlayers array with the winners of the sub rounds.
         *          the pos is based on sub rounds position attribute.
         */
        this.thisRoundPlayers[pos] = winner;
        /**
        *System.out.println("Sub round winner: " + winner.getFirstName() + " " + winner.getLastname());
         */
    }
    protected void setParentWinner(Player winner){
        /**
         *          as the rootRound class has no parent it does not need to set the winner to the parent.
         *          when the winner is passed to this method it will be the winner of the entire tournament.
         *
         */
        dataCollector.setWinner(winner);
    }


}

