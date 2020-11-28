public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty ArrrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 7;
        size = 0;
    }

//    /** Initialize a ArrrayDeque. */
//    public ArrayDeque(T el) {
//        items = (T[]) new Object[8];
//        items[0] = el;
//        nextFirst = 1;
//        nextLast = 7;
//        size = 1;
//    }

    /** Resizes the underlying array to the target capacity.
     *  根据变大变小，前后索引的位置，确定变换方式
     **/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (capacity > items.length) {
            if (nextFirst <= nextLast) {
                if (nextFirst != 0) {
                    System.arraycopy(items, 0, a, 0, nextFirst);
                }
                int endLength = items.length - nextLast;
                if (nextLast != items.length - 1) {
                    System.arraycopy(items, nextLast, a, (capacity - endLength), endLength);
                }
                nextLast = capacity - endLength;
            } else {
                System.arraycopy(items, nextLast + 1, a, nextLast + 1, size);
            }
        } else {
            if (nextFirst <= nextLast) {
                if (nextFirst != 0) {
                    System.arraycopy(items, 0, a, 0, nextFirst);
                }
                int endLength = items.length - nextLast;
                if (nextLast != items.length - 1) {
                    System.arraycopy(items, nextLast, a, (capacity - endLength), endLength);
                }
                nextLast = capacity - endLength;
            } else {
                System.arraycopy(items, nextLast + 1, a, 0, size);
                nextFirst = size;
                nextLast = capacity - 1;
            }
        }
        items = a;
    }

    /** Add a elment to the back of the list. */
    public void addFirst(T x) {
        if (nextFirst == nextLast) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (nextFirst == nextLast) {
            resize(items.length * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = items.length - 1;
        }
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        T x = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst -= 1;
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        T x = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if (items.length >= 16 && (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        if (size == 0 || i >= size) {
            return null;
        }
        int location = nextFirst - 1 - i;
        if (location < 0) {
            location = items.length + location;
        }
        return items[location];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int index = 0;
        while (index < size) {
            int location = nextFirst - 1 - index;
            if (location < 0) {
                location = items.length + location;
            }
            System.out.println(items[location] + " ");
            index += 1;
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> L1 = new ArrayDeque<>();
//        L1.addFirst("first");
//        L1.addFirst("first1");
//        L1.addFirst("first2");
//        L1.addFirst("first3");
//        L1.addFirst("first4");
//        L1.addFirst("first5");
//        L1.addFirst("first6");
//        L1.addFirst("first7");
//        L1.addFirst("first8");
//        L1.addLast("last1");
//        L1.addLast("last2");
//        L1.addLast("last3");
//        L1.addLast("last4");
//        L1.addLast("last5");
//        L1.addLast("last6");
//        L1.addLast("last7");
//        L1.addLast("last8");
//        L1.addLast("last9");
//        System.out.println(L1.get(1));
//        System.out.println(L1.get(0));
//        L1.printDeque();
//        System.out.println(L1.size());
//        System.out.println(L1.isEmpty());
//        L1.removeFirst();
//        L1.printDeque();
//        L1.removeLast();
//    }
}
