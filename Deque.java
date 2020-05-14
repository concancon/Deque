import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;      // number of items on the queue

    // construct an empty deque
    public Deque() {
        this.N = 0;
    }


    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public boolean isEmpty() {
        return (first == null || last == null);
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
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot call removeLast when deque is empty");
        }

        Item item = last.item;
        last = last.previous;

        if (isEmpty()) first = null;
        else last.next = null;
        N--;
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

        Deque<Integer> myDeque = new Deque<>();
        myDeque.addLast(3);
        myDeque.addLast(6);
        myDeque.addFirst(3);
        myDeque.addLast(6);
        myDeque.addLast(7);
        myDeque.addFirst(7);
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeLast();
        myDeque.removeFirst();
        System.out.println(myDeque);
        System.out.println(myDeque.size());
        System.out.println(myDeque.isEmpty());
        myDeque.addFirst(3);
        System.out.println(myDeque);
        System.out.println(myDeque.isEmpty());
    }


}

