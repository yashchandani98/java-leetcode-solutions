package LinkedList;
import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
        this.random = null;
    }
}

/*
* Brute force Approach : (using HashMap) Here we will do Node to Node mapping, for every old node, we will create new node in 1st iteration
* Then in second iteration, we will update next and random pointers using that hashMap. and we will return hashMap.get(head)That's it
* TC: O(2n)(Twice iteration, first to create all the nodes, second to update next and random pointer using the same hashMap)
* SC: O(n): HashMap
*
* Optimal Approach  : (Without HashMap or adding a copied node in between two nodes) In the first iteration: Here we will create duplicate node of the current node and
* insert in between two nodes (Between current and next node)
* In the second iteration, we will traverse the linked list and will consider copied node as the next node of current node and will update it's
* random pointer and in the next iteration, we will close all the copied node in the new pointer
*
* TC: O(3n)(Thrice iteration: First to create copied node(With value and it's next pointer) and insert it into between two nodes. Second to
* update random pointer of the copied node to it's original node's random next(Since new node will be present in the next pointer). Third iteration to
* update next pointer of all the copied node and in the last return newHead.next)
* SC: O(1)
*
* */
public class Leetcode138CopyListWithRandomPointer {
    public Node copyRandomListHashMapApproach(Node head) {
        Node iter = head;
        Map<Node, Node> nodeToNodeMapping = new HashMap<>();
        while(iter!=null){
            nodeToNodeMapping.put(iter, new Node(iter.val));
            iter = iter.next;
        }
        iter = head;
        while(iter!=null){
            nodeToNodeMapping.get(iter).next =  nodeToNodeMapping.get(iter.next);
            nodeToNodeMapping.get(iter).random =  nodeToNodeMapping.get(iter.random);
            iter = iter.next;
        }

        return nodeToNodeMapping.get(head);
    }


    public Node copyRandomListOptimalApproach(Node head) {
        if(head==null)
            return head;
        Node iter = head;
        // Insert copied node in between all the nodes
        while(iter!=null){
            Node temp = new Node(iter.val, iter.next);
            // temp.next = iter.next;
            iter.next = temp;
            iter = temp.next;
        }
        Node newHead = new Node(0);

        iter = head;
        Node temp = newHead;

        while(iter!=null){
            if(iter.random!=null)
                iter.next.random=iter.random.next;
            iter = iter.next!=null?iter.next.next:null;
        }

        iter = head;
        temp.next = head.next;
        temp = temp.next;
        while(iter!=null){
            iter.next = iter.next.next;
            temp.next = temp.next!=null?temp.next.next:null;
            iter = iter.next;
            temp = temp.next;
        }
        return newHead.next;
    }
}
