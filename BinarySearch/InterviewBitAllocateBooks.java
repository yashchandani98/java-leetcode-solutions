package BinarySearch;

public class InterviewBitAllocateBooks {

    /*
    *
    * Problem: https://www.interviewbit.com/problems/allocate-books/
    *
    * Algorithm: Binary Search and Greedy
    * We have to find maximum number of pages allotted to a student which is minimum among all the students.

    We will use Binary Search in which our search space should be between low= min(Array of pages) and high = sum(Array of pages)

    we will use mid = (low+high)/2. At each step of binary search, we will check with the current Barrier(mid we will refer to current
     barrier which might be the number of pages allotted to all the students in some way).  if it is possible to allote mid pages to all the students. It might be our answer so we will update the result and look for the minimum number of max pages and shift high towards left (Eliminate right half). if allocation not possible, eliminate left space and shift low pointer by mid+1.

    Condition of allocationPossible: we will iterate over array of pages and will be greedy to allocate as many pages less than barrier to a single student.
     and if it has breached  barrier, we will allocate that page to another student and increment student counter. In the end we will check if we are able to allocate exact number of students. If yes, return true else return false.
    *
    * TC: O(N * log(sum(arr[])-min(arr[])+1))
    * SC: O(1)
    * */

    public static void main(String[] args){

        int[] pages = {12,34,67,90};
        System.out.println(books(pages, 2));
//        int[] pages = {31, 14, 19, 75};
//        System.out.println(books(pages, 12));
    }
    public static int books(int[] A, int B) {
        if(B>A.length) return -1;
        int low = Integer.MIN_VALUE;
        int high = 0;
        int result = Integer.MAX_VALUE;
        for(int page: A){
            low = Math.max(page, low);
            high+=page;
        }
        while(low<=high){
            int mid = (low+high)/2;
            if(isAllocationPossible(mid, A, B)){
                result = Math.min(mid, result);
                high = mid-1;
            } else{
                low = mid+1;
            }
        }
        return result;
    }

    private static boolean isAllocationPossible(int pagesMax, int[] A, int B){
        int pages = 0, studentsAllocated = 1;
        for(int page: A){
            if(page>pagesMax) return false;
            if(pages+page >pagesMax){
                studentsAllocated++;
                pages = page;
            } else {
                pages+=page;
            }
        }
//        if(studentsAllocated>B)
//            return false;
//        else return true;
        return studentsAllocated==B;
    }
}
