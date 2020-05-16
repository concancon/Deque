import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {


    private Node first;
    private Node last;
    private int N;      // number of items on the queue

    // construct an empty deque
    public Deque() {
        N = 0;
    }


    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public boolean isEmpty() {
        return (N == 0);
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot provide a null argument in addFirst");
        }


        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        N++;
    }

    public void addLast(Item item) {  // Add item to the end of the list.
        if (item == null) {
            throw new IllegalArgumentException("cannot provide a null argument in addLast");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item removeFirst() {  // Remove item from the beginning of the list.

        if (isEmpty()) {
            throw new NoSuchElementException("cannot call removeFirst when deque is empty");
        }
        N--;
        Item item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
            first = null;

        }
        else first.previous = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot call removeLast when deque is empty");
        }
        N--;
        Item item = last.item;
        last = last.previous;

        if (isEmpty()) {
            first = null;
            last = null;
        }
        else last.next = null;


        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }


    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }


        public void remove() {
            throw new UnsupportedOperationException("this operation is not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("there are no more items to return in next()");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }


    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Node x = first; x != null; x = x.next) {
            sb.append(x.item).append(" ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();
        deque.addLast(31);
        deque.addLast(32);
        deque.addLast(33);
        deque.addLast(34);
        deque.addLast(35);
        deque.addLast(36);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        Iterator<Integer> iter = deque.iterator();
        int num = 0;
        while (iter.hasNext()) {
            num = iter.next();
            System.out.println(num);
        }


    }


}

