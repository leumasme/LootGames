package ru.timeconqueror.timecore.api.util;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandHelper {

    public static final Random RAND = new Random();

    /**
     * Returns {@code a} with 50% chance otherwise return {@code b} Uses built-in random instance.
     */
    public static <T> T flipCoin(T a, T b) {
        return flipCoin(RAND, a, b);
    }

    /**
     * Returns {@code a} with 50% chance otherwise return {@code b}
     */
    public static <T> T flipCoin(Random random, T a, T b) {
        return random.nextBoolean() ? a : b;
    }

    /**
     * Returns {@code a} with {@code chance}% (from 0 to 100) otherwise return {@code b} Uses built-in random instance.
     *
     * @throws IllegalArgumentException if provided chance is more than 100.
     */
    public static <T> T chance(int chance, T a, T b) {
        return chance(RAND, chance, a, b);
    }

    /**
     * Returns {@code a} with {@code chance}% (from 0 to 100) otherwise return {@code b}
     *
     * @throws IllegalArgumentException if provided chance is more than 100.
     */
    public static <T> T chance(Random random, int chance, T a, T b) {
        return chance(random, chance) ? a : b;
    }

    /**
     * Returns true with {@code chance}% (from 0 to 100). Uses built-in random instance.
     *
     * @throws IllegalArgumentException if provided chance is more than 100.
     */
    public static boolean chance(int chance) {
        return chance(RAND, chance);
    }

    /**
     * Returns true with {@code chance}% (from 0 to 100).
     *
     * @throws IllegalArgumentException if provided chance is more than 100.
     */
    public static boolean chance(Random random, int chance) {
        if (chance > 100)
            throw new IllegalArgumentException("Chance shouldn't be greater than 100. Provided: " + chance);

        return random.nextInt(100) < chance;
    }

    /**
     * Chooses with equal probability.
     *
     * @throws IllegalArgumentException if empty array is provided.
     */
    @SafeVarargs
    public static <T> T chooseEqually(Random r, T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Provided array 'items' shouldn't be empty!");
        }
        return items[r.nextInt(items.length)];
    }

    /**
     * Chooses with equal probability. Uses built-in random instance.
     *
     * @throws IllegalArgumentException if empty array is provided.
     */
    @SafeVarargs
    public static <T> T chooseEqually(T... items) {
        return chooseEqually(RAND, items);
    }

    /**
     * Chooses with equal probability. Doesn't check for duplicates in the list.
     *
     * @throws IllegalArgumentException if empty list is provided.
     */
    public static <T> T chooseEqually(Random r, List<T> items) {
        if (items.size() == 0) {
            throw new IllegalArgumentException("Provided array 'items' shouldn't be empty!");
        }
        return items.get(r.nextInt(items.size()));
    }

    /**
     * Chooses with equal probability. Doesn't check for duplicates in the list. Uses built-in random instance.
     *
     * @throws IllegalArgumentException if empty list is provided.
     */
    public static <T> T chooseEqually(List<T> items) {
        return chooseEqually(RAND, items);
    }

    /**
     * Chooses with equal probability. Doesn't check for duplicates. Complexity: O(n).
     *
     * @throws IllegalArgumentException if empty collection is provided.
     */
    public static <T> T chooseEqually(Random r, Collection<T> items) {
        if (items.size() == 0) {
            throw new IllegalArgumentException("Provided array 'items' shouldn't be empty!");
        }

        int i = 0;
        int size = items.size();
        int selected = r.nextInt(size);
        for (T item : items) {
            if (i == selected) {
                return item;
            }

            i++;
        }

        throw new IllegalArgumentException("Can't find randomly chosen element while iterating the collection.");
    }

    /**
     * Chooses with equal probability. Doesn't check for duplicates. Complexity: O(n). Uses built-in random instance.
     *
     * @throws IllegalArgumentException if empty collection is provided.
     */
    public static <T> T chooseEqually(Collection<T> items) {
        return chooseEqually(RAND, items);
    }
}
