package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        assertEquals((Integer) 4, arb.peek());
        assertEquals((Integer) 4, arb.dequeue());
        assertEquals((Integer) 5, arb.dequeue());
        assertEquals((Integer) 6, arb.dequeue());
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
