/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    public int N;

    private void resize(int max) {  // Move queue to a new array of size max.
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;// remember temp will then have an empty latter half
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

        N = 0;
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
        if (N == a.length) resize(2 * a.length);
        a[N++] = item;
    }

    // remove and return a random item
  /*  public Item dequeue() {

    }

    // return a random item (but do not remove it)
    public Item sample() {

    }*/

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        public Item next() {
            int rand = StdRandom.uniform(N);
            i++;
            return a[rand];
        }

        public void remove() {
            throw new UnsupportedOperationException("this operation is not supported");
        }


    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> myQue = new RandomizedQueue<>();
        myQue.enqueue(3);
        myQue.enqueue(6);
        myQue.enqueue(3);
        myQue.enqueue(6);
        myQue.enqueue(3);
        myQue.enqueue(6);

        System.out.println("size is: " + myQue.size());

        printCollection(myQue);
    }

    public static <E> void printCollection(RandomizedQueue<E> myQue) {
        Iterator<E> i = myQue.iterator();
        while (i.hasNext()) {
            E s = i.next();
            StdOut.println(s);
        }
    }
}
