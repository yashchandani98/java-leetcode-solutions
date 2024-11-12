package LinkedList;

import java.util.*;

public class Leetocde725SplitLinkedListInParts {
    /**
     * Use Size of ListNode for every element equivalent to sizeOfLinkedList / k and
     * extra nodes for the initial few elements is equivalent to sizeOfLinkedList%k.
     * */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode iter = head;
        ListNode[] result = new ListNode[k];
        while(iter!=null){
            len++;
            iter=iter.next;
        }

        int nodeSizeSingle = len>k ? len / k : 1;
        int initialCountExtraNode = len / k!=0 ? len % k : 0;
        int[] nodeSize = new int[k];
        Arrays.fill(nodeSize, nodeSizeSingle);
        for(int i=0;i<initialCountExtraNode;i++){
            nodeSize[i]++;
        }
        iter = head;

        for(int i=0; i<k; i++){
            int temp = nodeSize[i];
            result[i] = iter;
            if(iter==null) continue;
            ListNode prev = null;
            while(temp>0 && iter!=null){
                temp--;
                prev = iter;
                iter=iter.next;
            }
            if(initialCountExtraNode>0){
                initialCountExtraNode--;
            }
            if(iter==null) continue;
            prev.next = null;
        }

        return result;

    }
}
