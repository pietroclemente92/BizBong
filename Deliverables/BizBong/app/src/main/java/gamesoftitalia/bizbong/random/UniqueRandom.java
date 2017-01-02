package gamesoftitalia.bizbong.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by gamesoftitalia on 21/12/2016.
 */

public class UniqueRandom {
    private Random random = new Random();
    private Set<Integer> usedNumbers = new HashSet<>();


    public int nextInt(int n) {
        int potentialRandom = this.random.nextInt(n);

        while (this.usedNumbers.contains(potentialRandom)) {
            potentialRandom = this.random.nextInt(n);
        }


        this.usedNumbers.add(potentialRandom);

        return potentialRandom;
    }

}

