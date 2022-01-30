package com.platinumg17.rigoranthusemortisreborn.magica.common.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtil {
    // Implementing Fisher–Yates shuffle
    public static <T>void shuffleArray(T[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}