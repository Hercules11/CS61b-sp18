import static org.junit.Assert.*;

import org.junit.Test;

public class LLDTest {

    /**
     * Example test that verifies correctness of the IntList.of static
     * method. The main point of this is to convince you that
     * assertEquals knows how to handle IntLists just fine.
     */
//    @Test
//    public void testRemove() {
//        LinkedListDeque<String> L1 = new LinkedListDeque<>();
//        L1.addLast("first");
//        L1.addLast("last");
//        assertEquals("first", L1.removeFirst());
//        assertFalse(L1.isEmpty());
//        assertEquals("last", L1.removeLast());
//        L1.addLast("wu");
//        L1.addLast("liu");
//        assertEquals("wu", L1.removeFirst());
//    }

//    @Test
//    public void TestRemoveAdd() {
//        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
//        L1.addFirst(0);
//        L1.addFirst(1);
//        L1.removeFirst();
//        L1.addFirst(3);
//        L1.removeLast();
//        L1.addLast(5);
//        L1.addLast(6);
//        L1.removeLast();
//        L1.addFirst(8);
//        int value = L1.removeFirst();
//        assertEquals(8, value);
//        int value1 = L1.removeFirst();
//        assertEquals(3, value1);
//    }

    /** If you're running this from the command line, you'll need
     * to add a main method. See ArithmeticTest.java for an
     * example. */
    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", LLDTest.class);
    }

}
