package top.oneyi.demo;
/**
 * 实现简单二叉树
 * @author oneyi
 * @date 2023/3/21
 */

public class TreeNode<T> {

    private TreeNode<T> left;

    private TreeNode<T> right;

    private  TreeNode<T> root;

    private static class Node<T>{
        private  Node<T> root;

        private  T val;

        public Node(Node<T> root, T val) {
            this.root = root;
            this.val = val;
        }

    }






}
