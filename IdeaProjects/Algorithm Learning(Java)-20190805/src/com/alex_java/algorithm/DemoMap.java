package com.alex_java.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DemoMap {//图的最小生成树Mininum Spanning Tree算法(Kruskal + Prim) + 图的搜索(广度优先搜索BFS + 深度优先搜索DFS)
    // 标记当前步骤
    private static int countPrim = 0;
    private static int countKruskal = 0;

    // 通过index从给定点集vertexs中获取MapNode节点
    public static MapNode getNodeByIndex(Set<MapNode> vertexs, int index) {
        for (MapNode temp : vertexs) {
            if (temp.index == index) {
                return temp;
            }
        }
        return null;
    }

    // 加点法(Prim)：
    // 随机选取一个初始点，依次加入最小代价点同时避免成环(已选点不能再次被选加入)，直至所有点被选加入。
    public static void primMST(Set<MapNode> vertexs, int[][] edges) {
        // choose an original node randomly
        int rand = (int) Math.floor(Math.random() * vertexs.size());
        MapNode originNode = getNodeByIndex(vertexs, rand);
        Set<MapNode> nodeSetMST = new HashSet<>();
        nodeSetMST.add(originNode);
        System.out.println(String.format("Node %d has been choosen to be the original node!", rand));

        // add the lowest cost node to the group step by step until all nodes are fully added
        MapNode minCostNode = null;
        while (true) {
            minCostNode = getMinCostNode(vertexs, edges, nodeSetMST);
            nodeSetMST.add(minCostNode);
            if (nodeSetMST.size() == vertexs.size()) {
                break;
            }
        }
    }

    // 获取最小代价点
    public static MapNode getMinCostNode(Set<MapNode> vertexs, int[][] edges, Set<MapNode> nodeSetMST) {
        int minCost = Integer.MAX_VALUE;
        MapNode fromNode = null;
        MapNode minCostNode = null;
        // 获取最小代价点(不能属于原来点集)
        for (MapNode temp : nodeSetMST) {
            for (int i = 0; i < vertexs.size(); i ++) {
                MapNode toNode = getNodeByIndex(vertexs, i);
                if (edges[temp.index][i] < minCost && !nodeSetMST.contains(toNode)) {
                    fromNode = temp;
                    minCost = edges[temp.index][i];
                    minCostNode = toNode;
                }
            }
        }
        if (minCostNode != null) {
            fromNode.neighbour.add(minCostNode);
            minCostNode.neighbour.add(fromNode);
            nodeSetMST.add(minCostNode);
            System.out.println(String.format("Prim step %d: node %d added, corresponding edge with cost %d added!", ++countPrim, minCostNode.index, minCost));
        }
        return minCostNode;
    }

    // 加边法(Kruskal)：
    // 每次加入当前最小代价边，同时保证不能成环(避免所选边两端节点是同一点集内部两点即可)；
    // 不断迭代，直至原图点集中所有点都被加入最小生成树点集之中(同时，迭代过程中产生的最小生成树点集合并归一)；
    public static void kruskalMST(Set<MapNode> vertexs, int[][] edges) {
        List<Set<MapNode>> groups = new LinkedList<>();
        // 获取最小边
        while (true) {
            int minCost = Integer.MAX_VALUE;
            MapNode from = null;
            MapNode to = null;
            MapNode tmpFrom = null;
            MapNode tmpTo = null;
            for (int i = 0; i < vertexs.size(); i++) {
                for (int j = i; j < vertexs.size(); j++) {
                    if (edges[i][j] < minCost) {
                        tmpFrom = getNodeByIndex(vertexs, i);
                        tmpTo = getNodeByIndex(vertexs, j);
                        if (checkLinkable(groups, tmpFrom, tmpTo)) {
                            minCost = edges[i][j];
                            from = tmpFrom;
                            to = tmpTo;
                        }
                    }
                }
            }
            link(groups, from, to);
            from.neighbour.add(to);
            to.neighbour.add(from);
            System.out.println(String.format("Kruskal step %d: link node %d and node %d, edge cost between these two points is %d!", ++countKruskal, from.index, to.index, minCost));
            if (groups.size() == 1 && groups.get(0).size() == vertexs.size()) {
                break;
            }
        }
    }

    // 在Kruskal算法加边迭代过程中，形成多个点集(最终归一)，检查图中给定两点能否连接(同一非空点集内部两点不能相连，避免成环)
    public static boolean checkLinkable(List<Set<MapNode>> groupList, MapNode nodeFrom, MapNode nodeTo) {
        // 初始化给定两点所属点集为空
        Set<MapNode> nodeFromGroup = null;
        Set<MapNode> nodeToGroup = null;
        // 遍历当前所有点集，更新两点所属点集(如果某个点集包含该点，该点即属这个点集)
        for (Set<MapNode> group : groupList) {
            if (group.contains(nodeFrom)) {
                nodeFromGroup = group;
            }
            if (group.contains(nodeTo)) {
                nodeToGroup = group;
            }
        }
        if (nodeFromGroup != null && nodeToGroup != null && nodeFromGroup == nodeToGroup) {
            return false;
        }
        return true;
    }

    // 连接两点
    public static void link(List<Set<MapNode>> groupList, MapNode nodeFrom, MapNode nodeTo) {
        // 初始化给定两点所属点集为空
        Set<MapNode> nodeFromGroup = null;
        Set<MapNode> nodeToGroup = null;
        // 遍历当前所有点集，更新两点所属点集(如果某个点集包含该点，该点即属这个点集)
        for (Set<MapNode> group : groupList) {
            if (group.contains(nodeFrom)) {
                nodeFromGroup = group;
            }
            if (group.contains(nodeTo)) {
                nodeToGroup = group;
            }
        }
        if (nodeFromGroup != null && nodeToGroup != null && nodeFromGroup == nodeToGroup) {
            return;
        } else if (nodeFromGroup != null && nodeToGroup != null && nodeFromGroup != nodeToGroup) {
            nodeFromGroup.addAll(nodeToGroup);
            groupList.remove(nodeToGroup);
            // nodeToGroup.addAll(nodeFromGroup);
            // groupList.remove(nodeFromGroup);
        } else if (nodeFromGroup != null && nodeToGroup == null) {
            nodeFromGroup.add(nodeTo);
        } else if (nodeFromGroup == null && nodeToGroup != null) {
            nodeToGroup.add(nodeFrom);;
        } else if (nodeFromGroup == null && nodeToGroup == null) {
            Set<MapNode> newGroup = new HashSet<>();
            newGroup.add(nodeFrom);
            newGroup.add(nodeTo);
            groupList.add(newGroup);
        }
    }

    public static void main(String[] args) {
        // G = (V. E)，初始化图
        MapNode a = new MapNode(0, "A");
        MapNode b = new MapNode(1, "B");
        MapNode c = new MapNode(2, "C");
        MapNode d = new MapNode(3, "D");
        MapNode e = new MapNode(4, "E");
        MapNode f = new MapNode(5, "F");
        MapNode g = new MapNode(6, "G");

        Set<MapNode> vertexs = new HashSet<>();
        vertexs.add(a); vertexs.add(b); vertexs.add(c); vertexs.add(d); vertexs.add(e); vertexs.add(f); vertexs.add(g);

        int[][] edges = {
                {Integer.MAX_VALUE, 2, 3, 4, 9, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {2, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, 16},
                {3, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 25, Integer.MAX_VALUE},
                {4, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 6, Integer.MAX_VALUE},
                {9, Integer.MAX_VALUE, 5, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 7},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 25, 6, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 16, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, Integer.MAX_VALUE, Integer.MAX_VALUE},
        };

// C语言如下声明二维数组，JAVA如下则会报错“edges未初始化”
//        int[][] edges;
//        for (int i = 0; i < vertexs.size(); i ++) {
//            for (int j = 0; j < vertexs.size(); j ++) {
//                edges[i][j] = Integer.MAX_VALUE;
//            }
//        }
//        edges[0][1] = 2; edges[0][2] = 3; edges[0][3] = 4; edges[0][4] = 9;
//        edges[1][0] = 2; edges[1][3] = 4; edges[1][6] = 16;
//        edges[2][0] = 3; edges[2][4] = 5; edges[2][5] = 25;
//        edges[3][0] = 4; edges[3][1] = 4; edges[3][4] = 1; edges[3][5] = 6;
//        edges[4][0] = 9; edges[4][2] = 5; edges[4][3] = 1; edges[4][6] = 7;
//        edges[5][2] = 25; edges[5][3] = 6;
//        edges[6][1] = 16; edges[6][4] = 7;

        primMST(vertexs, edges);
        System.out.println(a);
//        kruskalMST(vertexs, edges);
//        bfsMap();
//        dfsMap();
    }
}

class MapNode {
    int index;
    String name;
    List<MapNode> neighbour;

    public MapNode(int index, String name) {
        this.index = index;
        this.name = name;
        this.neighbour = new LinkedList<>();
    }
}
