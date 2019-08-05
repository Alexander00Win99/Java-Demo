package com.alex_java.algorithm.pointingOffer;

import java.util.ArrayList;

/**
 * reconstuctTreeByPreIn|reconstuctTreeByInPost：根据[pre + in | in + post]重建二叉树
 * DLR|LDR|LRD：返回ArrayList<Integer>
 * preOrder|inOrder|postOrder：打印输出
 */

public class Q004ReconstructTree {
    // 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    public static TreeNode reconstuctTreeByPreIn(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0 || pre.length != in.length) return null;
        TreeNode root = null;
        TreeNode left = null;
        TreeNode right = null;

        root = new TreeNode(pre[0]);
        int index = indexOf(in, pre[0]);

        // 根据现有遍历截取左右子树的前序遍历和中序遍历
        int[] leftPre = new int[index];
        int[] leftIn = new int[index];
        System.arraycopy(pre, 1, leftPre, 0, index);
        System.arraycopy(in, 0, leftIn, 0, index);
        int[] rightPre = new int[pre.length - index - 1];
        int[] rightIn = new int[in.length - index - 1];
        System.arraycopy(pre, index + 1, rightPre, 0, pre.length - index - 1);
        System.arraycopy(in, index + 1, rightIn, 0, in.length - index - 1);

        // 递归求取左右子树
        left = reconstuctTreeByPreIn(leftPre, leftIn);
        right = reconstuctTreeByPreIn(rightPre, rightIn);
        root.left = left;
        root.right = right;

        return root;
    }

    public static TreeNode reconstuctTreeByInPost(int[] in, int[] post) {
        if (in.length == 0 || post.length == 0 || in.length != post.length) return null;
        TreeNode root = new TreeNode(post[post.length - 1]);

        for (int i = 0; i < in.length; i ++) {
            if (in[i] == post[post.length - 1]) {
                int index = i;
                int[] leftIn = new int[index];
                int[] leftPost = new int[index];
                System.arraycopy(in, 0, leftIn, 0, index);
                System.arraycopy(post, 0, leftPost, 0, index);
                int[] rightIn = new int[in.length - index - 1];
                int[] rightPost = new int[post.length - index - 1];
                System.arraycopy(in, index + 1, rightIn, 0, in.length - index - 1);
                System.arraycopy(post, index, rightPost, 0, post.length - index - 1);

                root.left = reconstuctTreeByInPost(leftIn, leftPost);
                root.right = reconstuctTreeByInPost(rightIn, rightPost);
                return root;
            }
        }
        return root;
    }

    private static int indexOf(int[] array, int key) {
        if (array == null || array.length == 0) return -1;
        for (int i = 0; i < array.length; i ++) if (array[i] == key) return i;
        return -1;
    }

    public static ArrayList<Integer> DLR(TreeNode root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        arr.add(root.val);
        arr.addAll(DLR(root.left));
        arr.addAll(DLR(root.right));
        return arr;
    }

    public static ArrayList<Integer> LDR(TreeNode root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        arr.addAll(LDR(root.left));
        arr.add(root.val);
        arr.addAll(LDR(root.right));
        return arr;
    }

    public static ArrayList<Integer> LRD(TreeNode root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        arr.addAll(LRD(root.left));
        arr.addAll(LRD(root.right));
        arr.add(root.val);
        return arr;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode tree = reconstuctTreeByPreIn(pre, in);
        postOrder(tree);

        ArrayList<Integer> postArrList = LRD(tree);
        for (int i : postArrList) System.out.println(i);

        int[] postArrInt = new int[postArrList.size()];
        for (int i = 0; i < postArrList.size(); i ++) postArrInt[i] = postArrList.get(i);
        TreeNode treeVerified = reconstuctTreeByInPost(in, postArrInt);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
