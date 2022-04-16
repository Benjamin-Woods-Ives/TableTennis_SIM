package TennisTounament;

import myUsefulPackage.CSVReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TControl {
    public String[][] playerStrArr;
    public Player[] playerObjArr;
    public int playerAmount;
    public TournamentDataCollector[] data;

    public TControl(int playerAmount, String[][]strPlayers, int tournamentAmount) {
        this.playerStrArr = strPlayers;
        this.playerAmount = playerAmount;
        this.playerObjArr = new Player[playerAmount];
        this.data = new TournamentDataCollector[tournamentAmount];
        randomisePlayerStrArr();
        createPlayerObjects();
        int a = 1;
        while (a <= tournamentAmount) {
            randomisePlayerObjArr();
            TournamentDataCollector tempCollector = new TournamentDataCollector(a);
            new RootRound(true, this.playerObjArr, tempCollector);
            this.data[a-1] = tempCollector;
            a++;
        }
    }


    private void createPlayerObjects() {
        // creates player objects. total player objects is equal to amount attribute.
        // the for loop loops over a 2d string array of string player data.
        for (int i = 0; i < this.playerAmount; i++) {
            String[] player = this.playerStrArr[i];
            playerObjArr[i] = new Player(
                    Integer.parseInt(player[0]),
                    player[1],
                    player[2],
                    Integer.parseInt(player[3]),
                    Integer.parseInt(player[4]),
                    Integer.parseInt(player[5]),
                    Integer.parseInt(player[6]),
                    Integer.parseInt(player[7]),
                    Integer.parseInt(player[8]),
                    Integer.parseInt(player[9]));
        }
    }
    private void randomisePlayerStrArr() {
        List<String[]> tempList = Arrays.asList(this.playerStrArr);
        Collections.shuffle(tempList);
        tempList.toArray(this.playerStrArr);
    }
    private void randomisePlayerObjArr() {
        List<Player> tempList = Arrays.asList(this.playerObjArr);
        Collections.shuffle(tempList);
        tempList.toArray(this.playerObjArr);
    }
    public static void main(String[] args) {
        TControl test = new TControl(128, CSVReader.staticRead("players.csv",128), 2);
    }
}