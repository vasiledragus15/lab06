package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int READ = 1_000;
    private static final int ELEMS = 100_000;
    private static final int START = 1_000;
    private static final int END = 2_000;
    private static final long AFRICA_POPULATION = 1_110_635_000L;
    private static final long EUROPE_POPULATION = 742_452_000L;
    private static final long AMERICAS_POPULATION = 972_005_000L;
    private static final long ASIA_POPULATION = 4_298_723_000L;
    private static final long OCEANIA_POPULATION = 38_304_000L;
    private static final long ANTARCTICA_POPULATION = 0L;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
            List<Integer> alist = new ArrayList<>();

            for (int i = START; i < END ; i++) {
                alist.add(i);
            }
            log(alist);

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
            List<Integer> llist = new LinkedList<>(alist);
            log(llist);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */ 
            int el = alist.get(0);
            alist.set(0, alist.get(alist.size()-1));
            alist.set(alist.size()-1, el);
            log("First element: " + alist.get(0) + " Last element: " + alist.get(alist.size()-1));
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
            StringBuilder builder = new StringBuilder();
            for (Integer i : alist) {
                builder.append(i);
                builder.append(", ");
            }
            if (builder.length() > 0) {
                builder.delete(builder.length() - 2, builder.length());
            }

            log(builder);
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
            long time = System.nanoTime();
            for (int i = 0; i <= ELEMS; i++) {
                alist.add(0, i);
            }
            time = System.nanoTime() - time;
            log("Inserting " + ELEMS + " elements as first in an ArrayList took " + timeAsString(time));
            
            time = System.nanoTime();
            for (int i = 0; i <= ELEMS; i++) {
                llist.add(0, i);
            }
            time = System.nanoTime() - time;
            log("Inserting " + ELEMS + " elements as first in a LinkedList took " + timeAsString(time));
            
            
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
            time = System.nanoTime();
            for (int i = 0; i < READ; i++) {
                alist.get(alist.size() / 2);
            }
            time = System.nanoTime() - time;
            log("Reading " + READ + " elements in the middle of an ArrayList took " + timeAsString(time));

            time = System.nanoTime();
            for (int i = 0; i < READ; i++) {
                llist.get(llist.size() / 2 );
            }
            time = System.nanoTime() - time;
            log("Reading " + READ + " elements in the middle of a LinkedList took " + timeAsString(time));

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
            final Map<String, Long> world = new HashMap<>();
            world.put("Africa", AFRICA_POPULATION);
            world.put("Americas", AMERICAS_POPULATION);
            world.put("Antarctica", ANTARCTICA_POPULATION);
            world.put("Asia", ASIA_POPULATION);
            world.put("Europe", EUROPE_POPULATION);
            world.put("Oceania", OCEANIA_POPULATION);
        /*
         * 8) Compute the population of the world
         */
        long worldPop = 0;
        for (Map.Entry<String, Long>  entry : world.entrySet()) {
            worldPop += entry.getValue();
        }
        System.out.println("There is ~" + String.format("%,d", worldPop) + " people in the world!");

    }

    private static String timeAsString(final long nanoseconds) {
        return nanoseconds + "ns (" + NANOSECONDS.toMillis(nanoseconds) + "ms)";
    }  

    private static void log(final Object message) {
        System.out.println(message);
    }
}
