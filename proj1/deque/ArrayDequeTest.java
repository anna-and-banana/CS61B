package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ads = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", ads.isEmpty());
        ads.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ads.size());
        assertFalse("lld1 should now contain 1 item", ads.isEmpty());

        ads.addLast("middle");
        assertEquals(2, ads.size());

        ads.addLast("back");
        assertEquals(3, ads.size());

        System.out.println("Printing out deque: ");
        ads.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", adi.isEmpty());

        adi.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", adi.isEmpty());

        adi.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", adi.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        adi.addFirst(3);

        adi.removeLast();
        adi.removeFirst();
        adi.removeLast();
        adi.removeFirst();

        int size = adi.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String> ads = new ArrayDeque<>();
        ArrayDeque<Double> add = new ArrayDeque<>();
        ArrayDeque<Boolean> adb = new ArrayDeque<>();

        ads.addFirst("string");
        add.addFirst(3.14159);
        adb.addFirst(true);

        String s = ads.removeFirst();
        double d = add.removeFirst();
        boolean b = adb.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,",
                null, adi.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,",
                null, adi.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            adi.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) adi.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) adi.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        adi.addLast(1);
        adi.addLast(4);
        adi.addLast(8);
        adi.addLast(11);

        adi.printDeque();
        adi.get(0);
        adi.get(1);
        adi.get(3);
    }
}
