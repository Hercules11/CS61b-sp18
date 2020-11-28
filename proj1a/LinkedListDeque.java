public class LinkedListDeque<T> {
    private int size;
    private final DequeNode sentinel;

    private class DequeNode {
        private T item;
        private DequeNode prev;
        private DequeNode next;
        DequeNode(T el, DequeNode head, DequeNode tail) {
            item = el;
            prev = head;
            next = tail;
        }
    }

    public LinkedListDeque() {
        sentinel = new DequeNode((T) null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(T item) {
//        sentinel = new DequeNode((T) null, null, null);
//        sentinel.prev = new DequeNode(item, sentinel, sentinel);
//        sentinel.next = sentinel.prev;
//        size = 1;
//    }

    public void addFirst(T item) {
        DequeNode first = new DequeNode(item, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    public void addLast(T item) {
        DequeNode last = new DequeNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DequeNode ptr = sentinel;
        int count = size;
        while (count > 0) {
            System.out.print(ptr.next.item + " ");
            ptr = ptr.next;
            count -= 1;
        }
    }

    public T removeFirst() {
        if (size > 0) {
            DequeNode ptr = sentinel.next;
            ptr.next.prev = sentinel;
            sentinel.next = ptr.next;
            size -= 1;
            return ptr.item;
        }
        return null;
    }

    public T removeLast() {
        if (size > 0) {
            DequeNode ptr = sentinel.prev;
            ptr.prev.next = sentinel;
            sentinel.prev = ptr.prev;
            size -= 1;
            return ptr.item;
        }
        return null;
    }

    public T get(int index) {
        if (0 <= index && index + 1 <= size) {
            DequeNode ptr = sentinel.next;
            while (index > 0) {
                ptr = ptr.next;
                index -= 1;
            }
            return ptr.item;
        }
        return null;
    }

    public T getRecursive(int index) {
        if (0 <= index && index + 1 <= size) {
            DequeNode ptr = sentinel.next;
            if (index == 0) {
                return ptr.item;
            } else {
                return getRecursive(index - 1);
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        LinkedListDeque<String> L1 = new LinkedListDeque<>();
//        L1.addFirst("first");
//        L1.addLast("last");
//        System.out.println(L1.getRecursive(1));
//        L1.printDeque();
//        L1.size();
//        L1.isEmpty();
//        L1.removeFirst();
//        L1.printDeque();
//        L1.removeLast();
//    }
}
