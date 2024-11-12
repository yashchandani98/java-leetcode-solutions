package Graph;


import java.util.*;

public class Leetcode133CloneGraph {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    class Solution {
        /**
         * Perform BFS, First we will create a graph adjacencyList. Then use the adjacencyList to fetch all the node's neighbours and use the hashMap valCSNode
         * to store node instance using node key. If encountered the same key in another node, use the older instance.
         * TC: O(V+E) (AdjacencyList) + O(V+E) (BFS)
         * SC: O(V+E) (AdjacencyList) + O(V) (BFS)
         * */
        public Node cloneGraph(Node startNode) {
            if(startNode == null) return null;
            if(startNode.neighbors.size() == 0) return new Node(1);
            // BFS
            // Graph adjacencyList
            Map<Integer, List<Integer>> nodeVSNeighbours = new HashMap<>();

            // graph key vs Node instance to reuse the node instance using key if it's neighbour of multiple nodes
            Map<Integer, Node> valVSNode = new HashMap<>();

            // Traversal in BFS fashion
            Queue<Node> que = new LinkedList<>();

            // Avoid visiting the same node twice
            Set<Integer> visited = new HashSet<>();
            que.offer(startNode);

            while(!que.isEmpty()) {
                int n = que.size();
                for(int i=0; i<n;i++) {
                    Node node = que.poll();
                    int key = node.val;
                    if(visited.contains(key)) continue;
                    visited.add(key);
                    List<Node> neighbours = node.neighbors;
                    for(Node neighbour: neighbours) {
                        nodeVSNeighbours.computeIfAbsent(key, k -> new ArrayList<>()).add(neighbour.val);
                        que.offer(neighbour);
                    }
                }
            }

            Node res = null;


            for(Map.Entry<Integer, List<Integer>> entry: nodeVSNeighbours.entrySet()) {
                int nodeVal = entry.getKey();
                List<Integer> nodeNeighboursVal = entry.getValue();

                Node node;
                if(valVSNode.containsKey(nodeVal)) {
                    node = valVSNode.get(nodeVal);
                } else {
                    node = new Node(nodeVal);
                    valVSNode.put(nodeVal, node);
                }

                List<Node> neighbours = new ArrayList<>();

                for(Integer neighbourVal: nodeNeighboursVal) {
                    Node neighbour = null;
                    if(valVSNode.containsKey(neighbourVal)) {
                        neighbour = valVSNode.get(neighbourVal);
                    } else {
                        neighbour = new Node(neighbourVal);
                        valVSNode.put(neighbourVal, neighbour);
                    }
                    neighbours.add(neighbour);
                }

                node.neighbors = neighbours;
                if(res == null) res = node;
            }
            return res;
        }
    }
    }
