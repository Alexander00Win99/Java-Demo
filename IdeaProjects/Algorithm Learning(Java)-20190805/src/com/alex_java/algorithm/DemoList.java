package com.alex_java.algorithm;

import com.sun.org.apache.xpath.internal.operations.String;

public class DemoList {
    public boolean findBiList(BiListNode node, int target) { // node节点任意，并非必须root根节点
        BiListNode p = node;
        BiListNode q = node;

        // C语言嵌套风格的代码
//        while (p != null) {
//            if (p.value == target) return true;
//            p = p.pre;
//        }
//        while (q != null) {
//            if (q.value == target) return true;
//            q = q.next;
//        }
//        return false;

        // 优雅的代码(1) 尽量减少嵌套；2) 尽量减少if...else...；3) 如果必须使用if语句尽量减少else语句；4) 尽量减少数字；)
        while (p != null && p.value != target) p = p.pre;
        while (q != null && q.value != target) q = q.next;
        return p != null || q != null; // p和q其中任意一个不是null，代表找到
    }

    public void insertBiList(BiListNode node, BiListNode nodeA, BiListNode nodeB) { // 假设nodeA和nodeB之间插入node
        node.pre = nodeA;
        nodeA.next = node;
        node.next = nodeB;
        nodeB.pre = node;
    }

    public void removeBiList(BiListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void main(String[] args) {

    }
}

class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
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