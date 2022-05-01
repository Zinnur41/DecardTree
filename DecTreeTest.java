package DecardTree;

import java.util.Random;

class DecTreeTest {
    private static final Long SEED = 123L;
    private static final Random RAND = new Random(SEED);
    public static void main(String[] args) {

        DecTree dec = new DecTree(1,5);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            dec.add(RAND.nextInt(5000));
        }
        long end = System.nanoTime();
        long elapsed = end - start;
        System.out.println(elapsed/1000);

        /*
        10 -> 86
        100 -> 388
        1000 -> 2300
        10000 -> 8400
         */


        long start1 = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            dec.remove(RAND.nextInt(5000));
        }
        long end1 = System.nanoTime();
        long elapsed1 = end1 - start1;
        System.out.println(elapsed1/1000);

        /*
         10 -> 14
         100 -> 65
         1000 -> 615
         10000 -> 2300
         */


        long start2 = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            dec.contains(RAND.nextInt(5000));
        }
        long end2 = System.nanoTime();
        long elapsed2 = end2 - start2;
        System.out.println(elapsed2/1000);

        /*
        10 -> 10
        100 -> 75
        1000 -> 720
        10000 -> 2400
         */
    }
}