package top.oneyi.demo;

public class SinglyLinkedListSentinel {
    /**
     * 添加一个Node作为哨兵/哑元
     */
    private final Node head = new Node(Integer.MIN_VALUE, null);

    private static class Node {
        /**
         * 节点值
         */
        private final int value;
        /**
         * 下一个节点
         */
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

    }

    /**
     * 查找到最后一个元素
     *
     * @return
     */
    private Node findLast() {
        if (this.head == null) {
            return null;
        }
        Node cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    private Node findNode(int index) {
        // 从头节点遍历一定的次数
        int i = 0;
        Node curr = this.head;
        while (i < index) {
            curr = curr.next;
            i++;
        }
        return curr;
    }

    public void addLast(int value) {
        Node last = findLast();
        /*
        改动前
        if (last == null) {
            this.head = new Node(value, null);
            return;
        }
        */
        last.next = new Node(value, null);
    }

    public void insert(int index, int value) {
        /*
        改动前
        if (index == 0) {
            this.head = new Node(value, this.head);
            return;
        }
        */
        // index 传入 0 时，返回的是哨兵
        Node prev = findNode(index - 1);
        if (prev != null) {
            prev.next = new Node(value, prev.next);
        }
        throw illegalIndex(index);
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    public void remove(int index) {
        /*
        改动前
        if (index == 0) {
            if (this.head != null) {
                this.head = this.head.next;
                return;
            } else {
                throw illegalIndex(index);
            }
        }
        */
        // index 传入 0 时，返回的是哨兵
        Node prev = findNode(index - 1);
        Node curr;
        if (prev != null && (curr = prev.next) != null) {
            prev.next = curr.next;
        } else {
            throw illegalIndex(index);
        }
    }

    public void addFirst(int value) {
        /*
        改动前
        this.head = new Node(value, this.head);
        */
        this.head.next = new Node(value, this.head.next);
        // 也可以视为 insert 的特例, 即 insert(0, value);
    }


}