package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                // addLast
                case 0:
                    int randVal = StdRandom.uniform(0, 100);
                    correct.addLast(randVal);
                    broken.addLast(randVal);
                    System.out.println("addLast(" + randVal + ")");
                    break;

                // size
                case 1:
                    assertEquals(correct.size(), broken.size());
                    break;

                // getLast
                case 2:
                    if (correct.size() > 0) {
                        assertEquals(correct.getLast(), broken.getLast());
                    }
                    break;

                // removeLast
                case 3:
                    if (correct.size() > 0) {
                        assertEquals(correct.removeLast(), broken.removeLast());
                    }
                    break;
            }
        }
    }
}
