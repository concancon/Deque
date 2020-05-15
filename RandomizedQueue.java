import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {

        N = 0;
    }

    private void resize(int max) {  // Move queue to a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp; // remember temp will then have an empty latter half
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (N == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can't call enque with null arg");
        }
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    private void swapWithEnd(int index) {

        a[index] = a[N - 1];
        a[N - 1] = null;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException(
                    "can't call deque when array is already empty");
        }

        int rand = StdRandom.uniform(N);
        Item randItem = a[rand];
        swapWithEnd(rand);
        N--;
        return randItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException(
                    "can't call deque when array is already empty");
        }

        int rand = StdRandom.uniform(N);
        Item randItem = a[rand];
        return randItem;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private final Item[] copy;


        public ArrayIterator() {
            copy = a.clone();
            StdRandom.shuffle(copy, 0, N);
        }

        public boolean hasNext() {
            return i < N;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = copy[i++];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("this operation is not supported");
        }


    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();

        q.enqueue(2);

        q.enqueue(3);

        q.enqueue(1);

        q.enqueue(0);

        q.enqueue(4);

        q.enqueue(5);

        Iterator<Integer> it = q.iterator();

        Iterator<Integer> it2 = q.iterator();

        //then, to show the issue with your iterator, add the following:

        while (it.hasNext())

            System.out.println("Iterator it " + it.next());

        while (it2.hasNext())

            System.out.println("Iterator it2 " + it2.next());
    }

    private static <E> void printCollection(RandomizedQueue<E> myQue) {
        Iterator<E> i = myQue.iterator();
        while (i.hasNext()) {
            E s = i.next();
            StdOut.println(s);
        }
    }
}
