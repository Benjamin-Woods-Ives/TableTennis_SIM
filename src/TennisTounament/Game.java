package TennisTounament;
//{servePower, serveSkill, Spin, forehandPower, backhandPower, Fitness, Age}
//fitness will be base multipliers
//specific skills will just be additional constants
//serve attributes will give base multiplier if they serve
//whoever has the most points wins that point
//each time a point is scored both players receive another base multiplier


import java.util.Random;

public class Game {
    private final Player p1, p2;
    private int pointsP1, pointsP2;
    private final Random randomTest;
    private Player winner;
    private GameDataCollector data;

    public GameDataCollector getData() {
        return data;
    }

    public Game(Player p1, Player p2) {
        this.randomTest = new Random();
        this.p1 = p1;
        this.p2 = p2;
        this.pointsP1 = 0;
        this.pointsP2 = 0;
        this.data = new GameDataCollector();
         Player[] player = new Player[] {p1, p2};
        this.data.setPlayers(player);
        gameIterator();
        this.data.setWinner(this.winner);


    }
    private double scoreForPoint(Player player) {
        double score;
        int[]sArr = player.getSkills();
        /**the age constant should get a value between9 and around 3
         * uses the bell curve to give players at 30 the max points as they are around peak fitness
         * the random multiplier increases there score  up to 25% better */
        //todo this method may need some more work and change so this method is not called every time a point is calculated.
        double ageMultiplier =
                (4*(Math.pow(Math.exp(1),(-0.0003*(Math.pow((sArr[6]-30),2))))))
                        +
                (5*(Math.pow(Math.exp(1),(-0.01*(Math.pow((sArr[6]-30),2))))));
        double constants = sArr[2] + sArr[3] + sArr[4];
        double fitnessMultiplier = (sArr[5])/4;
        int randomMultiplier2 = this.randomTest.nextInt(25);
        score = (ageMultiplier + fitnessMultiplier)*Math.log(constants);
        return score*(1+(1.0/(randomMultiplier2+0.01)));
    }
    private void pointCalculator(){
        /**
         *         passes True/False to the point incrementer method. if true p1 gets points, if False p2 gets point
          */
        boolean pointWinner;
        double p1Score = scoreForPoint(this.p1);
        double p2Score = scoreForPoint(this.p2);
        //System.out.println("p1 score is: "+ p1Score + " p2 score is: " + p2Score);
        pointWinner = p1Score >= p2Score;
        //System.out.println(pointWinner);
        pointIncrement(pointWinner);
    }
    private void pointIncrement(Boolean point) {
        /**
         *         increases a points player. if true then p1 gets a point. and if false is passed then p2 gets a point
          */
        if (point) {
            this.pointsP1 = this.pointsP1 + 1;
            this.data.setPoints(this.p1.getFirstName() + " " + this.p1.getLastname() + " won a point!. " + "[" + this.pointsP1 + " : " + this.pointsP2 + "]");
        } else {
            this.pointsP2 = this.pointsP2 + 1;
            this.data.setPoints(this.p2.getFirstName() + " " + this.p2.getLastname() + " won a point!. " + "[" + this.pointsP1 + " : " + this.pointsP2 + "]");
        }
    }

    private void gameIterator() {
        /**
         * will loop until win condition has met then winner is returned
         */
        Boolean p1Wins = false;
        Boolean p2Wins = false;
        while (!(p1Wins || p2Wins)){
            pointCalculator();
            p1Wins = winChecker(this.pointsP1, this.pointsP2);
            p2Wins = winChecker(this.pointsP2, this.pointsP1);
        }
        if (p1Wins){
            this.winner = this.p1;
        }
        else {
            this.winner = this.p2;
        }
    }
    private Boolean winChecker(int points, int compare) {
        /**
         * this method deals with the conditions of the while loop in the gameIterator method
         */
        return (points >= 11) & ((points) - compare) >= 2;
    }

    public Player getWinner(){
        /**
         * returns a the winner Player object
         */
        return this.winner;
    }
}
