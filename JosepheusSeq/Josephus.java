package CodeDojo.JosepheusSeq;

import java.util.*;

import java.util.List;

public class Josephus {
    public static class Soldat<T> {
        public T value;
        public Soldat<T> nextNode;
        public boolean killed;

        public Soldat(T value) {
            this.value = value;
            this.killed = false;
        }
    }

    public static class CircularList<T> {
        private Soldat<T> head = null;
        private Soldat<T> tail = null;
        private List<T> coffin = new ArrayList<T>();
        private int size = 0;

        public CircularList(List<T> init) {
            for (T t : init) {
                Soldat<T> newNode = new Soldat<T>(t);
                if (head == null) {
                    head = newNode;
                } else {
                    tail.nextNode = newNode;
                }
                tail = newNode;
                tail.nextNode = head;
                size++;
            }
        }

        public Soldat<T> killKth(int k, Soldat<T> killer) {
            Soldat<T> currentNode = killer;
            while (k > 1) {
                currentNode = currentNode.nextNode;
                if (!currentNode.killed) {
                    k--;
                }
            }
            currentNode.killed = true;
            this.coffin.add(currentNode.value);
            while (currentNode.nextNode.killed && !(coffin.size() == this.size)) {
                currentNode = currentNode.nextNode;
            }
            return currentNode.nextNode;
        }
    }

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
        CircularList<T> killCircle = new CircularList<>(items);
        Soldat<T> current = killCircle.head;
        for (int i = 0; i < killCircle.size; i++) {
            current = killCircle.killKth(k, current);
        }
        return killCircle.coffin;
    }

    public static void main(String[] args) {

    }
}
