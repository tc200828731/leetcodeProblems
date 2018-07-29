package leetcode;
/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.*/
public class ReverseNodesinkGroup {
    /* my solution 效率较低*/
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;
        ListNode res = head;
        ListNode tem = head;
        int sign = 0;
        ListNode before = null;
        while(tem!=null){
            int i = 0;
            ListNode fir = tem;
            while(i < k && tem!=null){
                i++;
                if(sign == 0 && i == k){
                    res = tem;
                }
                tem = tem.next;
            }
            if(i == k){
                ListNode nextNode = null;
                ListNode node = fir;
                for( int l = 0; l< k-1; l++){
                    ListNode tbefore = before;
                    for(int m = 0; m<k-l-1 ;m++){
                        ListNode beforeNode = node.next;
                        if(m==0){
                            nextNode = beforeNode;
                        }
                        twoChange(tbefore,node,node.next);
                        tbefore = beforeNode;
                    }
                    node = nextNode;//更新node 为该k个节点的第一节点
                }
                sign++;
                before = fir;
            }

        }
        return res;
    }

    private void twoChange(ListNode l1,ListNode l2,ListNode l3){
        if(l1!=null){
            l1.next = l3;
        }
        l2.next = l3.next;
        l3.next = l2;
    }
}
