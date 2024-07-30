package BinarySearch;

public class Leetcode4MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Brute force approach: Use Separate array whose length will be length(nums1) + length(nums1)
        // Better approach: Two pointer: Use 1 pointer at nums1 and 2nd pointer at nums2. Iterate until half of the total size of array is traversed. Traverse in sprted manner for both of the pointers. If we are at total_array_length/2 -1 or total_array_length/2. Do consider those elements and take median out of these two if
        // total length is even, or else take total_array_length/2 if total_length is odd
        // TC: O(m+n), SC: O(1)


        // Optimal approach: Binary search (To-Do: Watch video solution of binary search on 01st of August)

        if(nums1.length == 0 && nums2.length == 0)
            return 0.0;

        if(nums1.length == 0)
            return nums2.length %2 == 0 ? ((nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0) : nums2[nums2.length/2];
        if(nums2.length == 0)
            return nums1.length %2 == 0 ? ((nums1[nums1.length/2]+nums1[nums1.length/2-1])/2.0) : nums1[nums1.length/2];



        int ptr1 = 0, ptr2 = 0;

        int total_length = nums1.length + nums2.length;

        int ctr=0;

        double candidates[] = new double[2];

        while(ctr<=(total_length/2)){
            int qualifiedNumber;
            if(ptr1<nums1.length && (ptr2 >= nums2.length || nums1[ptr1]<=nums2[ptr2])){
                qualifiedNumber = nums1[ptr1];
                ptr1++;
            } else{
                qualifiedNumber = nums2[ptr2];
                if(ptr2<nums2.length)
                    ptr2++;
            }

            if(ctr+1 == total_length/2){
                candidates[0] = qualifiedNumber;
            }
            else if(ctr == total_length/2){
                candidates[1] = qualifiedNumber;
            }
            ctr++;
        }
        if(total_length%2==0) return (candidates[0]+candidates[1])/2;
        else return candidates[1];

    }
}
