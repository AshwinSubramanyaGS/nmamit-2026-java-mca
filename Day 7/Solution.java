//LeetCode 160. Intersection of Two Linked Lists
import java.util.HashSet;



public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> seen = new HashSet<>();

        while (headA != null) {
            seen.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (seen.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
        
    } 
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
      }
 }