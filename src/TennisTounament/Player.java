package TennisTounament;

import java.util.Arrays;

public class Player {

    public String firstName, lastname;
    private int  id;
    private int[] skillSet;

    public Player(int id, String firstName, String lastname, int servePower, int serveSkill, int Spin, int forehandPower, int backhandPower, int Fitness, int Age ) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.skillSet = new int[]{servePower, serveSkill, Spin, forehandPower, backhandPower, Fitness, Age};
    }
    public int[] getSkills(){
        /** returns the skill set attribute.
         */
        return this.skillSet;
    }

    public String getFirstName() {
        /** returns the first name attribute.
         */
        return firstName;
    }

    public String getLastname() {
        /** returns the last name attribute.
         */
        return lastname;
    }

    @Override
    public String toString() {
        /**
         * overrides the toString method so that the below message is printed instead of its memory location.
         */
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", id=" + id +
                '}';
    }
}
