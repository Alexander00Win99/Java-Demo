package com.alex_java.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DemoBST {
    private int callLevel = GlobalSet.count ++;

    public static Node[] nodes;

    public static void printBSTPreOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.id);
        printBSTPreOrder(root.left);
        printBSTPreOrder(root.right);
    }

    public static void printBSTMidOrder(Node root) {
        if (root == null) {
            return;
        }
        printBSTMidOrder(root.left);
        System.out.println(root.id);
        printBSTMidOrder(root.right);
    }

    public static void printBSTPostOrder(Node root) {
        if (root == null) {
            return;
        }
        printBSTPostOrder(root.left);
        printBSTPostOrder(root.right);
        System.out.println(root.id);
    }

    // 深度优先搜索DFS(Depth First Search)和广度优先搜索BFS(Breadth First Search)是图的两种遍历方法
    // https://blog.csdn.net/zuihongyan518/article/details/80823924
    // https://www.cnblogs.com/xiaolovewei/p/7763867.html
    // 树是有向无环图，对应搜索方法如下

    public static boolean dfsBST(Node root, int value) {
        if (root == null) {
//            System.out.println("Target failed");
            return false;
        } else {
            System.out.println(String.format("%d has been visited.", root.id));
        }
        if (root.id == value) {
            System.out.println(String.format("%d is found finally!", value));
            return true;
        }
        return dfsBST(root.left, value) || dfsBST(root.right, value);
    }

    public static boolean bfsBST(Node root, int value) {
        Node left, right;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();

        if (root == null) {
            System.out.println("Target failed");
            return false;
        } else {
            System.out.println(String.format("%d has been visited.", root.id));
            if (root.id == value) {
                System.out.println(String.format("%d is found finally!", value));
                return true;
            } else {
                queue.offer(root);
                while (!queue.isEmpty()) {
                    Node headNode = queue.poll();
                    left = headNode.left;
                    right = headNode.right;
                    if (left != null) {
                        queue.offer(left);
                        System.out.println(String.format("%d has been visited.", left.id));
                        if (left.id == value) {
                            System.out.println(String.format("%d is found finally!", value));
                            return true;
                        }
                    }
                    if (right != null) {
                        queue.offer(right);
                        System.out.println(String.format("%d has been visited.", right.id));
                        if (right.id == value) {
                            System.out.println(String.format("%d is found finally!", value));
                            return true;
                        }
                    }
                }
            }
        }

//        if(root.left != null) {
//            System.out.println(String.format("%d has been visited.", root.left.id));
//            if (root.left.id == value){
//                System.out.println(String.format("%d is found finally!", value));
//                return true;
//            }
//        }
//        if(root.right != null) {
//            System.out.println(String.format("%d has been visited.", root.right.id));
//            if (root.right.id == value) {
//                System.out.println(String.format("%d is found finally!", value));
//                return true;
//            }
//        }
//
//        if (root.left == null && root.right == null) {
//            System.out.println("Target failed");
//            return false;
//        } else {
//            return dlr00BST(root.left.left, value) || dlr00BST(root.left.right, value) || dlr00BST(root.right.left, value) || dlr00BST(root.right.right, value);
//        }
        return false;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            System.out.println(arr[i]);
        }
    }

    private static int indexOf(int[] array, int key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        for (int i = 0; i < array.length; i ++) {
            if (array[i] == key) {
                System.out.println(String.format("index is: %d", i));
                return i;
            }
        }
        return -1;
    }

    public static Node getTreeByPreMid(int[] dlr, int[] ldr) {
        Node root = null;
        Node left = null;
        Node right = null;
        int[] leftTreeDLR, leftTreeLDR, rightTreeDLR, rightTreeLDR;

        if (dlr == null) System.out.println("前序数组为空");
        if (ldr == null) System.out.println("中序数组为空");
        if (ldr.length == 0) System.out.println("中序数组为int[0]，返回null");
        if (ldr.length == 0) System.out.println("中序数组为int[0]，返回null");
        if (dlr == null || ldr == null || dlr.length == 0 || ldr.length == 0 || dlr.length != ldr.length) {
            return null;
        }
        // 生成根节点
        root = new Node(dlr[0]);
        int index = indexOf(ldr, dlr[0]);
        System.out.println(index);

        // 左子树的前序
        leftTreeDLR = new int[index];
        System.arraycopy(dlr, 1, leftTreeDLR, 0, index);
        System.out.println("打印左子树前序内容:");
        print(leftTreeDLR);
        // 左子树的中序
        leftTreeLDR = new int[index];
        System.arraycopy(ldr, 0, leftTreeLDR, 0, index);
        System.out.println("打印左子树中序内容:");
        print(leftTreeLDR);

        // 递归取得左子树节点
        System.out.println("调用之前：");
        System.out.println(leftTreeDLR);
        print(leftTreeDLR);
        System.out.println(leftTreeLDR);
        print(leftTreeLDR);
        left = getTreeByPreMid(leftTreeDLR, leftTreeLDR);

        // 右子树的前序
        rightTreeDLR = new int[ldr.length - index -1];
        System.arraycopy(dlr, index + 1, rightTreeDLR, 0, ldr.length - index -1);
        System.out.println("打印右子树前序内容:");
        print(rightTreeDLR);
        // 右子树的中序
        rightTreeLDR = new int[ldr.length - index -1];
        System.arraycopy(ldr, index + 1, rightTreeLDR, 0, ldr.length - index -1);
        System.out.println("打印右子树中序内容:");
        print(rightTreeLDR);

        // 递归取得右子树节点
        System.out.println("调用之前：");
        System.out.println(rightTreeDLR);
        print(rightTreeDLR);
        System.out.println(rightTreeLDR);
        print(rightTreeLDR);
        right = getTreeByPreMid(rightTreeDLR, rightTreeLDR);

        System.out.println(left);
        System.out.println(right);
        // 生成树：根节点 + 左子树 + 右子树 ==> 树
        root.left = left;
        root.right = right;
        return root;
    }

    public static Node getTreeByPostMid(int[] lrd, int[] ldr) {
        Node root = null;
        Node left = null;
        Node right = null;
        int[] leftTreeLRD, leftTreeLDR, rightTreeLRD, rightTreeLDR;

        if (lrd == null || ldr == null || lrd.length == 0 || ldr.length == 0 || lrd.length != ldr.length) {
            return null;
        }

        root = new Node(lrd[lrd.length - 1]);
        int index = indexOf(ldr, lrd[lrd.length - 1]);

        // 左子树的后序
        leftTreeLRD = new int[index];
        System.arraycopy(lrd, 0, leftTreeLRD, 0, index);
        // 左子树的中序
        leftTreeLDR = new int[index];
        System.arraycopy(ldr, 0, leftTreeLDR, 0, index);
        // 递归取得左子树节点
        left = getTreeByPostMid(leftTreeLRD, leftTreeLDR);

        // 右子树的后序
        rightTreeLRD = new int[ldr.length - index -1];
        System.arraycopy(lrd, index, rightTreeLRD, 0, ldr.length - index -1);
        // 右子树的中序
        rightTreeLDR = new int[ldr.length - index -1];
        System.arraycopy(ldr, index + 1, rightTreeLDR, 0, ldr.length - index -1);
        // 递归取得右子树节点
        right = getTreeByPostMid(rightTreeLRD, rightTreeLDR);

        // 生成树：根节点 + 左子树 + 右子树 ==> 树
        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {
        int[] dlr = {1, 2, 4, 8, 9, 5, 3, 6, 7, 10, 11};
        int[] ldr = {8, 4, 9, 2, 5, 1, 6, 3, 10, 7, 11};
        int[] lrd = {8, 9, 4, 5, 2, 6, 10, 11, 7, 3, 1};

        Node root1 = getTreeByPreMid(dlr, ldr);
        System.out.println(root1);
        Node root2 = getTreeByPostMid(lrd, ldr);
        System.out.println(root2);

        DemoBST.printBSTPreOrder(root1);
        DemoBST.printBSTMidOrder(root1);
        DemoBST.printBSTPostOrder(root1);

        DemoBST.printBSTPreOrder(root2);
        DemoBST.printBSTMidOrder(root2);
        DemoBST.printBSTPostOrder(root2);

        System.out.println("++++++++================++++++++");
        boolean bFound = dfsBST(root1, 4);
        System.out.println(bFound);
        bFound = dfsBST(root1, 16);
        System.out.println(bFound);
        bFound = bfsBST(root2, 4);
        System.out.println(bFound);
        bFound = bfsBST(root2, 16);
        System.out.println(bFound);
    }
}

class Node {
    int id;
    Node left;
    Node right;

    public Node(int id) {
        this.id = id;
    }
}
/*
树型如下：
8,9是4的子树
5,6是叶子节点
10,11是7的子树
				1
        2               3
    4		5		6		7
8       9               10      11
存储如下：
dlr = [1, 2, 4, 8, 9, 5, 3, 6, 7, 10, 11]
ldr = [8, 4, 9, 2, 5, 1, 6, 3, 10, 7, 11]
lrd = [8, 9, 4, 5, 2, 6, 10, 11, 7, 3, 1]
*/