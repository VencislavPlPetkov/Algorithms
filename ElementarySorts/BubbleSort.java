package elementarySorts;

/**
 * 
 * Bubble sort, also referred to as sinking sort, is a simple sorting algorithm
 * that works by repeatedly stepping through the list to be sorted, comparing
 * each pair of adjacent items and swapping them if they are in the wrong order.
 * The pass through the list is repeated until no swaps are needed, which
 * indicates that the list is sorted. The algorithm gets its name from the way
 * smaller elements "bubble" to the top of the list.
 * 
 * Because it only uses
 * comparisons to operate on elements, it is a comparison sort. Bubble sort has
 * worst-case and average complexity both Î(n2), where n is the number of items
 * being sorted. There exist many sorting algorithms with substantially better
 * worst-case or average complexity of O(n log n). Even other Î(n2) sorting
 * algorithms, such as insertion sort, tend to have better performance than
 * bubble sort. Therefore, bubble sort is not a practical sorting algorithm when
 * n is large.Performance of bubble sort over an already-sorted list (best-case)
 * is O(n). 
 * 
 */

public class BubbleSort {

	public static void main(String[] args) {

		int[] nums = { 11, 2, 9, 8, 3, 1 };

		System.out.print("Initial: ");
		printArray(nums);

		for (int i = 0; i < nums.length - 1; i++) {
			// (nums.length - i - 1) is for ignoring comparisons of elements which have already been compared in earlier iterations
			
			for (int j = 0; j < nums.length - i - 1; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
		}

		
		System.out.print("Sorted: ");
		printArray(nums);

	}// END Main

	private static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}
}// END Class
