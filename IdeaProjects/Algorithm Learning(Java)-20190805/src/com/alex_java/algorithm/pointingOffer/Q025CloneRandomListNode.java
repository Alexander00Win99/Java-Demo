package com.alex_java.algorithm.pointingOffer;

import java.util.ArrayList;

public class Q025CloneRandomListNode {
    public static RandomListNode clone(RandomListNode pHead) { // pHead作为原始链表头传入以后作为指针顺序后移指向下一节点
        if (pHead == null) return null;
        ArrayList<RandomListNode> originArr = new ArrayList<>(); // 原始数组，存放原始链表节点
        ArrayList<RandomListNode> targetArr = new ArrayList<>(); // 目标数组，存放原始链表节点

        RandomListNode targetHead = new RandomListNode(pHead.label); // 用于作为克隆链表头返回
        RandomListNode targetPointer = targetHead; // 用于作为克隆链表的当前指针和原始链表中的pHead指针保持同步

        originArr.add(pHead); // 原始数组，用于保存原始链表的各个节点(不重不漏，不多不少)
        targetArr.add(targetPointer); // 目标数组，用于保存目标链表的各个节点(参考原始数组元素一一映射)

        while (pHead.next != null) { // 只能使用原始链表的主链的next节点是否为null作为循环终止的控制条件，辅链random节点无法作为判断条件
            if (originArr.contains(pHead.next)) { // originArr数组包含原始链表的主链next节点：前面已经遍历过，当前直接跳过
                targetPointer.next = targetArr.get(originArr.indexOf(pHead.next));
            } else { // 目标链表参考原始链表当前节点直接新建节点并予添加
                targetPointer.next = new RandomListNode(pHead.next.label);
                originArr.add(pHead.next);
                targetArr.add(targetPointer.next);
            }
            if (pHead.random != null) { // 处理辅链
                if (originArr.contains(pHead.random)) { // originArr数组包含原始链表的辅链random节点：前面已经遍历过，当前直接跳过
                    targetPointer.random = targetArr.get(originArr.indexOf(pHead.random));
                } else { // 目标链表参考原始链表当前节点直接新建节点并予添加
                    targetPointer.random = new RandomListNode(pHead.random.label);
                    originArr.add(pHead.random);
                    targetArr.add(targetPointer.random);
                }
            }
            pHead = pHead.next; // pHead在主链上往后移动
            targetPointer = targetPointer.next; // targetPointer在主链上往后移动
        }
        return targetHead;
    }

    public static void main(String[] args) {
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        RandomListNode d = new RandomListNode(4);
        RandomListNode e = new RandomListNode(5);
        RandomListNode f = new RandomListNode(6);
        RandomListNode g = new RandomListNode(7);
        a.next =b; b.next = c; c.next = d; d.next = e; e.next = f; f.next = g;
        a.random = c; b.random = e; d.random = b; e.random = b; f.random = c; g.random = f;

        RandomListNode originList = a;
        RandomListNode clonedList = clone(originList);
    }
}

class RandomListNode {
    int label;
    RandomListNode next;
    RandomListNode random;

    RandomListNode(int label) {
        this.label = label;
    }
}
