import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /**
     * Randomly call StudentArrayDeque and ArrayDequeSolution methods until disagree output.
     */
    @Test
    public void testRandomBreak() {
        StudentArrayDeque<Integer> std = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        for (int i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                std.addLast(i);
                sol.addLast(i);
            } else {
                std.addFirst(i);
                sol.addFirst(i);
            }
        }
        for (int i = 0; i<10; i++) {
            assertEquals("There is a get error!\n", sol.get(i), std.get(i));
            assertEquals("There is a removeFirst error!\n", sol.removeFirst(), std.removeFirst());
            assertEquals("There is a removeLast error!\n", sol.removeLast(), std.removeLast());
            assertEquals("There is a size error!\n", sol.size(), std.size());
            assertEquals("There is a isEmpty error!\n", sol.isEmpty(), std.isEmpty());
        }

    }

}
