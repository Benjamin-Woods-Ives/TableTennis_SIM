package TennisTounament;

public class Round extends RootRound {
    // this class inherits from the RootRound class.
    // the use of this class is to add/change properties of the RootRound class.
    // for example the RootRound class does not have a parent round that it can belong to where other rounds do.
    private final RootRound parentRound;
    private final int position;


    public Round(Player[] players, RootRound parentRound, int pos, TournamentDataCollector data) {
        // the position attribute represents the left/right node within a binary tree.
        super(false, players, data);
        this.position = pos;
        this.parentRound = parentRound;
        this.constructorExtension();
        }
    @Override
    protected void setParentWinner(Player winner){
        //overrides the method within super class this is because this method needs to update the winner so the next game can begin,
        // where as in the super class there is no next game.
        this.parentRound.setWinner(this.position, winner);
        }
    }
