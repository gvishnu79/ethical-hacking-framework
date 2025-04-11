package com.ethicalhackingai.quantum;

import java.util.Random;

public class BB84Simulator {

    public String simulateKeyExchange(int keyLength) {
        Random rand = new Random();
        StringBuilder aliceBits = new StringBuilder();
        StringBuilder aliceBases = new StringBuilder();
        StringBuilder bobBases = new StringBuilder();
        StringBuilder finalKey = new StringBuilder();

        // Alice generates random bits and bases
        for (int i = 0; i < keyLength; i++) {
            aliceBits.append(rand.nextInt(2));
            aliceBases.append(rand.nextInt(2) == 0 ? "+" : "x");
        }

        // Bob measures in random bases
        for (int i = 0; i < keyLength; i++) {
            bobBases.append(rand.nextInt(2) == 0 ? "+" : "x");
            if (aliceBases.charAt(i) == bobBases.charAt(i)) {
                finalKey.append(aliceBits.charAt(i));
            }
        }

        return finalKey.toString();
    }
}