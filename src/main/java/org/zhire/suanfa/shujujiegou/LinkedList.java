package org.zhire.suanfa.shujujiegou;


/**
 * 双向链表
 *
 * @Author: chenqi
 * @Date: 2020.7.7 16:44
 */
public class LinkedList<T> {
    private int size;
    public Node head;
    public Node tail;

    public LinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
    }

    public int size() {
        return size;
    }

    private boolean accessible(int index) {
        return index > -1 && index < size;
    }

    private void link(Node front, Node back) {
        if (front != null) {
            front.next = back;
        }
        if (back != null) {
            back.previous = front;
        } else {
            tail = front;
        }
    }

    private void abandon(Node garbage) {
        garbage.next = null;
        garbage.previous = null;
    }

    public T get(int index) {
        return (T) search(index).data;
    }

    public Node getNode(int index) {
        return search(index);
    }

    private Node search(int index) {
        if (accessible(index)) {
            Node t = head.next;
            while (index-- > 0) {
                t = t.next;
            }
            return t;
        }
        return null;
    }

    public void add(T obj) {
        Node newNode = new Node(obj);
        link(tail.data == null ? head : tail, newNode);
        tail = newNode;
        size++;
    }

    public T remove(int index) {
        if (accessible(index)) {
            Node previous = search(index - 1);
            Node garbage = previous.next;
            link(previous, garbage.next);
            if (garbage == tail) {
                tail = previous;
            }
            abandon(garbage);
            size--;
            return (T) garbage.data;
        }
        return null;
    }

    public boolean add(T obj, int index) {
        if (index == size) {
            add(obj);
            return true;
        }
        if (accessible(index)) {
            Node _new = new Node(obj), target = search(index), previous = target.previous;
            link(previous, _new);
            link(_new, target);
            size++;
            return true;
        }
        return false;
    }

    public boolean addList(LinkedList<T> linkedList, int index) {
        if (index == size) {
            link(tail, linkedList.head.next);
            tail = linkedList.tail;
            size += linkedList.size;
            return true;
        }
        if (accessible(index)) {
            Node previous = search(index).previous, next = previous.next;
            link(previous, linkedList.head.next);
            link(linkedList.tail, next);
            size += linkedList.size;
            return true;
        }
        return false;
    }

    public int removeAll(T target) {
        Node iterator = head;
        int counter = 0;
        while (iterator.next != null) {
            iterator = iterator.next;
            if (iterator.data.equals(target)) {
                Node garbage = iterator;
                link(iterator.previous, iterator.next);
                iterator = iterator.previous;
                if (garbage == tail) {
                    tail = garbage.previous;
                }
                abandon(garbage);
                counter++;
            }
        }
        size -= counter;
        return counter;
    }

    public boolean remove(int left, int right) {
        if (accessible(left) && accessible(right) && left <= right) {
            Node leftNode = search(left).previous, rightNode = search(right).next;
            if (right == size - 1) {
                tail = leftNode;
            }
            abandon(leftNode.next);
            abandon(rightNode.previous);
            link(leftNode, rightNode);
            size -= (right - left + 1);
            return true;
        }
        return false;
    }

    public boolean change(T obj, int index) {
        if (accessible(index)) {
            Node target = search(index);
            target.data = obj;
            return true;
        }
        return false;
    }

    public int changeAll(T old, T intention) {
        Node iterator = head;
        int counter = 0;
        while (iterator.next != null) {
            iterator = iterator.next;
            if (iterator.data.equals(old)) {
                iterator.data = intention;
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node t = head;
        while (t.next != null) {
            t = t.next;
            if (t.data instanceof LinkedList) {
                sb.append("> @");
                sb.append(t.data.hashCode());
                sb.append(" <\t");
            } else {
                sb.append(t.data + "\t");
            }
        }
        return sb.toString();
    }


    class Node {
        public Object data;
        public Node next;
        public Node previous;

        public Node(Object obj) {
            data = obj;
            next = null;
            previous = null;
        }
    }

    public static void main(String[] args) {
        LinkedList myLinked = new LinkedList();
        myLinked.add(1);
        myLinked.add(2);
        myLinked.add(3);
        System.out.println(myLinked);
    }
}
