package com.alex_java.algorithm.pointingOffer;

import java.util.ArrayList;

public class Q003ReverseList {
    // 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
    public static ArrayList<Integer> reverseList2Array(ListNode head) {
        ArrayList<Integer> res = new ArrayList<>();
        if (head == null) return res;
        if (head.next == null) {
            res.add(head.val);
        } else {
            res = reverseList2Array(head.next);
            res.add(head.val);
        }
        return res;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) return null; // 严谨性判断
        if (head.next == null) return head; // 严谨性判断
        if (head.next.next == null) { // 找到倒数第二节点，根据它找到它的下一节点也即倒数第一节点
            head.next.next = head; // 使得下一节点也即倒数第一节点指向自己(倒数第二节点)
            System.out.println(head.next.val);
            System.out.println(head.val);
            return head.next; // 递归出口(不再递归调用下一节点)，返回倒数第一节点
        } else {
            ListNode res = reverseList(head.next);
            System.out.println(head.val);
            head.next.next = head; // 使得下一节点也即next属性所指节点反向指向当前节点
            head.next = null; // 使得当前节点next指向为空
            return res;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(0);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(4);
        ListNode f = new ListNode(5);
        ListNode g = new ListNode(6);
        ListNode h = new ListNode(7);
        ListNode i = new ListNode(8);
        ListNode j = new ListNode(9);
        a.next = b; b.next = c; c.next = d; d.next = e; e.next = f; f.next = g; g.next = h; h.next = i; i.next = j;
        ListNode list = a;
        ListNode reversedList = reverseList(list);
        ArrayList<Integer> arr = reverseList2Array(reversedList);
        for (int nodeVal : arr) System.out.println(nodeVal);
//        for (ListNode node = reversedList; node.next != null; node = node.next) System.out.println(node.val);
//        ListNode node = reversedList;
//        do {
//            System.out.println(node.val);
//            node = node.next;
//        } while (node.next != null);

        BiListNode node1 = new BiListNode(1);
    }
}

class BiListNode {
    int value;
    BiListNode pre;
    BiListNode next;

    public BiListNode(int value) {
        this.value = value;
    }
}