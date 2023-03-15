package top.oneyi.demo;

/**
 * 单向链表
 *
 * @author oneyi
 * @date 2023/3/15
 */

public class SinglyLinkedList {
    /**
     * 头节点
     */
    private Node head;


    private static class Node {
        /**
         * 节点值
         */
        private int value;
        /**
         * 下一个节点
         */
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue(){
            return value;
        }
    }


    /**
     * 头部添加节点数据
     *
     * @param value
     */
    public void addFirst(int value) {
        // 该条代码是由右向左执行
        this.head = new Node(value, this.head);
    }

    /**
     * 查找到最后一个元素
     *
     * @return
     */
    private Node findLast() {
        if(this.head == null){
            return null;
        }
        Node cur=this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 在链表最后添加节点
     * @param value
     */
    public void addLast(int value) {
        Node last = this.findLast();
        if (last == null){
            addFirst(value);
        }
        last.next = new Node(value,null);
    }

    /**
     * 在 链表后面添加多个节点
     * @param first
     * @param rest
     */
    public void addLast(int first, int... rest){

        // 先自成一个链表
        Node sublist = new Node(first,null);
        // 创建一个curr代存名义上的头节点
        Node curr = sublist;
        for (int value : rest) {
            curr.next=new Node(value,null);
            // 将节点换到新添加的节点上
            curr=curr.next;
        }
        // 先查找最后一个节点
        Node last = findLast();
        if(last == null){
            //表明这个链表是空的
            this.head=sublist;
            return;
        }
            last.next=sublist;

    }

    /**
     * 根据索引获取节点
     * @param index
     * @return
     */
    private Node findNode(int index){
        // 从头节点遍历一定的次数
        int i=0;
        Node curr = this.head;
        while(i < index){
            curr = curr.next;
            i++;
        }
        return curr;
    }
    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node != null) {
            return node.value;
        }
        throw illegalIndex(index);
    }

    /**
     * 链表插入数据
     * @param index
     * @param value
     */
    public void insert(int index,int value){
        if(index == 0){
            addFirst(value);
            return;
        }
        // 首先找到index索引的上一个节点
        Node prev = findNode(index - 1);
        if(prev == null){
            throw illegalIndex(index);
        }
        prev.next= new Node(value,prev.next);
    }

    /**
     * 删除节点
     * @param index
     */
    public void remove(int index){
        if(index == 0){
            if(this.head != null){
                this.head=null;
            }else{
                throw illegalIndex(index);
            }
        }
        // 前置节点   需要判断 前置节点和前置节点的后置节点 不为 null
        Node prev = findNode(index - 1);
        Node curr;
        if(prev != null && (curr = prev.next) != null){
            prev=curr.next;
        }else{
            throw illegalIndex(index);
        }
    }

}
